package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.FriendGroup;
import cn.gokong.www.gokongmain.domain.FriendGroupUser;
import cn.gokong.www.gokongmain.vo.group.PageQueryInviteGroupUserOutput;
import cn.gokong.www.gokongmain.vo.group.PageQueryUserGroupOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupUserOutput;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 好友群用户信息 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
public interface FriendGroupUserService extends IService<FriendGroupUser> {

    @Override
    boolean save(FriendGroupUser friendGroupUser);

    /**
     * 退出群聊编码
     * @param userNo    用户编码
     * @param groupNo   群聊编码
     * @return
     */
    boolean exitGroup(String userNo, String groupNo);

    /**
     * 批量删除群成员
     * @param userNo    群主编码
     * @param groupNo   群编码
     * @param userNos   待删除的用户编码
     * @return
     */
    boolean batchDelGroupUser(String userNo, String groupNo, List<Map<String,String>> userNos);

    /**
     * 邀请用户加入群
     * @param userNo    邀请方的用户编码
     * @param groupNo   群聊编码
     * @param userNos   用户编码集合
     * @return
     */
    boolean inviteJoin(String userNo, String groupNo, List<Map<String, String>> userNos);

    /**
     * 用户待加入群聊
     * @param groupNo           群编码
     * @param userNo            用户编码
     * @param inviteUserNo      邀请者编码
     * @param groupUserState    群用户状态
     * @param verifyInfo        验证信息
     * @return
     */
    boolean stayJoinGroup(String groupNo,String userNo,String inviteUserNo,String groupUserState,String verifyInfo);

    /**
     * 申请加入群聊
     * @param userNo        用户编码
     * @param groupNo       群聊编码
     * @param verifyInfo    验证信息
     * @return
     */
    boolean applyJoin(String userNo, String groupNo, String verifyInfo);

    /**
     * 判断用户是否加入该群
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    boolean isJoin(String groupNo, String userNo);

    /**
     * 分页查询群用户信息
     * @param groupNo   群编码
     * @param keyword   关键字
     * @param start     开始位置
     * @param end       检索长度
     * @return
     */
    List<QueryGroupUserOutput> pageQueryGroupUsers(String groupNo, String keyword, int start, int end);

    /**
     * 查询群成员列表
     * @param groupNo   用户编码
     * @param content   检索内容
     * @param pageNo    开始位置
     * @param pageSize  页面长度
     * @return
     */
    List<QueryGroupUserOutput> pageQueryGroupUser(String groupNo, String content, Integer pageNo, Integer pageSize);

    /**
     * 查询群成员编码
     * @param groupNo
     * @return
     */
    List<String> selectOneUserNoResult(String groupNo);

    /**
     * 审核（申请/邀请）
     * @param inviteNo      申请编码
     * @param auditState    审核状态
     * @return
     */
    boolean auditInvite(String inviteNo, boolean auditState);

    /**
     * 分页查询邀请群成员列表
     * @param groupNo   群编码
     * @param userNo    邀请者编码
     * @param content   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUser(String groupNo, String userNo, String content, Integer pageNo, Integer pageSize);

    /**
     * 判断是否是群成员
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    boolean isGroupUser(String groupNo,String userNo);

    /**
     * 分页查询用户群列表
     *
     * @param keyword   检索内容
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryUserGroupOutput> pageQueryUserGroup(String keyword, String userNo, Integer pageNo, Integer pageSize);

    /**
     * 根据用户编码查询加入的群
     *
     * @param userNo 用户编码
     * @return
     */
    List<Map<String, String>> queryJoinGroup(@Param("userNo")String userNo);

    /**
     * 成为群成员
     *
     * @param applyUserNo  用户编码
     * @param friendGroup  群对象
     * @param inviteUserNo 邀请用户编码
     * @return
     */
    boolean becomeGroupUser(String applyUserNo, FriendGroup friendGroup, String inviteUserNo);


    /**
     * 查询群和用户的关系集合
     * @param groupNo   群编码
     * @return
     */
    List<FriendGroupUser> findByGroupNo(String groupNo);

    /**
     * 删除群与用户的关系记录
     * @param groupNo   群编码
     */
    void delByGroupNo(String groupNo);

    /**
     * 根据群编码和用户编码查询群关系信息
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    FriendGroupUser findByGroupNoAndUserNo(String groupNo, String userNo);
}
