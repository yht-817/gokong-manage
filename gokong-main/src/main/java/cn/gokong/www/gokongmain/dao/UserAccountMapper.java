package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserAccount;
import cn.gokong.www.gokongmain.vo.user.QueryUserAccountOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    /**
     * 根据用户编码查询用户余额
     *
     * @param userNo 用户编码
     * @return
     */
    @Select("select (account_amount + cash_account_amount) as accountAmount from user_account where user_no = #{userNo}")
    QueryUserAccountOutput findByUserNo(@Param("userNo") String userNo);

    @Update("update user_account set account_amount = account_amount + #{amount} where user_no = #{userNo}")
    int addUserAccount(@Param("userNo") String userNo, @Param("amount") BigDecimal amount);

    @Update("update user_account set account_amount = account_amount - #{amount} where user_no = #{userNo}")
    int subtractUserAccount(@Param("userNo") String userNo, @Param("amount") BigDecimal amount);
}
