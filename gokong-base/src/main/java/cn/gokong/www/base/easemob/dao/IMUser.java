package cn.gokong.www.base.easemob.dao;

import cn.gokong.www.base.easemob.api.impl.EasemobIMUsers;
import cn.gokong.www.base.easemob.util.EasemobTools;
import cn.hutool.json.JSONArray;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;


/**
 * 说明: 环信用户API
 *
 * @author Mick
 * CreateDate 2018/5/30 15:56
 * Email ：ideacoding@163.com
 **/
public class IMUser extends EasemobTools {
    private static EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
    /**
     * 注册IM用户
     * @param userName  用户名
     * @param password  用户密码
     * @return  true：成功 false:失败
     */
    public static boolean createUser(String userName, String password) {
        RegisterUsers users = new RegisterUsers();
        users.add(new User().username(userName).password(password));
        Object result = easemobIMUsers.createNewIMUserSingle(users);
        return verifyResult(result, "注册IM用户:{}-{}");
    }



    /**
     * 删除IM用户
     * @param userName  用户名
     * @return  true:成功 false：失败
     */
    public static boolean delUser(String userName) {
        Object result = easemobIMUsers.deleteIMUserByUserName(userName);
        return verifyResult(result, "删除IM用户:{}-{}");

    }

    /**
     * 重置IM密码
     * @param userName  用户名
     * @param password  用户密码
     * @return  true：成功 false：失败
     */
    public static boolean resetUserPwd(String userName,String password) {
        NewPassword psd = new NewPassword().newpassword(password);
        Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(userName, psd);
        return verifyResultIsNull(result,"重置IM密码:{}-{}");
    }

    /**
     * 修改IM昵称
     * @param userName  用户名
     * @param nickName  昵称
     * @return  true：成功 false：失败
     */
    public static boolean updateUserNickName(String userName,String nickName){
        Nickname nickname = new Nickname();
        nickname.setNickname(nickName);
        Object result = easemobIMUsers.modifyIMUserNickNameWithAdminToken(userName, nickname);
        return verifyResult(result, "修改IM昵称:{}-{}");
    }

    /**
     * 添加好友
     * @param userName          用户名称
     * @param friendUserName    好友用户名称
     * @return  rue：成功 false：失败
     */
    public static boolean addFriendSingle(String userName,String friendUserName){
        Object result =  easemobIMUsers.addFriendSingle(userName, friendUserName);
        return verifyResult(result, "添加好友:{}-{}");
    }

    /**
     * 删除好友
     * @param userName          用户名称
     * @param friendUserName    好友用户名称
     * @return  rue：成功 false：失败
     */
    public static boolean deleteFriendSingle(String userName,String friendUserName){
        Object result =  easemobIMUsers.deleteFriendSingle(userName, friendUserName);
        return verifyResult(result, "删除好友:{}-{}");
    }

    /**
     * 查询好友列表
     * @param userName  用户名
     * @return  好友列表
     */
    public static JSONArray getFriends(String userName){
        Object result =  easemobIMUsers.getFriends(userName);
        return verifyResultList(result, "查询好友列表:{}-{}");
    }

    /**
     * 获取用户群组
     * @param userName  用户名
     * @return  用户群组列表
     */
    public static JSONArray getIMUserAllChatGroups(String userName){
        Object result =  easemobIMUsers.getIMUserAllChatGroups(userName);
        return verifyResultList(result, "获取用户群组:{}-{}");
    }

    public static void main(String[] args) {
        //注册IM
        IMUser.createUser("admin", "admin1");
        IMUser.createUser("admin1", "admin1");
        //重置密码a
        IMUser.resetUserPwd("admin","asd");
        //更新用户昵称
        IMUser.updateUserNickName("admin", "程序员");
        //添加好友
        IMUser.addFriendSingle("admin","admin1");
        //查询用户的好友
        IMUser.getFriends("admin");
        //删除用户好友
        IMUser.deleteFriendSingle("admin","admin1");
        //查询用户的群组
        IMUser.getIMUserAllChatGroups("admin");
        //删除IM
        IMUser.delUser("admin");
        IMUser.delUser("admin1");
    }
}
