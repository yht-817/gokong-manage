package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.crypto.AesBase64Util;
import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.imgcodetools.CodeImgTools;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 刷单的控制器
 * </p>
 *
 * @author tom
 * @since 2018-09-25
 */
@RestController
@RequestMapping("/brushSingle")
@Api(tags = "刷单显示列表", description = "BrushSingleContrroller")
public class BrushSingleContrroller extends BaseController {

    /**
     * 展示刷单的数据列表
     */
    @RequestMapping(value = "/findBrushSingle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "刷单的数据列表", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "搜索的关键词", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "分页的页码", paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "分页的页码", paramType = "query"),
    })
    public Object findBrushSingle(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String keyword = requestData.getData().get("keyWord");
        int pageno = Integer.valueOf(requestData.getData().get("pageNo"));
        String phoneNo = requestData.getData().get("phoneNo");
        ResponseData responseData = brushSingleService.findBrushSingle(keyword, pageno, phoneNo);
        return responseData;
    }

    /**
     * 进行下单处理然后进行上传
     */
    @RequestMapping(value = "/addPlaceOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "下单", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productNo", value = "商品的编码号", paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "用户的电话号码", paramType = "query"),
            @ApiImplicitParam(name = "highPraise", value = "评论类型", paramType = "query"),
    })
    public Object addPlaceOrder(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String productNo = requestData.getData().get("productNo");
        String phoneNo = requestData.getData().get("phoneNo");
        int highPraise = Integer.valueOf(requestData.getData().get("highPraise"));
        ResponseData responseData = brushSingleService.addPlaceOrder(productNo, phoneNo,highPraise);
        return responseData;
    }

    /**
     * 上传图片信息
     */
    @RequestMapping(value = "/addOrderUrl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "成功后上传信息", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", value = "上传好评的图片", paramType = "query"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", paramType = "query"),
            @ApiImplicitParam(name = "payNo", value = "支付的账号", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "支付的账号类型", paramType = "query"),
    })
    public Object addOrderUrl(@RequestParam("img") MultipartFile img, @RequestParam String orderNo, @RequestParam String payNo, @RequestParam String payType) {
        return brushSingleService.addOrderUrl(img, orderNo, payNo, payType);
    }


    /**
     * 查询当前用户的订单
     */
    @RequestMapping(value = "/queryOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户购买的订单", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
    })
    public Object queryOrder(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String phoneNo = requestData.getData().get("phoneNo");
        return brushSingleService.queryOrder(phoneNo);
    }


    /**
     * 查询下线的人
     */
    @RequestMapping(value = "/queryOffline", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询下线的人的信息", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
            @ApiImplicitParam(name = "offphoneNo", value = "下线的手机号码", paramType = "query"),
    })
    public Object queryOffline(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String phoneNo = requestData.getData().get("phoneNo");
        String offphoneNo = requestData.getData().get("offphoneNo");
        StaticLog.info("主手机：" + phoneNo + "下线：" + offphoneNo);
        return brushSingleService.queryOffline(phoneNo, offphoneNo);
    }

    /**
     * 生成分享的链接
     */
    @RequestMapping(value = "/createShare", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "生成分享的链接", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
    })
    public Object createShare(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, Object> data = new HashMap<>();
        ResponseData responseData = new ResponseData();
        String phoneNo = requestData.getData().get("phoneNo");
        String decryptStr = AesBase64Util.encryptAES(phoneNo);
        // 查询当前手机号码的链接
        Map<String, String> codeurl = brushSingleService.queryCode(phoneNo);
        // 查询当前手机是否为市长
        Map<String, Object> mayor = brushSingleService.queryOnOnUserInfo(phoneNo);
        // 查询当前手机是否为议员
        Map<String, String> mps = brushSingleService.queryMps(phoneNo);
        // 判断用户的等级
        if (ObjectUtil.isNotNull(mayor)) {
            // 说明是市长
            if (ObjectUtil.isNotNull(codeurl)) {
                data.put("surl", codeurl.get("mps_url"));
                data.put("url", codeurl.get("people_url"));
                responseData.setCode(200);
                responseData.setData(data);
                responseData.setMessage("分享链接生成成功");
                return responseData;
            } else {
                // 议员
                String mpsurl = CodeImgTools.imgCode("http://h5.sunwukong.net/v2/components/brush.html?id=ON=" + decryptStr, 1);
                // 普通市民
                String purl = CodeImgTools.imgCode("http://h5.sunwukong.net/v2/components/brush.html?id=NO=" + decryptStr, 0);
                int insertMurl = brushSingleService.insertMurl(DataBaseTool.createId(), phoneNo, purl, mpsurl);
                data.put("url", purl);
                data.put("surl", mpsurl);
                responseData.setCode(200);
                responseData.setData(data);
                responseData.setMessage("分享链接生成成功");
                return responseData;
            }
        } else {
            if (ObjectUtil.isNotNull(mps)) {
                if (ObjectUtil.isNotNull(codeurl)) {
                    data.put("url", codeurl.get("people_url"));
                    responseData.setCode(200);
                    responseData.setData(data);
                    responseData.setMessage("分享链接生成成功");
                    return responseData;
                } else {
                    String purl = CodeImgTools.imgCode("http://h5.sunwukong.net/v2/components/brush.html?id=NO=" + decryptStr, 0);
                    int insertMurl = brushSingleService.insertMurl(DataBaseTool.createId(), phoneNo, purl, null);
                    data.put("url", purl);
                    responseData.setCode(200);
                    responseData.setData(data);
                    responseData.setMessage("分享链接生成成功");
                    return responseData;
                }
            } else {
                data.put("ordinaryurl", CodeImgTools.imgCode("http://h5.sunwukong.net/v2/components/brush.html", 0));
                responseData.setCode(200);
                responseData.setData(data);
                responseData.setMessage("分享链接生成成功");
                return responseData;
            }
        }
    }

    /**
     * 用户点击链接然后注册信息
     */
    @RequestMapping(value = "/registerRelation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户点击链接然后注册信息", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "连接上生成的ID", paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
    })
    public Object registerRelation(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        System.out.println(requestData.getData().toString());
        String phoneNo = requestData.getData().get("phoneNo");
        String id = requestData.getData().get("id");
        StaticLog.info("获取的参数" + id);
        // 截取字符串然后判断是上线的上线还是只是上线
        if (ObjectUtil.isNotNull(id)) {
            String partNo = AesBase64Util.decryptAES(id.substring(3, id.length()));
            Object object = null;
            StaticLog.info("截取的符号:" + id.substring(0, 3) + "解密的电话号码：" + partNo);
            if (id.substring(0, 3).equals("ON=")) {
                // 说明是上线的上线发展上线
                StaticLog.info("说明是上线的上线发展上线");
                object = brushSingleService.registerOnRelation(phoneNo, partNo);
            } else {
                // 说明名只是上线发展下线
                StaticLog.info("说明名只是上线发展下线");
                object = brushSingleService.registerRelation(phoneNo, partNo);
            }
            return object;
        } else {
            responseData.setMessage("当前天的订单领取完啦，请关注其他商品");
            responseData.setCode(500);
            return responseData;
        }
    }

    /**
     * 1.0 根据用户编码查询当前用户的收益金额
     * 2.0 查询当前用户是否是上线的用户
     */
    @RequestMapping(value = "/queryTransactionAmount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询用户的信息和金额", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
    })
    public Object queryTransactionAmount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String userNo = requestData.getData().get("userNo");
        return brushSingleService.queryTransactionAmount(userNo);
    }

    /**
     * 用户进行提现处理
     */
    @RequestMapping(value = "/withdrawal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户进行提现处理", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "用户电话", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "支付类型", paramType = "query"),
            @ApiImplicitParam(name = "payNo", value = "提现账号", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "提现金额", paramType = "query"),
    })
    public Object withdrawal(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String userNo = requestData.getData().get("userNo");
        String payNo = requestData.getData().get("payNo");
        String amount = requestData.getData().get("amount");
        String payType = requestData.getData().get("payType");
        String phoneNo = requestData.getData().get("phoneNo");
        return brushSingleService.withdrawal(userNo, payNo, amount, payType, phoneNo);
    }


    /**
     * 查询用户绑定的付款账号
     */
    @RequestMapping(value = "/queryPayAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询用户绑定的付款账号", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
    })
    public Object queryPayAccount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String phoneNo = requestData.getData().get("phoneNo");
        return brushSingleService.queryPayAccount(phoneNo);
    }

    /**
     * 修改用户绑定的付款账号
     */
    @RequestMapping(value = "/changePayAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改用户绑定的付款账号", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "支付类型", paramType = "query"),
            @ApiImplicitParam(name = "payNo", value = "支付账号", paramType = "query"),
    })
    public Object changePayAccount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String phoneNo = requestData.getData().get("phoneNo");
        String payType = requestData.getData().get("payType");
        String payNo = requestData.getData().get("payNo");
        return brushSingleService.changePayAccount(phoneNo, payType, payNo);
    }

}

