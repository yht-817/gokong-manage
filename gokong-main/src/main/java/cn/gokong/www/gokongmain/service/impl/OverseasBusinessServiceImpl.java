package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.OverseasBusiness;
import cn.gokong.www.gokongmain.dao.OverseasBusinessMapper;
import cn.gokong.www.gokongmain.service.OverseasBusinessService;
import cn.gokong.www.gokongmain.vo.overseas_business.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.overseas_business.QueryDetailsOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 海外创业 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-25
 */
@Service
public class OverseasBusinessServiceImpl extends ServiceImpl<OverseasBusinessMapper, OverseasBusiness> implements OverseasBusinessService {

    /**
     * 分页查询列表
     *
     * @param keyword   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryList(String keyword, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryList(keyword,pageNo,pageSize);
    }

    /**
     * 查询详情
     * @param userNo        用户编码
     * @param businessNo    创业编码
     * @return
     */
    @Override
    public QueryDetailsOutput queryDetails(String userNo, String businessNo) {
        return baseMapper.queryDetails(userNo,businessNo);
    }
}
