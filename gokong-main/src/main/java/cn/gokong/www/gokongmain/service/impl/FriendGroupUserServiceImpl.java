package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.easemob.dao.IMChatGroup;
import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.jedis.KeysUtil;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.FriendGroupUserMapper;
import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.domain.FriendGroup;
import cn.gokong.www.gokongmain.domain.FriendGroupUser;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.MsgTitleEnum;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.group.PageQueryInviteGroupUserOutput;
import cn.gokong.www.gokongmain.vo.group.PageQueryUserGroupOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupUserOutput;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/**
 * <p>
 * 好友群用户信息 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@Service
public class FriendGroupUserServiceImpl extends ServiceImpl<FriendGroupUserMapper, FriendGroupUser> implements FriendGroupUserService {

    @Autowired
    FriendGroupService friendGroupService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    @Autowired
    FriendGroupUserService friendGroupUserService;

    @Autowired
    FriendInviteService friendInviteService;

    @Override
    public boolean save(FriendGroupUser entity) {
        entity.setId(DataBaseTool.createId());
        if (StrUtil.isEmpty(entity.getInviteNo())){
            entity.setInviteNo(DataBaseTool.createNo("invite_no"));
        }
        entity.setJoinDate(DataBaseTool.createDate());
        return super.save(entity);
    }

    /**
     * 退出群聊
     *
     * @param userNo  用户编码
     * @param groupNo 群聊编码
     * @return
     */
    @Override
    @Transactional
    public boolean exitGroup(String userNo, String groupNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        wrapper.eq("group_no", groupNo);
        List<FriendGroupUser> list = list(wrapper);
        list.forEach(friendGroupUser -> {
            //删除本地群成员记录
            boolean removeById = removeById(friendGroupUser.getId());
            if (!removeById) {
                throw new GrilException("删除本地群成员记录失败");
            }
        });

        //删除环信成员记录
        boolean removeUsers = IMChatGroup.removeUsers(groupNo, userNo);

        if (!removeUsers) {
            throw new GrilException("删除环信成员记录失败");
        }

        return true;
    }

    /**
     * 批量删除群成员
     *
     * @param userNo  群主编码
     * @param groupNo 群编码
     * @param userNos 待删除的用户编码
     * @return
     */
    @Override
    @Transactional
    public boolean batchDelGroupUser(String userNo, String groupNo, List<Map<String, String>> userNos) {
        List<String> list = new ArrayList<>();
        userNos.forEach(map -> {
            list.add(map.get("userNo"));
        });
        //查询群聊信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("group_no",groupNo);
        FriendGroup friendGroup = friendGroupService.getOne(wrapper);

        if (!friendGroup.getUserNo().equals(userNo)){
            throw new GrilException("当前用户不是群主 无法操作删除群成员");
        }



        //查询群主信息
        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        //批量删除群成员
        baseMapper.deleteBatchGroupUser(groupNo, list);

        list.forEach(s -> {
            boolean removeUsers = IMChatGroup.removeUsers(groupNo, s);
            if (!removeUsers){
                throw new GrilException("删除环信失败");
            }

            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setId(DataBaseTool.createId());
            chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_msg"));
            chatServiceMessage.setSendText("你已被 " + userInfo.getNickName() + " 从 " + friendGroup.getGroupName() + " 中移除");
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessage.setUserNo(s);
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
            chatServiceMessageService.save(chatServiceMessage);

            if (friendGroup.getGroupType().equals("2")){
                FriendGroupUser byGroupNoAndUserNo = friendGroupUserService.findByGroupNoAndUserNo(groupNo, s);
                if (byGroupNoAndUserNo != null) {
                    removeById(byGroupNoAndUserNo);
                }
            }
        });
        return true;
    }

    /**
     * 邀请用户加入群
     *
     * @param userNo  邀请方的用户编码
     * @param groupNo 群聊编码
     * @param userNos 用户编码集合
     * @return
     */
    @Override
    @Transactional
    public boolean inviteJoin(String userNo, String groupNo, List<Map<String, String>> userNos) {
        for (int i = 0; i < userNos.size(); i++) {
            //判断是否已发送申请
            FriendGroupUser byGroupNoAndUserNo = baseMapper.findByGroupNoAndUserNo(groupNo, userNos.get(i).get("userNo"));
            if (ObjectUtil.isNotNull(byGroupNoAndUserNo)){
                continue;
            }
            stayJoinGroup(groupNo,userNos.get(i).get("userNo"),userNo,SysCodeEnum.CODE_10170003.getCode(),"");
        }
        return true;
    }

    /**
     * 显示当前用户已经加入的群
     *
     * @param requestData
     * @return
     */
    public ResponseData findJoinGroup(RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        int pageno = (Integer.valueOf(requestData.getData().get("pageNo")) - 1) * 10;
        String cityno = requestData.getData().get("cityNo");
        String userno = requestData.getData().get("userNo");
        List<Map<String, String>> usergroup = this.baseMapper.findUserGroup(pageno, cityno, userno);
        responseData.setData(usergroup);
        responseData.setCode(200);
        responseData.setMessage("加载成功!");
        return responseData;
    }

    /**
     * 用户待加入群聊
     * @param groupNo           群编码
     * @param userNo            用户编码
     * @param inviteUserNo      邀请者编码
     * @param groupUserState    群用户状态
     * @param verifyInfo        验证信息
     * @return
     */
    @Override
    public boolean stayJoinGroup(String groupNo, String userNo, String inviteUserNo, String groupUserState,String verifyInfo) {

        //判断是否是群成员
        boolean groupUser = isGroupUser(groupNo, userNo);
        if (groupUser){
            throw new GrilException("你已是群成员 无需再申请");
        }

        //获取群信息
        FriendGroup friendGroup = friendGroupService.findByGroupNo(groupNo);

        if (friendGroup.getGroupType().equals("2")){
            throw new GrilException("活动群不能邀请好友！");
        }

        //如果用户群状态为申请状态并且群为自动加入（就直接成为群用户）
        if (groupUserState.equals(SysCodeEnum.CODE_10170005.getCode())&&!friendGroup.isJoinMark()){
            return becomeGroupUser(userNo,friendGroup,null);
        }

        FriendGroupUser friendGroupUser = new FriendGroupUser();
        friendGroupUser.setId(DataBaseTool.createId());
        friendGroupUser.setUserNo(userNo);
        friendGroupUser.setFriendUserNo(inviteUserNo);
        friendGroupUser.setGroupNo(groupNo);
        friendGroupUser.setInviteNo(DataBaseTool.createNo("group_user"));
        friendGroupUser.setGroupUserState(groupUserState);
        friendGroupUser.setJoinDate(DataBaseTool.createDate());
        friendGroupUser.setVerifyInfo(verifyInfo);

        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();

        //判断是邀请还是申请
        if (groupUserState.equals(SysCodeEnum.CODE_10170003.getCode())){
            // TODO:邀请成功发送推送消息给被邀请者
            //获取邀请者的信息
            UserInfo inviteUserInfo = userInfoService.findByUserNo(inviteUserNo);
            //获取消息接受者信息
            chatServiceMessage.setUserNo(userNo);
            chatServiceMessage.setSendHead(inviteUserInfo.getUserHead());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10008.getMsg());
            chatServiceMessage.setSendText(inviteUserInfo.getNickName() + " 邀请您加入" + friendGroup.getGroupName());
            //设置服务通知类型
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390003.getCode());
            chatServiceMessage.setServiceNo(friendGroupUser.getInviteNo());

        } else if (groupUserState.equals(SysCodeEnum.CODE_10170005.getCode())) {
            //获取申请者的信息
            UserInfo userInfo = userInfoService.findByUserNo(userNo);
            //TODO:申请成功发送推送消息给群主

            //设置消息接收者信息
            chatServiceMessage.setUserNo(friendGroup.getUserNo());
            chatServiceMessage.setSendNo(userInfo.getUserNo());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10007.getMsg());
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setSendText(userInfo.getNickName() + " 申请加入 " + friendGroup.getGroupName());
            //设置服务通知类型
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390004.getCode());
            chatServiceMessage.setServiceNo(friendGroupUser.getInviteNo());

        } else if (groupUserState.equals(SysCodeEnum.CODE_10170001.getCode())) {
            //TODO:群主自己创建群时自动添加群记录
            //设置消息接收者信息
            chatServiceMessage.setUserNo(friendGroup.getUserNo());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
            chatServiceMessage.setSendText("你已成功创建 " + friendGroup.getGroupName());
            //设置服务通知类型
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
        } else {
            throw new GrilException("请输入正确的在群状态!");
        }

        boolean save = save(friendGroupUser);

        if (!save){
            throw new GrilException("添加好友群记录失败");
        }

        boolean save1 = chatServiceMessageService.save(chatServiceMessage);

        if (!save1){
            throw new GrilException("发送加入群通知失败");
        }
        KeysUtil.set(friendGroupUser.getInviteNo(), friendGroupUser.getInviteNo(), 60 * 5);
        return true;
    }

    /**
     * 判断是否是群成员
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    @Override
    public boolean isGroupUser(String groupNo, String userNo) {
        FriendGroupUser byGroupNoAndUserNoAndGroupUserState = baseMapper.findByGroupNoAndUserNoAndGroupUserState(groupNo, userNo, SysCodeEnum.CODE_10170001.getCode());
        if (ObjectUtil.isNotNull(byGroupNoAndUserNoAndGroupUserState)){
            return true;
        }
        return false;
    }

    /**
     * 申请加入群聊
     *
     * @param userNo        用户编码
     * @param groupNo       群聊编码
     * @param verifyInfo    验证消息
     * @return
     */
    @Override
    @Transactional
    public boolean applyJoin(String userNo, String groupNo, String verifyInfo) {
        //判断是否已发送申请
        FriendGroupUser byGroupNoAndUserNo = baseMapper.findByGroupNoAndUserNo(groupNo, userNo);
        if (ObjectUtil.isNotNull(byGroupNoAndUserNo)){
            //判断存储的申请编码是否存在
            if (KeysUtil.exists(byGroupNoAndUserNo.getInviteNo())) {
                throw new GrilException("发送申请过于频繁");
            }
            KeysUtil.set(byGroupNoAndUserNo.getInviteNo(), byGroupNoAndUserNo.getInviteNo(), 60 * 5);

            ChatServiceMessage chatServiceMessage = chatServiceMessageService.findByServiceNo(byGroupNoAndUserNo.getInviteNo());
            if (ObjectUtil.isNull(chatServiceMessage)){
                throw new GrilException("申请添加好友 信息丢失");
            }
            chatServiceMessage.setSendDate(DataBaseTool.createDate());
            return chatServiceMessageService.saveOrUpdate(chatServiceMessage);
        }else {
            return stayJoinGroup(groupNo,userNo,null,SysCodeEnum.CODE_10170005.getCode(),verifyInfo);
        }
    }

    /**
     * 判断当前用户是否加入该群
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    @Override
    public boolean isJoin(String groupNo, String userNo) {
        FriendGroupUser byGroupNoAndUserNoAndGroupUserState = baseMapper.findByGroupNoAndUserNoAndGroupUserState(groupNo, userNo, SysCodeEnum.CODE_10170001.getCode());
        if (ObjectUtil.isNotNull(byGroupNoAndUserNoAndGroupUserState)){
            return true;
        }
        return false;
    }

    /**
     * 查询群用户信息
     *
     * @param groupNo 群编码
     * @param keyword 关键字
     * @param start   开始位置
     * @param end     检索长度
     * @return
     */
    @Override
    public List<QueryGroupUserOutput> pageQueryGroupUsers(String groupNo, String keyword, int start, int end) {
        //查询群成员信息
        List<QueryGroupUserOutput> queryGroupUserOutputs = baseMapper.pageQueryGroupUsers(groupNo, keyword, start, end);

        //查询群信息
        FriendGroup byGroupNo = friendGroupService.findByGroupNo(groupNo);

        //判读用户是否是群主
        queryGroupUserOutputs.forEach(queryGroupUserOutput -> {
            if (queryGroupUserOutput.getUserNo().equals(byGroupNo.getUserNo())){
                queryGroupUserOutput.setIsokOwner(true);
            }
        });
        return queryGroupUserOutputs;
    }

    /**
     * 查询群成员列表
     *
     * @param groupNo  用户编码
     * @param content  检索内容
     * @param pageNo   开始位置
     * @param pageSize 页面长度
     * @return
     */
    @Override
    public List<QueryGroupUserOutput> pageQueryGroupUser(String groupNo, String content, Integer pageNo, Integer pageSize) {
        return pageQueryGroupUsers(groupNo,content,pageNo,pageSize);
    }

    /**
     * 查询群成员编码
     * @param groupNo   群编码
     * @return
     */
    @Override
    public List<String> selectOneUserNoResult(String groupNo) {
        return baseMapper.findByGroupNoAndGroupUserState(groupNo,SysCodeEnum.CODE_10170001.getCode());
    }

    /**
     * 审核（申请/邀请）
     *
     * @param inviteNo   申请编码
     * @param auditState 审核状态
     * @return
     */
    @Override
    @Transactional
    public boolean auditInvite(String inviteNo, boolean auditState) {
        //获取用户群关系信息
        FriendGroupUser friendGroupUser = baseMapper.findByInviteNo(inviteNo);

        if (ObjectUtil.isNull(friendGroupUser)){
            throw new GrilException("用户群关系信息不存在");
        }
        //如果消息状态不等于邀请中或申请中 说明已处理
        if (!friendGroupUser.getGroupUserState().equals(SysCodeEnum.CODE_10170003.getCode())&&!friendGroupUser.getGroupUserState().equals(SysCodeEnum.CODE_10170005.getCode())&&!friendGroupUser.getGroupUserState().equals(SysCodeEnum.CODE_10170006.getCode())){
            throw new GrilException("已处理");
        }
        //获取群信息
        FriendGroup friendGroup = friendGroupService.findByGroupNo(friendGroupUser.getGroupNo());

        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();

        //判断是否通过
        if (auditState){
            //判断是邀请还是申请
            if (friendGroupUser.getGroupUserState().equals(SysCodeEnum.CODE_10170003.getCode())){
                //判断邀请者是否是群主
                if (friendGroupUser.getFriendUserNo().equals(friendGroup.getUserNo())){
                    //如果状态为邀请并且邀请者还是群主且自己还同意了邀请,那么状态就为正常
                    friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
                    //添加环信群成员
                    boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
                    if (!addUsers){
                        throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
                    }
                    // TODO:发送成功消息给群主
                    //查询被邀请者的信息
                    UserInfo byUserNo = userInfoService.findByUserNo(friendGroupUser.getUserNo());
                    chatServiceMessage.setUserNo(friendGroup.getUserNo());
                    chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
                    chatServiceMessage.setSendText(byUserNo.getNickName()+" 已成功加入 "+friendGroup.getGroupName());
                    chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
                    chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
                }else {
                    //修改消息状态
                    ChatServiceMessage byServiceNo = chatServiceMessageService.findByServiceNo(inviteNo);
                    byServiceNo.setServiceNo(DataBaseTool.createNo("service_no"));
                    byServiceNo.setMsgState(SysCodeEnum.CODE_10200002.getCode());
                    chatServiceMessageService.saveOrUpdate(byServiceNo);


                    //如果状态为邀请并且邀请者不是群主且自己还同意了邀请,那么状态为群主待审核
                    friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170006.getCode());
                    //获取被邀请者的用户信息
                    UserInfo byUserNo = userInfoService.findByUserNo(friendGroupUser.getUserNo());
                    //发送申请通知给群主
                    chatServiceMessage.setUserNo(friendGroup.getUserNo());
                    chatServiceMessage.setSendNo(friendGroupUser.getUserNo());
                    chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10007.getMsg());
                    chatServiceMessage.setSendText(byUserNo.getUserNo()+" 申请加入 "+friendGroup.getGroupName());
                    chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390004.getCode());
                    chatServiceMessage.setServiceNo(friendGroupUser.getInviteNo());
                }
                // TODO:审核成功发送推送消息给群主
                StaticLog.info("审核成功发送推送消息给群主");
                //修改好友群关系信息
                boolean saveOrUpdate = saveOrUpdate(friendGroupUser);
                if (!saveOrUpdate){
                    throw new GrilException("修改好友群关系信息失败");
                }

                boolean save = chatServiceMessageService.save(chatServiceMessage);
                if (!save){
                    throw new GrilException("插入消息失败");
                }
                return true;
            }else {
                //如果审核通过 并且状态为申请中 那么状态就更新为正常
                friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
                //添加环信群成员
                boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
                if (!addUsers){
                    throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
                }
                // TODO:审核成功发送推送消息给申请者
                StaticLog.info("审核成功发送推送消息给申请者");

                // TODO:发送成功消息给被邀请者
                //查询群主的信息
                //UserInfo byUserNo = userInfoService.findByUserNo(friendGroup.getUserNo());
                chatServiceMessage.setUserNo(friendGroupUser.getUserNo());
                chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
                chatServiceMessage.setSendText("您已成功加入"+friendGroup.getGroupName());
                chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
                chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
            }
        }else {
            //判断是邀请还是申请
            if (friendGroupUser.getGroupUserState().equals(SysCodeEnum.CODE_10170003.getCode())){
                //查询被邀请者的信息
                UserInfo byUserNo = userInfoService.findByUserNo(friendGroupUser.getUserNo());
                // TODO:发送拒绝通知给群主
                StaticLog.info("审核拒绝发送推送消息给群主");
                chatServiceMessage.setUserNo(friendGroupUser.getFriendUserNo());
                chatServiceMessage.setSendText(byUserNo.getNickName()+" 已拒绝加入 "+ friendGroup.getGroupName());
                chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
            }else {
                //查询群主信息
                UserInfo byUserNo = userInfoService.findByUserNo(friendGroup.getUserNo());
                // TODO:发送拒绝通知给申请者
                StaticLog.info("审核拒绝发送推送消息给申请者");
                chatServiceMessage.setUserNo(friendGroupUser.getUserNo());
                chatServiceMessage.setSendText(byUserNo.getNickName()+" 拒绝您加入 "+friendGroup.getGroupName());
                chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
            }
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());

            //如果是拒绝 并且状态为邀请中 那么状态就更新为拒绝
            friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170004.getCode());
        }

        //修改好友群关系信息
        boolean saveOrUpdate = saveOrUpdate(friendGroupUser);
        if (!saveOrUpdate){
            throw new GrilException("修改好友群关系信息失败");
        }

        boolean save = chatServiceMessageService.save(chatServiceMessage);
        if (!save){
            throw new GrilException("插入消息失败");
        }

        //修改消息状态
        ChatServiceMessage byServiceNo = chatServiceMessageService.findByServiceNo(inviteNo);
        byServiceNo.setMsgState(SysCodeEnum.CODE_10200002.getCode());
        chatServiceMessageService.saveOrUpdate(byServiceNo);

        return true;
    }

    /**
     * 分页查询邀请群成员列表
     *
     * @param groupNo  群编码
     * @param userNo   邀请者编码
     * @param content  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUser(String groupNo, String userNo, String content, Integer pageNo, Integer pageSize) {
        //查询我的好友列表
        List<String> friendNo = friendInviteService.selectOneUserNoResult(userNo);

        //查询群成员编码
        List<String> groupUserNo = friendGroupUserService.selectOneUserNoResult(groupNo);

        friendNo.retainAll(groupUserNo);

        StaticLog.error(friendNo.toString());

        //查询成员是否已被邀请
        List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUserOutputs = friendInviteService.pageQueryInviteGroupUser(userNo, content, pageNo, pageSize);

        pageQueryInviteGroupUserOutputs.forEach(pageQueryInviteGroupUserOutput -> {
            friendNo.forEach(retainUserNo->{
                if (retainUserNo.equals(pageQueryInviteGroupUserOutput.getUserNo())){
                    pageQueryInviteGroupUserOutput.setIsokInvite(true);
                }
            });
        });

        CollUtil.sort(pageQueryInviteGroupUserOutputs, new Comparator<PageQueryInviteGroupUserOutput>() {
            @Override
            public int compare(PageQueryInviteGroupUserOutput o1, PageQueryInviteGroupUserOutput o2) {
                return String.valueOf(o1.isIsokInvite()).compareTo(String.valueOf(o2.isIsokInvite()));//顺序
            }
        });

        return pageQueryInviteGroupUserOutputs;
    }

    /**
     * 分页查询用户群列表
     *
     * @param keyword   检索内容
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryUserGroupOutput> pageQueryUserGroup(String keyword, String userNo, Integer pageNo, Integer pageSize) {
        List<PageQueryUserGroupOutput> pageQueryUserGroupOutputs = baseMapper.pageQueryUserGroup(keyword,userNo,SysCodeEnum.CODE_10170001.getCode(), pageNo, pageSize);
        pageQueryUserGroupOutputs.forEach(pageQueryUserGroupOutput -> {
            if (pageQueryUserGroupOutput.getUserNo().equals(userNo)){
                pageQueryUserGroupOutput.setIsokOwner(true);
            }
        });
        return pageQueryUserGroupOutputs;
    }

    /**
     * 根据用户编码查询加入的群
     * @param userNo    用户编码
     * @return
     */
    @Override
    public List<Map<String, String>> queryJoinGroup(String userNo) {
        return baseMapper.queryJoinGroup(userNo,SysCodeEnum.CODE_10170001.getCode());
    }

    /**
     * 成为群成员
     *
     * @param applyUserNo  用户编码
     * @param friendGroup  群对象
     * @param inviteUserNo 邀请用户编码
     * @return
     */
    @Override
    public boolean becomeGroupUser(String applyUserNo, FriendGroup friendGroup,String inviteUserNo) {

        // TODO:保存群用户关系信息
        FriendGroupUser friendGroupUser = new FriendGroupUser();
        friendGroupUser.setId(DataBaseTool.createId());
        friendGroupUser.setUserNo(applyUserNo);
        friendGroupUser.setFriendUserNo(inviteUserNo);
        friendGroupUser.setGroupNo(friendGroup.getGroupNo());
        friendGroupUser.setInviteNo(DataBaseTool.createNo("group_user"));
        friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
        friendGroupUser.setJoinDate(DataBaseTool.createDate());
        save(friendGroupUser);


        // TODO:获取消息接受者信息
        UserInfo userInfo = userInfoService.findByUserNo(friendGroupUser.getUserNo());

        // TODO:发送通知给群主
        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setUserNo(friendGroup.getUserNo());
        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
        chatServiceMessage.setSendText(userInfo.getNickName() + " 已成功加入 " + friendGroup.getGroupName());
        //设置服务通知类型
        chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
        chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
        chatServiceMessageService.save(chatServiceMessage);

        // TODO:发送通知给群成员
        chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setUserNo(friendGroupUser.getUserNo());
        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
        chatServiceMessage.setSendText("您已成功加入 " + friendGroup.getGroupName());
        //设置服务通知类型
        chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
        chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
        chatServiceMessageService.save(chatServiceMessage);

        //添加环信群用户
        boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
        if (!addUsers){
            throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
        }

        KeysUtil.set(friendGroupUser.getInviteNo(), friendGroupUser.getInviteNo(), 60 * 5);

        return true;
    }

    @Override
    public List<FriendGroupUser> findByGroupNo(String groupNo) {
        QueryWrapper<FriendGroupUser> wrapper = new QueryWrapper();
        wrapper.eq("group_no",groupNo);
        List<FriendGroupUser> friendGroupUsers = list(wrapper);
        return friendGroupUsers;
    }

    @Override
    public void delByGroupNo(String groupNo) {
        List<String> strings = new ArrayList<>();
        List<FriendGroupUser> byGroupNo = findByGroupNo(groupNo);
        byGroupNo.forEach(friendGroupUser -> {
            strings.add(friendGroupUser.getUserNo());
        });
        if (strings.size() > 0) {
            removeByIds(strings);
        }
    }

    @Override
    public FriendGroupUser findByGroupNoAndUserNo(String groupNo, String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("group_no",groupNo);
        wrapper.eq("user_no",userNo);
        FriendGroupUser friendGroupUser = getOne(wrapper);
        return friendGroupUser;
    }
}
