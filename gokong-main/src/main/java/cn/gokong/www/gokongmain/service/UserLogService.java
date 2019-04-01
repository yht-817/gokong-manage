package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tom
 * @since 2019-01-18
 */
public interface UserLogService extends IService<UserLog> {

    int querySeven(String userno, String seven);
}
