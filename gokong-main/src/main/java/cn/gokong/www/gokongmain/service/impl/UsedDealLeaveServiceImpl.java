package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.UsedDeal;
import cn.gokong.www.gokongmain.domain.UsedDealLeave;
import cn.gokong.www.gokongmain.dao.UsedDealLeaveMapper;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.UsedDealLeaveService;
import cn.gokong.www.gokongmain.service.UsedDealService;
import cn.gokong.www.gokongmain.vo.used_deal_leave.PageQueryListOutput;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 二手交易留言 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@Service
public class UsedDealLeaveServiceImpl extends ServiceImpl<UsedDealLeaveMapper, UsedDealLeave> implements UsedDealLeaveService {

    @Autowired
    UsedDealService usedDealService;

    /**
     * 发布留言
     *
     * @param userNo       用户编码
     * @param usedNo       二手交易编码
     * @param leaveContent 留言内容
     * @return
     */
    @Override
    public boolean releaseLeave(String userNo, String usedNo, String leaveContent) {
        //留言数+1
        UsedDeal usedDeal = usedDealService.findByUsedNo(usedNo);
        usedDeal.setCommentNum(usedDeal.getCommentNum()+1);
        usedDealService.saveOrUpdate(usedDeal);

        UsedDealLeave usedDealLeave = new UsedDealLeave();
        usedDealLeave.setId(DataBaseTool.createId());
        usedDealLeave.setLeaveNo(DataBaseTool.createNo("leave_no"));
        usedDealLeave.setFromUserNo(userNo);
        usedDealLeave.setLeaveContent(leaveContent);
        usedDealLeave.setLeaveTime(DataBaseTool.createDate());
        usedDealLeave.setUsedNo(usedNo);
        return save(usedDealLeave);
    }

    /**
     * 分页查询留言列表
     * @param usedNo    二手交易编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    @Override
    public List<PageQueryListOutput> pageQueryList(String usedNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryList(usedNo,pageNo,pageSize);
    }

    /**
     * 回复留言
     * @param userNo        用户编码
     * @param leaveNo       留言编码
     * @param leaveContent  回复内容
     * @return
     */
    @Override
    @Transactional
    public boolean replyLeave(String userNo, String leaveNo, String leaveContent) {
        //查询留言信息
        UsedDealLeave byLeaveNo = findByLeaveNo(leaveNo);

        //留言数+1
        UsedDeal usedDeal = usedDealService.findByUsedNo(byLeaveNo.getUsedNo());
        usedDeal.setCommentNum(usedDeal.getCommentNum()+1);
        usedDealService.saveOrUpdate(usedDeal);

        UsedDealLeave usedDealLeave = new UsedDealLeave();
        usedDealLeave.setId(DataBaseTool.createId());
        usedDealLeave.setLeaveNo(DataBaseTool.createNo("leave_no"));
        usedDealLeave.setFromUserNo(userNo);
        usedDealLeave.setLeaveContent(leaveContent);
        usedDealLeave.setToUserNo(byLeaveNo.getFromUserNo());
        usedDealLeave.setLeaveTime(DataBaseTool.createDate());
        usedDealLeave.setUsedNo(byLeaveNo.getUsedNo());
        return save(usedDealLeave);
    }

    /**
     * 根据编码查询留言信息
     *
     * @param leaveNo 留言编码
     * @return
     */
    @Override
    public UsedDealLeave findByLeaveNo(String leaveNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("leave_no",leaveNo);
        UsedDealLeave usedDealLeave = getOne(wrapper);
        if (ObjectUtil.isNull(usedDealLeave)){
            throw new GrilException("留言信息不存在");
        }
        return usedDealLeave;
    }
}
