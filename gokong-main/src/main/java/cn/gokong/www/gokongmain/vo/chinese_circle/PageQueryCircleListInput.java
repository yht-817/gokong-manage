package cn.gokong.www.gokongmain.vo.chinese_circle;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/12/17 15:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Data
public class PageQueryCircleListInput extends PageQueryBase {
    @ApiModelProperty(dataType = "String", name = "userNo", value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50, message = "用户编码长度<=50")
    private String userNo;

    private String log;

    private String lat;

    private String cityName;

}
