package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UsedDealLeave;
import cn.gokong.www.gokongmain.vo.used_deal_leave.PageQueryListOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 二手交易留言 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
public interface UsedDealLeaveMapper extends BaseMapper<UsedDealLeave> {

    @Select("select fromUser.user_no as fromUserNo,fromUser.user_head as fromUserHead,fromUser.nick_name as fromUserNick,toUser.user_no as toUserNo,toUser.nick_name as toUserNick,udl.leave_no,udl.leave_content,udl.leave_time\n" +
            "from used_deal_leave udl\n" +
            "left join user_info fromUser on fromUser.user_no = udl.from_user_no\n" +
            "left join user_info toUser on toUser.user_no = udl.to_user_no\n" +
            "where udl.used_no = #{usedNo}\n" +
            "order by leave_time desc\n" +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryListOutput> pageQueryList(@Param("usedNo")String usedNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
