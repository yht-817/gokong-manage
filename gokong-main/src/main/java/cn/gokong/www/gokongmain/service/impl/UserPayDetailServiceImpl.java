package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.dao.UserPayDetailMapper;
import cn.gokong.www.gokongmain.domain.UserPayDetail;
import cn.gokong.www.gokongmain.service.UserPayDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tom
 * @since 2019-01-09
 */
@Service
public class UserPayDetailServiceImpl extends ServiceImpl<UserPayDetailMapper, UserPayDetail> implements UserPayDetailService {

    /**
     * 查询当前充值信息
     *
     * @param userPayDetail
     * @return
     */
    public UserPayDetail queryDataInfo(UserPayDetail userPayDetail) {
        return this.baseMapper.selectOnes(userPayDetail.getPayNo());
    }

    /**
     * 用户充值（小程序（套餐日志））
     *
     * @param userPayDetail
     * @return
     */
    public int addPayDetailLog(UserPayDetail userPayDetail) {
        return this.baseMapper.insert(userPayDetail);
    }


    /**
     * 修改当前订单的状态
     *
     * @param wxorderno
     * @return
     */
    public int updateOrderStates(String wxorderno) {
        return this.baseMapper.updateS(wxorderno);
    }
}
