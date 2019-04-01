package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UsedDeal;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.used_deal.QueryDetailsOutput;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 二手交易 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
public interface UsedDealService extends IService<UsedDeal> {

    /**
     * 发布二手信息
     * @param userNo        用户编码
     * @param usedTitle     标题
     * @param usedContent   描述
     * @param usedImgs      图片
     * @param typeNo        类型编码
     * @param cityName      发布城市
     * @param price         价格
     * @return
     */
    String releaseUsedDeal(String userNo, String usedTitle, String usedContent, MultipartFile[] usedImgs, String typeNo, String cityName, BigDecimal price);

    /**
     * 分页查询二手交易列表
     *
     * @param cityName  所在城市
     * @param typeNo    类型编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(String cityName, String typeNo, String keyWord, Integer pageNo, Integer pageSize);

    /**
     * 查询二手交易详情
     * @param userNo    用户编码
     * @param usedNo    二手交易编码
     * @return
     */
    QueryDetailsOutput queryDetails(String userNo, String usedNo);

    /**
     * 根据二手交易编码查询信息
     * @param usedNo    二手交易编码
     * @return
     */
    UsedDeal findByUsedNo(String usedNo);


    /**
     * 分页查询我发布的二手交易
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryMyUsedDeal(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 删除二手交易信息
     * @param userNo    用户编码
     * @param usedNo    二手交易编码
     * @return
     */
    boolean del(String userNo, String usedNo);
}
