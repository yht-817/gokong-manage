package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.Merchants;
import cn.gokong.www.gokongmain.domain.MerchantsEvaluate;
import cn.gokong.www.gokongmain.dao.MerchantsEvaluateMapper;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.MerchantsEvaluateService;
import cn.gokong.www.gokongmain.service.MerchantsService;
import cn.gokong.www.gokongmain.vo.merchants_evaluate.PageQueryListOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商户评价表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@Service
public class MerchantsEvaluateServiceImpl extends ServiceImpl<MerchantsEvaluateMapper, MerchantsEvaluate> implements MerchantsEvaluateService {

    @Autowired
    MerchantsService merchantsService;

    /**
     * 发布商户评价
     *
     * @param userNo          用户编码
     * @param merchantsNo     商户编码
     * @param evaluateContent 评价内容
     * @param evaluateScore   评分
     * @return
     */
    @Override
    @Transactional
    public boolean releaseMerchantsEvaluate(String userNo, String merchantsNo, String evaluateContent, int evaluateScore) {
        MerchantsEvaluate merchantsEvaluate = new MerchantsEvaluate();
        merchantsEvaluate.setId(DataBaseTool.createId());
        merchantsEvaluate.setUserNo(userNo);
        merchantsEvaluate.setMerchantsNo(merchantsNo);
        merchantsEvaluate.setEvaluateContent(evaluateContent);
        merchantsEvaluate.setEvaluateScore(evaluateScore);
        merchantsEvaluate.setEvaluateTime(DataBaseTool.createDate());

        boolean save = save(merchantsEvaluate);
        if (!save) {
            throw new GrilException("发布商户评价失败");
        }

        Merchants merchants = merchantsService.findByMerchantsNo(merchantsNo);

        if (ObjectUtil.isNull(merchants)){
            throw new GrilException("发布商户评价 未查询到商户信息");
        }
        merchants.setScoreTotal(merchants.getScoreTotal()+evaluateScore);
        merchants.setEvaluateNum(merchants.getEvaluateNum()+1);

        boolean saveOrUpdate = merchantsService.saveOrUpdate(merchants);
        if (!saveOrUpdate){
            throw new GrilException("更新商户评论数量失败");
        }
        return true;
    }

    /**
     * 分页查询评论列表
     *
     * @param merchantsNo 商户编码
     * @param pageNo      开始位置
     * @param pageSize    检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryList(String merchantsNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryList(merchantsNo,pageNo,pageSize);
    }
}
