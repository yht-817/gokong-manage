package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.InternationalCode;
import cn.gokong.www.gokongmain.dao.InternationalCodeMapper;
import cn.gokong.www.gokongmain.service.InternationalCodeService;
import cn.gokong.www.gokongmain.vo.login.CountryMobilePrefixOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@Service
public class InternationalCodeServiceImpl extends ServiceImpl<InternationalCodeMapper, InternationalCode> implements InternationalCodeService {

    @Override
    public List<CountryMobilePrefixOutput> getMobilePrefixs() {
        return baseMapper.getMobilePrefixs();
    }
}
