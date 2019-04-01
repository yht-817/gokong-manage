package cn.gokong.www.gokongmain.web;


import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 * 同城活动 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-10-04
 */
@RestController
@RequestMapping("/v3/localActivity")
@Api(tags = "同城活动", description = "LocalActivityApi")
public class LocalActivityApiV3 extends BaseController {

    @RequestMapping(value = "/release", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布同城活动(小程序使用 不加密)", httpMethod = "POST", response = PageQueryInformationCollectionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "发布者编码", paramType = "query"),
            @ApiImplicitParam(name = "activityType", value = "活动分类", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "activityTitle", value = "活动标题", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "activityDesc", value = "活动描述", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "activityImg", value = "活动图片", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "cityName", value = "发布城市", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "address", value = "具体位置", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "activityTime", value = "活动时间（Date类型）", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "hopePersonNum", value = "希望人数", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "personNumScope", value = "希望人数范围", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "hasCharge", value = "是否收费", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "price", value = "价格", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "hasVerify", value = "是否需要验证", paramType = "query", defaultValue = ""),
    })
    public Object release(String userNo, String activityType, String activityTitle, String activityDesc, String activityImg,
                          String cityName, String address,
                          String activityTime, Integer hopePersonNum, String personNumScope, boolean hasCharge, BigDecimal price, boolean hasVerify) {
        String activityNo = localActivityService.release(userNo, activityType, activityTitle, activityDesc, activityImg, cityName, address, activityTime, hopePersonNum, personNumScope, hasCharge, price, hasVerify);
        userAccountService.addUserAccount(userNo, new BigDecimal(5));
        // 添加用户增加
        userAccountLogService.inserlog("10050010", new BigDecimal(5), activityNo, userNo, "活动收益");
        return getSuccess(activityNo);
    }
}

