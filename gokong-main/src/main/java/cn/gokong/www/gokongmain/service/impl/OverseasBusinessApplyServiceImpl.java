package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.OverseasBusinessApply;
import cn.gokong.www.gokongmain.dao.OverseasBusinessApplyMapper;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.OverseasBusinessApplyService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 海外创业申请 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-25
 */
@Service
public class OverseasBusinessApplyServiceImpl extends ServiceImpl<OverseasBusinessApplyMapper, OverseasBusinessApply> implements OverseasBusinessApplyService {

    @Override
    public boolean save(OverseasBusinessApply entity) {
        entity.setId(DataBaseTool.createId());
        entity.setApplyNo(DataBaseTool.createNo("business_apply"));
        entity.setApplyTime(DataBaseTool.createDate());
        entity.setJoinState(SysCodeEnum.CODE_10460002.getCode());
        return super.save(entity);
    }

    /**
     * 加入海外创业
     *
     * @param userNo     用户编码
     * @param businessNo 创业编码
     * @param name       用户姓名
     * @param phone      用户电话
     * @param city       加盟城市
     * @return
     */
    @Override
    public boolean join(String userNo, String businessNo, String name, String phone, String city) {
        //判断当前用户是否加入这个创业信息
        boolean join = isJoin(userNo, businessNo);
        if (join){
            throw new GrilException("当前用户已经参加这个活动 无需再参加!");
        }
        OverseasBusinessApply overseasBusinessApply = new OverseasBusinessApply();
        overseasBusinessApply.setUserNo(userNo);
        overseasBusinessApply.setName(name);
        overseasBusinessApply.setPhone(phone);
        overseasBusinessApply.setCity(city);
        overseasBusinessApply.setBusinessNo(businessNo);
        save(overseasBusinessApply);
        return true;
    }

    /**
     * 判断用户是否加入这个海外创业
     *
     * @param userNo     用户编码
     * @param businessNo 创业编码
     */
    @Override
    public boolean isJoin(String userNo, String businessNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no",userNo);
        wrapper.eq("business_no",businessNo);
        wrapper.eq("join_state", SysCodeEnum.CODE_10460003.getCode());
        OverseasBusinessApply businessApply = getOne(wrapper);
        if (ObjectUtil.isNotNull(businessApply)){
            return true;
        }
        return false;
    }
}
