package cn.gokong.www.base.easemob.util;


import cn.gokong.www.base.easemob.entity.Entities;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;

/**
 * 说明:环信工具类
 *
 * @author Mick
 * CreateDate 2018/5/30 16:38
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class EasemobTools {
    /**
     * 校验结果
     * @param result    结果集
     * @param s 输出日志
     * @return  true：成功 false：失败
     */
    protected static boolean verifyResult(Object result, String s) {
        if (result==null){
            return false;
        }
        JSONObject jsonObject = JSONUtil.parseObj(result, false);
        Entities entities = JSONUtil.toBean(String.valueOf(jsonObject.getJSONArray("entities").get(0)),Entities.class);
        StaticLog.info(s, entities.isActivated(),jsonObject);
        return entities.isActivated();
    }

    /**
     * 校验结果
     * @param result    结果集
     * @return  true：成功 false：失败
     */
    protected static boolean verifyResultIsNull(Object result,String s) {
        boolean isok = false;
        if (result==null){
            isok =  false;
        }else {
            isok =  true;
        }
        StaticLog.info(s, isok,result);
        return isok;
    }

    /**
     * 解析列表
     * @param result    结果集
     * @return  列表信息
     */
    protected static JSONArray verifyResultList(Object result, String s) {
        JSONObject jsonObject = JSONUtil.parseObj(result, false);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        StaticLog.info(s, jsonArray,result);
        return jsonArray;
    }
}
