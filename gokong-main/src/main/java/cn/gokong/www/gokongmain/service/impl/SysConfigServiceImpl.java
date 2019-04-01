package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.SysConfig;
import cn.gokong.www.gokongmain.dao.SysConfigMapper;
import cn.gokong.www.gokongmain.service.SysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Override
    public SysConfig findBySysCode(String configCode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("config_code",configCode);
        return getOne(wrapper);
    }
}
