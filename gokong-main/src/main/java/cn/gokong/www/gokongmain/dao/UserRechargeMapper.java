package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserRecharge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * <p>
 * 用户充值记录表 Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-18
 */
public interface UserRechargeMapper extends BaseMapper<UserRecharge> {

    @Select("UPDATE user_recharge SET recharge_state = '" + 10130001 + "' where pay_bill_no = #{payNillNo}")
    int updateUserRecharge(@Param("payNillNo") String payNillNo);

    @Select("SELECT * FROM user_recharge where pay_bill_no = #{wxorderno}")
    UserRecharge findRecharge(String wxorderno);

    /**
     * 修改支付状态
     *
     * @param wxorderno
     * @return
     */
    @Update("UPDATE user_pay_detail SET pay_state = 1 WHERE pay_no = #{wxorderno}")
    int updatePayS(@Param("wxorderno") String wxorderno);

    @Select("SELECT user_no FROM user_pay_detail WHERE pay_no = #{wxorderno}")
    Map<String, String> queryCattleInfo(@Param("wxorderno") String wxorderno);
}
