package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.PositionType;
import cn.gokong.www.gokongmain.vo.company_recruitment.QueryPositionTypeListOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位类型 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
public interface PositionTypeService extends IService<PositionType> {

    /**
     * 查询职位类型列表
     * @return
     */
    List<QueryPositionTypeListOutput> queryPositionTypeList();

    @Override
    boolean save(PositionType positionType);
}
