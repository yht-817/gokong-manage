package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.RechargeSetMeal;
import cn.gokong.www.gokongmain.dao.RechargeSetMealMapper;
import cn.gokong.www.gokongmain.service.RechargeSetMealService;
import cn.gokong.www.gokongmain.vo.recharge_set_meal.QueryRechargeSetMealOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 充值套餐表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@Service
public class RechargeSetMealServiceImpl extends ServiceImpl<RechargeSetMealMapper, RechargeSetMeal> implements RechargeSetMealService {

    /**
     * 获取充值套餐列表
     * @return
     */
    @Override
    public List<QueryRechargeSetMealOutput> queryRechargeSetMealList() {
        return baseMapper.findAll();
    }
}
