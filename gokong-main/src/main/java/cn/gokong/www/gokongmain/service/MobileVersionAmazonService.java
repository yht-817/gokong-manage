package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.MobileVersionAmazon;
import cn.gokong.www.gokongmain.vo.mobile_version.QueryMobileVersionOutput;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP版本跟新控制 服务类
 * </p>
 *
 * @author tom
 * @since 2018-11-20
 */
public interface MobileVersionAmazonService extends IService<MobileVersionAmazon> {

    QueryMobileVersionOutput queryMobileVersion(String v) throws Exception;
}
