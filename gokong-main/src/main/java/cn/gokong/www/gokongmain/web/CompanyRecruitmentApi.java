package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.company_recruitment.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 招聘求职 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
@RestController
@RequestMapping("/v2/companyRecruitment")
@Api(tags = "招聘求职", description = "CompanyRecruitmentApi")
public class CompanyRecruitmentApi extends BaseController{

    @RequestMapping(value = "/release", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布招聘求职", httpMethod = "POST", response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "companyLogo", value = "公司logo",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "companyName", value = "公司名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "contactNumber", value = "联系电话",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "staffSize", value = "人员规模",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "positionType", value = "职位类型",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "workingPlace", value = "工作地点",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "jobCategory", value = "工作性质",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "jobSalary", value = "工作薪资",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "experience", value = "经验要求",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "minimumDegree", value = "最低学历",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "jobDescription", value = "职位描述",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "userNo", value = "发布者编码",paramType = "query",dataType = "String",defaultValue = ""),
            @ApiImplicitParam(required = true, name = "cityName", value = "发布城市名称",paramType = "query",dataType = "String",defaultValue = ""),
    })
    public Object release(@RequestBody(required = false) @Valid RequestData<ReleaseInput> requestData, BindingResult bindingResult) {
        ReleaseInput data = requestData.getData();
        String recruitmentNo = companyRecruitmentService.release(data);
        return getSuccess(recruitmentNo);
    }

    @RequestMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询招聘求职列表", httpMethod = "POST", response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "cityName", value = "城市名称",paramType = "query",dataType = "String",defaultValue = "成都市"),
            @ApiImplicitParam(required = false, name = "keyWord", value = "检索内容",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页",paramType = "query",dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String",defaultValue = "10"),
    })
    public Object pageQueryList(@RequestBody(required = false) @Valid RequestData<PageQueryListInput> requestData, BindingResult bindingResult) {
        PageQueryListInput data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs = companyRecruitmentService.pageQueryList(data.getCityName(),data.getKeyWord(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }

    @RequestMapping(value = "/details", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询招聘求职详情", httpMethod = "POST", response = DetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(required = true, name = "recruitmentNo", value = "招聘求职编码",paramType = "query",dataType = "String",defaultValue = "recruitment_no153846038552177267"),
    })
    public Object details(@RequestBody(required = false) @Valid RequestData<DetailsInput> requestData, BindingResult bindingResult) {
        DetailsInput data = requestData.getData();
        DetailsOutput detailsOutput = companyRecruitmentService.details(data.getUserNo(),data.getRecruitmentNo());
        return getSuccess(detailsOutput);
    }

    @RequestMapping(value = "/queryPositionTypeList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询职位类型列表", httpMethod = "POST", response = DetailsOutput.class)
    public Object queryPositionTypeList(@RequestBody(required = false) @Valid RequestData requestData, BindingResult bindingResult) {
        List<QueryPositionTypeListOutput> queryPositionTypeListOutput = positionTypeService.queryPositionTypeList();
        return getSuccess(queryPositionTypeListOutput);
    }

    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除招聘求职信息", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(required = true, name = "recruitmentNo", value = "招聘求职编码",paramType = "query",dataType = "String",defaultValue = "recruitment_no153846038552177267"),
    })
    public Object del(@RequestBody(required = false) @Valid RequestData<DetailsInput> requestData, BindingResult bindingResult) {
        DetailsInput data = requestData.getData();
        boolean del =  companyRecruitmentService.del(data.getUserNo(),data.getRecruitmentNo());
        return getBoolean(del);
    }
}

