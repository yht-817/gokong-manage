package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.easemob.dao.IMUser;
import cn.gokong.www.base.jedis.KeysUtil;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.FriendInviteMapper;
import cn.gokong.www.gokongmain.domain.*;
import cn.gokong.www.gokongmain.enums.MsgTitleEnum;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.friend.PageQueryFriendOutput;
import cn.gokong.www.gokongmain.vo.group.PageQueryInviteGroupUserOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友邀请表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class FriendInviteServiceImpl extends ServiceImpl<FriendInviteMapper, FriendInvite> implements FriendInviteService {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    @Autowired
    ChoiceUserPayService choiceUserPayService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountLogService userAccountLogService;


    @Override
    public boolean save(FriendInvite entity) {
        entity.setId(DataBaseTool.createId());
        entity.setInviteDate(DataBaseTool.createDate());
        return super.save(entity);
    }

    /**
     * 是否为好友
     *
     * @param userNo    用户编码
     * @param visitorNo 用户编码
     * @return
     */
    @Override
    public boolean isFriend(String userNo, String visitorNo) {
        FriendInvite byUserNoAndFriendUserNo = baseMapper.findByUserNoAndFriendUserNoAndInviteState(userNo, visitorNo, SysCodeEnum.CODE_10150002.getCode());
        if (ObjectUtil.isNotNull(byUserNoAndFriendUserNo)) {
            return true;
        }
        return false;
    }


    /**
     * 查询当前是否是好友而且是否在申请中
     *
     * @param userNo    用户编码
     * @param visitorNo 用户编码
     * @return
     */
    @Override
    public boolean isFriendss(String userNo, String visitorNo) {
        FriendInvite byUserNoAndFriendUserNo = baseMapper.findByUserNoAndFriendUserNoAndInviteStates(userNo, visitorNo, SysCodeEnum.CODE_10150003.getCode());
        if (ObjectUtil.isNotNull(byUserNoAndFriendUserNo)) {
            return true;
        }
        return false;
    }

    /**
     * 查询两个用户编码是否在同一个数据列表中
     *
     * @param userno   用户编码
     * @param friendno 用户编码
     * @return
     */
    public boolean isFriends(String userno, String friendno) {
        FriendInvite byUserNoAndFriendUserNo = baseMapper.findByUserNoAndFriendUserNoAndInviteStateS(userno, friendno);
        if (ObjectUtil.isNotNull(byUserNoAndFriendUserNo)) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> selectOneUserNoResult(String userNo) {
        return baseMapper.findByUserNoAndInviteState(userNo, SysCodeEnum.CODE_10150002.getCode());
    }

    /**
     * 分页查询邀请好友列表
     *
     * @param userNo  用户编码
     * @param content 检索内容
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */
    @Override
    public List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUser(String userNo, String content, Integer start, Integer end) {
        return baseMapper.pageQueryInviteGroupUser(userNo, content, start, end);
    }

    /**
     * 申请添加好友
     *
     * @param userNo       申请者编码
     * @param friendUserNo 好友编码
     * @param verifyInfo   验证码消息
     * @return
     */
    @Override
    @Transactional
    public boolean applyFriend(String userNo, String friendUserNo, String verifyInfo) {
        boolean friend = isFriend(userNo, friendUserNo);
        if (friend) {
            throw new GrilException("已经是好友,无需再添加");
        }
        //判断是否已经发送邀请
        FriendInvite friendInvite = baseMapper.findByUserNoAndFriendUserNoAndInviteState(userNo, friendUserNo, SysCodeEnum.CODE_10150001.getCode());

        if (ObjectUtil.isNotNull(friendInvite)) {
            //判断存储的申请编码是否存在
            if (KeysUtil.exists(friendInvite.getInviteNo())) {
                return true;
            }
            KeysUtil.set(friendInvite.getInviteNo(), friendInvite.getInviteNo(), 60 * 5);

            ChatServiceMessage chatServiceMessage = chatServiceMessageService.findByServiceNo(friendInvite.getInviteNo());
            if (ObjectUtil.isNull(chatServiceMessage)) {
                throw new GrilException("申请添加好友 信息丢失");
            }
            chatServiceMessage.setSendDate(DataBaseTool.createDate());
            return chatServiceMessageService.saveOrUpdate(chatServiceMessage);
        } else {
            friendInvite = new FriendInvite();
            friendInvite.setInviteNo(DataBaseTool.createNo("friend_apply"));
            friendInvite.setUserNo(userNo);
            friendInvite.setFriendUserNo(friendUserNo);
            friendInvite.setVerifyInfo(verifyInfo);
            //设置状态邀请中
            friendInvite.setInviteState(SysCodeEnum.CODE_10150001.getCode());

            boolean save = save(friendInvite);
            if (!save) {
                throw new GrilException("申请添加好友失败");
            }

            KeysUtil.set(friendInvite.getInviteNo(), friendInvite.getInviteNo(), 60 * 5);


            //查询申请者的用户信息
            UserInfo userInfo = userInfoService.findByUserNo(userNo);

            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setUserNo(friendInvite.getFriendUserNo());
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390002.getCode());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10010.getMsg());
            chatServiceMessage.setSendText(userInfo.getNickName() + " 申请添加您为好友");
            chatServiceMessage.setServiceNo(friendInvite.getInviteNo());
            chatServiceMessage.setSendDate(DataBaseTool.createDate());
            return chatServiceMessageService.save(chatServiceMessage);
        }
    }

    /**
     * 审核好友申请
     *
     * @param inviteNo   申请编码
     * @param auditState 审核状态
     * @return
     */
    @Override
    @Transactional
    public boolean auditFriendApply(String inviteNo, String auditState) {
        FriendInvite friendInvite = baseMapper.findByInviteNo(inviteNo);

        QueryWrapper<UserAccountLog> wrapper = new QueryWrapper<>();
        wrapper.eq("change_no", friendInvite.getVerifyInfo());
        UserAccountLog userAccountLog = userAccountLogService.getOne(wrapper);

        if (ObjectUtil.isNull(friendInvite)) {
            throw new GrilException("未查询到审核好友申请信息");
        }

        if (!friendInvite.getInviteState().equals(SysCodeEnum.CODE_10150001.getCode())) {
            throw new GrilException("已处理");
        }

        friendInvite.setChangeDate(DataBaseTool.createDate());
        friendInvite.setInviteState(auditState);
        boolean saveOrUpdate = saveOrUpdate(friendInvite);

        //查询审核者的用户信息
        UserInfo userInfo = userInfoService.findByUserNo(friendInvite.getFriendUserNo());
        if (!saveOrUpdate) {
            throw new GrilException("审核失败");
        }
        if (auditState.equals(SysCodeEnum.CODE_10150002.getCode())) {
            //添加反向好友
            FriendInvite friend = new FriendInvite();
            friend.setUserNo(friendInvite.getFriendUserNo());
            friend.setFriendUserNo(friendInvite.getUserNo());
            //设置状态为已同意
            friend.setInviteState(auditState);
            friend.setInviteNo(DataBaseTool.createNo("friend_apply"));
            save(friend);
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setUserNo(friendInvite.getUserNo());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10010.getMsg());
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setSendText(userInfo.getNickName() + " 已同意你的好友请求");
            chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
            chatServiceMessageService.save(chatServiceMessage);


//            Map<String, Object> moneyinfo = baseMapper.queryMoney(friendInvite.getVerifyInfo());
//            if (ObjectUtil.isNotNull(moneyinfo)) {
//                BigDecimal money = (BigDecimal) moneyinfo.get("pay_amount");
//                if (money.compareTo(new BigDecimal(0)) != 0) {
//                    boolean updateUserAccount = userAccountService.updateUserAccount(userInfo.getUserNo(), SysCodeEnum.CODE_10050013, false, true, money);
//                    if (!updateUserAccount) {
//                        StaticLog.info("修改用户账户金额失败");
//                    }
//                }
//            }
            // 牛人获取相应的收益（根据订单）
            if (userAccountLog != null) {
                userAccountService.addUserAccount(userInfo.getUserNo(), userAccountLog.getChangeAmount());
                userAccountLogService.inserlog("10050013", userAccountLog.getChangeAmount(), friendInvite.getVerifyInfo(), userInfo.getUserNo(), "牛人拒绝");
            }

            boolean addFriendSingle = IMUser.addFriendSingle(friendInvite.getUserNo(), friendInvite.getFriendUserNo());
            if (!addFriendSingle) {
                throw new GrilException("审核好友 - 添加环信好友失败");
            }
        } else {
            //拒绝
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setUserNo(friendInvite.getUserNo());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10010.getMsg());
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setSendText(userInfo.getNickName() + " 拒绝了你的好友请求");
            chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
            chatServiceMessageService.save(chatServiceMessage);

//            //如果是牛人 调用退款请求
//            if (StrUtil.isNotEmpty(friendInvite.getVerifyInfo()) && friendInvite.getVerifyInfo().contains("JHY")) {
//
//                String rep = HttpUtil.post("https://pay.gokong.cn:8043/v2/peoplePay/peopleRefund", "orderNo=" + friendInvite.getVerifyInfo());
//                StaticLog.info("rep:{}", rep);
//            }
            // 拒绝然后退回当前支付的悟空比
            if (userAccountLog != null) {
                userAccountService.addUserAccount(friendInvite.getUserNo(), userAccountLog.getChangeAmount());
                userAccountLogService.inserlog("10050009", userAccountLog.getChangeAmount(), friendInvite.getVerifyInfo(), friendInvite.getUserNo(), "牛人拒绝");
                removeById(friendInvite.getId());
            }
        }
        //修改消息状态
        ChatServiceMessage byServiceNo = chatServiceMessageService.findByServiceNo(inviteNo);
        byServiceNo.setMsgState(SysCodeEnum.CODE_10200002.getCode());
        chatServiceMessageService.saveOrUpdate(byServiceNo);
        return true;
    }

    /**
     * 删除好友
     *
     * @param inviteNo 申请编码
     * @return
     */
    @Override
    @Transactional
    public boolean delFriend(String inviteNo) {
        FriendInvite friendInvite = baseMapper.findByInviteNo(inviteNo);

        if (ObjectUtil.isNull(friendInvite)) {
            throw new GrilException("未查询到好友关系信息");
        }
        baseMapper.delFriend(friendInvite.getUserNo(), friendInvite.getFriendUserNo());

        //删除环信好友
        boolean deleteFriendSingle = IMUser.deleteFriendSingle(friendInvite.getUserNo(), friendInvite.getFriendUserNo());
        if (!deleteFriendSingle) {
            throw new GrilException("删除环信好友失败");
        }
        //删除服务通知
        ChatServiceMessage byServiceNo = chatServiceMessageService.findByServiceNo(friendInvite.getInviteNo());
        if (ObjectUtil.isNotNull(byServiceNo)) {
            chatServiceMessageService.removeById(byServiceNo.getId());
        }
        return true;
    }

    /**
     * 查询我的好友
     *
     * @param keyword  检索内容
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 结束位置
     * @return
     */
    @Override
    public List<PageQueryFriendOutput> pageQueryFriend(String keyword, String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryFriend(keyword, userNo, pageNo, pageSize);
    }

    /**
     * 成为好友
     *
     * @param userNo       用户编码
     * @param friendUserNo 好友编码
     * @return
     */
    @Override
    public boolean becomeFriend(String userNo, String friendUserNo) {
        //判断当前用户关系是否是好友
        boolean friend = isFriend(userNo, friendUserNo);
        if (friend) {
            throw new GrilException("已经是好友关系无需添加好友");
        }

        FriendInvite friendInvite = new FriendInvite();
        friendInvite.setUserNo(userNo);
        friendInvite.setFriendUserNo(friendUserNo);
        //设置状态为已同意
        friendInvite.setInviteState(SysCodeEnum.CODE_10150002.getCode());
        friendInvite.setInviteNo(DataBaseTool.createNo("friend_apply"));
        save(friendInvite);

        //添加反向好友
        friendInvite.setUserNo(friendUserNo);
        friendInvite.setFriendUserNo(userNo);
        //设置状态为已同意
        friendInvite.setInviteState(SysCodeEnum.CODE_10150002.getCode());
        friendInvite.setInviteNo(DataBaseTool.createNo("friend_apply"));
        save(friendInvite);

        boolean addFriendSingle = IMUser.addFriendSingle(userNo, friendUserNo);
        if (!addFriendSingle) {
            throw new GrilException("成为好友 - 添加环信好友失败");
        }

        return true;
    }


    /**
     * 查询黑名单信息
     *
     * @param userBlack
     * @return
     */
    public List<BlacFriendInfo> queryBlackList(List<Map<String, String>> userBlack) {
        List<String> listdata = new ArrayList<>();
        for (int i = 0; i < userBlack.size(); i++) {
            listdata.add(userBlack.get(i).get("userNo"));
        }
        return baseMapper.queryBlackList(listdata);
    }

    /**
     * 查询好友状态
     *
     * @param userNo
     * @param cattelUserNo
     * @param code
     * @return
     */
    public boolean isFriendInfo(String userNo, String cattelUserNo, String code) {
        FriendInvite byUserNoAndFriendUserNo = baseMapper.findByUserNoAndFriendUserNoAndInviteState(userNo, cattelUserNo, code);
        if (ObjectUtil.isNotNull(byUserNoAndFriendUserNo)) {
            return true;
        }
        return false;
    }


}
