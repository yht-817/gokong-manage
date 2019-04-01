package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.SysCode;
import cn.gokong.www.gokongmain.vo.user_pay.QueryPayType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 控制代码表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface SysCodeMapper extends BaseMapper<SysCode> {

    @Select("select code_name as hasIosPay from sys_code where code_no = '10470001'")
    QueryPayType usePayType();
}
