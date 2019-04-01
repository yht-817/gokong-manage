package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.OverseasBusinessApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 海外创业申请 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-25
 */
public interface OverseasBusinessApplyService extends IService<OverseasBusinessApply> {

    @Override
    boolean save(OverseasBusinessApply overseasBusinessApply);

    /**
     * 加入海外创业
     * @param userNo        用户编码
     * @param businessNo    创业编码
     * @param name          用户姓名
     * @param phone         用户电话
     * @param city          加盟城市
     * @return
     */
    boolean join(String userNo, String businessNo, String name, String phone, String city);

    /**
     * 判断用户是否加入这个海外创业
     * @param userNo        用户编码
     * @param businessNo    创业编码
     */
    boolean isJoin(String userNo,String businessNo);
}
