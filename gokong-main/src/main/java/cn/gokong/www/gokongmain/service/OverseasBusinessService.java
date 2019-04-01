package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.OverseasBusiness;
import cn.gokong.www.gokongmain.vo.overseas_business.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.overseas_business.QueryDetailsOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 海外创业 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-25
 */
public interface OverseasBusinessService extends IService<OverseasBusiness> {

    /**
     * 分页查询列表
     *
     * @param keyword   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(String keyword, Integer pageNo, Integer pageSize);

    /**
     * 查询详情
     * @param userNo        用户编码
     * @param businessNo    创业编码
     * @return
     */
    QueryDetailsOutput queryDetails(String userNo, String businessNo);
}
