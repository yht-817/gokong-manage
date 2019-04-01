package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.UiControl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
public interface UiControlService extends IService<UiControl> {

    Map<String, Object> findHomePage(String userNo, String cityNo, String pageNo, String logno, String latno);

    Map<String, Object> findHomePageV3(String userNo, String cityNo, String pageNo, String logno, String latno);

    ResponseData findCattlePeople(String cityNo, String pageNo, String userName);

    ResponseData findCattlePeopleDetails(RequestData<Map<String, String>> requestData);

    Object myUI();
}
