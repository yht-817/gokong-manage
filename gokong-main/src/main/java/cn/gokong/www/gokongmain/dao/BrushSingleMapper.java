package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.BrushSingle;
import cn.gokong.www.gokongmain.vo.brush.BuyOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-25
 */
public interface BrushSingleMapper extends BaseMapper<BrushSingle> {

    List<Map<String, Object>> findBrushSingle(@Param("keyword") String keyword, @Param("page") int page, @Param("date") String date, @Param("pagesize") int pagesize, @Param("phoneNo") String phoneNo, @Param("twoMonth") String twoMonth);

    @Select("SELECT * FROM brush_single WHERE product_no = #{productNo}")
    Map<String, Object> queryInfo(@Param("productNo") String productNo);

    /**
     * 进行下单
     *
     * @param id
     * @param order
     * @param productNo
     * @param updateId
     * @param branch
     * @param phoneNo
     * @param highPraise
     * @param price
     * @param date
     * @return
     */
    @Insert("INSERT INTO shop_order(id,order_no,product_no,high_praise,phone_no,update_id,price,buy_date,branch,shop_name)VALUES(#{id},#{order},#{productNo},#{highPraise},#{phoneNo},#{updateId},#{price},#{date},#{branch},#{shopname})")
    int addPlaceOrder(@Param("id") String id, @Param("order") String order, @Param("productNo") String productNo, @Param("updateId") String updateId,
                      @Param("branch") String branch, @Param("phoneNo") String phoneNo, @Param("highPraise") int highPraise, @Param("price") String price, @Param("date") Date date, @Param("shopname") String shopname);


    /**
     * 查询是否下单而且没有进行上传信息的
     *
     * @param productNo
     * @param phoneNo
     * @return
     */
    @Select("SELECT * FROM shop_order WHERE phone_no = #{phoneNo} AND product_no = #{productNo}")
    Map<String, Object> queryOrderInfo(@Param("productNo") String productNo, @Param("phoneNo") String phoneNo);

    /**
     * 查询下单的次数是否超过设定的限制
     *
     * @param productNo
     * @param wektime
     * @return
     */
    @Select("SELECT count(id) FROM shop_order where buy_date >= #{wektime} AND product_no = #{productNo}")
    int queryOrderSum(@Param("productNo") String productNo, @Param("wektime") String wektime);

    @Update("UPDATE shop_order SET pay_no = #{payNo},picture_url=#{path},upload_time=#{date} WHERE order_no = #{orderNo}")
    int updateOrderInfo(@Param("path") String path, @Param("payNo") String payNo, @Param("orderNo") String orderNo, @Param("date") Date date);


    /**
     * 插入好评截图
     *
     * @param path
     * @param payNo
     * @param orderNo
     * @param date
     * @return
     */
    @Update("UPDATE shop_order SET pay_no = #{payNo},highpraise_url=#{path},upload_time=#{date} WHERE order_no = #{orderNo}")
    int updateOrderInfoHighpraiseUrl(@Param("path") String path, @Param("payNo") String payNo, @Param("orderNo") String orderNo, @Param("date") Date date);

    /**
     * @param month    当前时间一个月前的时间
     * @param phoneNo  电话号码
     * @param shopname 商店名字
     * @return
     */
    @Select("SELECT count(id) FROM shop_order WHERE phone_no = #{phoneNo} AND shop_name = #{shopname} AND buy_date >= #{month}")
    int findMonthSum(@Param("month") String month, @Param("phoneNo") String phoneNo, @Param("shopname") String shopname);

    /**
     * 查看当前的商品好评有几单
     *
     * @param productNo
     * @return
     */

    @Select("SELECT count(id) FROM shop_order WHERE high_praise = 1 AND product_no = #{productNo}")
    int findHighpraiseSum(@Param("productNo") String productNo);

    /**
     * 查询不是好评的几单
     *
     * @param productNo
     * @return
     */
    @Select("SELECT count(id) FROM shop_order WHERE high_praise = 0 AND product_no = #{productNo}")
    int findNoHighpraiseSum(String productNo);

    /**
     * 查询当前用户的订单
     *
     * @param phoneNo
     */
    List<BuyOrder> queryOrder(@Param("phoneNo") String phoneNo);

    /**
     * 已经打款的数据
     *
     * @param phoneNo
     * @return
     */
    List<BuyOrder> queryOrderPay(@Param("phoneNo") String phoneNo);

    /**
     * 当前用户绑定的人数
     *
     * @param phoneNo
     * @param offphoneNo
     * @return
     */
    List<Map<String, Object>> queryOffline(@Param("phoneNo") String phoneNo, @Param("offphoneNo") String offphoneNo);

    /**
     * 绑定账号
     *
     * @param id
     * @param phoneNo 被绑定账号
     * @param partNo  主题账号
     * @param date
     * @return
     */
    @Insert("INSERT INTO distribution_relationship(id,host_no,under_no,bind_date)VALUES(#{id},#{partNo},#{phoneNo},#{date})  ON DUPLICATE KEY UPDATE id = #{id},host_no=#{partNo},under_no=#{phoneNo},bind_date=#{date}")
    int registerRelation(@Param("id") String id, @Param("phoneNo") String phoneNo, @Param("partNo") String partNo, @Param("date") Date date);

    /**
     * 查询子账号是否被绑定
     *
     * @param phoneNo
     */
    @Select("SELECT count(under_no) FROM distribution_relationship WHERE under_no = #{phoneNo}")
    int queryUserEsit(@Param("phoneNo") String phoneNo);

    /**
     * 查询上线的金额
     *
     * @param userNo
     * @return
     */
    @Select("SELECT user_no,phone_no FROM online_info WHERE user_no = #{userNo} AND exist_state = '0' LIMIT 1")
    Map<String, Object> queryOnlineInfo(@Param("userNo") String userNo);

    /**
     * 查询当前账户今天的收益金额
     *
     * @param userNo  用户编码
     * @param daytime 今天的日期
     *                201001 表示收益的钱
     */

    @Select("SELECT SUM(arrive_amount) FROM online_info_log WHERE change_mode = '201001' AND arrive_time >= #{daytime} AND user_no = #{userNo}")
    String queryDayAmount(@Param("userNo") String userNo, @Param("daytime") String daytime);

    /**
     * 查询当前用户的总金额
     *
     * @param userNo 用户编码
     * @return
     */
    @Select("SELECT amount FROM online_account WHERE user_no = #{userNo}")
    Map<String, BigDecimal> querySumAmount(@Param("userNo") String userNo);

    /**
     * 插入提现的变动
     *
     * @param id     id
     * @param userNo 用户编码
     * @param amount 提现金额
     * @param date   日期
     * @param order  订单
     * @param payNo  支付账号
     * @return
     */
    @Insert("INSERT INTO online_withdrawal(id,user_no,withdrawal_amount,withdrawal_number,withdrawal_time,withdrawal_order) VALUES(#{id},#{userNo},#{amount},#{payNo},#{date},#{order})")
    int addOrderLog(@Param("id") String id, @Param("userNo") String userNo, @Param("amount") String amount, @Param("date") Date date, @Param("order") String order, @Param("payNo") String payNo);

    /**
     * 减去用户的金额
     *
     * @param userNo 用户编码
     * @param amount 金额
     * @return
     */
    @Update("UPDATE online_account SET amount = amount - #{amount} WHERE user_no = #{userNo}")
    int deleteUserAmont(@Param("userNo") String userNo, @Param("amount") String amount);

    @Delete("DELETE FROM online_withdrawal WHERE withdrawal_order = #{order}")
    void deleteOrder(@Param("order") String order);

    @Select("SELECT * FROM shop_order WHERE order_no = #{orderNo}")
    Map<String, Object> queryIsQuery(@Param("orderNo") String orderNo);

    @Update("UPDATE brush_single SET get_order = get_order + 1 WHERE product_no = #{product_no}")
    void addOrder(@Param("product_no") String product_no);

    /**
     * 查询是否是上线的线（用户编码查询）
     *
     * @param userNo 用户编码
     * @return
     */
    @Select("SELECT * FROM online_online_info WHERE ouser_no = #{userNo}")
    Map<String, Object> queryOnOnline(@Param("userNo") String userNo);

    /**
     * 查询是否是上线的线（电话号码查询）
     *
     * @param phoneNo 电话号
     * @return
     */
    @Select("SELECT * FROM online_online_info WHERE ouser_phone = #{phoneNo}")
    Map<String, Object> queryOnOnUserInfo(@Param("phoneNo") String phoneNo);

    /**
     * 查询用户信息
     *
     * @param phoneNo
     * @return
     */
    @Select("SELECT * FROM user_info WHERE phone_no = #{phoneNo}")
    Map<String, Object> queryUserInfo(@Param("phoneNo") String phoneNo);

    /**
     * 查询最上面一级是否有下线
     *
     * @param userno
     * @return
     */
    @Select("SELECT count(user_no) FROM online_info WHERE user_no = #{userno}")
    int quereyOnUserEsit(@Param("userno") String userno);

    /**
     * 插入上线的下一级
     *
     * @param userno
     * @param phoneNo
     * @param partNo
     * @param date
     * @return
     */
    @Insert("INSERT INTO online_info(user_no,phone_no,exist_state,host_phone,daytime) VALUES(#{userno},#{phoneNo},'0',#{partNo},#{date})")
    int insertOnUser(@Param("userno") String userno, @Param("phoneNo") String phoneNo, @Param("partNo") String partNo, @Param("date") Date date);

    /**
     * 查询上线的上线用户
     *
     * @param phoneNo
     * @param offphoneNo
     * @return
     */
    List<Map<String, Object>> queryOnOffline(@Param("phoneNo") String phoneNo, @Param("offphoneNo") String offphoneNo);

    /**
     * 查询当前用户在一个月提现了几次
     *
     * @param datetime 当前月开始时间
     * @param userNo   当前用户的编码
     * @return
     */
    @Select("SELECT count(id) FROM online_withdrawal WHERE withdrawal_time >= #{datetime} AND user_no = #{userNo}")
    int queryTxSum(@Param("datetime") String datetime, @Param("userNo") String userNo);

    /**
     * 获取当前用户一个月类买的东西的店铺名
     *
     * @param month
     * @param phoneNo
     * @return
     */
    @Select("SELECT shop_name FROM shop_order WHERE phone_no = #{phoneNo} AND buy_date >= #{month}")
    List<Map<String, String>> queryShopName(@Param("month") String month, @Param("phoneNo") String phoneNo);

    /**
     * 查询当前手机号码30天内的好评单数
     *
     * @param phoneNo
     * @param datetime
     * @return
     */
    @Select("SELECT count(id) FROM shop_order WHERE high_praise = 1 AND phone_no = #{phoneNo} AND buy_date >= #{datetime}")
    int quereyOrderWell(@Param("phoneNo") String phoneNo, @Param("datetime") String datetime);


    /**
     * 查询当前手机号码30天内的不是好评单数
     *
     * @param phoneNo
     * @param datetime
     * @return
     */
    @Select("SELECT count(id) FROM shop_order WHERE high_praise = 0 AND phone_no = #{phoneNo} AND buy_date >= #{datetime}")
    int quereyOrdernoWell(@Param("phoneNo") String phoneNo, @Param("datetime") String datetime);


    /**
     * 查询用户的支付信息
     *
     * @param phone_no
     * @param payType
     * @return
     */

    @Select("SELECT * FROM pay_account_data WHERE phone_no = #{phone_no} AND pay_type = #{payType}")
    Map<String, Object> queryPayNo(@Param("phone_no") String phone_no, @Param("payType") String payType);

    @Insert("INSERT INTO pay_account_data(pay_no,pay_type,phone_no) VALUES(#{payNo},#{payType},#{phone_no})")
    int insertPayNo(@Param("phone_no") String phone_no, @Param("payType") String payType, @Param("payNo") String payNo);


    /**
     * 查询支付信息
     *
     * @param phoneNo
     * @return
     */
    @Select("SELECT * FROM pay_account_data WHERE phone_no = #{phoneNo}")
    List<Map<String, String>> quereyPay(@Param("phoneNo") String phoneNo);


    /**
     * 只有修改图片
     *
     * @param path
     * @param orderNo
     * @param date
     * @return
     */
    @Update("UPDATE shop_order SET picture_url=#{path},upload_time=#{date},audit_status = '0' WHERE order_no = #{orderNo}")
    int updateOrderInfoURL(@Param("path") String path, @Param("orderNo") String orderNo, @Param("date") Date date);

    @Update("UPDATE shop_order SET highpraise_url=#{path},audit_status = '0',upload_time = #{date} WHERE order_no = #{orderNo}")
    int updateOrderInfoHURL(@Param("path") String path, @Param("orderNo") String orderNo, @Param("date") Date date);

    /**
     * 修改支付账户的账号
     *
     * @param phone_no
     * @param payType
     * @param payNo
     * @return
     */
    @Update("UPDATE pay_account_data SET pay_no=#{payNo} WHERE phone_no = #{phone_no} and pay_type = #{payType}")
    int updatePayNo(@Param("phone_no") String phone_no, @Param("payType") String payType, @Param("payNo") String payNo);

    /**
     * 查询当前公司的剩余金额
     *
     * @param company 公司名
     */
    @Select("SELECT upload_amount,phone,credit FROM business_capital WHERE branch = #{company}")
    Map<String, Object> queryCompanyAmount(@Param("company") String company);

    @Update("UPDATE business_capital SET upload_amount = upload_amount - #{commodityprices} WHERE branch = #{company}")
    int deductionsAmount(@Param("company") String company, @Param("commodityprices") BigDecimal commodityprices);

    @Insert("INSERT INTO business_capital_log VALUES (#{id}, #{company}, #{commodityprices}, #{explaintype}, #{explain}, #{productNo}, #{order}, #{date}, 0, null)")
    int insertAmountLog(@Param("id") String id, @Param("company") String company, @Param("commodityprices") BigDecimal commodityprices, @Param("explain") String explain, @Param("explaintype") String explaintype, @Param("productNo") String productNo, @Param("order") String order, @Param("date") Date date);

    @Select("SELECT * FROM brush_single_codelink  WHERE phone_no = #{phoneNo}")
    Map<String, String> queryCode(@Param("phoneNo") String phoneNo);

    @Select("SELECT * FROM online_info WHERE phone_no = #{phoneNo}")
    Map<String, String> queryMps(@Param("phoneNo") String phoneNo);

    @Insert("INSERT INTO brush_single_codelink(id,phone_no,mps_url,people_url) VALUES(#{id},#{phoneNo},#{imgCode1},#{imgCode}) ON DUPLICATE KEY UPDATE id=#{id},phone_no=#{phoneNo},mps_url=#{imgCode1},people_url=#{imgCode}")
    int insertMurl(@Param("id") String id, @Param("phoneNo") String phoneNo, @Param("imgCode") String imgCode, @Param("imgCode1") String imgCode1);

    @Select("SELECT pay_no,pay_type,phone_no FROM pay_account_data WHERE phone_no = #{phoneNo}")
    List<Map<String, String>> queryPayAccount(@Param("phoneNo") String phoneNo);
}
