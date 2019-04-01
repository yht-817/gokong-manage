package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.CardNumberCheck;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.PayApplyMapper;
import cn.gokong.www.gokongmain.domain.PayApply;
import cn.gokong.www.gokongmain.service.PayApplyService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
@Service
public class PayApplyServiceImpl extends ServiceImpl<PayApplyMapper, PayApply> implements PayApplyService {

    /**
     * @param userNo      用户编码
     * @param userName    用户姓名
     * @param userPhone   用户电话号码
     * @param userAddres  用户开户地址
     * @param userAccount 用户银行卡号
     * @param bankName    银行名
     * @return
     */
    public ResponseData addBindingBank(String userNo, String userName, String userPhone, String userAddres, String userAccount, String bankName) {
        StaticLog.info("------" + userAddres);
        ResponseData responseData = new ResponseData();
        boolean cartf = CardNumberCheck.checkBankCard(userAccount);
        if (cartf) {
            // 1.0 查看用户是否存在当前的账号
            int exits = this.baseMapper.findExtis(userNo);
            if (exits == 0) {
                int addinfo = this.baseMapper.addBackNo(DataBaseTool.createId(), userNo, userName, userPhone, userAddres, userAccount, bankName);
                if (addinfo > 0) {
                    responseData.setCode(200);
                    responseData.setData("银行卡信息添加成功");
                    return responseData;
                }
                responseData.setData("银行卡信息添加失败");
                responseData.setCode(500);
                return responseData;
            }
            responseData.setData("银行卡信息已存在");
            responseData.setCode(500);
            return responseData;
        } else {
            responseData.setData("添加正确的银行卡号！");
            responseData.setCode(500);
            return responseData;
        }
    }

    /**
     * 删除用户的银行信息
     *
     * @param userNo      用户编码
     * @param userAccount 用户卡号
     * @return
     */
    public ResponseData removeAccount(String userNo, String userAccount) {
        ResponseData responseData = new ResponseData();
        if (ObjectUtil.isNotNull(userNo)) {
            // 查询当前账号是否还有没有提现操作
            int exist = this.baseMapper.existInfo(userNo);
            if (exist == 0) {
                // 删除当前的信息
                int de = this.baseMapper.deleteInfo(userNo);
                if (de > 0) {
                    responseData.setData("删除信息成功");
                    responseData.setCode(200);
                    return responseData;
                } else {
                    responseData.setData("删除信息失败");
                    responseData.setCode(500);
                    return responseData;
                }
            } else {
                responseData.setData("当前还有订单未处理");
                responseData.setCode(500);
                return responseData;
            }
        }
        responseData.setData("用户参数错误");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 进行提现
     *
     * @param userNo      用户编码
     * @param applyAmount 提现金额
     * @param userAccount 用户银行卡号
     * @return
     */
    public ResponseData addWithdrawalInfo(String userNo, BigDecimal applyAmount, String userAccount) {
        ResponseData responseData = new ResponseData();
        BigDecimal max = new BigDecimal(1000);
        // 单次金额不能大于1000
        if (applyAmount.compareTo(max) == -1) {
            // 求一周的交易次数不能超过2次
            int sum = this.baseMapper.findTransactionNumber(DataBaseTool.getMonday(), userNo);
            StaticLog.error("当前数据库里面的数据：" + sum);
            if (sum < 2) {
                // 查询当前用户的可以提现的金额
                BigDecimal account = this.baseMapper.findAccount(userNo);
                if (account.compareTo(applyAmount) == 1 || account.compareTo(applyAmount) == 0) { // 进行提现
                    // 进行数据提现申请下单
                    String id = DataBaseTool.createId();
                    String sqno = "SQTX" + id;
                    Date data = DataBaseTool.createDate();
                    String sqstates = "105210003";
                    // 加入提现日志 user_cash_account_log
                    String changType = "10330004";
                    String changeremark = "用户提现";
                    int logtx = this.baseMapper.addLog(id, userNo, changType, applyAmount, data, sqno, changeremark);
                    // 进行申请
                    if (logtx > 0) {
                        // 减去提现的现有金额
                        int subtraction = this.baseMapper.reductionAccount(userNo, applyAmount);
                        if (subtraction > 0) {
                            // 查询当前用户的信息
                            Map<String, String> usercarinfo = this.baseMapper.seeCarInfo(userNo);
                            try {
                                int sq = this.baseMapper.addTxInfo(id, userNo, data, applyAmount, sqno, sqstates, userAccount, usercarinfo.get("user_name"), usercarinfo.get("user_addres"), usercarinfo.get("user_phone"), usercarinfo.get("bank_name"));
                                if (sq > 0) { // 申请体现成功
                                    responseData.setData("正在提现中..");
                                    responseData.setCode(200);
                                    return responseData;
                                }
                            } catch (Exception e) {
                                StaticLog.error("申请提现失败" + e);
                            }
                            // 插入提现数据失败，返回减去的提现金额，删除日志信息
                            int addJe = this.baseMapper.addJe(userNo, applyAmount);
                            int delog = this.baseMapper.deleteLog(sqno);
                            if (addJe == 0 && delog == 0) {
                                StaticLog.error("提现订单处理失败后回滚失败，订单号：" + sqno);
                            }
                            responseData.setData("提现失败，请稍后重试!");
                            responseData.setCode(500);
                            return responseData;
                        }
                        responseData.setData("提现失败，请稍后重试!");
                        responseData.setCode(500);
                        return responseData;
                    }
                    responseData.setData("提现失败，请稍后重试!");
                    responseData.setCode(500);
                    return responseData;
                }
                responseData.setData("提现余额不足！");
                responseData.setCode(500);
                return responseData;
            }
            responseData.setData("您的提现次数超过规定次数！");
            responseData.setCode(500);
            return responseData;
        }
        responseData.setData("提现金额大于预定额度！");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 查询用户银行卡信息
     *
     * @param userNo
     * @return
     */
    public Map<String, String> seeCarNo(String userNo) {
        return this.baseMapper.seeCar(userNo);
    }

    /**
     * 查询用户的金额
     *
     * @param userNo
     * @return
     */
    public BigDecimal getMony(String userNo) {
        return this.baseMapper.getUserAmount(userNo);
    }

    /**
     * 查看用户是否存在
     *
     * @param userNo
     * @return
     */
    public boolean seeUserInfoR(String userNo) {
        int contuserinfo = this.baseMapper.findExtis(userNo);
        if (contuserinfo > 0) {
            return true;
        }
        return false;
    }
}
