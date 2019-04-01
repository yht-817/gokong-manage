package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.UsedDealMapper;
import cn.gokong.www.gokongmain.domain.UsedDeal;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.used_deal.QueryDetailsOutput;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
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
 * 二手交易 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@Service
public class UsedDealServiceImpl extends ServiceImpl<UsedDealMapper, UsedDeal> implements UsedDealService {

    @Autowired
    UploadService uploadService;

    @Autowired
    ShopCityService shopCityService;

    @Autowired
    PrivilegeUserService privilegeUserService;

    @Autowired
    UserInfoService userInfoService;

    /**
     * 发布二手信息
     *
     * @param userNo      用户编码
     * @param usedTitle   标题
     * @param usedContent 描述
     * @param usedImgs    图片
     * @param typeNo      类型编码
     * @param cityName    发布城市
     * @param price       价格
     * @return
     */
    @Override
    public String releaseUsedDeal(String userNo, String usedTitle, String usedContent, MultipartFile[] usedImgs, String typeNo, String cityName, BigDecimal price) {
        if (ObjectUtil.isNull(userNo) || ObjectUtil.isNull(usedTitle) || ObjectUtil.isNull(usedContent) || ObjectUtil.isNull(usedImgs)
                || ObjectUtil.isNull(typeNo) || ObjectUtil.isNull(cityName)) {
            throw new GrilException("参数缺失！");
        }

        //判断当前用户是否是特权用户
        boolean hesPrivilege = privilegeUserService.hesPrivilege(userNo, "used_deal");
        if (hesPrivilege){
            UserInfo randomUserInfo = userInfoService.findRandomUserInfo(SysCodeEnum.CODE_10020003.getMsg());
            if (ObjectUtil.isNotNull(randomUserInfo)){
                userNo = randomUserInfo.getUserNo();
            }
        }
        //上传图片
        List<String> strings = uploadService.ftpImgs(usedImgs, "7");

        String joinImg = CollUtil.join(strings, ",");


        //保存数据
        UsedDeal usedDeal = new UsedDeal();
        usedDeal.setId(DataBaseTool.createId());
        usedDeal.setUsedNo(DataBaseTool.createNo("used_no"));
        usedDeal.setUserNo(userNo);
        usedDeal.setUsedTitle(usedTitle);
        usedDeal.setUsedContent(usedContent);
        usedDeal.setUsedImg(joinImg);
        usedDeal.setTypeNo(typeNo);
        usedDeal.setGoldSymbols(shopCityService.queryCurrencySymbol(cityName));
        usedDeal.setPrice(price);
        usedDeal.setCityName(cityName);
        usedDeal.setCreateTime(DataBaseTool.createDate());
        usedDeal.setBrowseNum(0);
        usedDeal.setCommentNum(0);

        boolean save = save(usedDeal);
        if (!save){
            throw new GrilException("发布二手交易失败");
        }
        return usedDeal.getUsedNo();
    }

    /**
     * 分页查询二手交易列表
     *
     * @param cityName  所在城市
     * @param typeNo    类型编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryList(String cityName, String typeNo, String keyWord, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryList(cityName,typeNo,keyWord,pageNo,pageSize);
    }

    /**
     * 查询二手交易详情
     * @param userNo    用户编码
     * @param usedNo    二手交易编码
     * @return
     */
    @Override
    @Transactional
    public QueryDetailsOutput queryDetails(String userNo, String usedNo) {
        UsedDeal usedDeal = findByUsedNo(usedNo);
        usedDeal.setBrowseNum(usedDeal.getBrowseNum()+1);
        saveOrUpdate(usedDeal);
        return baseMapper.queryDetails(userNo,usedNo);
    }

    /**
     * 根据二手交易编码查询信息
     *
     * @param usedNo 二手交易编码
     * @return
     */
    @Override
    public UsedDeal findByUsedNo(String usedNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("used_no",usedNo);
        UsedDeal usedDeal = getOne(wrapper);
        if (ObjectUtil.isNull(usedDeal)){
            throw new GrilException("二手交易信息不存在");
        }
        return usedDeal;
    }

    /**
     * 分页查询我发布的二手交易
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryMyUsedDeal(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryMyUsedDeal(userNo,pageNo,pageSize);
    }

    /**
     * 删除二手交易信息
     *
     * @param userNo 用户编码
     * @param usedNo 二手交易编码
     * @return
     */
    @Override
    public boolean del(String userNo, String usedNo) {
        UsedDeal byUsedNo = findByUsedNo(usedNo);
        if (!byUsedNo.getUserNo().equals(userNo)) {
            throw new GrilException("无权限");
        }
        return removeById(byUsedNo.getId());
    }
}
