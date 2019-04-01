package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserHomePage;
import cn.gokong.www.gokongmain.vo.user.QueryUserHomeOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * 用户主页 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface UserHomePageMapper extends BaseMapper<UserHomePage> {

    /**
     * 查询个人主页信息
     *
     * @param userNo 用户编码
     * @return
     */
    @Select("select * from user_home_page where user_no = #{userNo}")
    QueryUserHomeOutput findByUserNo(@Param("userNo") String userNo);

    /**
     * 根据用户编码动态修改字段内容
     *
     * @param userNo    用户编码
     * @param fields    字段名称
     * @param homePhoto 字段内容
     * @return
     */
    int updateByUserNoFieldVal(@Param("userNo") String userNo, @Param("fields") String fields, @Param("homePhoto") String homePhoto);

    @Select("SELECT home_photo1,home_photo2,home_photo3,home_photo4,home_photo5,home_photo6,home_photo7,home_photo8,home_photo9 FROM user_home_page WHERE user_no = #{userNo}")
    Map<String, String> findImgIndex(@Param("userNo") String userNo);


}
