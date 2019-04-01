package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.ChineseCircleEvaluate;
import cn.gokong.www.gokongmain.vo.chinese_circle_evaluate.PageQueryCircleEvaluateOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 华人圈评论表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
public interface ChineseCircleEvaluateService extends IService<ChineseCircleEvaluate> {

    /**
     * 分页查询华人圈评论列表
     * @param circleNo  华人圈编码
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryCircleEvaluateOutput> pageQueryCircleEvaluate(String circleNo, String userNo, Integer pageNo, Integer pageSize);

    /**
     * 发布评论
     * @param circleNo          华人圈编码
     * @param userNo            用户编码
     * @param evaluateContent   评论内容
     * @return
     */
    boolean releaseEvaluate(String circleNo, String userNo, String evaluateContent);

    /**
     * 根据评论编码查询评论信息
     * @param evaluateNo    评论编码
     * @return
     */
    ChineseCircleEvaluate findByEvaluateNo(String evaluateNo);

    /**
     * 回复评论
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param replyContent  回复内容
     * @return
     */
    boolean replyEvaluate(String evaluateNo, String userNo, String replyContent);
}
