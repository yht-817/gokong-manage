package cn.gokong.www.gokongmain.vo.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询更多热门群
 *
 * @author ikook
 * @CreateDate 2018/9/27 16:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryHotGroupOutput", description = "分页查询更多热门群")
public class PageQueryHotGroupOutput{

    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    private String groupHead;

    @ApiModelProperty(dataType = "boolean",name = "personCount",value = "群人数")
    private int personCount;

    @ApiModelProperty(dataType = "String",name = "applyState",value = "申请状态 (10170001:正常 10170005：申请中 '':未申请)")
    private String applyState;

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {

        this.applyState = applyState;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }
}
