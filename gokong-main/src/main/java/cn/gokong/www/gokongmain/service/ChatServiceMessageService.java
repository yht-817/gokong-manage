package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.vo.service_message.PageQueryServiceMessageOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务通知模板 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
public interface ChatServiceMessageService extends IService<ChatServiceMessage> {

    @Override
    boolean save(ChatServiceMessage chatServiceMessage);

    @Override
    boolean saveOrUpdate(ChatServiceMessage chatServiceMessage);

    /**
     * 根据服务编码查询消息信息
     * @param serviceNo  服务编码
     * @return
     */
    ChatServiceMessage findByServiceNo(String serviceNo);

    /**
     * 分页查询服务消息
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryServiceMessageOutput> pageQueryServiceMessage(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 发送消息
     * @param userNo    接受者用户编码
     * @param content   消息内容
     * @param msgType   消息类型
     * @return
     */
    boolean sendMsg(String userNo,String content,String msgType);

    int findByServiceNoAndChatMessage(String applyNo);
}
