package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfo;
import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfoListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChoiceMapper {
    // 顶部
    List<Map<String, Object>> findTopInfo(@Param(value = "city") String city);

    // 中部
    List<Map<String, String>> findMilddleInfo(@Param(value = "city") String city);

    // 底部
    List<ChoiceInfoListVo> findBottom(@Param(value = "city") String city, @Param(value = "userNo") String userNo, @Param(value = "pageNo") String pageNo,
                                      @Param(value = "pageSize") String pageSize, @Param(value = "keyWord") String keyWord);

    // 查看详情
    ChoiceInfo findDetails(@Param(value = "resourceno") String resourceno);
}
