package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ShopCity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-16
 */
public interface ShopCityMapper extends BaseMapper<ShopCity> {

    @Select("select sc.currency " +
            "from shop_city sc " +
            "where sc.city_name = (select scope_one_name from service_scope_one where scope_one_no = (select scope_one_no from service_scope_two where scope_two_name = #{cityName}))")
    String queryCurrencySymbol(@Param("cityName")String cityName);
}
