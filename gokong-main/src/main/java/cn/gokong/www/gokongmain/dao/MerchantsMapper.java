package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.Merchants;
import cn.gokong.www.gokongmain.vo.merchants.QueryMerchantsDetailsOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商户数据 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface MerchantsMapper extends BaseMapper<Merchants> {

    /**
     * 分页查询商户数据
     *
     * @param cityName      城市名称
     * @param keyword       检索内容
     * @param startNo       开始位置
     * @param pageSize      检索长度
     * @param merchantsType 商户类型
     * @return
     */

    List<PageQueryMerchantsOutput> pageQueryMerchants(@Param("cityName")String cityName, @Param("keyword") String keyword, @Param("startNo") int startNo, @Param("pageSize") int pageSize, @Param("merchantsType") String merchantsType);


    /**
     * 根据商户编码查询商户信息
     * @param merchantsNo   商户编码
     * @return
     */
    @Select("select merchants_no,merchants_name,title_img,merchants_desc,address,create_time,merchants_score,phone_no,(select count(1) from merchants_evaluate where merchants_no = #{merchantsNo}) as evaluateCount " +
            "from merchants " +
            "where merchants_no = #{merchantsNo}")
    QueryMerchantsDetailsOutput queryMerchantsDetails(@Param("merchantsNo") String merchantsNo);


}
