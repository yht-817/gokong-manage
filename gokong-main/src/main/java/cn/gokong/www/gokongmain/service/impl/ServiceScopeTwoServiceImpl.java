package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.dao.ServiceScopeTwoMapper;
import cn.gokong.www.gokongmain.domain.ServiceCountry;
import cn.gokong.www.gokongmain.domain.ServiceScopeTwo;
import cn.gokong.www.gokongmain.service.ServiceScopeTwoService;
import cn.gokong.www.gokongmain.vo.city.ServiceScopeOne;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二级服务范围 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-13
 */
@Service
public class ServiceScopeTwoServiceImpl extends ServiceImpl<ServiceScopeTwoMapper, ServiceScopeTwo> implements ServiceScopeTwoService {

    /**
     * 首页获取城市列表
     *
     * @return
     */
    public ResponseData getCityList() {
        ResponseData responseData = new ResponseData();
        // 获取国外的城市名
        List<ServiceScopeOne> foreigncity = this.baseMapper.findForeignCity();
        // 获取国内的城市名
        List<ServiceScopeTwo> inlandcity = this.baseMapper.findInlandCity();
        Map<String, Object> data = new HashMap<>();
        data.put("foreigncity", foreigncity);
        data.put("inlandcity", inlandcity);
        responseData.setData(data);
        responseData.setMessage("加载完成");
        responseData.setCode(200);
        return responseData;
    }

    /**
     * 用户根据城市进行搜索
     *
     * @param cityName 城市名称
     * @return
     */
    public ResponseData findCity(String cityName) {
        StaticLog.info("城市名：" + cityName);
        ResponseData responseData = new ResponseData();
        List<Map<String, String>> city = this.baseMapper.findCity(cityName);
        StaticLog.error(city.toString());
        responseData.setMessage("数据返回成功");
        responseData.setCode(200);
        responseData.setData(city);
        return responseData;
    }

    /**
     * 牛人申请选择城市
     *
     * @return
     */
    public Object queryServiceCity() {
        List<ServiceCountry> serviceCountryList = this.baseMapper.findCountry();
        return serviceCountryList;
    }
}
