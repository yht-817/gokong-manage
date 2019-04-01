package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.RechargeSetMeal;
import cn.gokong.www.gokongmain.vo.recharge_set_meal.QueryRechargeSetMealOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 充值套餐表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface RechargeSetMealService extends IService<RechargeSetMeal> {

    /**
     * 获取充值套餐列表
     * @return
     */
    List<QueryRechargeSetMealOutput> queryRechargeSetMealList();
}
