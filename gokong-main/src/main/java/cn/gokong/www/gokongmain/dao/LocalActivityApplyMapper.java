package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.LocalActivityApply;
import cn.gokong.www.gokongmain.vo.local_activity_apply.PageQueryActivityMemberOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 同城活动用户申请表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-05
 */
public interface LocalActivityApplyMapper extends BaseMapper<LocalActivityApply> {

    /**
     * 分页查询活动成员列表
     *
     * @param activityNo 活动编码
     * @param pageNo     开始位置
     * @param pageSize   页面长度
     * @return
     */
    @Select("select ui.user_no,ui.user_head,ui.nick_name,laa.phone,laa.sex\n" +
            "from local_activity_apply laa\n" +
            "left join user_info ui on ui.user_no = laa.user_no\n" +
            "where laa.apply_state = '10470003'\n" +
            "and laa.user_no in \n" +
            "(\n" +
            "\tselect fgu.user_no \n" +
            "\tfrom friend_group_user fgu \n" +
            "\tleft join friend_group fg on fg.group_no = fgu.group_no\n" +
            "\tleft join local_activity la on la.group_no = fgu.group_no\n" +
            "\twhere fgu.group_user_state = '10170001' \n" +
            "\tand fg.group_type='2' \n" +
            "\tand fgu.group_no = la.group_no\n" +
            ")\n" +
            "and laa.activity_no = #{activityNo}\n" +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryActivityMemberOutput> pageQueryActivityMember(@Param("activityNo") String activityNo, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 修改当前用户参加的活动的状态
     *
     * @param orderNo
     * @param code
     * @return
     */
    @Update("UPDATE local_activity_apply SET apply_state = #{code}  WHERE apply_no = #{orderNo}")
    int updateActivity(@Param("orderNo") String orderNo, @Param("code") String code);

    @Delete("DELETE FROM local_activity_apply WHERE apply_no = #{applyNo}")
    int deleteActivityApply(@Param("applyNo") String applyNo);

    @Update("UPDATE user_pay_detail SET pay_state = 1 WHERE pay_no = #{wxorderno}")
    int updatePayState(@Param("wxorderno") String orderNo);
}
