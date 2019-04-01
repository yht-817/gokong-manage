package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.easemob.dao.IMChatGroup;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.FriendGroupMapper;
import cn.gokong.www.gokongmain.domain.FriendGroup;
import cn.gokong.www.gokongmain.domain.FriendGroupUser;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.FriendGroupService;
import cn.gokong.www.gokongmain.service.FriendGroupUserService;
import cn.gokong.www.gokongmain.vo.group.PageQueryHotGroupOutput;
import cn.gokong.www.gokongmain.vo.group.PageSearchGroupOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupDetailsOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupUserOutput;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友群信息 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
@Service
public class FriendGroupServiceImpl extends ServiceImpl<FriendGroupMapper, FriendGroup> implements FriendGroupService {

    @Autowired
    FriendGroupUserService friendGroupUserService;

    /**
     * 分页查询更多热门群
     * @param userNo    用户编码
     * @param cityName  城市名称
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryHotGroupOutput> pageQueryHotGroup(String userNo, String cityName, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryHotGroup(userNo,cityName,pageNo,pageSize);
    }

    /**
     * 获取热门群
     *
     * @param cityNo
     * @return
     */
    @Override
    public ResponseData findHot(String cityNo) {
        ResponseData data = new ResponseData();
        List<Map<String, String>> hot = this.baseMapper.getHots(cityNo);
        data.setCode(200);
        data.setMessage("数据请求成功");
        data.setData(hot);
        return data;
    }

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
     * @param joinMark   是否需要群主同意加入群
     * @param groupType  群类型
     * @return
     */
    @Override
    @Transactional
    public String createGroup(String userNo, String groupName, String groupHead, String groupNode, String ifPayGroup, BigDecimal payAmount, String cityNo, boolean joinMark,String groupType) {
        //创建环信群聊
        String groupNo = IMChatGroup.createGroup(groupName, groupNode, userNo);
        if (StrUtil.isEmpty(groupNo)) {
            throw new GrilException("创建环信群聊失败");
        }
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setId(DataBaseTool.createId());
        friendGroup.setGroupNo(groupNo);
        friendGroup.setUserNo(userNo);
        friendGroup.setCreateDate(DataBaseTool.createDate());
        friendGroup.setGroupName(groupName);
        friendGroup.setGroupHead(groupHead);
        friendGroup.setGroupNode(groupNode);
        friendGroup.setCityNo(cityNo);
        friendGroup.setGroupState(SysCodeEnum.CODE_10160001.getCode());
        friendGroup.setIfPayGroup(ifPayGroup);
        friendGroup.setPayAmount(payAmount);
        friendGroup.setJoinMark(joinMark);
        friendGroup.setGroupType(groupType);

        boolean saveOrUpdate = save(friendGroup);
        if (!saveOrUpdate) {
            throw new GrilException("创建群聊失败");
        }

        //插入群主与群的关系状态
        FriendGroupUser friendGroupUser = new FriendGroupUser();
        friendGroupUser.setGroupNo(groupNo);
        friendGroupUser.setUserNo(userNo);
        friendGroupUser.setGroupUserState(SysCodeEnum.CODE_10170001.getCode());
        boolean save = friendGroupUserService.save(friendGroupUser);
        if (!save) {
            throw new GrilException("插入群主与群的关系失败");
        }

        return groupNo;
    }

    /**
     * 解散群聊
     *
     * @param groupNo 群编码
     * @return
     */
    @Override
    @Transactional
    public boolean dissolveGroup(String groupNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("group_no", groupNo);
        FriendGroup friendGroup = getOne(wrapper);
        if (ObjectUtil.isNotNull(friendGroup)) {
            removeById(friendGroup);

            //删除群关系信息
            friendGroupUserService.delByGroupNo(groupNo);

            //删除环信群信息
            boolean delGroup = IMChatGroup.delGroup(friendGroup.getGroupNo());

            if (!delGroup) {
                throw new GrilException("删除环信群聊失败");
            }
        }
        return true;
    }

    /**
     * 根据群编码查询群信息
     * @param groupNo   群编码
     * @return
     */
    @Override
    public FriendGroup findByGroupNo(String groupNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("group_no",groupNo);
        FriendGroup friendGroup = getOne(wrapper);
        if (ObjectUtil.isNull(friendGroup)){
            throw new GrilException("未查询到编码为 "+groupNo+" 的群消息");
        }
        return friendGroup;
    }

    /**
     * 修改群资料
     *
     * @param groupNo   群聊编码
     * @param groupName 群名称
     * @param groupNode 群公告
     * @param groupHead 群头像
     * @param joinMark  是否需要群主同意加入(true false)
     * @return
     */
    @Override
    @Transactional
    public boolean updateGroupData(String groupNo, String groupName, String groupNode, String groupHead, boolean joinMark) {
        FriendGroup group = findByGroupNo(groupNo);
        if (ObjectUtil.isNotNull(group)){
            group.setGroupName(groupName);
            group.setGroupNode(groupNode);
            group.setGroupHead(groupHead);
            group.setJoinMark(joinMark);
            boolean saveOrUpdate = saveOrUpdate(group);
            if (!saveOrUpdate){
                throw new GrilException("修改群信息失败");
            }
            //更新环信群信息
            boolean chatGroup = IMChatGroup.modifyChatGroup(groupNo, groupName, groupNode);
            if (!chatGroup){
                throw new GrilException("修改环信群信息失败");
            }
        }
        return true;
    }

    /**
     * 查询群详情
     *
     * @param groupNo 群编码
     * @param userNo  用户编码
     * @return
     */
    @Override
    public QueryGroupDetailsOutput queryGroupDetails(String groupNo, String userNo) {
        //查询群详情信息
        QueryGroupDetailsOutput queryGroupDetailsOutput = baseMapper.queryGroupDetails(groupNo,userNo);

        //判断当前浏览用户是否加入该群
        boolean isJoin = friendGroupUserService.isJoin(groupNo, userNo);
        queryGroupDetailsOutput.setIsokJoin(isJoin);
        //如果是群主
        if (queryGroupDetailsOutput.getUserNo().equals(userNo)){
            queryGroupDetailsOutput.setIsokOwner(true);
        }
        //获取群详情 群主信息+7位成员信息
        List<QueryGroupUserOutput> groupUsers = friendGroupUserService.pageQueryGroupUsers(groupNo, null, 0, 7);
        queryGroupDetailsOutput.setGroupUsers(groupUsers);

        return queryGroupDetailsOutput;
    }



    /**
     * 分页检索群
     *
     * @param userNo   用户编码
     * @param keyword  检索内容
     * @param cityName 城市名称
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageSearchGroupOutput> pageSearchGroup(String userNo, String keyword, String cityName, Integer pageNo, Integer pageSize) {
        List<PageSearchGroupOutput> pageSearchGroupOutputs = baseMapper.pageSearchGroup(userNo,keyword,cityName,pageNo, pageSize);

        pageSearchGroupOutputs.forEach(pageSearchGroupOutput -> {
            //boolean join = friendGroupUserService.isJoin(pageSearchGroupOutput.getGroupNo(), userNo);
            //pageSearchGroupOutput.setIsokJoin(join);
            if (pageSearchGroupOutput.getUserNo().equals(userNo)){
                pageSearchGroupOutput.setIsokOwner(true);
            }
        });
        return pageSearchGroupOutputs;
    }
}
