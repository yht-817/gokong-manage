package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.InternationalCode;
import cn.gokong.www.gokongmain.vo.login.CountryMobilePrefixOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
public interface InternationalCodeMapper extends BaseMapper<InternationalCode> {

    @Select("select zh_name,code,first_letter,priority from international_code")
    List<CountryMobilePrefixOutput> getMobilePrefixs();
}
