package cn.gokong.www.gokongmain.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

/**
 * 说明:公用分页查询数据
 *
 * @author ikook
 * @CreateDate 2018/9/11 14:55
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryBase", description = "公用分页查询数据")
public class PageQueryBase {
    @ApiModelProperty(dataType = "int",name = "pageNo",value = "当前页数")
    @Range(min = 1,message = "当前页数最小为1")
    private Integer pageNo;

    @ApiModelProperty(dataType = "int", name = "pageNo", value = "检索长度")
    @Range(min = 1,message = "检索长度为1")
    private Integer pageSize;

    public Integer getPageNo() {
        return (pageNo - 1) * pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
