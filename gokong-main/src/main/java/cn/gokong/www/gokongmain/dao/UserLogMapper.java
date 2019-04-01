package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2019-01-18
 */
public interface UserLogMapper extends BaseMapper<UserLog> {

    @Select("SELECT COUNT(id) AS sumd FROM user_log WHERE user_no = #{userno} and user_login_date >= #{seven}")
    int querySeven(@Param("userno") String userno, @Param("seven") String seven);
}
