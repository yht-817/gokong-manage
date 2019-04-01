package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.CompanyRecruitment;
import cn.gokong.www.gokongmain.vo.company_recruitment.DetailsOutput;
import cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.company_recruitment.ReleaseInput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 招聘信息 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
public interface CompanyRecruitmentService extends IService<CompanyRecruitment> {

    /**
     * 发布招聘求职
     * @param data  入参
     * @return
     */
    String release(ReleaseInput data);

    /**
     * 分页查询招聘求职列表
     * @param cityName  城市名称
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(String cityName, String keyWord, Integer pageNo, Integer pageSize);

    /**
     * 查询招聘求职详情
     * @param userNo        用户编码
     * @param recruitmentNo 招聘编码
     * @return
     */
    DetailsOutput details(String userNo, String recruitmentNo);

    /**
     * 根据招聘编码查询招聘信息
     * @param recruitmentNo 招聘编码
     * @return
     */
    CompanyRecruitment findByRecruitmentNo(String recruitmentNo);

    /**
     * 分页获取我发布的招聘求职
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryMyCompanyRecruitment(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 删除招聘求职信息
     * @param userNo        用户编码
     * @param recruitmentNo 招聘求职编码
     * @return
     */
    boolean del(String userNo, String recruitmentNo);
}
