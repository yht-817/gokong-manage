package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.ChineseCircleEvaluateMapper;
import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.domain.ChineseCircle;
import cn.gokong.www.gokongmain.domain.ChineseCircleEvaluate;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.ChatServiceMessageService;
import cn.gokong.www.gokongmain.service.ChineseCircleEvaluateService;
import cn.gokong.www.gokongmain.service.ChineseCircleService;
import cn.gokong.www.gokongmain.service.UserInfoService;
import cn.gokong.www.gokongmain.vo.chinese_circle_evaluate.PageQueryCircleEvaluateOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 华人圈评论表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@Service
public class ChineseCircleEvaluateServiceImpl extends ServiceImpl<ChineseCircleEvaluateMapper, ChineseCircleEvaluate> implements ChineseCircleEvaluateService {

    @Autowired
    ChineseCircleService chineseCircleService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    /**
     * 分页查询华人圈评论列表
     *
     * @param circleNo 华人圈编码
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryCircleEvaluateOutput> pageQueryCircleEvaluate(String circleNo, String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryCircleEvaluate(circleNo, userNo, pageNo, pageSize);
    }

    @Override
    public boolean releaseEvaluate(String circleNo, String userNo, String evaluateContent) {

        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        ChineseCircleEvaluate chineseCircleEvaluate = new ChineseCircleEvaluate();
        chineseCircleEvaluate.setId(DataBaseTool.createId());
        chineseCircleEvaluate.setEvaluateNo(DataBaseTool.createNo("circle_eva_no"));
        chineseCircleEvaluate.setCircleNo(circleNo);
        chineseCircleEvaluate.setFromUserNo(userNo);
        chineseCircleEvaluate.setEvaluateContent(evaluateContent);
        chineseCircleEvaluate.setEvaluateDate(DataBaseTool.createDate());
        chineseCircleEvaluate.setPraiseNum(0);
        chineseCircleEvaluate.setReplyNum(0);
        boolean save = save(chineseCircleEvaluate);
        if (!save) {
            throw new GrilException("评论失败");
        }
        //添加评论数量
        ChineseCircle chineseCircle = chineseCircleService.findByCircleNo(circleNo);
        chineseCircle.setCommentsNum(chineseCircle.getCommentsNum() + 1);
        chineseCircleService.saveOrUpdate(chineseCircle);

        if (!userNo.equals(chineseCircle.getUserNo())){
            // 发送消息提醒
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setSendHead(userInfo.getUserHead());
            chatServiceMessage.setSendTitle(userInfo.getNickName() + " 评论了华人圈 "+chineseCircle.getInstructions());
            chatServiceMessage.setSendText(evaluateContent);
            chatServiceMessage.setUserNo(chineseCircle.getUserNo());
            chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390005.getCode());
            chatServiceMessage.setSendUrl("http://h5.gokong.cn/v2/components/circle.html?circleNo="+chineseCircle.getCircleNo()+"&userNo="+userInfo.getUserNo()+"&system=true");
            chatServiceMessageService.save(chatServiceMessage);
        }
        return true;
    }

    /**
     * 根据评论编码查询评论信息
     *
     * @param evaluateNo 评论编码
     * @return
     */
    @Override
    public ChineseCircleEvaluate findByEvaluateNo(String evaluateNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("evaluate_no",evaluateNo);
        ChineseCircleEvaluate circleEvaluate = getOne(wrapper);
        if (ObjectUtil.isNull(circleEvaluate)){
            throw new GrilException("未查询到相关评论信息");
        }
        return circleEvaluate;
    }

    /**
     * 回复评论
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param replyContent  回复内容
     * @return
     */
    @Override
    public boolean replyEvaluate(String evaluateNo, String userNo, String replyContent) {

        ChineseCircleEvaluate circleEvaluate = findByEvaluateNo(evaluateNo);

        ChineseCircleEvaluate chineseCircleEvaluate = new ChineseCircleEvaluate();
        chineseCircleEvaluate.setId(DataBaseTool.createId());
        chineseCircleEvaluate.setEvaluateNo(DataBaseTool.createNo("circle_eva_no"));
        chineseCircleEvaluate.setCircleNo(circleEvaluate.getCircleNo());
        chineseCircleEvaluate.setFromUserNo(userNo);
        chineseCircleEvaluate.setEvaluateContent(replyContent);
        chineseCircleEvaluate.setToUserNo(circleEvaluate.getFromUserNo());
        chineseCircleEvaluate.setEvaluateDate(DataBaseTool.createDate());
        chineseCircleEvaluate.setPraiseNum(0);
        chineseCircleEvaluate.setReplyNum(0);
        boolean save = save(chineseCircleEvaluate);
        if (!save) {
            throw new GrilException("评论失败");
        }
        //添加评论数量
        ChineseCircle chineseCircle = chineseCircleService.findByCircleNo(circleEvaluate.getCircleNo());
        chineseCircle.setCommentsNum(chineseCircle.getCommentsNum() + 1);
        chineseCircleService.saveOrUpdate(chineseCircle);

        //添加评论回复数
        ChineseCircleEvaluate byEvaluateNo = findByEvaluateNo(evaluateNo);
        byEvaluateNo.setReplyNum(byEvaluateNo.getReplyNum()+1);
        boolean saveOrUpdate = saveOrUpdate(byEvaluateNo);
        if (!saveOrUpdate){
            throw new GrilException("添加评论回复数失败");
        }
        return true;
    }
}
