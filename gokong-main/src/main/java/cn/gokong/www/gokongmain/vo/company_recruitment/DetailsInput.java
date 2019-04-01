package cn.gokong.www.gokongmain.vo.company_recruitment;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:查询招聘求职详情
 *
 * @author ikook
 * @CreateDate 2018/10/2 14:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "DetailsInput", description = "查询招聘求职详情")
public class DetailsInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "recruitmentNo",value = "招聘编码")
    @NotNull(message = "招聘编码不能为空")
    @Length(max = 50,message = "招聘编码长度<=50")
    private String recruitmentNo;

    public String getRecruitmentNo() {
        return recruitmentNo;
    }

    public void setRecruitmentNo(String recruitmentNo) {
        this.recruitmentNo = recruitmentNo;
    }
}
