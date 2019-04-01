package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserAccount;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.vo.user.QueryUserAccountOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface UserAccountService extends IService<UserAccount> {

    /**
     * 根据用户编码查询用户余额
     *
     * @param userNo 用户编码
     * @return
     */
    QueryUserAccountOutput queryUserAccount(String userNo);

    /**
     * 增加户蟠桃的总数
     *
     * @param userNo 用户编码
     * @param amount 增加的金额
     * @return
     */
    int addUserAccount(String userNo, BigDecimal amount);

    /**
     * 减去户蟠桃的总数
     *
     * @param userNo 用户编码
     * @param amount 扣款的金额
     * @return
     */

    int subtractUserAccount(String userNo, BigDecimal amount);

    /**
     * 修改用户账户金额
     *
     * @param userNo       用户编码
     * @param changeMode   资金变动方式
     * @param isWithdrawal 是否可提现
     * @param hasPlus      （添加金额 true 减去金额 false）
     * @param money        变动金额
     * @return
     */
    boolean updateUserAccount(String userNo, SysCodeEnum changeMode, boolean isWithdrawal, boolean hasPlus, BigDecimal money);

    /**
     * 根据用户编码查询用户账户信息
     *
     * @param userNo 用户编码
     * @return
     */
    UserAccount findByUserNo(String userNo);

    /**
     * 根据用户编码查询用户账户信息(如果用户账户信息为空 就插入默认信息)
     * @param userNo
     * @return
     */
    UserAccount findByUserNoIfNullIns(String userNo);
}
