package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;

import java.util.Map;

public interface ChoiceService {

    Map<String, Object> findTopMiddleInfo(String city);

    Object findBottom(Map<String, String> data);

    ResponseData findDetails(Map<String, String> data);
}
