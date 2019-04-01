package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.ShopCity;
import cn.gokong.www.gokongmain.dao.ShopCityMapper;
import cn.gokong.www.gokongmain.service.ShopCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-19
 */
@Service
public class ShopCityServiceImpl extends ServiceImpl<ShopCityMapper, ShopCity> implements ShopCityService {

    @Override
    public String queryCurrencySymbol(String cityName) {
        return baseMapper.queryCurrencySymbol(cityName);
    }
}
