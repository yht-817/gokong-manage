package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.InformationEvaluateMapper;
import cn.gokong.www.gokongmain.domain.Information;
import cn.gokong.www.gokongmain.domain.InformationEvaluate;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.InformationEvaluateService;
import cn.gokong.www.gokongmain.service.InformationService;
import cn.gokong.www.gokongmain.vo.Information_evaluate.PageQueryInformationEvaluateOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯信息表评价记录 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@Service
public class InformationEvaluateServiceImpl extends ServiceImpl<InformationEvaluateMapper, InformationEvaluate> implements InformationEvaluateService {

    @Autowired
    InformationService informationService;

    /**
     * 分页查询资讯评论
     *
     * @param informationNo 资讯编码
     * @param userNo        用户编码
     * @param pageNo        开始位置
     * @param pageSize      检索长度
     * @return
     */
    @Override
    public List<PageQueryInformationEvaluateOutput> pageQueryInformationEvaluate(String informationNo, String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryInformationEvaluate(informationNo, userNo, pageNo, pageSize);
    }

    /**
     * 查询一条资讯评论
     *
     * @param evaluateNo 资讯编码
     * @param userNo     用户编码
     * @return
     */
    @Override
    public PageQueryInformationEvaluateOutput queryOneEvaluate(String evaluateNo, String userNo) {
        return baseMapper.queryInformationEvaluate(evaluateNo, userNo);
    }

    /**
     * 发布评论
     *
     * @param informationNo   资讯编码
     * @param userNo          用户编码
     * @param evaluateContent 评论内容
     * @return
     */
    @Override
    public boolean releaseEvaluate(String informationNo, String userNo, String evaluateContent) {
        InformationEvaluate informationEvaluate = new InformationEvaluate();
        informationEvaluate.setId(DataBaseTool.createId());
        informationEvaluate.setEvaluateNo(DataBaseTool.createNo("inf_eva_no"));
        informationEvaluate.setInformationNo(informationNo);
        informationEvaluate.setUserNo(userNo);
        informationEvaluate.setEvaluateContent(evaluateContent);
        informationEvaluate.setEvaluateDate(DataBaseTool.createDate());
        informationEvaluate.setPraiseNum(0);
        informationEvaluate.setReplyNum(0);
        boolean save = save(informationEvaluate);
        if (!save) {
            throw new GrilException("评论失败");
        }
        //添加资讯评论数量
        Information byInformationNo = informationService.findByInformationNo(informationNo);
        byInformationNo.setEvaluateNum(byInformationNo.getEvaluateNum()+1);
        informationService.saveOrUpdate(byInformationNo);
        return true;
    }

    @Override
    public InformationEvaluate findByEvaluateNo(String evaluateNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("evaluate_no", evaluateNo);
        InformationEvaluate informationEvaluate = getOne(wrapper);
        if (ObjectUtil.isNull(informationEvaluate)) {
            throw new GrilException("评论信息不存在");
        }
        return informationEvaluate;
    }
}
