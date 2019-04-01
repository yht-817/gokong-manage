package cn.gokong.www.base.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 根据经纬度获取两点的的距离
 */
public class LatLngTools {
    private final Logger logger = LoggerFactory.getLogger(LatLngTools.class);
    private static final double EARTH_RADIUS = 6378.137;//地球半径,单位千米

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 传入两点的经纬度
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return 返回距离两点的距离（km）
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        /* double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;*/
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    /**
     * 坐标逆解析
     *
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    public static JSONObject coordinateResolution(double lat, double lng) {
        String url = "https://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=ZJVBZ-6ARL6-5BCSC-MDP4T-V3BFJ-DYFIP&get_poi=1";
        String resp = HttpUtil.get(url);
        return JSONUtil.parseObj(resp);
    }

    /**
     * 坐标转国家
     *
     * @param lat 维度
     * @param lng 经度
     * @return
     */
    public static String coordinatesToNation(double lat, double lng) {
        JSONObject jsonObject = coordinateResolution(lat, lng);
        Object nation = jsonObject.getJSONObject("result").getJSONObject("address_component").get("nation");
        StaticLog.error("nation:{}", nation);
        return nation.toString();
    }

    /**
     * 根据IP转经纬度然后在获取城市
     */
    public static JSONObject ipTurntoCityname(String ip) {
        //根据IP获取经纬度
        String url = "https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=ZJVBZ-6ARL6-5BCSC-MDP4T-V3BFJ-DYFIP";
        String resp = HttpUtil.get(url);
        JSONObject object = JSONUtil.parseObj(resp);
        JSONObject data = (JSONObject) object.get("result");
        JSONObject datalatlng = (JSONObject) data.get("location");
        double lat = (double) datalatlng.get("lat");
        double lng = (double) datalatlng.get("lng");
        // 转换经纬度吧获取城市名
        JSONObject cityJson = coordinateResolution(lat, lng);
        return cityJson;
    }

    public static void main(String[] args) {
        System.out.println(ipTurntoCityname("56.23.52.41"));
    }
}
