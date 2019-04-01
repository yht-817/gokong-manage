package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserPayDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2019-01-09
 */
public interface UserPayDetailMapper extends BaseMapper<UserPayDetail> {

    UserPayDetail selectOnes(@Param("payNo") String payNo);

    @Update("UPDATE user_pay_detail SET pay_state = 1 WHERE pay_no = #{wxorderno}")
    int updateS(@Param("wxorderno") String wxorderno);
}
