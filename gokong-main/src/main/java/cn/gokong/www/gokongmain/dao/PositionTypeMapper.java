package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.PositionType;
import cn.gokong.www.gokongmain.vo.company_recruitment.QueryPositionTypeListOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 职位类型 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
public interface PositionTypeMapper extends BaseMapper<PositionType> {

    /**
     * 查询职位类型列表
     *
     * @return
     */
    @Select("select type_no,type_name from position_type")
    List<QueryPositionTypeListOutput> queryPositionTypeList();
}
