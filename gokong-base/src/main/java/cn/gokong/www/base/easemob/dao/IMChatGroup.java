package cn.gokong.www.base.easemob.dao;

import cn.gokong.www.base.easemob.api.impl.EasemobChatGroup;
import cn.hutool.json.JSONObject;
import io.swagger.client.model.Group;
import io.swagger.client.model.ModifyGroup;
import io.swagger.client.model.NewOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明: 环信群聊API
 *
 * @author Mick
 * CreateDate 2018/5/30 15:51
 * Email ：ideacoding@163.com
 **/
public class IMChatGroup {
    private static EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
    private static final Logger logger = LoggerFactory.getLogger(IMChatGroup.class);

    /**
     * 创建群组
     * @param groupName
     * @param desc
     * @param owner
     */
    public static String createGroup(String groupName,String desc,String owner) {
        Group group = new Group();
        group.groupname(groupName).desc(desc)._public(true).maxusers(2000).approval(false).owner(owner);
        Object result = easemobChatGroup.createChatGroup(group);
        logger.info(result.toString());
        if(result!=null){
            JSONObject jsonObject = new JSONObject(result);
            jsonObject = new JSONObject(jsonObject.get("data"));
            return String.valueOf(jsonObject.get("groupid"));
        }else {
            return null;
        }
    }

    /**
     * 删除群组
     * @param groupId
     * @return
     */
    public static boolean delGroup(String groupId){
        Object result = easemobChatGroup.deleteChatGroup(groupId);
        logger.info(result.toString());
        if(result!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 添加群组成员(单个)
     * @param groupId
     * @param userId
     */
    public static boolean addUsers(String groupId,String userId) {
        Object result = easemobChatGroup.addSingleUserToChatGroup(groupId, userId);
        logger.info(result.toString());
        if(result!=null){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 删除群组成员(单个)
     * @param groupId
     * @param userId
     * @return
     */
    public static boolean removeUsers(String groupId,String userId){
        Object result = easemobChatGroup.removeSingleUserFromChatGroup(groupId,userId);
        logger.info(result.toString());
        if(result!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 修改群名称
     * @param groupId
     * @param groupName
     * @param desc
     * @return
     */
    public static boolean modifyChatGroup(String groupId,String groupName,String desc){
        ModifyGroup modifyGroup = new ModifyGroup();
        modifyGroup.setGroupname(groupName);
        modifyGroup.setDescription(desc);
        Object result = easemobChatGroup.modifyChatGroup(groupId, modifyGroup);
        if(result!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 转让群聊
     * @param groupId
     * @param userId
     * @return
     */
    public static boolean transferGroupOwner(String groupId,String userId) {
        NewOwner newOwner = new NewOwner();
        newOwner.newowner(userId);
        Object result = easemobChatGroup.transferChatGroupOwner(groupId, newOwner);
        if(result!=null){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        //IMChatGroup.addUsers("15481499090946", "useradmin");
        //IMChatGroup.delGroup("15481499090946");
        //IMChatGroup.createGroup("旅游社","描述","987891");
        //IMChatGroup.modifyChatGroup("16530228183042","admin");
        IMChatGroup.transferGroupOwner("17801414770689","100000");
    }
}
