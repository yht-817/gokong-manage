package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.Merchants;
import cn.gokong.www.gokongmain.vo.merchants.QueryMerchantsDetailsOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商户数据 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface MerchantsService extends IService<Merchants> {

    /**
     * 分页查询商户数据
     *
     *
     * @param cityName      城市名称
     * @param keyword       检索内容
     * @param startNo       开始位置
     * @param pageSize      检索长度
     * @param merchantsType 商户类型
     * @return
     */
    List<PageQueryMerchantsOutput> pageQueryMerchants(String cityName, String keyword, int startNo, int pageSize, String merchantsType);

    /**
     * 获取商户详情
     * @param userNo        用户编码
     * @param merchantsNo   商户编码
     * @return
     */
    QueryMerchantsDetailsOutput queryMerchantsDetails(String userNo, String merchantsNo);

    /**
     * 根据商户编码查询商户信息
     * @param merchantsNo   商户编码
     * @return
     */
    Merchants findByMerchantsNo(String merchantsNo);
}
