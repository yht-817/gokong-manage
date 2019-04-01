package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UsedDealType;
import cn.gokong.www.gokongmain.vo.used_deal.QueryUsedTypeOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 二手交易分类 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
public interface UsedDealTypeMapper extends BaseMapper<UsedDealType> {

    @Select("select type_no,type_name from used_deal_type order by priority desc")
    List<QueryUsedTypeOutput> queryUsedType();
}
