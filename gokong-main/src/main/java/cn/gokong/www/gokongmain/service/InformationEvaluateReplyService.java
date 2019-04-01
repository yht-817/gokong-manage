package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.InformationEvaluateReply;
import cn.gokong.www.gokongmain.vo.Information_evaluate.PageQueryInformationChildEvaluateOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 咨询信息评价回复记录表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
public interface InformationEvaluateReplyService extends IService<InformationEvaluateReply> {
    /**
     * 分页查询子评论
     *
     * @param evaluateNo 评论编码
     * @param userNo     用户编码
     * @param pageNo     开始位置
     * @param pageSize   检索长度
     * @return
     */
    List<PageQueryInformationChildEvaluateOutput> pageQueryInformationChildEvaluate(String evaluateNo, String userNo, Integer pageNo, Integer pageSize);

    /**
     * 回复评论
     * @param informationNo 资讯编码
     * @param evaluateNo    评论编码
     * @param fromUserNo    用户编码
     * @param replyContent  回复内容
     * @param toUserNo      被回复者编码
     * @return
     */
    boolean replyEvaluate(String informationNo, String evaluateNo, String fromUserNo, String replyContent, String toUserNo);


}
