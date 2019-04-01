package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.InformationEvaluateReplyMapper;
import cn.gokong.www.gokongmain.domain.InformationEvaluate;
import cn.gokong.www.gokongmain.domain.InformationEvaluateReply;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.InformationEvaluateReplyService;
import cn.gokong.www.gokongmain.service.InformationEvaluateService;
import cn.gokong.www.gokongmain.vo.Information_evaluate.PageQueryInformationChildEvaluateOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 咨询信息评价回复记录表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@Service
public class InformationEvaluateReplyServiceImpl extends ServiceImpl<InformationEvaluateReplyMapper, InformationEvaluateReply> implements InformationEvaluateReplyService {

    @Autowired
    InformationEvaluateService informationEvaluateService;

    /**
     * 分页查询子评论
     *
     * @param evaluateNo 评论编码
     * @param userNo     用户编码
     * @param pageNo     开始位置
     * @param pageSize   检索长度
     * @return
     */
    @Override
    public List<PageQueryInformationChildEvaluateOutput> pageQueryInformationChildEvaluate(String evaluateNo, String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryInformationChildEvaluate(evaluateNo, pageNo, pageSize);
    }

    /**
     * 回复评论
     *
     * @param informationNo 资讯编码
     * @param evaluateNo    评论编码
     * @param fromUserNo    用户编码
     * @param replyContent  回复内容
     * @param toUserNo      被回复者编码
     * @return
     */
    @Override
    @Transactional
    public boolean replyEvaluate(String informationNo, String evaluateNo, String fromUserNo, String replyContent, String toUserNo) {
        InformationEvaluateReply informationEvaluateReply = new InformationEvaluateReply();
        informationEvaluateReply.setId(DataBaseTool.createId());
        informationEvaluateReply.setReplyNo(DataBaseTool.createNo("inf_eva_rep_no"));
        informationEvaluateReply.setEvaluateNo(evaluateNo);
        informationEvaluateReply.setInformationNo(informationNo);
        informationEvaluateReply.setFromUserNo(fromUserNo);
        informationEvaluateReply.setReplyContent(replyContent);
        informationEvaluateReply.setToUserNo(toUserNo);
        informationEvaluateReply.setReplyDate(DataBaseTool.createDate());
        boolean save = save(informationEvaluateReply);
        if (!save){
            throw new GrilException("回复评论失败");
        }
        //修改评论数量
        InformationEvaluate byEvaluateNo = informationEvaluateService.findByEvaluateNo(evaluateNo);
        byEvaluateNo.setReplyNum(byEvaluateNo.getReplyNum()+1);
        informationEvaluateService.saveOrUpdate(byEvaluateNo);
        return true;
    }
}
