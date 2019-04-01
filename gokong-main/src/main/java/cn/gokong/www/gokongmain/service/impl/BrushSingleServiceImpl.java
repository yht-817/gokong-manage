package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.SendSMS;
import cn.gokong.www.base.util.TimeUtil;
import cn.gokong.www.gokongmain.dao.BrushSingleMapper;
import cn.gokong.www.gokongmain.domain.BrushSingle;
import cn.gokong.www.gokongmain.domain.BrushSingleData;
import cn.gokong.www.gokongmain.service.BrushSingleService;
import cn.gokong.www.gokongmain.vo.brush.BuyOrder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import cn.ikeek.www.ftp.FTPUtil;
import cn.ikeek.www.ftp.enums.DirectoryEnums;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 服务实现类
 */
@Service
public class BrushSingleServiceImpl extends ServiceImpl<BrushSingleMapper, BrushSingle> implements BrushSingleService {

    /**
     * 页面展示的列表
     */
    public ResponseData findBrushSingle(String keyword, int pageno, String phoneNo) {
        System.out.println(pageno + "页码");
        int pagesize = 10;
        BrushSingleData brushSingleData = null;
        ResponseData responseData = new ResponseData();
        int page = (pageno - 1) * pagesize;
        String date = DataBaseTool.getWeeHours();
        List<BrushSingleData> datalist = new ArrayList<>();
        if (phoneNo.equals("15198222800")) {
            phoneNo = "";
        }
        List<Map<String, Object>> data = this.baseMapper.findBrushSingle(keyword, page, date, pagesize, phoneNo, DataBaseTool.getTwoMonth());
        for (int i = 0; i < data.size(); i++) {
            brushSingleData = new BrushSingleData();
            brushSingleData.setSingularno(Integer.valueOf(String.valueOf(data.get(i).get("sumdata"))));
            brushSingleData.setId((String) data.get(i).get("id"));
            brushSingleData.setProductNo((String) data.get(i).get("product_no"));
            brushSingleData.setPictureUrl((String) data.get(i).get("picture_url"));
            brushSingleData.setUpdateId((String) data.get(i).get("update_id"));
            // 查询购买的好评有几张了
            // int buyhighpraise = this.baseMapper.findHighpraiseSum((String) data.get(i).get("product_no"));
            if (Integer.valueOf(String.valueOf(data.get(i).get("high"))) < Integer.valueOf(String.valueOf(data.get(i).get("high_praise"))) && Integer.valueOf(String.valueOf(data.get(i).get("nohigh"))) < Integer.valueOf(String.valueOf(data.get(i).get("nohigh_praise")))) {
                int highPraise = (int) (2 * Math.random()); // 随机生成评论结果
                StaticLog.error("自动生成的结果：" + highPraise);
                brushSingleData.setHighPraise(highPraise);
            } else {
                if (Integer.valueOf(String.valueOf(data.get(i).get("high"))) < Integer.valueOf(String.valueOf(data.get(i).get("high_praise")))) {
                    if (Integer.valueOf(String.valueOf(data.get(i).get("nohigh"))) < Integer.valueOf(String.valueOf(data.get(i).get("nohigh_praise")))) {
                        int highPraise = 1;
                        StaticLog.error("现在是不需要好评了：" + highPraise);
                        brushSingleData.setHighPraise(highPraise);
                    } else {
                        int highPraise = 0;
                        StaticLog.error("好评还不够：" + highPraise);
                        brushSingleData.setHighPraise(highPraise);
                    }
                } else {
                    int highPraise = 0;
                    StaticLog.error("好评的大于不要好评：" + highPraise);
                    brushSingleData.setHighPraise(highPraise);
                }
            }
            brushSingleData.setLimitOrder((Integer) data.get(i).get("limit_order"));
            brushSingleData.setTotalOrder((Integer) data.get(i).get("total_order"));
            brushSingleData.setGetOrder((Integer) data.get(i).get("get_order"));
            brushSingleData.setPageNo((Integer) data.get(i).get("page_no"));
            brushSingleData.setBusinessName((String) data.get(i).get("business_name"));
            brushSingleData.setPrice((BigDecimal) data.get(i).get("price"));
            brushSingleData.setKeyword((String) data.get(i).get("keyword"));
            brushSingleData.setRegion((String) data.get(i).get("region"));
            brushSingleData.setPenny(String.valueOf(data.get(i).get("penny")));
            brushSingleData.setReceiveRules(String.valueOf(data.get(i).get("receive_rules")));
            brushSingleData.setPricesymbol(String.valueOf(data.get(i).get("pricesymbol")));
            if (data.get(i).get("total_order") == data.get(i).get("limit_order")) {
                // 说明就是活动结束的时间 然后 求区间秒数
                int time = DataBaseTool.calLastedTime((Date) data.get(i).get("remaine_time"));
                time = Math.abs(time);
                brushSingleData.setRemaineTime(time);
            } else {
                int time = DataBaseTool.getTimeSum();
                brushSingleData.setRemaineTime(time);
            }
            datalist.add(brushSingleData);
        }
        responseData.setCode(200);
        responseData.setData(datalist);
        responseData.setMessage("返回成功");
        return responseData;
    }

    /**
     * 进行下单处理
     *
     * @param productNo  商品的编码
     * @param phoneNo    用户的手机号码
     * @param highPraise
     * @return
     */
    public ResponseData addPlaceOrder(String productNo, String phoneNo, int highPraise) {
        List<Map<String, String>> paydata = baseMapper.quereyPay(phoneNo);
        ResponseData responseData = new ResponseData();
        Map<String, Object> brushData = this.baseMapper.queryInfo(productNo); // 查询当前商品编码的信息
        Map<String, Object> orderExist = this.baseMapper.queryOrderInfo(productNo, phoneNo);
        if (ObjectUtil.isNotNull(orderExist)) {
            // ===》》》》 说明当前产品购买了
            String pictureurl = (String) orderExist.get("picture_url");
            if (ObjectUtil.isNotNull(pictureurl)) {
                int highpraise = (int) orderExist.get("high_praise");
                // 当等于1是返回详情让上传好评图片
                if (highpraise == 1) {
                    brushData.put("order", (String) orderExist.get("order_no"));
                    brushData.put("payno", (String) orderExist.get("pay_no"));
                    brushData.put("goodpraise", orderExist.get("high_praise"));
                    brushData.put("pay", "1");
                    brushData.put("payinfo", paydata);
                    String userPayNo = (String) orderExist.get("pay_no"); // 判断账号
                    StaticLog.info("用户账号：" + userPayNo);
                    if (!userPayNo.equals("暂无")) {
                        int audit = (int) orderExist.get("audit_status");// 判断当前用户现在的购买了商家的审核状态
                        if (audit == 0) {
                            brushData.put("audit", "审核中");
                        } else if (audit == 2) {
                            brushData.put("audit", "审核不通过<br/>请从新上传");
                        } else {
                            brushData.put("audit", "等待返款");
                        }
                        brushData.put("results", "1");
                    } else {
                        brushData.put("results", "0");
                        brushData.put("audit", "");
                    }
                    responseData.setMessage("参数返回成功");
                    responseData.setCode(200);
                    responseData.setData(brushData);
                    return responseData;
                } else {
                    // 算下单的日期然后返回给前端
                    Date date = (Date) orderExist.get("buy_date");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    brushData.put("order", (String) orderExist.get("order_no"));
                    brushData.put("payno", (String) orderExist.get("pay_no"));
                    brushData.put("time", String.valueOf(DataBaseTool.twoHours(date)));
                    brushData.put("goodpraise", orderExist.get("high_praise")); // 是否需要好评
                    brushData.put("pay", "1");
                    brushData.put("results", "1");
                    brushData.put("payinfo", paydata);
                    // 判断当前用户现在的购买了商家的审核状态
                    int audit = (int) orderExist.get("audit_status");
                    if (audit == 0) {
                        brushData.put("audit", "审核中");
                    } else if (audit == 2) {
                        brushData.put("audit", "审核不通过<br/>请从新上传");
                    } else {
                        brushData.put("audit", "等待返款");
                    }
                    responseData.setMessage("参数返回成功");
                    responseData.setCode(200);
                    responseData.setData(brushData);
                    return responseData;
                }
            } else {
                // 算下单的日期然后返回给前端 pay 等于1是要上传好评图片，0 不需要
                Date date = (Date) orderExist.get("buy_date");
                brushData.put("order", (String) orderExist.get("order_no"));
                brushData.put("time", String.valueOf(DataBaseTool.twoHours(date)));
                brushData.put("pay", "0");
                brushData.put("results", "0");
                brushData.put("audit", "");
                brushData.put("goodpraise", orderExist.get("high_praise"));
                brushData.put("payinfo", paydata);
                responseData.setMessage("参数返回成功");
                responseData.setCode(200);
                responseData.setData(brushData);
                return responseData;
            }
        } else {
            /*<<<<<<==================说明用户没有购买过 // 1.0 进行下单前的查询，是否今天的交易已经够了===================>>>>>*/
            if (phoneNo.equals("15198222800")) {
                String shopname = (String) brushData.get("business_name");
                String order = DataBaseTool.createNo("CKXSD");
                String prices = String.valueOf(brushData.get("price"));
                // 佣金加本金的钱
                BigDecimal commodityprices = BigDecimal.valueOf(Double.valueOf(prices)).add(new BigDecimal("5"));
                // 先查询当前公司的金额是好多
                String company = (String) brushData.get("branch");
                Map<String, Object> data = baseMapper.queryCompanyAmount(company);
                if (!ObjectUtil.isNotNull(data)) {
                    responseData.setMessage("您今天的领取份额已经使用完");
                    responseData.setCode(500);
                    return responseData;
                }
                // 公司的余额
                BigDecimal residueAmount = (BigDecimal) data.get("upload_amount");
                // 是否有信用透支
                int credit = (int) data.get("credit");
                if (commodityprices.compareTo(residueAmount) == 1) {
                    if (credit == 0) { // 没有信用透支的
                        responseData.setMessage("亲！商家份额已经领取完了！");
                        responseData.setCode(500);
                        return responseData;
                    } else {
                        if (residueAmount.compareTo(new BigDecimal("-50")) == -1) {
                            responseData.setMessage("亲！商家份额已经领取完了！");
                            responseData.setCode(500);
                            return responseData;
                        }
                    }
                }
//                int highpraisesum = this.baseMapper.findHighpraiseSum(productNo);// 当前产品下单的好评总数量
//                int nohighpraisesum = this.baseMapper.findNoHighpraiseSum(productNo);// 查询产品下单的不好评总数量
//                // 上传的数量
//                int hp = (int) brushData.get("high_praise");// 产品需要的总好评数量
//                int nohp = (int) brushData.get("nohigh_praise");// 产品需要的总不需要好评数量
                // 先进行扣款处理
                int deductions = baseMapper.deductionsAmount(company, commodityprices);
                if (deductions > 0) {
                    String explain = "商品购买";
                    String explaintype = "100102";
                    int updateLog = baseMapper.insertAmountLog(DataBaseTool.createId(), company, commodityprices, explain, explaintype, productNo, order, DataBaseTool.createDate());
                    StaticLog.error("插入日志的条数:" + updateLog);
                    Date date = DataBaseTool.createDate();
                    int add = this.baseMapper.addPlaceOrder(DataBaseTool.createId(), order, productNo, (String) brushData.get("update_id"), company, phoneNo, highPraise, prices, date, shopname);
                    if (add > 0) {
                        brushData.put("order", order);
                        brushData.put("time", String.valueOf(DataBaseTool.twoHours(date)));
                        brushData.put("pay", "0");
                        brushData.put("results", "0");
                        brushData.put("audit", "");
                        brushData.put("goodpraise", highPraise);
                        brushData.put("payinfo", paydata);
                        responseData.setMessage("参数返回成功");
                        responseData.setCode(200);
                        responseData.setData(brushData);
                        return responseData;
                    }
                }
            } else {
                /** 用户下单区 */
                String wektime = DataBaseTool.getWeeHours(); // 2.0 获取今天凌晨的时间
                int ordersum = this.baseMapper.queryOrderSum(productNo, wektime);
                int limtno = Integer.valueOf(String.valueOf(brushData.get("limit_order")));
                if (ordersum >= limtno) {
                    // 3.0 说明订单表已经超出领取返回
                    responseData.setMessage("您今天的领取份额已经使用完");
                    responseData.setCode(500);
                    return responseData;
                } else {
                    String shopname = (String) brushData.get("business_name");
                    /* 1.0 查询当前手机号在一个月内是否在同家商店买过商品，买过不准再次购买，没有进行购买*/
                    String month = DataBaseTool.getMonth();// 获取当前时间一个月的时间
                    int monthSum = this.baseMapper.findMonthSum(month, phoneNo, shopname);
                    if (monthSum > 0) {
                        responseData.setMessage("您的领取份额已经使用完，明天早点来喔今天");
                        responseData.setCode(500);
                        return responseData;
                    } else {
                        /*2.0 用户在该商店一个月没有购买过任何商品*/
                        // 购买的
                        int highpraisesum = this.baseMapper.findHighpraiseSum(productNo);// 当前产品下单的好评总数量
                        int nohighpraisesum = this.baseMapper.findNoHighpraiseSum(productNo);// 查询产品下单的不好评总数量
                        // 上传的数量
                        int hp = (int) brushData.get("high_praise");// 产品需要的总好评数量
                        int nohp = (int) brushData.get("nohigh_praise");// 产品需要的总不需要好评数量
                        StaticLog.info("现在购买的好评数量：" + highpraisesum + "现在购买的不是好评的数量：" + nohighpraisesum + "上传的好评数量：" + hp + "上传不是好评的数量：" + nohp);
                        // 查询当前用户前期购买的好评单数
                        String datetime = DataBaseTool.getfewDaysTime(30);
                        StaticLog.info("获取的三十天数据：" + datetime);
                        int wellsingular = baseMapper.quereyOrderWell(phoneNo, datetime);
                        int nowellsingular = baseMapper.quereyOrdernoWell(phoneNo, datetime);
                        StaticLog.info("求得的好评单数为：" + wellsingular + "不是好评的数量：" + nohighpraisesum);
                        if (wellsingular > 2) {
                            if (nohighpraisesum >= nohp) {
                                responseData.setMessage("您的好评次数超过规定限制，请关注其他商品！");
                                responseData.setCode(500);
                                return responseData;
                            } else {
                                highPraise = 0;
                            }
                        }
                        String order = DataBaseTool.createNo("11XSD");
                        String prices = String.valueOf(brushData.get("price"));
                        // 佣金加本金的钱
                        BigDecimal commodityprices = BigDecimal.valueOf(Double.valueOf(prices)).add(new BigDecimal("5"));
                        // 先查询当前公司的金额是好多
                        String company = (String) brushData.get("branch");
                        Map<String, Object> data = baseMapper.queryCompanyAmount(company);
                        if (!ObjectUtil.isNotNull(data)) {
                            responseData.setMessage("该商品由于商家问题暂不能领取，请您领取其他商品！");
                            responseData.setCode(500);
                            return responseData;
                        }
                        // 公司的余额
                        BigDecimal residueAmount = (BigDecimal) data.get("upload_amount");
                        int credit = (int) data.get("credit");
                        if (commodityprices.compareTo(residueAmount) == 1) {
                            if (credit == 0) { // 没有信用透支的
                                // 公司电话
                                String phone = (String) data.get("phone");
                                SendSMS.sendSms(phone, residueAmount);
                                responseData.setMessage("亲！商家份额已经领取完了！");
                                responseData.setCode(500);
                                return responseData;
                            } else {
                                // 公司电话
                                String phone = (String) data.get("phone");
                                SendSMS.sendSms(phone, residueAmount);
                                if (residueAmount.compareTo(new BigDecimal("-30")) == -1) {
                                    responseData.setMessage("亲！商家份额已经领取完了！");
                                    responseData.setCode(500);
                                    return responseData;
                                }
                            }
                        }
                        // 先进行扣款处理
                        int deductions = baseMapper.deductionsAmount(company, commodityprices);
                        if (deductions > 0) {
                            String explain = "商品购买";
                            String explaintype = "100102";
                            int updateLog = baseMapper.insertAmountLog(DataBaseTool.createId(), company, commodityprices, explain, explaintype, productNo, order, DataBaseTool.createDate());
                            StaticLog.error("插入日志的条数:" + updateLog);
                            Date date = DataBaseTool.createDate();
                            int add = this.baseMapper.addPlaceOrder(DataBaseTool.createId(), order, productNo, (String) brushData.get("update_id"), company, phoneNo, highPraise, prices, date, shopname);
                            if (add > 0) {
                                brushData.put("order", order);
                                brushData.put("time", String.valueOf(DataBaseTool.twoHours(date)));
                                brushData.put("pay", "0");
                                brushData.put("results", "0");
                                brushData.put("audit", "");
                                brushData.put("goodpraise", highPraise);
                                brushData.put("payinfo", paydata);
                                responseData.setMessage("参数返回成功");
                                responseData.setCode(200);
                                responseData.setData(brushData);
                                return responseData;
                            }
                        }
                    }
                }
            }
        }
        responseData.setMessage("您今天的领取份额已经使用完");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * @param img     图片的流
     * @param orderNo 订单号
     * @param payNo   支付账号
     * @param payType
     * @return
     */
    public ResponseData addOrderUrl(MultipartFile img, String orderNo, String payNo, String payType) {
        // 上传信息时间
        Date date = DataBaseTool.createDate();
        StaticLog.info("订单编号" + orderNo + "支付号码：" + payNo);
        ResponseData responseData = new ResponseData();
        String path = null;
        try {
            path = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010009, img.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 查询当前数据是否为空：空 +1 ， 不为空：不加
        Map<String, Object> isNot = this.baseMapper.queryIsQuery(orderNo);
        if (ObjectUtil.isNotNull(isNot)) {
            if (ObjectUtil.isNotNull(isNot.get("picture_url"))) {
                StaticLog.info("不进行相加了（说明已经上传过了）");
            } else {
                // 说明为空进行 +1
                this.baseMapper.addOrder((String) isNot.get("product_no"));
            }
            if (ObjectUtil.isNotNull(path)) {
                if (ObjectUtil.isNotNull(orderNo) && ObjectUtil.isNotNull(payNo)) {
                    if (ObjectUtil.isNotNull(isNot)) {
                        // 判断是否好评，不需要好评的都插入picture_url字段，要好评的第一次插入picture_url，第二次插入highpraise_url
                        if (ObjectUtil.isNotNull(isNot.get("picture_url"))) {
                            // 说明不是第一次插入了，好评插入highpraise_url，不是好评插入picture_url
                            if ((int) isNot.get("high_praise") == 1) {
                                if (ObjectUtil.isNotNull(isNot.get("highpraise_url"))) {
                                    int up = this.baseMapper.updateOrderInfoHURL(path, orderNo, date);
                                    if (up > 0) {
                                        responseData.setMessage("上传信息成功");
                                        responseData.setCode(200);
                                        return responseData;
                                    }
                                } else {
                                    Map<String, Object> datapay = baseMapper.queryPayNo((String) isNot.get("phone_no"), payType);
                                    if (!ObjectUtil.isNotNull(datapay)) {
                                        // 插入当前数据
                                        int insertdatapay = baseMapper.insertPayNo((String) isNot.get("phone_no"), payType, payNo);
                                    } else {
                                        if (!datapay.get("pay_no").equals(payNo)) {
                                            // 修改账号
                                            int updatePayNo = baseMapper.updatePayNo((String) isNot.get("phone_no"), payType, payNo);
                                            StaticLog.info("修改支付账号的状态：" + updatePayNo);
                                        }
                                    }
                                    int up = this.baseMapper.updateOrderInfoHighpraiseUrl(path, payNo, orderNo, date);
                                    if (up > 0) {
                                        responseData.setMessage("上传信息成功");
                                        responseData.setCode(200);
                                        return responseData;
                                    }
                                }
                            } else {
                                // 不需要好评的修改
                                int up = this.baseMapper.updateOrderInfoURL(path, orderNo, date);
                                if (up > 0) {
                                    responseData.setMessage("上传信息成功");
                                    responseData.setCode(200);
                                    return responseData;
                                }
                            }
                        } else {
                            // 说明当前都是第一次插入图片
                            if ((int) isNot.get("high_praise") == 0) {
                                // 插入不是好评的购买账号
                                // 先用当前电话号和账号类型查询当前数据是否存在
                                Map<String, Object> datapay = baseMapper.queryPayNo((String) isNot.get("phone_no"), payType);
                                if (!ObjectUtil.isNotNull(datapay)) {
                                    // 插入当前数据
                                    int insertdatapay = baseMapper.insertPayNo((String) isNot.get("phone_no"), payType, payNo);
                                    StaticLog.info("插入支付账号的状态：" + insertdatapay);
                                } else {
                                    if (!datapay.get("pay_no").equals(payNo)) {
                                        // 修改账号
                                        int updatePayNo = baseMapper.updatePayNo((String) isNot.get("phone_no"), payType, payNo);
                                        StaticLog.info("修改支付账号的状态：" + updatePayNo);
                                    }
                                }
                            }
                            int up = this.baseMapper.updateOrderInfo(path, payNo, orderNo, date);
                            if (up > 0) {
                                responseData.setMessage("上传信息成功");
                                responseData.setCode(200);
                                return responseData;
                            }
                        }
                    }
                }
            }
        }
        responseData.setMessage("服务出错啦！");
        responseData.setCode(500);
        return responseData;
    }


    /**
     * 查询当前用户的订单
     *
     * @param phoneNo 电话号码
     * @return
     */
    public Object queryOrder(String phoneNo) {
        ResponseData responseData = new ResponseData();
        Map<String, Object> data = new HashMap<>();
        // 没有打款的
        List<BuyOrder> ordermap = this.baseMapper.queryOrder(phoneNo);
        StaticLog.info("查询的参数：" + ordermap.toString());
        // 已经打款的
        List<BuyOrder> orderpay = this.baseMapper.queryOrderPay(phoneNo);
        data.put("ordernopay", ordermap);
        data.put("orderpay", orderpay);
        responseData.setCode(200);
        responseData.setMessage("返回参数成功");
        responseData.setData(data);
        return responseData;
    }

    /**
     * 查询下线的人信息
     *
     * @param phoneNo
     * @param offphoneNo
     */
    public Object queryOffline(String phoneNo, String offphoneNo) {
        List<Map<String, String>> datalist = new ArrayList<>();
        Map<String, String> datal = null;
        ResponseData responseData = new ResponseData();
        Map<String, Object> data = new HashMap<>();
        // 查询当前用户是否是市长
        Map<String, Object> onOnUserInfo = this.baseMapper.queryOnOnUserInfo(phoneNo);
        if (ObjectUtil.isNotNull(onOnUserInfo)) {
            // 当前用户是市长（查看是分享的市长还是公司的市长）
            // 分享的市长还有普通用户
            List<Map<String, Object>> datap = this.baseMapper.queryOffline(phoneNo, offphoneNo);
            List<Map<String, Object>> ordermap = this.baseMapper.queryOnOffline(phoneNo, offphoneNo);
            for (int i = 0; i < ordermap.size(); i++) {
                datal = new HashMap<>();
                datal.put("under_no", (String) ordermap.get(i).get("phone_no"));
                Date date = (Date) ordermap.get(i).get("daytime");
                datal.put("bind_date", TimeUtil.getChatTimeStr(date.getTime()));
                datalist.add(datal);
            }
            for (int j = 0; j < datap.size(); j++) {
                datal = new HashMap<>();
                datal.put("under_no", (String) datap.get(j).get("under_no"));
                Date date = (Date) datap.get(j).get("bind_date");
                datal.put("bind_date", TimeUtil.getChatTimeStr(date.getTime()));
                datalist.add(datal);
            }
        } else {
            List<Map<String, Object>> ordermap = this.baseMapper.queryOffline(phoneNo, offphoneNo);
            for (int i = 0; i < ordermap.size(); i++) {
                datal = new HashMap<>();
                datal.put("under_no", (String) ordermap.get(i).get("under_no"));
                Date date = (Date) ordermap.get(i).get("bind_date");
                datal.put("bind_date", TimeUtil.getChatTimeStr(date.getTime()));
                datalist.add(datal);
            }
        }
        data.put("sum", data.size());
        data.put("data", datalist);
        responseData.setData(data);
        responseData.setCode(200);
        responseData.setMessage("操作成功");
        return responseData;
    }

    /**
     * 绑定用户的信息
     *
     * @param phoneNo 被绑定的用户
     * @param partNo  绑定的主体用户
     */
    public Object registerRelation(String phoneNo, String partNo) {
        ResponseData responseData = new ResponseData();
        /**
         *  还要查询绑定的人是否是顶级的人
         */
        Map<String, Object> datao = this.baseMapper.queryOnOnUserInfo(phoneNo);
        if (ObjectUtil.isNotNull(datao)) {
            responseData.setMessage("操作失败");
            responseData.setCode(500);
            return responseData;
        }
        /**
         *  先查询当前手机号码是否被绑定
         */
        StaticLog.info("绑定的用户号码：" + phoneNo + "上线的手机号码：" + partNo);
        if (phoneNo.equals(partNo)) {
            responseData.setMessage("操作失败");
            responseData.setCode(500);
            return responseData;
        }
        int esitUser = this.baseMapper.queryUserEsit(phoneNo);
        StaticLog.info("查询的结果：" + esitUser);
        if (esitUser == 0) {
            int register = this.baseMapper.registerRelation(DataBaseTool.createId(), phoneNo, partNo, DataBaseTool.createDate());
            if (register > 0) {
                responseData.setMessage("操作成功");
                responseData.setCode(200);
                return responseData;
            }
        }
        StaticLog.info("已经有绑定关系");
        responseData.setMessage("操作失败");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 绑定上线的上线的用户
     *
     * @param phoneNo 被绑定的用户
     * @param partNo  邀请绑定的用户
     * @return
     */
    public Object registerOnRelation(String phoneNo, String partNo) {
        ResponseData responseData = new ResponseData();
        if (phoneNo.equals(partNo)) {
            responseData.setMessage("操作失败");
            responseData.setCode(500);
            return responseData;
        }
        // 查询用户的信息
        Map<String, Object> userinfo = this.baseMapper.queryUserInfo(phoneNo);
        String userno = (String) userinfo.get("user_no");
        //查询被其他用户绑定没有
        int onuser = this.baseMapper.quereyOnUserEsit(userno);
        if (onuser == 0) {
            int inserOnUser = this.baseMapper.insertOnUser(userno, phoneNo, partNo, DataBaseTool.createDate());
            if (inserOnUser > 0) {
                // 说明没有绑定进行绑定
                responseData.setMessage("操作成功");
                responseData.setCode(200);
                return responseData;
            }
        }
        StaticLog.info("已经有绑定关系");
        responseData.setMessage("操作失败");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 查询当前用户二维码信息
     *
     * @param phoneNo
     */
    public Map<String, String> queryCode(String phoneNo) {
        return baseMapper.queryCode(phoneNo);
    }

    /**
     * 查询当前手机号码是否为议员
     */
    public Map<String, String> queryMps(String phoneNo) {
        return baseMapper.queryMps(phoneNo);
    }

    /**
     * 插入二维码记录
     *
     * @param id
     * @param phoneNo
     * @param imgCode  普通用户
     * @param imgCode1 议员
     * @return
     */
    public int insertMurl(String id, String phoneNo, String imgCode, String imgCode1) {
        return baseMapper.insertMurl(id, phoneNo, imgCode, imgCode1);
    }

    /**
     * 查询当前用户的金额和信息资料
     *
     * @param userNo 用户编码
     */
    public Object queryTransactionAmount(String userNo) {
        ResponseData responseData = new ResponseData();
        Map<String, Object> data = new HashMap<>();
        // 获取当前时间一天开始时间
        String daytime = DataBaseTool.getWeeHours();
        StaticLog.info("当前天的开始时间为：" + daytime);
        // 查询当前用户是否是上线的上线
        Map<String, Object> onesit = this.baseMapper.queryOnOnline(userNo);
        if (onesit != null) {
            data.put("whether", true);
            // 市长既能邀请议员也能邀请普通用户
            data.put("permissions", true);
        } else {
            // 1.0 查询当前用户是否是上线
            Map<String, Object> esit = this.baseMapper.queryOnlineInfo(userNo);
            if (esit != null) {
                data.put("whether", true);
                data.put("permissions", false);
            } else {
                data.put("whether", false);
            }
        }
        // 剩余的总金额online_account
        Map<String, BigDecimal> amountdata = this.baseMapper.querySumAmount(userNo);
        BigDecimal amount = new BigDecimal("0");
        if (ObjectUtil.isNotNull(amountdata)) {
            amount = amountdata.get("amount");
        }
        // 2.0 获取今天一天的金额
        String dayamount = this.baseMapper.queryDayAmount(userNo, daytime);
        data.put("sumAmount", amount);
        data.put("dayAmount", dayamount);
        responseData.setCode(200);
        responseData.setData(data);
        responseData.setMessage("返回参数成功");
        return responseData;
    }

    /**
     * 查询是否是上线的上线
     *
     * @param phoneNo
     * @return
     */
    public Map<String, Object> queryOnOnUserInfo(String phoneNo) {
        return this.baseMapper.queryOnOnUserInfo(phoneNo);
    }

    /**
     * 进行提现处理
     *
     * @param userNo  用户编码
     * @param payNo   支付账号
     * @param amount
     * @param payType
     * @param phoneNo
     * @return
     */
    public Object withdrawal(String userNo, String payNo, String amount, String payType, String phoneNo) {
        StaticLog.error("用户编码：" + userNo + "支付账号：" + payNo + "提现金额：" + amount + "支付方式：" + payType + "电话号码：" + phoneNo);
        ResponseData responseData = new ResponseData();
        Date date = DataBaseTool.createDate();
        String id = DataBaseTool.createId();
        if (Integer.valueOf(amount) >= 100) {
            if (Integer.valueOf(amount) % 100 == 0) {
                String order = DataBaseTool.createNo("YJ");
                if (ObjectUtil.isNotNull(userNo) && ObjectUtil.isNotNull(payNo) && ObjectUtil.isNotNull(amount)) {
                    // 查询该用户是否在一个月提现大于两次
                    String datetime = DataBaseTool.getfewDaysTime(7);
                    StaticLog.info("获取的时间：" + datetime);
                    int txsum = this.baseMapper.queryTxSum(datetime, userNo);
                    if (txsum >= 1) {
                        responseData.setCode(500);
                        responseData.setMessage("您超过七天规定次数...");
                        return responseData;
                    } else {
                        // 1.0 查询该用户的余额
                        Map<String, BigDecimal> amountsumdata = this.baseMapper.querySumAmount(userNo);
                        BigDecimal amountsum = new BigDecimal("0");
                        if (ObjectUtil.isNotNull(amountsumdata)) {
                            amountsum = amountsumdata.get("amount");
                        }
                        StaticLog.error("查询的金额：" + amountsum);
                        BigDecimal useramount = BigDecimal.valueOf(Long.valueOf(amount));
                        if (amountsum.compareTo(useramount) == -1) {
                            responseData.setCode(500);
                            responseData.setMessage("你的金额不足...");
                            return responseData;
                        } else {
                            Map<String, Object> datapay = baseMapper.queryPayNo(phoneNo, payType);
                            if (!ObjectUtil.isNotNull(datapay)) {
                                // 插入当前数据
                                int insertdatapay = baseMapper.insertPayNo(phoneNo, payType, payNo);
                                StaticLog.info("添加支付账号的状态：" + insertdatapay);
                            } else {
                                if (!datapay.get("pay_no").equals(payNo)) {
                                    // 修改账号
                                    int insertdatapay = baseMapper.updatePayNo(phoneNo, payType, payNo);
                                    StaticLog.info("修改支付账号的状态：" + insertdatapay);
                                }
                            }
                            // 表示总金额大于提现金额
                            int inserLog = this.baseMapper.addOrderLog(id, userNo, amount, date, order, payNo);
                            if (inserLog > 0) {
                                // 然后进行扣款处理
                                int de_useramount = this.baseMapper.deleteUserAmont(userNo, amount);
                                if (de_useramount > 0) {
                                    responseData.setCode(200);
                                    responseData.setMessage("提现中...");
                                    return responseData;
                                } else {
                                    // 删除当前提现订单
                                    this.baseMapper.deleteOrder(order);
                                    responseData.setCode(500);
                                    responseData.setMessage("提现失败...");
                                    return responseData;
                                }
                            } else {
                                responseData.setCode(500);
                                responseData.setMessage("提现失败...");
                                return responseData;
                            }
                        }
                    }
                } else {
                    responseData.setCode(500);
                    responseData.setMessage("提现失败...");
                    return responseData;
                }
            } else {
                responseData.setCode(500);
                responseData.setMessage("请输入100的倍数...");
                return responseData;
            }
        } else {
            responseData.setCode(500);
            responseData.setMessage("请输入100的倍数...");
            return responseData;
        }
    }

    /**
     * 查询用户绑定的付款账号
     *
     * @param phoneNo 电话号码
     * @return
     */
    public Object queryPayAccount(String phoneNo) {
        ResponseData responseData = new ResponseData();
        List<Map<String, String>> queryPayAccountData = baseMapper.queryPayAccount(phoneNo);
        if (ObjectUtil.isNotNull(queryPayAccountData)) {
            responseData.setData(queryPayAccountData);
            responseData.setMessage("查询支付账号成功");
            responseData.setCode(200);
            return responseData;
        }
        responseData.setMessage("查询支付账号数据为空");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 修改支付账号
     *
     * @param phoneNo 手机号码
     * @param payType 支付类型
     * @param payNo   支付账号
     * @return
     */
    public Object changePayAccount(String phoneNo, String payType, String payNo) {
        int insertdatapay = 0;
        ResponseData responseData = new ResponseData();
        Map<String, Object> datapay = baseMapper.queryPayNo(phoneNo, payType);
        if (!ObjectUtil.isNotNull(datapay)) {
            // 插入当前数据
            insertdatapay = baseMapper.insertPayNo(phoneNo, payType, payNo);
        } else {
            // 修改账号
            insertdatapay = baseMapper.updatePayNo(phoneNo, payType, payNo);
        }
        if (insertdatapay > 0) {
            responseData.setCode(200);
            responseData.setMessage("添加账号成功");
            return responseData;
        }
        responseData.setCode(500);
        responseData.setMessage("添加账号失败请稍后重试！");
        return responseData;
    }

}
