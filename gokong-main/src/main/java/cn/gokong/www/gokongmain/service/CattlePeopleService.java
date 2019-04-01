package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.CattlePeople;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
public interface CattlePeopleService extends IService<CattlePeople> {

    CattlePeople findCattlePeopleDetail(String userno);

    ResponseData updateCattlePeople(CattlePeople cattlePeople);

    ResponseData addCattleFriend(String userno, String friendno);

    Object addcattlePeople(String userno, String cattlePeopleName, String cattlePeopleAbstract, String cattlePeopleLabel, String cattlePeopleAddress, String cattlePeoplePhoto, String cattleWorth);

    CattlePeople querycattlePeopleInfo(String userno);
}
