package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.InformationEvaluate;
import cn.gokong.www.gokongmain.vo.Information_evaluate.PageQueryInformationEvaluateOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资讯信息表评价记录 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
public interface InformationEvaluateService extends IService<InformationEvaluate> {

    /**
     * 分页查询资讯评论
     * @param informationNo 资讯编码
     * @param userNo        用户编码
     * @param pageNo        开始位置
     * @param pageSize      检索长度
     * @return
     */
    List<PageQueryInformationEvaluateOutput> pageQueryInformationEvaluate(String informationNo, String userNo, Integer pageNo, Integer pageSize);

    /**
     * 查询一条资讯评论
     * @param evaluateNo    资讯评论
     * @param userNo        用户编码
     * @return
     */
    PageQueryInformationEvaluateOutput queryOneEvaluate(String evaluateNo, String userNo);


    /**
     * 发布评论
     * @param informationNo     资讯编码
     * @param userNo            用户编码
     * @param evaluateContent   评论内容
     * @return
     */
    boolean releaseEvaluate(String informationNo, String userNo, String evaluateContent);

    /**
     * 根据评论编码查询评论信息
     * @param evaluateNo    评论编码
     * @return
     */
    InformationEvaluate findByEvaluateNo(String evaluateNo);
}
