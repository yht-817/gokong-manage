package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.MobileVersion;
import cn.gokong.www.gokongmain.vo.mobile_version.QueryMobileVersionOutput;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP版本跟新控制 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-26
 */
public interface MobileVersionService extends IService<MobileVersion> {

    /**
     * 获取APP版本信息
     * @param appName   APP名称
     * @param appType   APP类型
     * @param version   当前版本信息
     * @return
     */
    QueryMobileVersionOutput queryMobileVersion(String appName, int appType,String version) throws Exception;
}
