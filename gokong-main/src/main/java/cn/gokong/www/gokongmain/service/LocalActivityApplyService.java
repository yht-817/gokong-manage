package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.LocalActivityApply;
import cn.gokong.www.gokongmain.vo.local_activity_apply.PageQueryActivityMemberOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 同城活动用户申请表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-10-05
 */
public interface LocalActivityApplyService extends IService<LocalActivityApply> {

    /**
     * 同城活动申请
     *
     * @param activityNo 活动编码
     * @param userNo     用户编码
     * @param phone      联系电话
     * @param introduce  自我介绍
     * @param sex        性别
     * @return
     */
    String userApply(String activityNo, String userNo, String phone, String introduce, String sex);

    /**
     * 判断用户是否已经加入
     *
     * @param activityNo 活动编码
     * @param userNo     用户编码
     * @return
     */
    boolean hasJoin(String activityNo, String userNo);

    /**
     * 审核申请
     *
     * @param applyNo    活动编码
     * @param userNo     用户编码
     * @param auditState 审核状态
     * @return
     */
    boolean auditApply(String applyNo, String userNo, boolean auditState);

    /**
     * 根据申请编码查询申请信息
     *
     * @param applyNo 申请编码
     * @return
     */
    LocalActivityApply findByApplyNo(String applyNo);

    /**
     * 分页查询活动成员列表
     *
     * @param activityNo 活动编码
     * @param pageNo     开始位置
     * @param pageSize   页面长度
     * @return
     */
    List<PageQueryActivityMemberOutput> pageQueryActivityMember(String activityNo, Integer pageNo, Integer pageSize);

    /**
     * 添加支付同城活动的信息
     *
     * @param data
     * @return
     */
    int sameAtivityAli(Map<String, Object> data);

    /**
     * 删除申请活动的信息
     *
     * @param applyNo
     * @return
     */
    int deleteActivityApply(String applyNo);
}
