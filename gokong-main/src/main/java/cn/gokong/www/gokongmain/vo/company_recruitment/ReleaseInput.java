package cn.gokong.www.gokongmain.vo.company_recruitment;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:发布招聘信息
 *
 * @author ikook
 * @CreateDate 2018/10/2 10:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReleaseInput", description = "发布招聘信息")
public class ReleaseInput extends UserNoBase {

    /**
     * 公司logo
     */
    @ApiModelProperty(dataType = "String",name = "companyLogo",value = "公司logo")
    @NotNull(message = "公司logo不能为空")
    @Length(max = 100,message = "公司logo长度<=100")
    private String companyLogo;

    /**
     * 公司名称
     */
    @ApiModelProperty(dataType = "String",name = "companyLogo",value = "公司名称")
    @NotNull(message = "公司名称不能为空")
    @Length(max = 50,message = "公司名称长度<=50")
    private String companyName;

    /**
     * 联系电话
     */
    @ApiModelProperty(dataType = "String",name = "contactNumber",value = "联系电话")
    @NotNull(message = "联系电话不能为空")
    @Length(max = 20,message = "联系电话长度<=20")
    private String contactNumber;

    /**
     * 人员规模
     */
    @ApiModelProperty(dataType = "String",name = "staffSize",value = "人员规模")
    @NotNull(message = "人员规模不能为空")
    @Length(max = 100,message = "人员规模长度<=100")
    private String staffSize;

    /**
     * 职位类型
     */
    @ApiModelProperty(dataType = "String",name = "positionType",value = "公司logo")
    @NotNull(message = "公司logo不能为空")
    @Length(max = 100,message = "公司logo长度<=100")
    private String positionType;

    /**
     * 工作地点
     */
    @ApiModelProperty(dataType = "String",name = "workingPlace",value = "工作地点")
    @NotNull(message = "工作地点不能为空")
    @Length(max = 100,message = "工作地点长度<=100")
    private String workingPlace;

    /**
     * 工作性质
     */
    @ApiModelProperty(dataType = "String",name = "jobCategory",value = "工作性质")
    @NotNull(message = "工作性质不能为空")
    @Length(max = 100,message = "工作性质长度<=100")
    private String jobCategory;

    /**
     * 工作薪资
     */
    @ApiModelProperty(dataType = "String",name = "jobSalary",value = "工作薪资")
    private String jobSalary;

    /**
     * 经验要求
     */
    @ApiModelProperty(dataType = "String",name = "experience",value = "经验要求")
    @NotNull(message = "经验要求不能为空")
    @Length(max = 100,message = "经验要求长度<=100")
    private String experience;

    /**
     * 最低学历
     */
    @ApiModelProperty(dataType = "String",name = "minimumDegree",value = "最低学历")
    @NotNull(message = "最低学历不能为空")
    @Length(max = 100,message = "最低学历长度<=100")
    private String minimumDegree;

    /**
     * 职位描述
     */
    @ApiModelProperty(dataType = "String",name = "jobDescription",value = "职位描述")
    @NotNull(message = "职位描述不能为空")
    @Length(max = 1000,message = "请精简您的职位描述，超过字数限制啦！")
    private String  jobDescription;

    /**
     * 发布城市名称
     */
    @ApiModelProperty(dataType = "String",name = "cityName",value = "发布城市名称")
    @NotNull(message = "发布城市名称不能为空")
    @Length(max = 20,message = "发布城市名称长度<=20")
    private String cityName;

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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
