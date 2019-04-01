package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.FansInfo;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 粉丝表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
public interface FansInfoMapper extends BaseMapper<FansInfo> {

    @Select("select * from fans_info where user_no = #{userNo} and fans_user_no = #{fansUserNo}")
    FansInfo findByUserNoAndFansUserNo(@Param("userNo")String userNo, @Param("fansUserNo")String fansUserNo);

    @Select("select ui.user_no,ui.user_head,ui.nick_name " +
            "from fans_info fi " +
            "left join user_info ui on fi.fans_user_no = ui.user_no " +
            "where fi.user_no = #{userNo} " +
            "limit #{pageNo},#{pageSize}")
    List<QueryUserOutput> pageQueryUserFocus(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    @Select("select * from fans_info where fans_user_no = #{userNo}")
    List<FansInfo> findByFansUserNo(@Param("userNo")String userNo);
}
