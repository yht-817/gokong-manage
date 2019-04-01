package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.MobileVersion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * APP版本跟新控制 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-26
 */
public interface MobileVersionMapper extends BaseMapper<MobileVersion> {

    @Select("select * " +
            "from mobile_version " +
            "where app_name = #{appName} " +
            "and app_type = #{appType} " +
            "and update_switch = '1' " +
            "order by app_version desc " +
            "limit 1")
    MobileVersion queryMobileVersion(@Param("appName")String appName, @Param("appType")int appType);
}
