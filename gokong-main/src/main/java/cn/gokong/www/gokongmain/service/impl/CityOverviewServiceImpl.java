package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.CityOverview;
import cn.gokong.www.gokongmain.dao.CityOverviewMapper;
import cn.gokong.www.gokongmain.service.CityOverviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-10-11
 */
@Service
public class CityOverviewServiceImpl extends ServiceImpl<CityOverviewMapper, CityOverview> implements CityOverviewService {

    /**
     * 获取城市概览的列表
     *
     * @param cityname 城市名
     * @return
     */
    public Object queryCityOverview(String cityname) {
        ResponseData responseData = new ResponseData();
        Map<String, Object> map = new HashMap<>();
        map.put("city_name", cityname);
        List<CityOverview> cityOverviews = this.baseMapper.selectByMap(map);
        responseData.setCode(200);
        responseData.setMessage("加载成功");
        responseData.setData(cityOverviews);
        return responseData;
    }
}
