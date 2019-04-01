package cn.gokong.www.gokongmain.vo.local_activity_apply;

import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询活动成员列表
 *
 * @author ikook
 * @CreateDate 2018/10/5 16:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryActivityMemberOutput", description = "分页查询活动成员列表")
public class PageQueryActivityMemberOutput extends QueryUserOutput {
    @ApiModelProperty(dataType = "String",name = "phone",value = "联系电话")
    private String phone;

    @ApiModelProperty(dataType = "String",name = "sex",value = "性别")
    private String sex;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
