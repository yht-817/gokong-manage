package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.UserHomePageVisit;
import cn.gokong.www.gokongmain.dao.UserHomePageVisitMapper;
import cn.gokong.www.gokongmain.service.UserHomePageVisitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户主页访问记录 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class UserHomePageVisitServiceImpl extends ServiceImpl<UserHomePageVisitMapper, UserHomePageVisit> implements UserHomePageVisitService {

    /**
     * 查询个人主页今日访问量
     * @param userNo    用户编码
     * @param date      当前时间
     * @return
     */
    @Override
    public Map<String, Long> queryUserHomeVisit(String userNo, Date date) {
        return baseMapper.queryUserHomeVisit(userNo,date);
    }
}
