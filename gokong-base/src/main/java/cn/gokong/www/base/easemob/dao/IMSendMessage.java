package cn.gokong.www.base.easemob.dao;

import cn.gokong.www.base.easemob.api.impl.EasemobSendMessage;
import com.google.gson.GsonBuilder;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.UserName;

import java.util.HashMap;
import java.util.Map;

public class IMSendMessage {
    private static EasemobSendMessage easemobSendMessage = new EasemobSendMessage();

    /**
     * 发送文本消息
     * @param msgContext 消息内容，参考[[start:100serverintegration:30chatlog|聊天记录]]里的bodies内容
     * @param targetType users 给用户发消息。chatgroups: 给群发消息，chatrooms: 给聊天室发消息
     * @param from 表示消息发送者。无此字段Server会默认设置为"from":"admin"，有from字段但值为空串("")时请求失败
     * @param to 接收者 多个接收者用","号分隔 如u1,u2,u3
     * @param ext 扩展字段
     */
    public static void sendText(String msgContext,String targetType,String from,String to,Map<String,Object> ext) {
        Msg msg = new Msg();
        MsgContent msgContent = new MsgContent();
        msgContent.type(MsgContent.TypeEnum.TXT).msg(msgContext);
        UserName userName = new UserName();
        String[] arrUserName = to.split(",");
        for (int i = 0;i < arrUserName.length;i++){
            userName.add(arrUserName[i]);
        }
        msg.from(from).target(userName).targetType(targetType).msg(msgContent).ext(ext);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    public static void main(String[] args) {
        IMSendMessage IMSendMessage = new IMSendMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("type",1);
        IMSendMessage.sendText("lkjlijljlij","users","10000","yh153715119880156650",map);
    }
}
