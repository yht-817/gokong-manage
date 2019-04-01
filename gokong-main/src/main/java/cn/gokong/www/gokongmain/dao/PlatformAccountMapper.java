package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.PlatformAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * <p>
 * 平台账户金额 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface PlatformAccountMapper extends BaseMapper<PlatformAccount> {

    @Update("update platform_account set account_amount = account_amount - #{changeamount}")
    int changeAmount(@Param("changeamount") BigDecimal changeamount);
}
