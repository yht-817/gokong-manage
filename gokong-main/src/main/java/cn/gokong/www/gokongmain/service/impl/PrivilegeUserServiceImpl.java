package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.PrivilegeUser;
import cn.gokong.www.gokongmain.dao.PrivilegeUserMapper;
import cn.gokong.www.gokongmain.service.PrivilegeUserService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 特权用户 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-19
 */
@Service
public class PrivilegeUserServiceImpl extends ServiceImpl<PrivilegeUserMapper, PrivilegeUser> implements PrivilegeUserService {

    /**
     * 判断当前使用有特权
     * @param userNo    用户编码
     * @param authority 权限
     * @return
     */
    @Override
    public boolean hesPrivilege(String userNo,String authority) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no",userNo);
        wrapper.eq(authority,1);
        PrivilegeUser one = getOne(wrapper);
        if (ObjectUtil.isNotNull(one)){
            return true;
        }
        return false;
    }
}
