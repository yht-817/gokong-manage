package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 招聘信息
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
@TableName("company_recruitment")
public class CompanyRecruitment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 招聘编码
     */
    private String recruitmentNo;

    /**
     * 公司logo
     */
    private String companyLogo;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 人员规模
     */
    private String staffSize;

    /**
     * 职位类型
     */
    private String positionType;

    /**
     * 工作地点
     */
    private String workingPlace;

    /**
     * 工作性质
     */
    private String jobCategory;

    /**
     * 工作薪资
     */
    private String jobSalary;

    /**
     * 经验要求
     */
    private String experience;

    /**
     * 最低学历
     */
    private String minimumDegree;

    /**
     * 职位描述
     */
    private String  jobDescription;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 浏览数量
     */
    private Integer browseNum;

    /**
     * 发布者编码
     */
    private String userNo;

    /**
     * 发布城市名称
     */
    private String cityName;

    /**
     * 金币符号
     */
    private String goldSymbols;

    public String getGoldSymbols() {
        return goldSymbols;
    }

    public void setGoldSymbols(String goldSymbols) {
        this.goldSymbols = goldSymbols;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(String staffSize) {
        this.staffSize = staffSize;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getMinimumDegree() {
        return minimumDegree;
    }

    public void setMinimumDegree(String minimumDegree) {
        this.minimumDegree = minimumDegree;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    public String toString() {
        return "CompanyRecruitment{" +
        "id=" + id +
        ", recruitmentNo=" + recruitmentNo +
        ", companyLogo=" + companyLogo +
        ", contactNumber=" + contactNumber +
        ", staffSize=" + staffSize +
        ", positionType=" + positionType +
        ", workingPlace=" + workingPlace +
        ", jobCategory=" + jobCategory +
        ", jobSalary=" + jobSalary +
        ", experience=" + experience +
        ", minimumDegree=" + minimumDegree +
        ",  jobDescription=" +  jobDescription +
        ", createTime=" + createTime +
        ", browseNum=" + browseNum +
        "}";
    }
}
