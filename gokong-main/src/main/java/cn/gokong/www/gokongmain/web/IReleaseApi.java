package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.base.PageQueryUserNoBase;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
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
 * 我的发布 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@RestController
@RequestMapping("/v2/release")
@Api(tags = "我的发布", description = "前端控制器")
public class IReleaseApi extends BaseController {

    @RequestMapping(value="/pageQueryMyInformation",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取我发布的资讯", httpMethod = "POST",response = PageQueryInformationCollectionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMyInformation(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryInformationCollectionOutput> pageQueryInformationCollectionOutputs = informationService.pageQueryMyInformation(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryInformationCollectionOutputs);
    }

    @RequestMapping(value="/pageQueryMyChineseCircle",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取我发布的华人圈", httpMethod = "POST",response = PageQueryChineseCircleOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMyChineseCircle(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryChineseCircleOutput> pageQueryChineseCircleOutputs = chineseCirclevice.pageQueryMyChineseCircle(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryChineseCircleOutputs);
    }

    @RequestMapping(value="/pageQueryMyUsedDeal",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取我发布的二手交易", httpMethod = "POST",response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMyUsedDeal(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs = usedDealService.pageQueryMyUsedDeal(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }

    @RequestMapping(value="/pageQueryMyCompanyRecruitment",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取我发布的招聘求职", httpMethod = "POST",response = cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMyCompanyRecruitment(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput> pageQueryListOutputs = companyRecruitmentService.pageQueryMyCompanyRecruitment(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }
}

