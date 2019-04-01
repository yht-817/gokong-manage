package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.LocalActivity;
import cn.gokong.www.gokongmain.vo.local_activity.DetailsOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryMyCreateActivityOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryMyJoinActivityOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 同城活动 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-04
 */
public interface LocalActivityMapper extends BaseMapper<LocalActivity> {

    /**
     * 分页获取同城活动列表
     *
     * @param cityName 城市名称
     * @param keyWord  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(@Param("nowTime")Date nowTime,@Param("cityName")String cityName, @Param("keyWord") String keyWord, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


    Integer countByCityName(@Param("cityName")String cityName);

    @Select("select ui.user_no,ui.user_head,ui.nick_name,la.activity_no,la.activity_title,la.address,fg.group_no,fg.group_name,fg.group_head,la.activity_time as activityDate," +
            "la.activity_time,la.price,la.activity_img,la.person_num_scope,la.activity_desc,la.has_verify," +
            "(select count(1) from local_activity_apply laa where laa.activity_no = la.activity_no and laa.apply_state = '10470003') as hasJoin," +
            "(select laa.apply_state from local_activity_apply laa where laa.activity_no = la.activity_no and laa.user_no = #{userNo}) as apply_state," +
            "if((select la.user_no = #{userNo} ),'true','false') as hasCreator\n" +
            "from local_activity la\n" +
            "left join user_info ui on ui.user_no = la.user_no\n" +
            "left join friend_group fg on fg.group_no = la.group_no\n" +
            "where la.activity_no = #{activityNo}")
    DetailsOutput details(@Param("activityNo") String activityNo, @Param("userNo")String userNo);


    /**
     * 分页查询我加入的活动列表
     * @param userNo    用户编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryMyJoinActivityOutput> pageQueryMyJoinActivity(@Param("userNo")String userNo, @Param("keyWord")String keyWord, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    /**
     * 分页查询我创建的活动列表
     * @param userNo    用户编码
     * @param keyWord   检索内容
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryMyCreateActivityOutput> pageQueryMyCreateActivity(@Param("userNo")String userNo, @Param("keyWord")String keyWord, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
