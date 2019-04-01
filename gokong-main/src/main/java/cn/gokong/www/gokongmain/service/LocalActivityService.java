package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.LocalActivity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * <p>
 * 同城活动 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-04
 */
public interface LocalActivityService extends IService<LocalActivity> {

    /**
     * 发布同城活动
     * @param userNo            用户编码
     * @param activityType      活动类型
     * @param activityTitle     活动标题
     * @param activityDesc      活动描述
     * @param activityImg       活动图片
     * @param cityName          城市名称
     * @param address           具体位置
     * @param activityTime      活动时间
     * @param hopePersonNum     活动人数
     * @param personNumScope    人数范围
     * @param hasCharge         是否收费
     * @param price             收费金额
     * @param hasVerify
     * @return
     */
    String release(String userNo, String activityType, String activityTitle, String activityDesc, String activityImg, String cityName, String address, String activityTime, Integer hopePersonNum, String personNumScope, boolean hasCharge, BigDecimal price, boolean hasVerify);

    /**
     * 发布同城活动
     * @param userNo            用户编码
     * @param activityType      活动类型
     * @param activityTitle     活动标题
     * @param activityDesc      活动描述
     * @param activityImg       活动图片
     * @param cityName          城市名称
     * @param address           具体位置
     * @param activityTime      活动时间
     * @param hopePersonNum     活动人数
     * @param personNumScope    人数范围
     * @param hasCharge         是否收费
     * @param price             收费金额
     * @param hasVerify
     * @return
     */
    String release(String userNo, String activityType, String activityTitle, String activityDesc, MultipartFile[] activityImg, String cityName, String address, String activityTime, Integer hopePersonNum, String personNumScope, boolean hasCharge, BigDecimal price, boolean hasVerify);

    /**
     * 分页获取同城活动列表
     *
     * @param cityName  城市名称
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    Object pageQueryList(String cityName, String keyWord, Integer pageNo, Integer pageSize);

    /**
     * 查询同城活动详情
     * @param activityNo    活动编码
     * @param userNo        用户编码
     * @return
     */
    Object details(String activityNo, String userNo);

    /**
     * 根据活动编码查询活动信息
     * @param activityNo    活动编码
     * @return
     */
    LocalActivity findByActivityNo(String activityNo);

    /**
     * 分页查询我加入的活动列表
     * @param userNo    用户编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    Object pageQueryMyJoinActivity(String userNo, String keyWord, Integer pageNo, Integer pageSize);

    /**
     * 分页查询我创建的活动列表
     * @param userNo    用户编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    Object pageQueryMyCreateActivity(String userNo, String keyWord, Integer pageNo, Integer pageSize);

    /**
     * 编辑活动信息
     * @param activityNo        活动编码
     * @param address           活动地址
     * @param activityTime      活动时间
     * @param hopePersonNum     希望人数
     * @param personNumScope    希望人数范围
     * @param hasVerify
     * @return
     */
    boolean editActivity(String activityNo, String address, String activityTime, int hopePersonNum, String personNumScope, boolean hasVerify);
}
