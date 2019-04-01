package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.PrivilegeUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 特权用户 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-19
 */
public interface PrivilegeUserService extends IService<PrivilegeUser> {
    /**
     * 判断当前使用有特权
     * @param userNo    用户编码
     * @param authority 权限
     * @return
     */
    boolean hesPrivilege(String userNo,String authority);
}
