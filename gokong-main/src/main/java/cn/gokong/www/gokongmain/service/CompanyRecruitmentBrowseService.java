package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.CompanyRecruitmentBrowse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
public interface CompanyRecruitmentBrowseService extends IService<CompanyRecruitmentBrowse> {

    /**
     * 判断用户是否浏览
     * @param userNo        用户编码
     * @param recruitmentNo 招聘编码
     * @return
     */
    boolean isBrowse(String userNo, String recruitmentNo);
}
