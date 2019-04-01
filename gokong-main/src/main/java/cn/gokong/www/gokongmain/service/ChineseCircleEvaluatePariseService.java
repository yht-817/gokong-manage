package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.ChineseCircleEvaluateParise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 华人圈评论点赞
 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
public interface ChineseCircleEvaluatePariseService extends IService<ChineseCircleEvaluateParise> {

    /**
     * 设置评论点赞
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param liked         是否点赞
     * @return
     */
    boolean setEvaluateLike(String evaluateNo, String userNo, boolean liked);

    /**
     * 根据用户编码和评论编码查询点赞信息
     * @param userNo        用户编码
     * @param evaluateNo    评论编码
     * @return
     */
    ChineseCircleEvaluateParise findByUserNoAndEvaluateNo(String userNo,String evaluateNo);
}
