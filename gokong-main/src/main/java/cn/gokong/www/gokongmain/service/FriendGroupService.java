package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.FriendGroup;
import cn.gokong.www.gokongmain.vo.group.PageQueryHotGroupOutput;
import cn.gokong.www.gokongmain.vo.group.PageSearchGroupOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupDetailsOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 好友群信息 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
public interface FriendGroupService extends IService<FriendGroup> {
    /**
     * 获取首页的热门群
     *
     * @param cityNo 城市编码
     * @return
     */
    ResponseData findHot(String cityNo);

    /**
     * 创建群聊
     *
     * @param userNo     创建人
     * @param groupName  群名称
     * @param groupHead  群头像
     * @param groupNode  群公告
     * @param ifPayGroup 是否付费
     * @param payAmount  付费蟠桃
     * @param cityNo     城市编码
     * @param joinMark   是否需要群主同意加入群里
     * @param groupType  群类型（1普通群 2活动群）
     * @return
     */
    String createGroup(String userNo, String groupName, String groupHead, String groupNode, String ifPayGroup, BigDecimal payAmount, String cityNo, boolean joinMark,String groupType);

    /**
     * 解散群聊
     *
     * @param groupNo 群编码
     * @return
     */
    boolean dissolveGroup(String groupNo);

    /**
     * 根据群编码查询群信息
     *
     * @param groupNo 群编码
     * @return
     */
    FriendGroup findByGroupNo(String groupNo);

    /**
     * 修改群聊信息
     *
     * @param groupNo   群聊编码
     * @param groupName 群名称
     * @param groupNode 群公告
     * @param groupHead 群头像
     * @param joinMark  是否需要群主同意加入(true false)
     * @return
     */
    boolean updateGroupData(String groupNo, String groupName, String groupNode, String groupHead, boolean joinMark);

    /**
     * 查询群详情
     *
     * @param groupNo 群编码
     * @param userNo  用户编码
     * @return
     */
    QueryGroupDetailsOutput queryGroupDetails(String groupNo, String userNo);

    /**
     * 分页检索群
     * @param userNo    用户编码
     * @param keyword   检索内容
     * @param cityName  城市名称
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageSearchGroupOutput> pageSearchGroup(String userNo, String keyword, String cityName, Integer pageNo, Integer pageSize);

    /**
     * 分页查询更多热门群
     * @param userNo    用户编码
     * @param cityName  城市名称
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryHotGroupOutput> pageQueryHotGroup(String userNo, String cityName, Integer pageNo, Integer pageSize);
}
