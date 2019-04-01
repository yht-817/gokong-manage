package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.UserAccountMapper;
import cn.gokong.www.gokongmain.domain.PlatformAccount;
import cn.gokong.www.gokongmain.domain.PlatformAccountLog;
import cn.gokong.www.gokongmain.domain.UserAccount;
import cn.gokong.www.gokongmain.domain.UserAccountLog;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.PlatformAccountLogService;
import cn.gokong.www.gokongmain.service.PlatformAccountService;
import cn.gokong.www.gokongmain.service.UserAccountLogService;
import cn.gokong.www.gokongmain.service.UserAccountService;
import cn.gokong.www.gokongmain.vo.user.QueryUserAccountOutput;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Autowired
    UserAccountLogService userAccountLogService;

    @Autowired
    PlatformAccountService platformAccountService;

    @Autowired
    PlatformAccountLogService platformAccountLogService;

    @Override
    public UserAccount findByUserNo(String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no",userNo);
        UserAccount userAccount = getOne(wrapper);
        if (ObjectUtil.isNull(userAccount)){
            throw new GrilException("用户账户信息不存在");
        }
        return userAccount;
    }

    /**
     * 根据用户编码查询用户余额
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public QueryUserAccountOutput queryUserAccount(String userNo) {
        return baseMapper.findByUserNo(userNo);
    }

    /**
     * 增加用户的不可提现的余额
     *
     * @param userNo 用户编码
     * @param amount 充值的金额
     * @return
     */
    public int addUserAccount(String userNo, BigDecimal amount) {
        return this.baseMapper.addUserAccount(userNo, amount);
    }

    /**
     * 减用户蟠桃的总数
     *
     * @param userNo 用户编码
     * @param amount 充值的金额
     * @return
     */
    public int subtractUserAccount(String userNo, BigDecimal amount) {
        return this.baseMapper.subtractUserAccount(userNo, amount);
    }

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
    @Override
    @Transactional
    public boolean updateUserAccount(String userNo, SysCodeEnum changeMode, boolean isWithdrawal, boolean hasPlus,BigDecimal money) {
        //如果是添加用户金额
        if (hasPlus) {
            //一.查询平台总金额
            PlatformAccount platformAccount = platformAccountService.getOne(null);
            if (ObjectUtil.isNull(platformAccount)) {
                StaticLog.error("未查询到平台金额");
                return false;
            }

            //平台金额
            BigDecimal accountAmount = platformAccount.getAccountAmount();

            if (accountAmount.compareTo(money)<0){
                throw new GrilException("平台金额不足");
            }
            //修改平台金额
            platformAccount.setAccountAmount(accountAmount.subtract(money));
            platformAccountService.saveOrUpdate(platformAccount);

            //修改个人金额
            UserAccount userAccount = findByUserNo(userNo);

            //是否可提现（如果可以提现 就加入到可提现的账户 否则加入到 不可提现的账户）
            if (isWithdrawal) {
                userAccount.setCashAccountAmount(userAccount.getCashAccountAmount().add(money));
            } else {
                userAccount.setAccountAmount(userAccount.getAccountAmount().add(money));
            }
            saveOrUpdate(userAccount);

            //添加平台账户日志
            PlatformAccountLog platformAccountLog = new PlatformAccountLog();
            platformAccountLog.setId(DataBaseTool.createId());
            platformAccountLog.setUserNo(userNo);
            platformAccountLog.setChangeAmount(money.negate());
            platformAccountLog.setChangeDate(DataBaseTool.createDate());
            platformAccountLog.setChangeMode(changeMode.getCode());
            platformAccountLog.setChangeNo(DataBaseTool.createNo("change_no"));
            platformAccountLog.setChangeRemark(changeMode.getMsg());
            platformAccountLogService.save(platformAccountLog);

            //添加个人账户日志
            UserAccountLog userAccountLog = new UserAccountLog();
            userAccountLog.setId(DataBaseTool.createId());
            userAccountLog.setUserNo(userNo);
            userAccountLog.setChangeDate(DataBaseTool.createDate());
            userAccountLog.setChangeAmount(money);
            userAccountLog.setChangeMode(changeMode.getCode());
            userAccountLog.setChangeRemark(changeMode.getMsg());
            userAccountLog.setChangeNo(DataBaseTool.createNo("change_no"));
            userAccountLogService.save(userAccountLog);
            return true;

        }else {
            //如果是扣除用户金额
            UserAccount userAccount = findByUserNo(userNo);


            //修改个人金额
            if (isWithdrawal) {

                if (userAccount.getCashAccountAmount().compareTo(money) < 0) {
                    throw new GrilException("蟠桃不足!");
                }
                userAccount.setCashAccountAmount(userAccount.getCashAccountAmount().subtract(money));
            } else {

                if (userAccount.getAccountAmount().compareTo(money) < 0) {
                    throw new GrilException("蟠桃不足!");
                }
                userAccount.setAccountAmount(userAccount.getAccountAmount().subtract(money));
            }
            saveOrUpdate(userAccount);

            //修改平台金额
            PlatformAccount platformAccount = platformAccountService.getOne(null);
            if (ObjectUtil.isNull(platformAccount)) {
                StaticLog.error("未查询到平台金额");
                return false;
            }
            platformAccount.setAccountAmount(platformAccount.getAccountAmount().add(money));
            platformAccountService.saveOrUpdate(platformAccount);

            //添加平台账户日志
            PlatformAccountLog platformAccountLog = new PlatformAccountLog();
            platformAccountLog.setId(DataBaseTool.createId());
            platformAccountLog.setUserNo(userNo);
            platformAccountLog.setChangeAmount(money);
            platformAccountLog.setChangeDate(DataBaseTool.createDate());
            platformAccountLog.setChangeMode(changeMode.getCode());
            platformAccountLog.setChangeNo(DataBaseTool.createNo("change_no"));
            platformAccountLog.setChangeRemark(changeMode.getMsg());
            platformAccountLogService.save(platformAccountLog);

            //添加个人账户日志
            UserAccountLog userAccountLog = new UserAccountLog();
            userAccountLog.setId(DataBaseTool.createId());
            userAccountLog.setUserNo(userNo);
            userAccountLog.setChangeDate(DataBaseTool.createDate());
            userAccountLog.setChangeAmount(money.negate());
            userAccountLog.setChangeMode(changeMode.getCode());
            userAccountLog.setChangeRemark(changeMode.getMsg());
            userAccountLog.setChangeNo(DataBaseTool.createNo("change_no"));
            userAccountLogService.save(userAccountLog);

            return true;
        }
    }

    @Override
    public UserAccount findByUserNoIfNullIns(String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no",userNo);
        UserAccount userAccount = getOne(wrapper);
        if (userAccount == null) {
            userAccount = new UserAccount();
            userAccount.setId(DataBaseTool.createId());
            userAccount.setUserNo(userNo);
            userAccount.setAccountAmount(new BigDecimal(0));
            userAccount.setCashAccountAmount(new BigDecimal(0));
            userAccount.setForzenAccountAmount(new BigDecimal(0));
            save(userAccount);
        }
        return userAccount;
    }
}
