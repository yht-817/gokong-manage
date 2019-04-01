package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ServiceCountry;
import cn.gokong.www.gokongmain.domain.ServiceScopeTwo;
import cn.gokong.www.gokongmain.vo.city.ServiceScopeOne;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二级服务范围 Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-13
 */
public interface ServiceScopeTwoMapper extends BaseMapper<ServiceScopeTwo> {

    List<ServiceScopeOne> findForeignCity();

    List<ServiceScopeTwo> findInlandCity();

    List<Map<String, String>> findCity(@Param(value = "cityname") String cityName);

    List<ServiceCountry> findCountry();
}
