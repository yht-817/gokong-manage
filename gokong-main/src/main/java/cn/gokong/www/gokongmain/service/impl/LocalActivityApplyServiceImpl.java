package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.easemob.dao.IMChatGroup;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.LocalActivityApplyMapper;
import cn.gokong.www.gokongmain.domain.*;
import cn.gokong.www.gokongmain.enums.MsgTitleEnum;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.local_activity_apply.PageQueryActivityMemberOutput;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 同城活动用户申请表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-05
 */
@Service
public class LocalActivityApplyServiceImpl extends ServiceImpl<LocalActivityApplyMapper, LocalActivityApply> implements LocalActivityApplyService {

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    LocalActivityService localActivityService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    FriendGroupUserService friendGroupUserService;

    @Autowired
    PlatformAccountLogService platformAccountLogService;

    @Autowired
    PlatformAccountService platformAccountService;

    @Autowired
    ActivityTabloidService activityTabloidService;

    @Autowired
    UserAccountLogService userAccountLogService;

    /**
     * 同城活动申请
     *
     * @param activityNo 活动编码
     * @param userNo     用户编码
     * @param phone      联系电话
     * @param introduce  自我介绍
     * @param sex        性别
     * @return
     */
    @Override
    @Transactional
    public String userApply(String activityNo, String userNo, String phone, String introduce, String sex) {
        //判断是否已经申请
        LocalActivityApply activityApply = findByActivityNoAndUserNo(activityNo, userNo);
        if (ObjectUtil.isNotNull(activityApply)) {
            if (!activityApply.getApplyState().equals(SysCodeEnum.CODE_10470001.getCode())) {
                throw new GrilException("您已申请报名！");
            }
        }


        //判断用户是否加入
        boolean hasJoin = hasJoin(activityNo, userNo);
        if (hasJoin) {
            throw new GrilException("您已申请！");
        }

        //查询活动信息
        LocalActivity localActivity = localActivityService.findByActivityNo(activityNo);

        //判断活动人数是否已满
        if (localActivity.getHopePersonNum() <= localActivity.getPersonNum()) {
            throw new GrilException("活动人数上限");
        }

        //查询申请者的信息
        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        // 活动小报
        ActivityTabloid activityTabloid = new ActivityTabloid();
        activityTabloid.setActivityNo(localActivity.getActivityNo());
        activityTabloid.setActivityTime(localActivity.getActivityTime());
        activityTabloid.setCityName(localActivity.getCityName());
        activityTabloid.setContent("<strong>" + userInfo.getNickName() + "</strong> 刚刚报名了位于 <strong>" + localActivity.getCityName() + "</strong> 的活动 《<strong>" + localActivity.getActivityTitle() + "</strong>》");
        activityTabloidService.save(activityTabloid);

        // 查看是否收费
        if (localActivity.isHasCharge()) {
            // 收费的
            LocalActivityApply localActivityApply = new LocalActivityApply();
            localActivityApply.setId(DataBaseTool.createId());
            localActivityApply.setApplyNo(DataBaseTool.createNo("apply_no"));
            localActivityApply.setActivityNo(activityNo);
            localActivityApply.setUserNo(userNo);
            localActivityApply.setPhone(phone);
            localActivityApply.setIntroduce(introduce);
            localActivityApply.setSex(sex);
            localActivityApply.setApplyTime(DataBaseTool.createDate());
            localActivityApply.setApplyState(SysCodeEnum.CODE_10470002.getCode());
            boolean save = save(localActivityApply);
            if (!save) {
                throw new GrilException("申请失败");
            }
            //发送申请通知
            return localActivityApply.getApplyNo();
        } else {
            // 不收费
            // 是否需要审核（需要）
            if (localActivity.isHasVerify()) {
                LocalActivityApply localActivityApply = new LocalActivityApply();
                localActivityApply.setId(DataBaseTool.createId());
                localActivityApply.setApplyNo(DataBaseTool.createNo("apply_no"));
                localActivityApply.setActivityNo(activityNo);
                localActivityApply.setUserNo(userNo);
                localActivityApply.setPhone(phone);
                localActivityApply.setIntroduce(introduce);
                localActivityApply.setSex(sex);
                localActivityApply.setApplyTime(DataBaseTool.createDate());
                localActivityApply.setApplyState(SysCodeEnum.CODE_10470002.getCode());

                boolean save = save(localActivityApply);

                if (!save) {
                    throw new GrilException("申请失败");
                }

                // 发送申请加入消息提醒
                ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
                chatServiceMessage.setSendHead(userInfo.getUserHead());
                chatServiceMessage.setSendTitle(userInfo.getNickName() + " 申请参加 " + localActivity.getActivityTitle());
                chatServiceMessage.setSendText("");
                chatServiceMessage.setUserNo(localActivity.getUserNo());
                chatServiceMessage.setServiceNo(localActivityApply.getApplyNo());
                chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390006.getCode());
                chatServiceMessageService.save(chatServiceMessage);
                //发送申请通知
                return localActivityApply.getApplyNo();
            } else {
                //如果活动不需要付费,直接加入活动
                LocalActivityApply localActivityApply = new LocalActivityApply();
                localActivityApply.setId(DataBaseTool.createId());
                localActivityApply.setApplyNo(DataBaseTool.createNo("apply_no"));
                localActivityApply.setActivityNo(activityNo);
                localActivityApply.setUserNo(userNo);
                localActivityApply.setPhone(phone);
                localActivityApply.setIntroduce(introduce);
                localActivityApply.setSex(sex);
                localActivityApply.setApplyTime(DataBaseTool.createDate());
                localActivityApply.setApplyState(SysCodeEnum.CODE_10470002.getCode());

                boolean save = save(localActivityApply);

                if (!save) {
                    throw new GrilException("申请失败");
                }

                ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
                chatServiceMessage.setSendHead(userInfo.getUserHead());
                chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10017.getMsg());
                chatServiceMessage.setSendText(userInfo.getNickName() + " 已经成功参加： " + localActivity.getActivityTitle());
                chatServiceMessage.setUserNo(localActivity.getUserNo());
                chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
                chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
                chatServiceMessageService.save(chatServiceMessage);

                //加入活动群聊
                FriendGroupUser friendGroupUser = new FriendGroupUser();
                friendGroupUser.setUserNo(localActivityApply.getUserNo());
                friendGroupUser.setGroupNo(localActivity.getGroupNo());
                friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
                friendGroupUserService.save(friendGroupUser);
                //添加环信群用户
                boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
                if (!addUsers) {
                    throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
                }
                //更新当前活动人数
                localActivity.setPersonNum(localActivity.getPersonNum() + 1);
                // 修改当前活动的状态然后发信息给活动创建人
                int updateActivity = baseMapper.updateActivity(localActivityApply.getApplyNo(), SysCodeEnum.CODE_10470003.getCode());
                StaticLog.info("修改用户状态：" + updateActivity);
                localActivityService.saveOrUpdate(localActivity);

                // 参加活动成功获得2悟空比的收益
                userAccountService.addUserAccount(userNo, new BigDecimal(2));
                // 添加用户增加
                userAccountLogService.inserlog("10050010", new BigDecimal(2), activityNo, userNo, "参加活动收益");

                return localActivityApply.getApplyNo();
            }
        }
    }

    private LocalActivityApply findByActivityNoAndUserNo(String activityNo, String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("activity_no", activityNo);
        wrapper.eq("user_no", userNo);
        LocalActivityApply localActivityApply = getOne(wrapper);
        return localActivityApply;
    }

    /**
     * 判断用户是否已经加入
     *
     * @param activityNo 活动编码
     * @param userNo     用户编码
     * @return
     */
    @Override
    public boolean hasJoin(String activityNo, String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("activity_no", activityNo);
        wrapper.eq("user_no", userNo);
        wrapper.eq("user_no", userNo);
        wrapper.eq("apply_state", SysCodeEnum.CODE_10470003.getCode());
        LocalActivityApply localActivityApply = getOne(wrapper);
        if (ObjectUtil.isNotNull(localActivityApply)) {
            return true;
        }
        return false;
    }

    /**
     * 审核申请
     *
     * @param applyNo    活动编码
     * @param userNo     用户编码
     * @param auditState 审核状态
     * @return
     */
    @Override
    @Transactional
    public boolean auditApply(String applyNo, String userNo, boolean auditState) {
        ChatServiceMessage byServiceNo = chatServiceMessageService.findByServiceNo(applyNo);
        if (byServiceNo.getMsgState().equals(SysCodeEnum.CODE_10200002)) {
            throw new GrilException("已处理");
        }

        LocalActivityApply localActivityApply = findByApplyNo(applyNo);

        LocalActivity localActivity = localActivityService.findByActivityNo(localActivityApply.getActivityNo());

        if (localActivity.getActivityTime().getTime() <= DateUtil.current(false)) {
            throw new GrilException("活动已结束");
        }

        UserInfo userInfo = userInfoService.findByUserNo(localActivityApply.getUserNo());

        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();

        if (auditState) {
            //如果审核通过
            localActivityApply.setApplyState(SysCodeEnum.CODE_10470003.getCode());

            if (localActivity.isHasCharge()) {
                //获取支付蟠桃
                userAccountService.updateUserAccount(localActivity.getUserNo(), SysCodeEnum.CODE_10050012, false, true, localActivity.getPrice());
            }

            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10017.getMsg());
            chatServiceMessage.setSendText("您已成功加入 同城活动 《" + localActivity.getActivityTitle() + "》");
            chatServiceMessage.setUserNo(userInfo.getUserNo());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessageService.save(chatServiceMessage);

            //加入活动群聊
            FriendGroupUser friendGroupUser = new FriendGroupUser();
            friendGroupUser.setUserNo(localActivityApply.getUserNo());
            friendGroupUser.setGroupNo(localActivity.getGroupNo());
            friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
            friendGroupUserService.save(friendGroupUser);

            //添加环信群用户
            boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
            if (!addUsers) {
                throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
            }

            //更新当前活动人数
            localActivity.setPersonNum(localActivity.getPersonNum() + 1);
            localActivityService.saveOrUpdate(localActivity);

            saveOrUpdate(localActivityApply);

            // 参加活动成功获得2悟空比的收益
            userAccountService.addUserAccount(localActivityApply.getUserNo(), new BigDecimal(2));
            // 添加用户增加
            userAccountLogService.inserlog("10050010", new BigDecimal(2), applyNo, localActivityApply.getUserNo(), "参加活动收益");

        } else {
            localActivityApply.setApplyState(SysCodeEnum.CODE_10470004.getCode());

            if (localActivity.isHasCharge()) {
                //返回支付蟠桃
                userAccountService.updateUserAccount(localActivityApply.getUserNo(), SysCodeEnum.CODE_10050009, false, true, localActivity.getPrice());
            }

            UserInfo byUserNo = userInfoService.findByUserNo(localActivity.getUserNo());

            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10017.getMsg());
            chatServiceMessage.setSendText(byUserNo.getNickName() + " 拒绝了您对 " + localActivity.getActivityTitle() + " 活动的报名申请<br/><a style='display:inline-block;border:#fb5555;background-color:#fb5555;padding: 2px 10px;color: #fff;font-size: 14px;margin:10px 0' href='http://h5.gokong.cn/v2/components/activity.html?activityNo=" + localActivity.getActivityNo() + "&userNo=" + localActivityApply.getUserNo() + "&system=true'>再次申请</a>");
            chatServiceMessage.setUserNo(userInfo.getUserNo());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessageService.save(chatServiceMessage);

            Map<String, Object> map = new HashMap<>();
            map.put("orderNo", applyNo);
            HttpUtil.post("https://pay.gokong.cn:8043/v2/common/unifiedRefund", map);

            //拒绝后删除申请信息
            int activityApply = deleteActivityApply(applyNo);
            StaticLog.info("删除关系：" + activityApply);
        }

        //修改服务消息状态
        ChatServiceMessage serviceByServiceNo = chatServiceMessageService.findByServiceNo(applyNo);
        serviceByServiceNo.setMsgState(SysCodeEnum.CODE_10200002.getCode());
        return chatServiceMessageService.saveOrUpdate(serviceByServiceNo);


    }

    /**
     * 根据申请编码查询申请信息
     *
     * @param applyNo 申请编码
     * @return
     */
    @Override
    public LocalActivityApply findByApplyNo(String applyNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("apply_no", applyNo);
        LocalActivityApply localActivityApply = getOne(wrapper);
        if (ObjectUtil.isNull(localActivityApply)) {
            throw new GrilException("申请信息不存在");
        }
        return localActivityApply;
    }

    /**
     * 分页查询活动成员列表
     *
     * @param activityNo 活动编码
     * @param pageNo     开始位置
     * @param pageSize   页面长度
     * @return
     */
    @Override
    public List<PageQueryActivityMemberOutput> pageQueryActivityMember(String activityNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryActivityMember(activityNo, pageNo, pageSize);
    }

    /**
     * 参加同城活动
     *
     * @param data
     * @return
     */
    public int sameAtivityAli(Map<String, Object> data) {
        String orderNo = (String) data.get("wxorderno");
        // 修改支付的状态
        int updatePays = baseMapper.updatePayState(orderNo);
        StaticLog.info("修改支付的状态：" + updatePays);
        BigDecimal money = BigDecimal.valueOf(Double.valueOf(String.valueOf(data.get("totalfee"))));

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("apply_no", orderNo);
        // 查询申请信息
        LocalActivityApply localActivityApply = getOne(wrapper);
        //查询活动信息
        LocalActivity localActivity = localActivityService.findByActivityNo(localActivityApply.getActivityNo());

        // 发送消息
        //查询申请者的信息
        UserInfo userInfo = userInfoService.findByUserNo(localActivityApply.getUserNo());
        //发送申请通知
        if (localActivity.isHasVerify()) {
            int me = chatServiceMessageService.findByServiceNoAndChatMessage(localActivityApply.getApplyNo());
            if (me > 0) {
                return 1;
            }
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setSendTitle(userInfo.getNickName() + " 申请参加 " + localActivity.getActivityTitle());
            chatServiceMessage.setSendText("");
            chatServiceMessage.setUserNo(localActivity.getUserNo());
            chatServiceMessage.setServiceNo(localActivityApply.getApplyNo());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390006.getCode());
            chatServiceMessageService.save(chatServiceMessage);
        } else {
            int me = chatServiceMessageService.findByServiceNoAndChatMessage(localActivityApply.getApplyNo());
            if (me > 0) {
                return 1;
            }
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10017.getMsg());
            chatServiceMessage.setSendText(userInfo.getNickName() + " 已经成功参加： " + localActivity.getActivityTitle());
            chatServiceMessage.setUserNo(localActivity.getUserNo());
            chatServiceMessage.setServiceNo(DataBaseTool.createNo("service_no"));
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390001.getCode());
            chatServiceMessageService.save(chatServiceMessage);

            //加入活动群聊
            FriendGroupUser friendGroupUser = new FriendGroupUser();
            friendGroupUser.setUserNo(localActivityApply.getUserNo());
            friendGroupUser.setGroupNo(localActivity.getGroupNo());
            friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
            friendGroupUserService.save(friendGroupUser);
            //添加环信群用户
            boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
            if (!addUsers) {
                throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
            }
            //更新当前活动人数
            localActivity.setPersonNum(localActivity.getPersonNum() + 1);
            // 修改当前活动的状态然后发信息给活动创建人
            int updateActivity = baseMapper.updateActivity(orderNo, SysCodeEnum.CODE_10470003.getCode());
            StaticLog.info("修改用户状态：" + updateActivity);
            localActivityService.saveOrUpdate(localActivity);
        }
        // 给活动创建者添加蟠桃收益
        boolean updateUserAccount = userAccountService.updateUserAccount(localActivity.getUserNo(), SysCodeEnum.CODE_10050011, false, true, money);
        if (!updateUserAccount) {
            StaticLog.info("修改用户账户金额失败");
        }
        return 1;
    }

    /**
     * 删除活动的申请
     *
     * @param applyNo
     * @return
     */
    public int deleteActivityApply(String applyNo) {
        return baseMapper.deleteActivityApply(applyNo);
    }
}
