package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserAccountLog;
import cn.gokong.www.gokongmain.vo.user_account.PageQueryAccountLogOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户账户日志表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface UserAccountLogService extends IService<UserAccountLog> {

    /**
     * 分页查询蟠桃变动记录
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索位置
     * @return
     */
    PageQueryAccountLogOutput pageQueryAccountLogList(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 蟠桃的变动日志
     *
     * @param changemode   变动方式 10050004
     * @param changeamount 变动金额
     * @param changeno     变动单号
     * @param userno       对应用户
     * @param changeremark 变动说明
     */
    boolean inserlog(String changemode, BigDecimal changeamount, String changeno, String userno, String changeremark);

    /**
     * 根据订单查询
     *
     * @param order
     * @return
     */
    Map<String, Object> deductMoney(String order);
}
