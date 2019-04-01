package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.UserAuthsMapper;
import cn.gokong.www.gokongmain.domain.UserAuths;
import cn.gokong.www.gokongmain.service.UserAuthsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户认证信息表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-09
 */
@Service
public class UserAuthsServiceImpl extends ServiceImpl<UserAuthsMapper, UserAuths> implements UserAuthsService {

    /**
     * 保存第三方信息
     *
     * @param userAuths 第三方信息
     * @return
     */
    @Override
    public boolean saveUserAuths(UserAuths userAuths) {
        userAuths.setId(DataBaseTool.createId());
        userAuths.setThirdDate(DataBaseTool.createDate());
        return save(userAuths);
    }

    @Override
    public UserAuths findByThirdKeyAndThirdType(String thirdKey, String thirdType) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("third_key", thirdKey);
        wrapper.eq("third_type", thirdType);
        UserAuths userAuths = getOne(wrapper);
        return userAuths;
    }

    /**
     * 查询当前微信信息是否存在
     *
     * @param userNo
     * @return
     */
    public UserAuths findThirdInfo(String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        UserAuths userAuths = getOne(wrapper);
        return userAuths;
    }
}
