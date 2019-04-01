package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.CityOverview;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2018-10-11
 */
public interface CityOverviewService extends IService<CityOverview> {

    Object queryCityOverview(String cityname);
}
