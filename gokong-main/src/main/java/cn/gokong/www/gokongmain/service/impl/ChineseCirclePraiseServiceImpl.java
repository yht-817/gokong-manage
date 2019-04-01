package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.ChineseCirclePraiseMapper;
import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.domain.ChineseCircle;
import cn.gokong.www.gokongmain.domain.ChineseCirclePraise;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.ChatServiceMessageService;
import cn.gokong.www.gokongmain.service.ChineseCirclePraiseService;
import cn.gokong.www.gokongmain.service.ChineseCircleService;
import cn.gokong.www.gokongmain.service.UserInfoService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 华人圈点赞表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@Service
public class ChineseCirclePraiseServiceImpl extends ServiceImpl<ChineseCirclePraiseMapper, ChineseCirclePraise> implements ChineseCirclePraiseService {

    @Autowired
    ChineseCircleService chineseCircleService;

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    @Autowired
    UserInfoService userInfoService;

    /**
     * 设置华人圈点赞
     *
     * @param userNo   用户编码
     * @param circleNo 华人圈编码
     * @param like
     * @return
     */
    @Override
    @Transactional
    public boolean setCircleLike(String userNo, String circleNo, boolean like) {
        ChineseCircle chineseCircle = chineseCircleService.findByCircleNo(circleNo);

        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        ChineseCirclePraise chineseCirclePraise = baseMapper.findByUserNoAndCircleNo(userNo,circleNo);
        if (like){
            if (ObjectUtil.isNotNull(chineseCirclePraise)){
                throw new GrilException("已经点赞 无需再点赞");
            }
            chineseCirclePraise = new ChineseCirclePraise();
            chineseCirclePraise.setId(DataBaseTool.createId());
            chineseCirclePraise.setUserNo(userNo);
            chineseCirclePraise.setCircleNo(circleNo);
            chineseCirclePraise.setCreateTime(DataBaseTool.createDate());
            boolean save = save(chineseCirclePraise);
            if (!save){
                throw new GrilException("点赞失败");
            }
            chineseCircle.setLikeNum(chineseCircle.getLikeNum()+1);

            if (!userNo.equals(chineseCircle.getUserNo())){
                // 发送消息提醒
                ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
                chatServiceMessage.setSendHead(userInfo.getUserHead());
                chatServiceMessage.setSendTitle(userInfo.getNickName() + " 赞了你的华人圈");
                chatServiceMessage.setSendText(chineseCircle.getInstructions());
                chatServiceMessage.setUserNo(chineseCircle.getUserNo());
                chatServiceMessage.setNoticeType(SysCodeEnum.CODE_10390005.getCode());
                chatServiceMessage.setSendUrl("http://h5.gokong.cn/v2/components/circle.html?circleNo="+chineseCircle.getCircleNo()+"&userNo="+userInfo.getUserNo()+"&system=true");
                chatServiceMessageService.save(chatServiceMessage);
            }
        }else {
            if (ObjectUtil.isNull(chineseCirclePraise)){
                throw new GrilException("你未点赞 无需取消点赞");
            }
            boolean removeById = removeById(chineseCirclePraise.getId());
            if (!removeById){
                throw new GrilException("取消点赞失败");
            }
            chineseCircle.setLikeNum(chineseCircle.getLikeNum()-1);
        }
        chineseCircleService.saveOrUpdate(chineseCircle);
        return true;
    }

    /**
     * 是否点赞
     *
     * @param userNo   用户编码
     * @param circleNo 华人圈编码
     * @return
     */
    @Override
    public boolean isLike(String userNo, String circleNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no",userNo);
        wrapper.eq("circle_no",circleNo);
        ChineseCirclePraise chineseCirclePraise = getOne(wrapper);
        if (ObjectUtil.isNotNull(chineseCirclePraise)){
            return true;
        }
        return false;
    }
}
