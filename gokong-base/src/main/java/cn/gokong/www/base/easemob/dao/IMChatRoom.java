package cn.gokong.www.base.easemob.dao;

import cn.gokong.www.base.easemob.api.impl.EasemobChatRoom;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.client.model.Chatroom;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/7.
 */
public class IMChatRoom {
    private static EasemobChatRoom easemobChatRoom = new EasemobChatRoom();

    /**
     * 创建聊天室
     * @param name
     * @param description
     * @param owner
     */
    public static String createChatRoom(String name,String description,String owner) {
        Chatroom chatroom = new Chatroom();
        chatroom.name(name).description(description).maxusers(10000).owner(owner);
        Object result = easemobChatRoom.createChatRoom(chatroom);
        if (result!=null){
            JSONObject jsonObject = new JSONObject(result);
            Object object1 = jsonObject.get("data");
            if (object1!=null){
                JSONObject jsonObject1 = new JSONObject(object1);
                return String.valueOf(jsonObject1.get("id"));
            }
        }
        return "";
    }

    /**
     * 添加聊天室成员(单个)
     * @param roomId
     * @param userName
     */
    public static boolean addSingleUserToChatRoom(String roomId,String userName){
        Object result = easemobChatRoom.addSingleUserToChatRoom(roomId,userName);
        if (result!=null){
            JSONObject jsonObject = new JSONObject(result);
            Object object1 = jsonObject.get("data");
            if (object1!=null){
                JSONObject jsonObject1 = new JSONObject(object1);
                return (Boolean)jsonObject1.get("result");
            }
        }
        return false;
    }

    /**
     * 获取聊天室详情
     * @param roomId
     * @return
     */
    public static Object getChatRoomDetail(String roomId){
        Object object = easemobChatRoom.getChatRoomDetail(roomId);
        return object;
    }

    /**
     * 获取聊天室人数
     * @param roomId
     * @return
     */
    public static Integer getRoomPersonNum(String roomId){
        Object object = easemobChatRoom.getChatRoomDetail(roomId);
        if (object!=null){
            JSONObject jsonObject = new JSONObject(object);
            Object object1 = jsonObject.get("data");
            if (object1!=null){
                JSONArray array = new JSONArray(jsonObject.get("data"));
                List<Map> list = JSONUtil.toList(array,Map.class);
                return Integer.parseInt(String.valueOf(list.get(0).get("affiliations_count")));
            }
        }
        return 0;

    }

    /**
     * 聊天室成员删除[单个]
     * @param roomId
     * @param userName
     */
    public static boolean removeSingleUserFromChatRoom(String roomId,String userName){
        Object result = easemobChatRoom.removeSingleUserFromChatRoom(roomId,userName);
        if (result!=null){
            JSONObject jsonObject = new JSONObject(result);
            Object object1 = jsonObject.get("data");
            if (object1!=null){
                JSONObject jsonObject1 = new JSONObject(jsonObject.get("data"));
                return (Boolean) jsonObject1.get("result");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(IMChatRoom.addSingleUserToChatRoom("36114637324290","14"));
        //System.out.println(IMChatRoom.removeSingleUserFromChatRoom("30402887614465","8"));

    }
}
