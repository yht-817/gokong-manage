package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserPayDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tom
 * @since 2019-01-09
 */
public interface UserPayDetailService extends IService<UserPayDetail> {

    UserPayDetail queryDataInfo(UserPayDetail userPayDetail);

    int addPayDetailLog(UserPayDetail userPayDetail);

    int updateOrderStates(String wxorderno);
}
