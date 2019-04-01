package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserHomePageVisit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户主页访问记录 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface UserHomePageVisitService extends IService<UserHomePageVisit> {

    /**
     * 查询用户主页访问量
     * @param userNo    用户编码
     * @param date      当前时间
     * @return
     */
    Map<String,Long> queryUserHomeVisit(String userNo, Date date);
}
