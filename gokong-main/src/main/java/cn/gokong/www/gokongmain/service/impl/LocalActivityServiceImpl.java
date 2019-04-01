package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.LocalActivityMapper;
import cn.gokong.www.gokongmain.domain.ActivityTabloid;
import cn.gokong.www.gokongmain.domain.LocalActivity;
import cn.gokong.www.gokongmain.domain.UserAccount;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.group.QueryGroupUserOutput;
import cn.gokong.www.gokongmain.vo.local_activity.DetailsOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryMyCreateActivityOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryMyJoinActivityOutput;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 同城活动 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-04
 */
@Service
public class LocalActivityServiceImpl extends ServiceImpl<LocalActivityMapper, LocalActivity> implements LocalActivityService {

    @Autowired
    UploadService uploadService;

    @Autowired
    FriendGroupService friendGroupService;

    @Autowired
    FriendGroupUserService friendGroupUserService;

    @Autowired
    LocalActivityApplyService localActivityApplyService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    PrivilegeUserService privilegeUserService;

    @Autowired
    ActivityTabloidService activityTabloidService;



    /**
     * 发布同城活动
     *
     * @param userNo         用户编码
     * @param activityType   活动类型
     * @param activityTitle  活动标题
     * @param activityDesc   活动描述
     * @param activityImg    活动图片
     * @param cityName       城市名称
     * @param address        具体位置
     * @param activityTime   活动时间
     * @param hopePersonNum  活动人数
     * @param personNumScope 人数范围
     * @param hasCharge      是否收费
     * @param price          收费金额
     * @param hasVerify
     * @return
     */
    @Override
    @Transactional
    public String release(String userNo, String activityType, String activityTitle, String activityDesc, MultipartFile[] activityImg, String cityName,
                          String address, String activityTime, Integer hopePersonNum, String personNumScope, boolean hasCharge, BigDecimal price, boolean hasVerify) {

        if (StrUtil.isEmpty(userNo) || StrUtil.isEmpty(activityTitle) || StrUtil.isEmpty(activityDesc)
                || StrUtil.isEmpty(userNo) || StrUtil.isEmpty(cityName) || StrUtil.isEmpty(address) || StrUtil.isEmpty(activityTime)
                || hopePersonNum==0 || StrUtil.isEmpty(personNumScope) || activityImg.length==0) {
            throw new GrilException("缺少参数");
        }

        //判断当前用户是否是特权用户
        boolean hesPrivilege = privilegeUserService.hesPrivilege(userNo, "local_activity");
        if (hesPrivilege){
            UserInfo randomUserInfo = userInfoService.findRandomUserInfo(SysCodeEnum.CODE_10020003.getMsg());
            if (ObjectUtil.isNotNull(randomUserInfo)){
                userNo = randomUserInfo.getUserNo();
            }
        }

        //上传图片
        List<String> strings = uploadService.ftpImgs(activityImg, "6");

        String joinImg = CollUtil.join(strings, ",");

        //创建环信群聊
        String groupNo = friendGroupService.createGroup(userNo, activityTitle, strings.size()>0?strings.get(0):null, "", "10340001", null, cityName, false, "2");

        LocalActivity localActivity = new LocalActivity();
        localActivity.setId(DataBaseTool.createId());
        localActivity.setActivityNo(DataBaseTool.createNo("activity_no"));
        localActivity.setActivityTitle(activityTitle);
        localActivity.setActivityType(activityType);
        localActivity.setActivityDesc(activityDesc);
        localActivity.setActivityTime(DateUtil.parseDateTime(activityTime).toJdkDate());
        localActivity.setAddress(address);
        localActivity.setHopePersonNum(hopePersonNum);
        localActivity.setPersonNum(0);
        localActivity.setPersonNumScope(personNumScope);
        localActivity.setHasCharge(hasCharge);
        localActivity.setHasVerify(hasVerify);
        if (ObjectUtil.isNull(price)){
            localActivity.setPrice(new BigDecimal(0));
        }else {
            localActivity.setPrice(price);
        }
        localActivity.setCityName(cityName);
        localActivity.setActivityImg(joinImg);
        localActivity.setGroupNo(groupNo);
        localActivity.setUserNo(userNo);
        localActivity.setCreateTime(DataBaseTool.createDate());

        boolean save = save(localActivity);
        if (!save){
            throw new GrilException("发布活动失败");
        }

        //获取创建者信息
        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        // 活动小报
        ActivityTabloid activityTabloid = new ActivityTabloid();
        activityTabloid.setActivityNo(localActivity.getActivityNo());
        activityTabloid.setActivityTime(localActivity.getActivityTime());
        activityTabloid.setCityName(localActivity.getCityName());
        activityTabloid.setContent("<strong>" + userInfo.getNickName() + "</strong> 刚刚创建了位于 <strong>" + localActivity.getCityName() + "</strong> 的活动 《<strong>" + localActivity.getActivityTitle() + "</strong>》");
        activityTabloidService.save(activityTabloid);

        return localActivity.getActivityNo();
    }

    @Override
    public String release(String userNo, String activityType, String activityTitle, String activityDesc, String activityImg, String cityName, String address, String activityTime, Integer hopePersonNum, String personNumScope, boolean hasCharge, BigDecimal price, boolean hasVerify) {
        if (StrUtil.isEmpty(userNo) || StrUtil.isEmpty(activityTitle) || StrUtil.isEmpty(activityDesc)
                || StrUtil.isEmpty(userNo) || StrUtil.isEmpty(cityName) || StrUtil.isEmpty(address) || StrUtil.isEmpty(activityTime)
                || hopePersonNum==0 || StrUtil.isEmpty(personNumScope) || StrUtil.isEmpty(activityImg)) {
            throw new GrilException("缺少参数");
        }

        //判断当前用户是否是特权用户
        boolean hesPrivilege = privilegeUserService.hesPrivilege(userNo, "local_activity");
        if (hesPrivilege){
            UserInfo randomUserInfo = userInfoService.findRandomUserInfo(SysCodeEnum.CODE_10020003.getMsg());
            if (ObjectUtil.isNotNull(randomUserInfo)){
                userNo = randomUserInfo.getUserNo();
            }
        }

        String[] strings = activityImg.split(",");

        //创建环信群聊
        String groupNo = friendGroupService.createGroup(userNo, activityTitle, strings.length>0?strings[0]:null, "", "10340001", null, cityName, false, "2");

        LocalActivity localActivity = new LocalActivity();
        localActivity.setId(DataBaseTool.createId());
        localActivity.setActivityNo(DataBaseTool.createNo("activity_no"));
        localActivity.setActivityTitle(activityTitle);
        localActivity.setActivityType(activityType);
        localActivity.setActivityDesc(activityDesc);
        localActivity.setActivityTime(DateUtil.parseDateTime(activityTime).toJdkDate());
        localActivity.setAddress(address);
        localActivity.setHopePersonNum(hopePersonNum);
        localActivity.setPersonNum(0);
        localActivity.setPersonNumScope(personNumScope);
        localActivity.setHasCharge(hasCharge);
        localActivity.setHasVerify(hasVerify);
        if (ObjectUtil.isNull(price)){
            localActivity.setPrice(new BigDecimal(0));
        }else {
            localActivity.setPrice(price);
        }
        localActivity.setCityName(cityName);
        localActivity.setActivityImg(activityImg);
        localActivity.setGroupNo(groupNo);
        localActivity.setUserNo(userNo);
        localActivity.setCreateTime(DataBaseTool.createDate());

        boolean save = save(localActivity);
        if (!save){
            throw new GrilException("发布活动失败");
        }

        //获取创建者信息
        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        // 活动小报
        ActivityTabloid activityTabloid = new ActivityTabloid();
        activityTabloid.setActivityNo(localActivity.getActivityNo());
        activityTabloid.setActivityTime(localActivity.getActivityTime());
        activityTabloid.setCityName(localActivity.getCityName());
        activityTabloid.setContent("<strong>" + userInfo.getNickName() + "</strong> 刚刚创建了位于 <strong>" + localActivity.getCityName() + "</strong> 的活动 《<strong>" + localActivity.getActivityTitle() + "</strong>》");
        activityTabloidService.save(activityTabloid);

        return localActivity.getActivityNo();
    }

    /**
     * 分页获取同城活动列表
     *
     *
     * @param cityName 城市名称
     * @param keyWord  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public Object pageQueryList(String cityName, String keyWord, Integer pageNo, Integer pageSize) {
        ResponseData responseData = new ResponseData();

        List<PageQueryListOutput> pageQueryListOutputs = baseMapper.pageQueryList(DataBaseTool.createDate(),cityName,keyWord, pageNo, pageSize);
        responseData.setData(pageQueryListOutputs);

        //查询总条数
        Integer totalCount = baseMapper.countByCityName(cityName);
        responseData.setTotalCount(totalCount);
        return responseData;
    }

    /**
     * 查询同城活动详情
     *
     * @param activityNo 活动编码
     * @param userNo     用户编码
     * @return
     */
    @Override
    public Object details(String activityNo, String userNo) {
        ResponseData responseData = new ResponseData();
        DetailsOutput details = baseMapper.details(activityNo,userNo);

        //查询参加者
        LocalActivity localActivity = findByActivityNo(activityNo);

        List<QueryGroupUserOutput> queryGroupUserOutputs = friendGroupUserService.pageQueryGroupUser(localActivity.getGroupNo(), null, 0, 999);

        details.setJoinUsers(queryGroupUserOutputs);
        //查询金币是否满足
        if (StrUtil.isNotEmpty(userNo)){
            UserAccount byUserNo = userAccountService.findByUserNoIfNullIns(userNo);
            if (byUserNo.getAccountAmount().compareTo(localActivity.getPrice())>=0){
                details.setHasCoinMeet(true);
            }
        }

        responseData.setData(details);

        return responseData;
    }

    /**
     * 根据活动编码查询活动信息
     *
     * @param activityNo 活动编码
     * @return
     */
    @Override
    public LocalActivity findByActivityNo(String activityNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("activity_no",activityNo);

        LocalActivity localActivity = getOne(wrapper);
        if (ObjectUtil.isNull(localActivity)){
            throw new GrilException("活动信息不存在");
        }
        return localActivity;
    }

    /**
     * 分页查询我加入的活动列表
     *
     * @param userNo   用户编码
     * @param keyWord  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public Object pageQueryMyJoinActivity(String userNo, String keyWord, Integer pageNo, Integer pageSize) {
        ResponseData responseData = new ResponseData();
        List<PageQueryMyJoinActivityOutput> pageQueryMyJoinActivityOutputs = baseMapper.pageQueryMyJoinActivity(userNo, keyWord, pageNo, pageSize);
        responseData.setData(pageQueryMyJoinActivityOutputs);
        return responseData;
    }

    /**
     * 分页查询我创建的活动列表
     * @param userNo    用户编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public Object pageQueryMyCreateActivity(String userNo, String keyWord, Integer pageNo, Integer pageSize) {
        ResponseData responseData = new ResponseData();
        List<PageQueryMyCreateActivityOutput> pageQueryMyCreateActivityOutputs = baseMapper.pageQueryMyCreateActivity(userNo, keyWord, pageNo, pageSize);
        responseData.setData(pageQueryMyCreateActivityOutputs);
        return responseData;
    }

    /**
     * 编辑活动信息
     *
     * @param activityNo     活动编码
     * @param address        活动地址
     * @param activityTime   活动时间
     * @param hopePersonNum  希望人数
     * @param personNumScope 希望人数范围
     * @param hasVerify
     * @return
     */
    @Override
    public boolean editActivity(String activityNo, String address, String activityTime, int hopePersonNum, String personNumScope, boolean hasVerify) {
        LocalActivity localActivity = findByActivityNo(activityNo);
        localActivity.setAddress(address);
        localActivity.setActivityTime(DateUtil.parseDateTime(activityTime).toJdkDate());
        localActivity.setHasVerify(hasVerify);
        if (hopePersonNum!=0){
            localActivity.setHopePersonNum(hopePersonNum);
        }
        localActivity.setPersonNumScope(personNumScope);
        return saveOrUpdate(localActivity);
    }
}
