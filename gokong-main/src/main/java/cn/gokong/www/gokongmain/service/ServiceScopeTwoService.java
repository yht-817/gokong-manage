package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.ServiceScopeTwo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 二级服务范围 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-13
 */
public interface ServiceScopeTwoService extends IService<ServiceScopeTwo> {

    ResponseData getCityList();

    ResponseData findCity(String cityName);

    Object queryServiceCity();
}
