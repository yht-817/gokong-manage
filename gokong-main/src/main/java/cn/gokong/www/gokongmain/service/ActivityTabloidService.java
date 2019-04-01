package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.ActivityTabloid;
import cn.gokong.www.gokongmain.vo.local_activity.QueryActivityTabloidOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 活动小报 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-22
 */
public interface ActivityTabloidService extends IService<ActivityTabloid> {

    /**
     * 查询活动小报
     * @param cityName  所在城市
     * @return
     */
    List<QueryActivityTabloidOutput> queryActivityTabloid(String cityName);

    @Override
    boolean save(ActivityTabloid activityTabloid);
}
