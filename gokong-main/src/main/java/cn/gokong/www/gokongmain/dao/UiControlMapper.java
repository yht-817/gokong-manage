package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.MyUi;
import cn.gokong.www.gokongmain.domain.UiControl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
public interface UiControlMapper extends BaseMapper<UiControl> {



    List<Map<String, String>> findHomePage(@Param("cityNo") String cityNo);

    List<Map<String, String>> findCityCattlePeople(@Param(value = "cityNo") String cityNo);

    List<Map<String, String>> findCityCattlePeoples(@Param(value = "cityNo") String cityNo, @Param(value = "pageno") int pageno, @Param(value = "userName") String userName);

    Map<String, Object> findCattlePeopleDetails(@Param(value = "userNo") String userNo);

    List<MyUi> queryMyUi();
}
