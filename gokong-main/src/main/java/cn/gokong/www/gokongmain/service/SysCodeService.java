package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.SysCode;
import cn.gokong.www.gokongmain.vo.user_pay.QueryPayType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 控制代码表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface SysCodeService extends IService<SysCode> {

    /**
     * 使用支付类型
     * @return
     */
    QueryPayType usePayType();
}
