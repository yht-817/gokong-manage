package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.CompanyRecruitmentBrowse;
import cn.gokong.www.gokongmain.dao.CompanyRecruitmentBrowseMapper;
import cn.gokong.www.gokongmain.service.CompanyRecruitmentBrowseService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
@Service
public class CompanyRecruitmentBrowseServiceImpl extends ServiceImpl<CompanyRecruitmentBrowseMapper, CompanyRecruitmentBrowse> implements CompanyRecruitmentBrowseService {
    /**
     * 判断用户是否浏览
     *
     * @param userNo        用户编码
     * @param recruitmentNo 招聘编码
     * @return
     */
    @Override
    public boolean isBrowse(String userNo, String recruitmentNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no",userNo);
        wrapper.eq("recruitment_no",recruitmentNo);

        CompanyRecruitmentBrowse companyRecruitmentBrowse = getOne(wrapper);

        if (ObjectUtil.isNull(companyRecruitmentBrowse)){
            return false;
        }
        return true;
    }
}
