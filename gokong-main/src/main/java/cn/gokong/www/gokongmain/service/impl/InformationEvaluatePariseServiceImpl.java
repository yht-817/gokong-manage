package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.InformationEvaluateParise;
import cn.gokong.www.gokongmain.dao.InformationEvaluatePariseMapper;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.InformationEvaluatePariseService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 咨询信息评价点赞记录 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@Service
public class InformationEvaluatePariseServiceImpl extends ServiceImpl<InformationEvaluatePariseMapper, InformationEvaluateParise> implements InformationEvaluatePariseService {

    /**
     * 设置评论点赞
     *
     *
     * @param informationNo 资讯编码
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param liked         点赞标识
     * @return
     */
    @Override
    @Transactional
    public boolean setEvaluateLike(String informationNo, String evaluateNo, String userNo, boolean liked) {
        //查询点赞信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("evaluate_no",evaluateNo);
        wrapper.eq("user_no",userNo);
        InformationEvaluateParise informationEvaluateParise = getOne(wrapper);

        if (liked){
            if (ObjectUtil.isNotNull(informationEvaluateParise)){
                throw new GrilException("已经点赞无需再点赞");
            }
            informationEvaluateParise = new InformationEvaluateParise();
            informationEvaluateParise.setId(DataBaseTool.createId());
            informationEvaluateParise.setEvaluateNo(evaluateNo);
            informationEvaluateParise.setUserNo(userNo);
            informationEvaluateParise.setInformationNo(informationNo);
            informationEvaluateParise.setPariseDate(DataBaseTool.createDate());
            boolean save = save(informationEvaluateParise);
            if (!save){
                throw new GrilException("点赞失败");
            }
        }else {
            if (ObjectUtil.isNull(informationEvaluateParise)){
                throw new GrilException("你未点赞无需取消点赞");
            }
            boolean removeById = removeById(informationEvaluateParise.getId());
            if (!removeById){
                throw new GrilException("取消点赞失败");
            }
        }
        return true;
    }
}
