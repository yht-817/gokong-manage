package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.ChineseCircleEvaluatePariseMapper;
import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.domain.ChineseCircleEvaluate;
import cn.gokong.www.gokongmain.domain.ChineseCircleEvaluateParise;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.ChatServiceMessageService;
import cn.gokong.www.gokongmain.service.ChineseCircleEvaluatePariseService;
import cn.gokong.www.gokongmain.service.ChineseCircleEvaluateService;
import cn.gokong.www.gokongmain.service.UserInfoService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 华人圈评论点赞
 * 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@Service
public class ChineseCircleEvaluatePariseServiceImpl extends ServiceImpl<ChineseCircleEvaluatePariseMapper, ChineseCircleEvaluateParise> implements ChineseCircleEvaluatePariseService {

    @Autowired
    ChineseCircleEvaluateService chineseCircleEvaluateService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    /**
     * 设置评论点赞
     *
     * @param evaluateNo 评论编码
     * @param userNo     用户编码
     * @param liked      是否点赞
     * @return
     */
    @Override
    public boolean setEvaluateLike(String evaluateNo, String userNo, boolean liked) {
        ChineseCircleEvaluate circleEvaluate = chineseCircleEvaluateService.findByEvaluateNo(evaluateNo);

        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        wrapper.eq("evaluate_no", evaluateNo);
        ChineseCircleEvaluateParise evaluateParise = getOne(wrapper);
        if (liked) {
            if (ObjectUtil.isNotNull(evaluateParise)) {
                throw new GrilException("已经点赞无需再点赞");
            }
            evaluateParise = new ChineseCircleEvaluateParise();
            evaluateParise.setId(DataBaseTool.createId());
            evaluateParise.setEvaluateNo(evaluateNo);
            evaluateParise.setUserNo(userNo);
            evaluateParise.setPariseDate(DataBaseTool.createDate());
            boolean save = save(evaluateParise);
            if (!save) {
                throw new GrilException("点赞失败");
            }

            //赋值评论点赞数量
            circleEvaluate.setPraiseNum(circleEvaluate.getPraiseNum()+1);

            if (!userNo.equals(circleEvaluate.getFromUserNo())){
                // 发送消息提醒
                ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
                chatServiceMessage.setSendHead(userInfo.getUserHead());
                chatServiceMessage.setSendTitle(userInfo.getNickName() + " 点赞了你的评论");
                chatServiceMessage.setSendText(circleEvaluate.getEvaluateContent());
                chatServiceMessage.setUserNo(circleEvaluate.getFromUserNo());
                chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390005.getCode());
                chatServiceMessage.setSendUrl("http://h5.gokong.cn/v2/components/commentList.html?circleNo="+circleEvaluate.getCircleNo()+"&userNo="+userNo+"&system=true");
                chatServiceMessageService.save(chatServiceMessage);
            }
        } else {
            if (ObjectUtil.isNull(evaluateParise)) {
                throw new GrilException("你未点赞无需取消点赞");
            }
            boolean removeById = removeById(evaluateParise.getId());
            if (!removeById) {
                throw new GrilException("取消点赞失败");
            }
            circleEvaluate.setPraiseNum(circleEvaluate.getPraiseNum()-1);
        }
        chineseCircleEvaluateService.saveOrUpdate(circleEvaluate);
        return true;
    }

    /**
     * 根据用户编码和评论编码查询点赞信息
     *
     * @param userNo     用户编码
     * @param evaluateNo 评论编码
     * @return
     */
    @Override
    public ChineseCircleEvaluateParise findByUserNoAndEvaluateNo(String userNo, String evaluateNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        wrapper.eq("evaluate_no", evaluateNo);
        ChineseCircleEvaluateParise evaluateParise = getOne(wrapper);
        if (ObjectUtil.isNull(evaluateNo)) {
            throw new GrilException("为查询到相关点赞信息");
        }
        return evaluateParise;
    }
}
