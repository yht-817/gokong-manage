package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.InternationalCode;
import cn.gokong.www.gokongmain.vo.login.CountryMobilePrefixOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
public interface InternationalCodeService extends IService<InternationalCode> {

    List<CountryMobilePrefixOutput> getMobilePrefixs();
}
