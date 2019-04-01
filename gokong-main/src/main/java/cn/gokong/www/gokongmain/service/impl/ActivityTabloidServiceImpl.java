package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.ActivityTabloid;
import cn.gokong.www.gokongmain.dao.ActivityTabloidMapper;
import cn.gokong.www.gokongmain.service.ActivityTabloidService;
import cn.gokong.www.gokongmain.vo.local_activity.QueryActivityTabloidOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 活动小报 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-22
 */
@Service
public class ActivityTabloidServiceImpl extends ServiceImpl<ActivityTabloidMapper, ActivityTabloid> implements ActivityTabloidService {

    /**
     * 查询活动小报
     * @param cityName  所在城市
     * @return
     */
    @Override
    public List<QueryActivityTabloidOutput> queryActivityTabloid(String cityName) {
        return baseMapper.queryActivityTabloid(cityName,DataBaseTool.createDate());
    }

    @Override
    public boolean save(ActivityTabloid entity) {
        entity.setId(DataBaseTool.createId());
        return super.save(entity);
    }
}
