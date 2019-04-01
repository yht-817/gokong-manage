package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.PayApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
public interface PayApplyMapper extends BaseMapper<PayApply> {

    // 查询当前是否存在
    @Select("select count(user_no) from pay_meth where  user_no = #{userNo}")
    int findExtis(@Param("userNo") String userNo);

    // 添加用户的银行卡
    @Insert("INSERT INTO pay_meth (id,user_no,user_name,user_addres,user_phone,user_account,bank_name) VALUES (#{id},#{userNo},#{userName},#{userAddress},#{userPhone},#{userAccount},#{bankName})")
    int addBackNo(@Param("id") String id, @Param("userNo") String userNo, @Param("userName") String userName, @Param("userPhone") String userPhone, @Param("userAddress") String userAddres, @Param("userAccount") String userAccount, @Param("bankName") String bankName);

    // 查看当前用户是否还存在没有完成的订单
    @Select("select count(user_no) from  pay_apply where execution_stat = '105210003' and user_no = #{userNo}")
    int existInfo(@Param("userNo") String userNo);

    // 删除当前用户银行卡的信息
    @Delete("DELETE FROM pay_meth WHERE user_no = #{userNo}")
    int deleteInfo(@Param("userNo") String userNo);

    @Select("SELECT count(user_no) FROM pay_apply where change_date >= #{monday} AND user_no = #{userNo}")
    int findTransactionNumber(@Param("monday") String monday, @Param("userNo") String userNo);

    @Select("select account_amount from user_account where  user_no = #{userNo}")
    BigDecimal findAccount(@Param("userNo") String userNo);

    @Insert("INSERT INTO user_account_log (id,user_no,change_mode,change_amount,change_date,change_no,change_remark) VALUES (#{id},#{userNo},#{changType},#{amount},#{data},#{sqno},#{changeremark})")
    int addLog(@Param("id") String id, @Param("userNo") String userNo, @Param("changType") String changType, @Param("amount") BigDecimal applyAmount, @Param("data") Date data, @Param("sqno") String sqno, @Param("changeremark") String changeremark);

    @Update("UPDATE user_account SET account_amount = account_amount - #{amount} WHERE user_no = #{userNo}")
    int reductionAccount(@Param("userNo") String userNo, @Param("amount") BigDecimal applyAmount);

    @Select("select user_name,user_addres,user_phone,bank_name from pay_meth where user_no = #{userNo} LIMIT 1")
    Map<String, String> seeCarInfo(String userNo);

    // 插入数据
    @Insert("INSERT INTO pay_apply (id,user_no,apply_no,apply_amount,execution_stat,change_date,user_account,user_phone,user_name,user_addres,bank_name) " +
            "VALUES(#{id},#{userNo},#{sqno},#{amount},#{sqstates},#{data},#{userAccount},#{userPhone},#{userName},#{userAddres},#{bankName})")
    int addTxInfo(@Param("id") String id, @Param("userNo") String userNo, @Param("data") Date data, @Param("amount") BigDecimal applyAmount,
                  @Param("sqno") String sqno, @Param("sqstates") String sqstates, @Param("userAccount") String userAccount, @Param("userName") String userName,
                  @Param("userAddres") String userAddres, @Param("userPhone") String userPhone, @Param("bankName") String bankName);

    @Update("UPDATE user_account SET account_amount = account_amount + #{amount} WHERE user_no = #{userNo}")
    int addJe(@Param("userNo") String userNo, @Param("amount") BigDecimal applyAmount);

    @Delete("DELETE FROM user_account_log WHERE change_no = #{sqno}")
    int deleteLog(@Param("sqno") String sqno);

    @Select("select user_account,bank_name from pay_meth where  user_no = #{userNo}")
    Map<String, String> seeCar(@Param("userNo") String userNo);

    @Select("select account_amount from user_account where user_no = #{userNo}")
    BigDecimal getUserAmount(@Param("userNo") String userNo);
}
