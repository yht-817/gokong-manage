package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.MobileVersion;
import cn.gokong.www.gokongmain.domain.MobileVersionAmazon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * APP版本跟新控制 Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-11-20
 */
public interface MobileVersionAmazonMapper extends BaseMapper<MobileVersionAmazon> {

    @Select("select * " +
            "from mobile_version_amazon " +
            "where update_switch = '1' " +
            "order by app_version desc " +
            "limit 1")
    MobileVersion queryMobileVersion();
}
