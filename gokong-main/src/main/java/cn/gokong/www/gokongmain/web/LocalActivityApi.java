package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.base.CityNameBase;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchUserNoBase;
import cn.gokong.www.gokongmain.vo.local_activity.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  同城活动 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-10-04
 */
@RestController
@RequestMapping("/v2/localActivity")
@Api(tags = "同城活动", description = "LocalActivityApi")
public class LocalActivityApi extends BaseController{

    @RequestMapping(value="/release",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "发布同城活动(文件方式提交 不加密)", httpMethod = "POST",response = PageQueryInformationCollectionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "发布者编码",paramType = "query"),
            @ApiImplicitParam(name = "activityType", value = "活动分类",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "activityTitle", value = "活动标题",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "activityDesc", value = "活动描述",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "activityImg", value = "活动图片",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "cityName", value = "发布城市",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "address", value = "具体位置",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "activityTime", value = "活动时间（Date类型）",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "hopePersonNum", value = "希望人数",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "personNumScope", value = "希望人数范围",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "hasCharge", value = "是否收费",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "price", value = "价格",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "hasVerify", value = "是否需要验证",paramType = "query",defaultValue = ""),
    })
    public Object release(String userNo, String activityType, String activityTitle, String activityDesc, MultipartFile[] activityImg,
                          String cityName, String address,
                          String activityTime, Integer hopePersonNum, String personNumScope, boolean hasCharge, BigDecimal price,boolean hasVerify){
        String activityNo = localActivityService.release(userNo, activityType, activityTitle, activityDesc, activityImg, cityName, address, activityTime, hopePersonNum, personNumScope, hasCharge, price,hasVerify);
        return getSuccess(activityNo);
    }

    @RequestMapping(value="/pageQueryList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取同城活动列表", httpMethod = "POST",response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityName", value = "城市名称",paramType = "query",defaultValue = "成都市"),
            @ApiImplicitParam(name = "keyWord", value = "检索内容",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryList(@RequestBody(required = false) @Valid RequestData<PageQueryListInput> requestData, BindingResult bindingResult){
        PageQueryListInput data = requestData.getData();
        return localActivityService.pageQueryList(data.getCityName(),data.getKeyWord(),data.getPageNo(), data.getPageSize());
    }

    @RequestMapping(value="/details",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查询同城活动详情", httpMethod = "POST",response = DetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityNo", value = "活动编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
    })
    public Object details(@RequestBody(required = false) @Valid RequestData<DetailsInput> requestData, BindingResult bindingResult){
        DetailsInput data = requestData.getData();
        return localActivityService.details(data.getActivityNo(),data.getUserNo());
    }

    @RequestMapping(value="/pageQueryMyJoinActivity",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询我加入的活动", httpMethod = "POST",response = DetailsInput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "keyWord", value = "检索内容",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMyJoinActivity(@RequestBody(required = false) @Valid RequestData<PageQuerySearchUserNoBase> requestData, BindingResult bindingResult){
        PageQuerySearchUserNoBase data = requestData.getData();
        return localActivityService.pageQueryMyJoinActivity(data.getUserNo(),data.getKeyWord(),data.getPageNo(),data.getPageSize());
    }

    @RequestMapping(value="/pageQueryMyCreateActivity",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询我创建的活动", httpMethod = "POST",response = DetailsInput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "keyWord", value = "检索内容",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMyCreateActivity(@RequestBody(required = false) @Valid RequestData<PageQuerySearchUserNoBase> requestData, BindingResult bindingResult){
        PageQuerySearchUserNoBase data = requestData.getData();
        return localActivityService.pageQueryMyCreateActivity(data.getUserNo(),data.getKeyWord(),data.getPageNo(),data.getPageSize());
    }

    @RequestMapping(value="/editActivity",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "编辑活动信息", httpMethod = "POST",response = DetailsInput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityNo", value = "活动编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "activityTime", value = "活动时间",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "address", value = "具体位置",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "hopePersonNum", value = "希望人数",paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "personNumScope", value = "希望人数范围",paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "hasVerify", value = "是否需要验证",paramType = "query",defaultValue = ""),
    })
    public Object editActivity(@RequestBody(required = false) @Valid RequestData<EditActivityInput> requestData, BindingResult bindingResult){
        EditActivityInput data = requestData.getData();
        boolean editActivity = localActivityService.editActivity(data.getActivityNo(), data.getAddress(), data.getActivityTime(), data.getHopePersonNum(), data.getPersonNumScope(),data.isHasVerify());
        return getBoolean(editActivity);
    }

    @RequestMapping(value="/queryActivityTabloid",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查询活动小报", httpMethod = "POST",response = QueryActivityTabloidOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityName", value = "城市名称",paramType = "query",defaultValue = "成都市"),
    })
    public Object queryActivityTabloid(@RequestBody(required = false) @Valid RequestData<CityNameBase> requestData, BindingResult bindingResult){
        CityNameBase data = requestData.getData();
        /*List<String> strings = ListUtil.get("activity_tabloid_" + data.getCityName());
        List<QueryActivityTabloidOutput> queryActivityTabloidOutputs = new ArrayList<>();
        strings.forEach(s -> {
            QueryActivityTabloidOutput queryActivityTabloidOutput = new QueryActivityTabloidOutput();
            String[] split = s.split("&");
            queryActivityTabloidOutput.setActivityNo(split[0]);
            queryActivityTabloidOutput.setContent(split[1]);
            queryActivityTabloidOutputs.add(queryActivityTabloidOutput);
        });*/
        List<QueryActivityTabloidOutput> queryActivityTabloidOutputs = activityTabloidService.queryActivityTabloid(data.getCityName());
        return getSuccess(queryActivityTabloidOutputs);
    }
}

