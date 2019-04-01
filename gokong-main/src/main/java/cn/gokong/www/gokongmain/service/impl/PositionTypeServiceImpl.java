package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.PositionType;
import cn.gokong.www.gokongmain.dao.PositionTypeMapper;
import cn.gokong.www.gokongmain.service.PositionTypeService;
import cn.gokong.www.gokongmain.vo.company_recruitment.QueryPositionTypeListOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 职位类型 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
@Service
public class PositionTypeServiceImpl extends ServiceImpl<PositionTypeMapper, PositionType> implements PositionTypeService {

    /**
     * 查询职位类型列表
     *
     * @return
     */
    @Override
    public List<QueryPositionTypeListOutput> queryPositionTypeList() {

        return baseMapper.queryPositionTypeList();
    }
}
