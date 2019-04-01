package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UsedDealType;
import cn.gokong.www.gokongmain.vo.used_deal.QueryUsedTypeOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 二手交易分类 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
public interface UsedDealTypeService extends IService<UsedDealType> {

    /**
     * 查询二手交易分类
     * @return
     */
    List<QueryUsedTypeOutput> queryUsedType();

    @Override
    boolean save(UsedDealType usedDealType);
}
