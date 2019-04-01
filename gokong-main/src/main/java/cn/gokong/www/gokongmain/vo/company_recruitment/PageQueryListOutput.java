package cn.gokong.www.gokongmain.vo.company_recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询招聘求职
 *
 * @author ikook
 * @CreateDate 2018/10/2 11:53
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryListOutput", description = "分页查询招聘求职")
public class PageQueryListOutput {
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
     * 职位描述
     */
    @ApiModelProperty(dataType = "String",name = "jobDescription",value = "职位描述")
    private String  jobDescription;

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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
