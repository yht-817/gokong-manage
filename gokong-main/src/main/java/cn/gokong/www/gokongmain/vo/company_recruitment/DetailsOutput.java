package cn.gokong.www.gokongmain.vo.company_recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询招聘求职详情
 *
 * @author ikook
 * @CreateDate 2018/10/2 14:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "DetailsInput", description = "查询招聘求职详情")
public class DetailsOutput{

    @ApiModelProperty(dataType = "String",name = "recruitmentNo",value = "招聘编码")
    private String recruitmentNo;

    /**
     * 公司logo
     */
    @ApiModelProperty(dataType = "String",name = "companyLogo",value = "公司logo")
    private String companyLogo;

    /**
     * 公司名称
     */
    @ApiModelProperty(dataType = "String",name = "companyName",value = "公司名称")
    private String companyName;

    /**
     * 人员规模
     */
    @ApiModelProperty(dataType = "String",name = "staffSize",value = "人员规模")
    private String staffSize;


    @ApiModelProperty(dataType = "String",name = "contactNumber",value = "联系电话")
    private String contactNumber;

    /**
     * 职位类型
     */
    @ApiModelProperty(dataType = "String",name = "positionType",value = "职位类型")
    private String positionType;

    /**
     * 工作薪资
     */
    @ApiModelProperty(dataType = "String",name = "jobSalary",value = "工作薪资")
    private String jobSalary;

    /**
     * 浏览数量
     */
    @ApiModelProperty(dataType = "String",name = "browseNum",value = "浏览数量")
    private Integer browseNum;

    /**
     * 工作地点
     */
    @ApiModelProperty(dataType = "String",name = "workingPlace",value = "工作地点")
    private String workingPlace;

    /**
     * 最低学历
     */
    @ApiModelProperty(dataType = "String",name = "minimumDegree",value = "最低学历")
    private String minimumDegree;

    /**
     * 经验要求
     */
    @ApiModelProperty(dataType = "String",name = "experience",value = "经验要求")
    private String experience;

    /**
     * 职位描述
     */
    @ApiModelProperty(dataType = "String",name = "jobDescription",value = "职位描述")
    private String  jobDescription;

    @ApiModelProperty(dataType = "String",name = "hasCollect",value = "是否收藏")
    private boolean hasCollect;

    public boolean isHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(boolean hasCollect) {
        this.hasCollect = hasCollect;
    }

    public String getRecruitmentNo() {
        return recruitmentNo;
    }

    public void setRecruitmentNo(String recruitmentNo) {
        this.recruitmentNo = recruitmentNo;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(String staffSize) {
        this.staffSize = staffSize;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getMinimumDegree() {
        return minimumDegree;
    }

    public void setMinimumDegree(String minimumDegree) {
        this.minimumDegree = minimumDegree;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
