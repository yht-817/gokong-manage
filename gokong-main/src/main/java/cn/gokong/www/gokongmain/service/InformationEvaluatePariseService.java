package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.InformationEvaluateParise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 咨询信息评价点赞记录 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
public interface InformationEvaluatePariseService extends IService<InformationEvaluateParise> {

    /**
     * 设置评论点赞
     *
     * @param informationNo 资讯编码
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param liked         点赞标识
     * @return
     */
    boolean setEvaluateLike(String informationNo, String evaluateNo, String userNo, boolean liked);
}
