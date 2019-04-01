package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserAccountLog;
import cn.gokong.www.gokongmain.vo.user_account.AccountLogOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账户日志表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface UserAccountLogMapper extends BaseMapper<UserAccountLog> {

    /**
     * 分页查询用户蟠桃变动记录
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select * " +
            "from user_account_log " +
            "where user_no = #{userNo} " +
            "order by change_date desc " +
            "limit #{pageNo},#{pageSize}")
    List<AccountLogOutput> pageFindByUserNo(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    @Select("select * from user_account_log where user_no = #{userNo} and TO_DAYS( NOW( ) ) - TO_DAYS(change_date) = 1")
    List<UserAccountLog> queryYesterDayLog(@Param("userNo")String userNo);

    @Select("select * from user_account_log where change_no = #{order}")
    Map<String, Object> deductMoney(@Param("order") String order);
}
