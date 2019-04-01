package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.easemob.dao.IMSendMessage;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.RelativeDateFormat;
import cn.gokong.www.gokongmain.dao.ChatServiceMessageMapper;
import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.service.ChatServiceMessageService;
import cn.gokong.www.gokongmain.service.FriendGroupUserService;
import cn.gokong.www.gokongmain.vo.service_message.PageQueryServiceMessageOutput;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务通知模板 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@Service
public class ChatServiceMessageServiceImpl extends ServiceImpl<ChatServiceMessageMapper, ChatServiceMessage> implements ChatServiceMessageService {

    @Autowired
    FriendGroupUserService friendGroupUserService;

    @Override
    public boolean save(ChatServiceMessage entity) {
        entity.setId(DataBaseTool.createId());
        entity.setSendDate(DataBaseTool.createDate());
        entity.setMsgState(SysCodeEnum.CODE_10200001.getCode());
        if (ObjectUtil.isNull(entity.getSendNo())) {
            entity.setSendNo(SysCodeEnum.SYS_SWK.getCode());
        }
        if (ObjectUtil.isNull(entity.getSendHead())) {
            entity.setSendHead("http://static.gokong.cn/img/defualt/userhead");
        }
        if (ObjectUtil.isNull(entity.getServiceNo())) {
            entity.setServiceNo(DataBaseTool.createNo("service_no"));
        }
        boolean save = super.save(entity);
        if (save) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "1");

            Map<String, String> rep = new HashMap<>();
            rep.put("sendText", entity.getSendText());
            rep.put("sendDate", RelativeDateFormat.format(entity.getSendDate()));

            IMSendMessage.sendText(JSONUtil.toJsonStr(rep), "users", SysCodeEnum.SYS_SWK.getCode(), entity.getUserNo(), map);
        }
        return save;
    }

    @Override
    public boolean saveOrUpdate(ChatServiceMessage entity) {
        boolean saveOrUpdate = super.saveOrUpdate(entity);
        if (saveOrUpdate) {
            IMSendMessage.sendText(JSONUtil.toJsonStr(entity), "users", SysCodeEnum.SYS_SWK.getCode(), entity.getUserNo(), null);
        }
        return saveOrUpdate;
    }

    /**
     * 根据服务编码查询消息信息
     *
     * @param serviceNo 服务编码
     * @return
     */
    @Override
    public ChatServiceMessage findByServiceNo(String serviceNo) {
        return baseMapper.findByServiceNo(serviceNo);
    }

    /**
     * 分页查询服务消息
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryServiceMessageOutput> pageQueryServiceMessage(String userNo, Integer pageNo, Integer pageSize) {
        //查询通知消息
        List<PageQueryServiceMessageOutput> pageQueryServiceMessageOutputs = baseMapper.pageQueryServiceMessage(userNo, pageNo, pageSize);
        //查询我加入的群
        pageQueryServiceMessageOutputs.forEach(pageQueryServiceMessageOutput -> {
            //如果是群申请通知（就查询已加入的群）
            StringBuffer groupNames = new StringBuffer();
            if (pageQueryServiceMessageOutput.getNoticeType().equals(SysCodeEnum.CODE_10390004.getCode())) {
                List<Map<String, String>> joinGroup = friendGroupUserService.queryJoinGroup(pageQueryServiceMessageOutput.getSendNo());
                joinGroup.forEach(map -> {
                    groupNames.append(map.get("group_name"));
                    groupNames.append("/");
                });
            }
            pageQueryServiceMessageOutput.setJoinGroup(StrUtil.removeSuffix(groupNames.toString(), "/"));
        });

        return pageQueryServiceMessageOutputs;
    }

    /**
     * 发送消息
     *
     * @param userNo  接受者用户编码
     * @param content 消息内容
     * @param msgType 消息类型
     * @return
     */
    @Override
    public boolean sendMsg(String userNo, String content, String msgType) {
        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setUserNo(userNo);
        chatServiceMessage.setSendText(content);
        chatServiceMessage.setNoticeType(msgType);
        return save(chatServiceMessage);
    }


    /**
     * 查询当前信息
     *
     * @param applyNo
     * @return
     */
    public int findByServiceNoAndChatMessage(String applyNo) {
        return baseMapper.findByServiceNoAndChatMessage(applyNo);
    }
}
