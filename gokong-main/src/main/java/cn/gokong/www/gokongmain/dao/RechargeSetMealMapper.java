package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.RechargeSetMeal;
import cn.gokong.www.gokongmain.vo.recharge_set_meal.QueryRechargeSetMealOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 充值套餐表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface RechargeSetMealMapper extends BaseMapper<RechargeSetMeal> {

    /**
     * 查询所有充值套餐
     * @return
     */
    @Select("select * from recharge_set_meal")
    List<QueryRechargeSetMealOutput> findAll();
}
