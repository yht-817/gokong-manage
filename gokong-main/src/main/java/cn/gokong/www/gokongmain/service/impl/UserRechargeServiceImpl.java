package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.UserRechargeMapper;
import cn.gokong.www.gokongmain.domain.UserRecharge;
import cn.gokong.www.gokongmain.service.*;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户充值记录表 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-18
 */
@Service
public class UserRechargeServiceImpl extends ServiceImpl<UserRechargeMapper, UserRecharge> implements UserRechargeService {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountLogService userAccountLogService;

    @Autowired
    PlatformAccountService platformAccountService;

    @Autowired
    PlatformAccountLogService platformAccountLogService;

    @Autowired
    FriendInviteService friendInviteService;

    /**
     * @param rechargeAmount 充值金额
     *                       充值状态
     *                       充值日期
     *                       充值单号
     * @param payNillNo      支付单号
     * @param payNo          支付方式
     * @param sendNum        送好多
     * @param payNum         充值好的
     * @param userNo         用户编码
     * @return
     */
    public int payUserRecharge(BigDecimal rechargeAmount, String payNillNo, Integer payNo, BigDecimal sendNum, BigDecimal payNum, String userNo) {
        String rechargeState = "10130002";
        // 插入数据
        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setId(DataBaseTool.createId());
        userRecharge.setRechargeAmount(rechargeAmount);
        userRecharge.setSendNum(sendNum);
        userRecharge.setPayNum(payNum);
        userRecharge.setUserNo(userNo);
        userRecharge.setRechargeDate(new Date());
        userRecharge.setPayNo(payNo);
        userRecharge.setRechargeState(rechargeState);
        userRecharge.setRechargeNo("CZ" + DataBaseTool.createId());
        userRecharge.setPayBillNo(payNillNo);
        boolean insertlog = save(userRecharge);
        if (insertlog) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 修改用户充值记录表
     *
     * @param payNillNo 订单号
     */
    public int updateUserRecharge(String payNillNo) {
        return this.baseMapper.updateUserRecharge(payNillNo);
    }

    // 充值成功，修改用户的记录表
    public int updateUserInfo(Map<String, Object> data) {
        // 用订单号查询记录
        String wxorderno = (String) data.get("wxorderno");
        String paysum = String.valueOf(data.get("totalfee"));
        StaticLog.info("订单编码：" + wxorderno + "金额：" + paysum);
        UserRecharge recharge = this.baseMapper.findRecharge(wxorderno);
        StaticLog.info("根据订单查询的信息：" + recharge.toString());
        if (recharge != null) {
            BigDecimal send = recharge.getSendNum();
            BigDecimal pays = recharge.getPayNum();
            StaticLog.info("支付返回金额：" + paysum + "入库的金额：" + pays);
            BigDecimal sum = send.add(pays);
            if (recharge.getRechargeAmount().compareTo(sum) == -1) {
                sum = recharge.getRechargeAmount();
            }
            boolean update = userAccountLogService.inserlog("10050004", sum, wxorderno, recharge.getUserNo(), "充值");
            if (update) {
                int add = userAccountService.addUserAccount(recharge.getUserNo(), sum);
                if (add > 0) {
                    // 减去平台的蟠桃总数
                    boolean reductionlog = platformAccountLogService.inserlog("10050004", sum, wxorderno, recharge.getUserNo(), "充值");
                    if (reductionlog) {
                        int reduction = platformAccountService.changeAmount(sum);
                        StaticLog.info("当前订单支付成功" + wxorderno);
                        return reduction;
                    }
                    return add;
                }
            }
        }
        return 0;
    }

    /**
     * 认识牛人添加好友
     *
     * @param data
     * @return
     */
    public int updatePeopelPayAli(Map<String, Object> data) {
        // 修改当前支付的的状态
        // 用订单号查询记录
        String wxorderno = (String) data.get("wxorderno");
        String paysum = String.valueOf(data.get("totalfee"));
        StaticLog.info("订单编码：" + wxorderno + "金额：" + paysum);
        int updateinfopay = this.baseMapper.updatePayS(wxorderno);
        // 查询当前订单信息
        Map<String, String> cattledata = baseMapper.queryCattleInfo(wxorderno);
        String user_No = cattledata.get("user_no");
        // 添加人的用户编码
        String userNo = user_No.substring(0, user_No.lastIndexOf("_"));
        // 牛人的用户编码
        String friendNo = user_No.substring(user_No.lastIndexOf("_") + 1, user_No.length());
        StaticLog.info("申请人的用户编码：" + userNo + ",牛人的用户编码：" + friendNo);
        boolean userfrind = friendInviteService.applyFriend(userNo, friendNo, wxorderno);
        if (userfrind) {
            StaticLog.info("申请牛人添加成功");
            return 1;
        }
        return 0;
    }
}
