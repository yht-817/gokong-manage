package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.ShopCity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-19
 */
public interface ShopCityService extends IService<ShopCity> {

    /**
     * 根据城市编码获取国家金钱符号
     * @param cityName  城市名称
     * @return
     */
    String queryCurrencySymbol(String cityName);
}
