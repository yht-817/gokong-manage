package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.CompanyRecruitmentMapper;
import cn.gokong.www.gokongmain.domain.CompanyRecruitment;
import cn.gokong.www.gokongmain.domain.CompanyRecruitmentBrowse;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.company_recruitment.DetailsOutput;
import cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.company_recruitment.ReleaseInput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 招聘信息 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
@Service
public class CompanyRecruitmentServiceImpl extends ServiceImpl<CompanyRecruitmentMapper, CompanyRecruitment> implements CompanyRecruitmentService {

    @Autowired
    CompanyRecruitmentBrowseService companyRecruitmentBrowseService;

    @Autowired
    ShopCityService shopCityService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    PrivilegeUserService privilegeUserService;

    /**
     * 发布招聘求职
     *
     * @param data 入参
     * @return
     */
    @Override
    public String release(ReleaseInput data) {
        //判断当前用户是否是特权用户
        boolean hesPrivilege = privilegeUserService.hesPrivilege(data.getUserNo(), "information");
        if (hesPrivilege){
            UserInfo randomUserInfo = userInfoService.findRandomUserInfo(SysCodeEnum.CODE_10020003.getMsg());
            if (ObjectUtil.isNotNull(randomUserInfo)){
                data.setUserNo(randomUserInfo.getUserNo());
            }
        }


        CompanyRecruitment companyRecruitment = new CompanyRecruitment();
        companyRecruitment.setId(DataBaseTool.createId());
        companyRecruitment.setRecruitmentNo(DataBaseTool.createNo("recruitment_no"));

        companyRecruitment.setCompanyLogo(data.getCompanyLogo());
        companyRecruitment.setCompanyName(data.getCompanyName());
        companyRecruitment.setContactNumber(data.getContactNumber());
        companyRecruitment.setStaffSize(data.getStaffSize());
        companyRecruitment.setPositionType(data.getPositionType());
        companyRecruitment.setWorkingPlace(data.getWorkingPlace());
        companyRecruitment.setJobCategory(data.getJobCategory());
        companyRecruitment.setJobSalary(data.getJobSalary());
        companyRecruitment.setGoldSymbols(shopCityService.queryCurrencySymbol(data.getCityName()));
        companyRecruitment.setExperience(data.getExperience());
        companyRecruitment.setMinimumDegree(data.getMinimumDegree());
        companyRecruitment.setJobDescription(data.getJobDescription().replaceAll("\n","<br/>"));

        companyRecruitment.setCreateTime(DataBaseTool.createDate());
        companyRecruitment.setBrowseNum(0);
        companyRecruitment.setUserNo(data.getUserNo());
        companyRecruitment.setCityName(data.getCityName());

        boolean save = save(companyRecruitment);
        if (!save){
            throw new GrilException("发布招聘求职失败");
        }
        return companyRecruitment.getRecruitmentNo();
    }

    /**
     * 分页查询招聘求职列表
     * @param cityName  城市名称
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryList(String cityName, String keyWord, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryList(cityName,keyWord,pageNo,pageSize);
    }

    /**
     * 查询招聘求职详情
     *
     * @param userNo        用户编码
     * @param recruitmentNo 招聘编码
     * @return
     */
    @Override
    @Transactional
    public DetailsOutput details(String userNo, String recruitmentNo) {
        //用户是否浏览
        boolean browse = companyRecruitmentBrowseService.isBrowse(userNo, recruitmentNo);
        if (!browse){
            CompanyRecruitmentBrowse companyRecruitmentBrowse = new CompanyRecruitmentBrowse();
            companyRecruitmentBrowse.setId(DataBaseTool.createId());
            companyRecruitmentBrowse.setUserNo(userNo);
            companyRecruitmentBrowse.setRecruitmentNo(recruitmentNo);
            companyRecruitmentBrowse.setCreateTime(DataBaseTool.createDate());
            companyRecruitmentBrowseService.save(companyRecruitmentBrowse);

            //添加访问记录
            CompanyRecruitment companyRecruitment = findByRecruitmentNo(recruitmentNo);
            companyRecruitment.setBrowseNum(companyRecruitment.getBrowseNum()+1);
            saveOrUpdate(companyRecruitment);
        }
        return baseMapper.details(userNo,recruitmentNo);
    }

    /**
     * 根据招聘编码查询招聘信息
     *
     * @param recruitmentNo 招聘编码
     * @return
     */
    @Override
    public CompanyRecruitment findByRecruitmentNo(String recruitmentNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("recruitment_no",recruitmentNo);
        CompanyRecruitment companyRecruitment = getOne(wrapper);
        if (ObjectUtil.isNull(companyRecruitment)){
            throw new GrilException("招聘信息不存在");
        }
        return companyRecruitment;
    }

    /**
     * 分页获取我发布的招聘求职
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryMyCompanyRecruitment(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryMyCompanyRecruitment(userNo,pageNo,pageSize);
    }

    /**
     * 删除招聘求职信息
     *
     * @param userNo        用户编码
     * @param recruitmentNo 招聘求职编码
     * @return
     */
    @Override
    public boolean del(String userNo, String recruitmentNo) {
        CompanyRecruitment byRecruitmentNo = findByRecruitmentNo(recruitmentNo);
        if (!byRecruitmentNo.getUserNo().equals(userNo)){
            throw new GrilException("无权限");
        }
        return removeById(byRecruitmentNo.getId());
    }
}
