package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.dao.MerchantsMapper;
import cn.gokong.www.gokongmain.domain.Merchants;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.CollectionInfoService;
import cn.gokong.www.gokongmain.service.MerchantsService;
import cn.gokong.www.gokongmain.vo.merchants.QueryMerchantsDetailsOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商户数据 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@Service
public class MerchantsServiceImpl extends ServiceImpl<MerchantsMapper, Merchants> implements MerchantsService {

    @Autowired
    MerchantsMapper merchantsMapper;

    @Autowired
    CollectionInfoService collectionInfoService;

    /**
     * 分页查询商户数据
     *
     *
     *
     * @param cityName      城市名称
     * @param keyword       检索内容
     * @param startNo       开始位置
     * @param pageSize      检索长度
     * @param merchantsType 商户类型
     * @return
     */
    @Override
    public List<PageQueryMerchantsOutput> pageQueryMerchants(String cityName, String keyword, int startNo, int pageSize, String merchantsType) {
        return merchantsMapper.pageQueryMerchants(cityName,keyword,startNo,pageSize,merchantsType);
    }

    /**
     * 获取商户详情
     *
     * @param userNo      用户编码
     * @param merchantsNo 商户编码
     * @return
     */
    @Override
    public QueryMerchantsDetailsOutput queryMerchantsDetails(String userNo, String merchantsNo) {
        //获取商户信息
        QueryMerchantsDetailsOutput byMerchantsNo = merchantsMapper.queryMerchantsDetails(merchantsNo);
        if (ObjectUtil.isNull(byMerchantsNo)){
            throw new GrilException("当前商户信息不存在");
        }
        //查询我是否收藏该条商户
        boolean userCollects = collectionInfoService.isCollection(userNo, merchantsNo);
        byMerchantsNo.setCollection(userCollects);
        return byMerchantsNo;
    }

    @Override
    public Merchants findByMerchantsNo(String merchantsNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("merchants_no",merchantsNo);
        Merchants merchants = getOne(wrapper);
        return merchants;
    }
}
