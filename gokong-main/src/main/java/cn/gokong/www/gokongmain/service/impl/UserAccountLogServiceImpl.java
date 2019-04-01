package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.UserAccountLog;
import cn.gokong.www.gokongmain.dao.UserAccountLogMapper;
import cn.gokong.www.gokongmain.service.UserAccountLogService;
import cn.gokong.www.gokongmain.vo.user_account.AccountLogOutput;
import cn.gokong.www.gokongmain.vo.user_account.PageQueryAccountLogOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账户日志表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@Service
public class UserAccountLogServiceImpl extends ServiceImpl<UserAccountLogMapper, UserAccountLog> implements UserAccountLogService {


    /**
     * 分页查询用户蟠桃变动记录
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public PageQueryAccountLogOutput pageQueryAccountLogList(String userNo, Integer pageNo, Integer pageSize) {
        PageQueryAccountLogOutput pageQueryAccountLogOutput = new PageQueryAccountLogOutput();

        List<AccountLogOutput> accountLogOutputs = baseMapper.pageFindByUserNo(userNo, pageNo, pageSize);

        pageQueryAccountLogOutput.setAccountLogs(accountLogOutputs);

        //第一页的时候就会查询昨日增加蟠桃数量
        if (pageNo == 0) {
            //昨日增加蟠桃数
            List<UserAccountLog> userAccountLogs = baseMapper.queryYesterDayLog(userNo);
            int sum = userAccountLogs.stream().mapToInt(userAccountLog -> userAccountLog.getChangeAmount().intValue()).sum();
            pageQueryAccountLogOutput.setAmount(new BigDecimal(sum));
        }

        return pageQueryAccountLogOutput;
    }

    /**
     * 插入用户的金额变动日志
     *
     * @param changemode   变动方式 10050004
     * @param changeamount 变动金额
     *                     变动日期
     * @param changeno     变动单号
     * @param userno       对应用户
     * @param changeremark 变动说明
     * @return
     */
    public boolean inserlog(String changemode, BigDecimal changeamount, String changeno, String userno, String changeremark) {
        UserAccountLog userAccountLog = new UserAccountLog();
        userAccountLog.setId(DataBaseTool.createId());
        userAccountLog.setChangeAmount(changeamount);
        userAccountLog.setChangeDate(new Date());
        userAccountLog.setChangeMode(changemode);
        userAccountLog.setChangeRemark(changeremark);
        userAccountLog.setUserNo(userno);
        userAccountLog.setChangeNo(changeno);
        return save(userAccountLog);
    }

    /**
     * 查询支付日志
     *
     * @param order
     * @return
     */
    public Map<String, Object> deductMoney(String order) {
        return this.baseMapper.deductMoney(order);
    }
}
