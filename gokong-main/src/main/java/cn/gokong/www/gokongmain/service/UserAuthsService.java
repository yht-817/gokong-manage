package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserAuths;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户认证信息表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-09
 */
public interface UserAuthsService extends IService<UserAuths> {

    /**
     * 保存第三方信息
     * @param userAuths 第三方信息
     * @return
     */
    boolean saveUserAuths(UserAuths userAuths);

    UserAuths findByThirdKeyAndThirdType(String thirdKey, String thirdType);

    UserAuths findThirdInfo(String userNo);
}
