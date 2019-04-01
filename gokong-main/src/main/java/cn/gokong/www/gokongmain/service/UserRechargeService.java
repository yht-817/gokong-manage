package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserRecharge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户充值记录表 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-18
 */
public interface UserRechargeService extends IService<UserRecharge> {
    // 插入用户充值记录表
    int payUserRecharge(BigDecimal rechargeAmount, String payNillNo, Integer payNo, BigDecimal sendNum, BigDecimal payNum, String userNo);

    // 修改充值记录表
    int updateUserRecharge(String payNillNo);

    // 充值成功后修改用户的信息
    int updateUserInfo(Map<String, Object> data);

    // 修改订单
    int updatePeopelPayAli(Map<String, Object> data);

}
