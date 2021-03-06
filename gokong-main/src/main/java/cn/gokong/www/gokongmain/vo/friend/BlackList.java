package cn.gokong.www.gokongmain.vo.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 说明:批量删除群成员入参
 *
 * @author ikook
 * @CreateDate 2018/9/13 11:33
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "BatchDelGroupUserInput", description = "批量删除群成员入参")
public class BlackList {

    @ApiModelProperty(dataType = "String", name = "userNo", value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50, message = "用户编码长度<=50")
    private String userNo;

    @ApiModelProperty(dataType = "String", name = "userNos", value = "待删除的用户编码集合")
    @NotNull(message = "待删除的用户编码集合不能为空")
    private List<Map<String, String>> userNos;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public List<Map<String, String>> getUserNos() {
        return userNos;
    }

    public void setUserNos(List<Map<String, String>> userNos) {
        this.userNos = userNos;
    }
}
