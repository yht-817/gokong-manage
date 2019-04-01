package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.UserLog;
import cn.gokong.www.gokongmain.dao.UserLogMapper;
import cn.gokong.www.gokongmain.service.UserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tom
 * @since 2019-01-18
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

    public int querySeven(String userno, String seven) {
        return this.baseMapper.querySeven(userno,seven);
    }
}
