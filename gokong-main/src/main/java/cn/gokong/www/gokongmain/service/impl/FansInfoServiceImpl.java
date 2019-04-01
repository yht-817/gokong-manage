package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.FansInfoMapper;
import cn.gokong.www.gokongmain.domain.FansInfo;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.FansInfoService;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 粉丝表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@Service
public class FansInfoServiceImpl extends ServiceImpl<FansInfoMapper, FansInfo> implements FansInfoService {

    /**
     * 添加关注
     *
     * @param userNo     用户编码
     * @param fansUserNo 粉丝编码
     * @param focus      状态
     * @return
     */
    @Override
    @Transactional
    public boolean setFocus(String userNo, String fansUserNo, boolean focus) {
        FansInfo fansInfo = baseMapper.findByUserNoAndFansUserNo(userNo, fansUserNo);

        if (focus) {
            if (ObjectUtil.isNotNull(fansInfo)) {
                throw new GrilException("你已关注，无需再关注");
            }
            fansInfo = new FansInfo();
            fansInfo.setId(DataBaseTool.createId());
            fansInfo.setUserNo(userNo);
            fansInfo.setFansUserNo(fansUserNo);
            fansInfo.setCreateTime(DataBaseTool.createDate());
            boolean save = save(fansInfo);
            if (!save) {
                throw new GrilException("关注失败");
            }
        } else {
            if (ObjectUtil.isNull(fansInfo)) {
                throw new GrilException("你未关注，无需取消关注");
            }
            boolean removeById = removeById(fansInfo.getId());
            if (!removeById) {
                throw new GrilException("取消关注失败");
            }
        }
        return true;
    }

    /**
     * 分页获取粉丝列表
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<QueryUserOutput> pageQueryUserFocus(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryUserFocus(userNo, pageNo, pageSize);
    }

    /**
     * 是否关注
     *
     * @param userNo     用户编码
     * @param fansUserNo 粉丝编码
     * @return
     */
    @Override
    public boolean isFocus(String userNo, String fansUserNo) {
        FansInfo fansInfo = baseMapper.findByUserNoAndFansUserNo(userNo, fansUserNo);
        if (ObjectUtil.isNotNull(fansInfo)) {
            return true;
        }
        return false;
    }

    /**
     * 查询我的粉丝
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public List<FansInfo> findByFansUserNo(String userNo) {
        return baseMapper.findByFansUserNo(userNo);
    }

    /**
     * 查询粉丝数量
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public int countByUserNo(String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        int count = count(wrapper);
        return count;
    }

    /**
     * @param fansUserNo
     * @param userNo
     * @return
     */
    public boolean setFocuss(String fansUserNo, String userNo) {
        FansInfo fansInfo = baseMapper.findByUserNoAndFansUserNo(userNo, fansUserNo);
        if (ObjectUtil.isNotNull(fansInfo)) {
            return false;
        } else {
            fansInfo = new FansInfo();
            fansInfo.setId(DataBaseTool.createId());
            fansInfo.setUserNo(userNo);
            fansInfo.setFansUserNo(fansUserNo);
            fansInfo.setCreateTime(DataBaseTool.createDate());
            boolean save = save(fansInfo);
            return save;
        }
    }
}
