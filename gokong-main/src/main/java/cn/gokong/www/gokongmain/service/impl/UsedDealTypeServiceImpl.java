package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.UsedDealType;
import cn.gokong.www.gokongmain.dao.UsedDealTypeMapper;
import cn.gokong.www.gokongmain.service.UsedDealTypeService;
import cn.gokong.www.gokongmain.vo.used_deal.QueryUsedTypeOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 二手交易分类 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@Service
public class UsedDealTypeServiceImpl extends ServiceImpl<UsedDealTypeMapper, UsedDealType> implements UsedDealTypeService {

    /**
     * 查询二手交易分类
     * @return
     */
    @Override
    public List<QueryUsedTypeOutput> queryUsedType() {
        return baseMapper.queryUsedType();
    }
}
