package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UsedDealLeave;
import cn.gokong.www.gokongmain.vo.used_deal_leave.PageQueryListOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 二手交易留言 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
public interface UsedDealLeaveService extends IService<UsedDealLeave> {
    /**
     * 发布留言
     * @param userNo        用户编码
     * @param usedNo        二手交易编码
     * @param leaveContent  留言内容
     * @return
     */
    boolean releaseLeave(String userNo, String usedNo, String leaveContent);

    /**
     * 分页查询留言列表
     * @param usedNo    二手交易编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(String usedNo, Integer pageNo, Integer pageSize);

    /**
     * 回复留言
     * @param userNo        用户编码
     * @param leaveNo       留言编码
     * @param leaveContent  回复内容
     * @return
     */
    boolean replyLeave(String userNo, String leaveNo, String leaveContent);

    /**
     * 根据编码查询留言信息
     * @param leaveNo   留言编码
     * @return
     */
    UsedDealLeave findByLeaveNo(String leaveNo);
}
