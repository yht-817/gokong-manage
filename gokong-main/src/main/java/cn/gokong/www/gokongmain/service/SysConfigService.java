package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 根据配置代码获取配置信息
     * @param configCode    配置代码
     * @return
     */
    SysConfig findBySysCode(String configCode);
}
