package cn.gokong.www.gokongmain.web;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.service.*;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.ikeek.www.emoji.EmojiUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 说明:公用Base
 *
 * @author ideac
 * @CreateDate 2018/9/8 1:12
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Controller
public class BaseController {

    /**
     * 静态实例构建
     */
    protected static final ResponseData SUCCESS_TIP = new ResponseData("操作成功", 200);

    protected static final ResponseData FAIL_TIP = new ResponseData("操作失败", 500);

    public static void main(String[] args) {
        String ss = JSONObject.toJSONString(SUCCESS_TIP, SerializerFeature.WriteNullStringAsEmpty);
        System.out.println(ss);
        System.out.println(JSONUtil.parseObj(ss));
    }

    /**
     * 响应成功实例
     *
     * @return
     */
    public ResponseData getSuccess() {
        return SUCCESS_TIP;
    }

    /**
     * 响应成功实例
     *
     * @return
     */
    public ResponseData getBoolean(boolean isok) {
        return isok ? getSuccess() : getFail();
    }

    /**
     * 响应失败实例
     *
     * @return
     */
    public ResponseData getFail() {
        return FAIL_TIP;
    }

    /**
     * 响应对象实例
     *
     * @param o 数据对象
     * @return
     */
    public ResponseData getSuccess(Object o) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage("操作成功");
        responseData.setCode(200);
        if (ObjectUtil.isNotNull(o)) {
            String s = EmojiUtil.strToEmoji(JSONObject.toJSONString(o, SerializerFeature.WriteNullStringAsEmpty));
            responseData.setData(JSONObject.parse(s));
        }
        return responseData;
    }

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    MerchantsService merchantsService;

    @Autowired
    CollectionInfoService collectionInfoService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    RechargeSetMealService rechargeSetMealService;

    @Autowired
    UserAccountLogService userAccountLogService;

    @Autowired
    ChoiceService choiceService;

    @Autowired
    UiControlService uiControlService;

    @Autowired
    FriendGroupService friendGroupService;

    @Autowired
    UserHomePageService userHomePageService;

    @Autowired
    UserHomePageVisitService userHomePageVisitService;

    @Autowired
    ChineseCircleService chineseCirclevice;

    @Autowired
    InformationService informationService;

    @Autowired
    UploadService uploadService;

    @Autowired
    ServiceScopeTwoService serviceScopeTwoService;

    @Autowired
    FriendGroupUserService friendGroupUserService;

    @Autowired
    FriendInviteService friendInviteService;

    @Autowired
    MerchantsEvaluateService merchantsEvaluateService;

    @Autowired
    InternationalCodeService internationalCodeService;

    @Autowired
    FansInfoService fansInfoService;

    @Autowired
    InformationEvaluateService informationEvaluateService;

    @Autowired
    InformationEvaluateReplyService informationEvaluateReplyService;

    @Autowired
    InformationEvaluatePariseService informationEvaluatePariseService;

    @Autowired
    ChatServiceMessageService chatServiceMessageService;

    @Autowired
    ChoiceUserPayService choiceUserPayService;

    @Autowired
    UserRechargeService userRechargeService;

    @Autowired
    ChineseCirclePraiseService chineseCirclePraiseService;

    @Autowired
    ChineseCircleEvaluateService chineseCircleEvaluateService;

    @Autowired
    ChineseCircleEvaluatePariseService chineseCircleEvaluatePariseService;

    @Autowired
    MarketingSendSetService marketingSendSetService;

    @Autowired
    MarketingPopSetService marketingPopSetService;

    @Autowired
    CattlePeopleService cattlePeopleService;

    @Autowired
    PayApplyService payApplyService;

    @Autowired
    BrushSingleService brushSingleService;

    @Autowired
    OverseasBusinessService overseasBusinessService;

    @Autowired
    OverseasBusinessApplyService overseasBusinessApplyService;

    @Autowired
    MobileVersionService mobileVersionService;

    @Autowired
    UsedDealTypeService usedDealTypeService;

    @Autowired
    UsedDealService usedDealService;

    @Autowired
    UsedDealLeaveService usedDealLeaveService;

    @Autowired
    CompanyRecruitmentService companyRecruitmentService;

    @Autowired
    PositionTypeService positionTypeService;

    @Autowired
    LocalActivityService localActivityService;

    @Autowired
    LocalActivityApplyService localActivityApplyService;

    @Autowired
    CityOverviewService cityOverviewService;

    @Autowired
    ActivityTabloidService activityTabloidService;

    @Autowired
    SysCodeService sysCodeService;

    @Autowired
    MobileVersionAmazonService mobileVersionAmazonService;

    @Autowired
    WeiXinJsSdkService weiXinJsSdkService;

    @Autowired
    UserPayDetailService userPayDetailService;

    @Autowired
    UserLogService userLogService;

    @Autowired
    UserAuthsService userAuthsService;
}
