package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.MerchantsEvaluate;
import cn.gokong.www.gokongmain.vo.merchants_evaluate.PageQueryListOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商户评价表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
public interface MerchantsEvaluateService extends IService<MerchantsEvaluate> {

    /**
     * 发布商户评价
     * @param userNo            用户编码
     * @param merchantsNo       商户编码
     * @param evaluateContent   评价内容
     * @param evaluateScore     评分
     * @return
     */
    boolean releaseMerchantsEvaluate(String userNo, String merchantsNo, String evaluateContent, int evaluateScore);

    /**
     * 分页查询评论列表
     * @param merchantsNo   商户编码
     * @param pageNo        开始位置
     * @param pageSize      检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(String merchantsNo, Integer pageNo, Integer pageSize);
}
