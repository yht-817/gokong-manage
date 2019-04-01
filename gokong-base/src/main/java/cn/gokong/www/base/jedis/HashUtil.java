package cn.gokong.www.base.jedis;

import cn.gokong.www.base.jedis.config.JedisConfig;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 说明:Jedis哈希工具类
 *
 * @author ideac
 * @CreateDate 2018/9/9 23:57
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class HashUtil {

    /**
     * 添加一个Hash
     * @param key   键名称
     * @param map   值
     * @return
     */
    public static boolean hmset(String key, Map map){
        Jedis jedis = JedisConfig.getJedis();
        String isok = jedis.hmset(key, map);
        jedis.close();
        return "ok".equalsIgnoreCase(isok)?true:false;
    }

    /**
     * 往Hash插入一个元素(K-V)
     * @param key       键名
     * @param mapKey    map键名
     * @param val       map值
     * @return
     */
    public static boolean hset(String key,String mapKey,String val){
        Jedis jedis = JedisConfig.getJedis();
        Long hset = jedis.hset(key, mapKey, val);
        jedis.close();
        return hset>0?true:false;
    }

    /**
     * 获取Hash的所有(K-V)
     * @param key   键名
     * @return
     */
    public static Map<String, String> hgetAll(String key){
        Jedis jedis = JedisConfig.getJedis();
        Map<String, String> map = jedis.hgetAll(key);
        jedis.close();
        return map;
    }

    /**
     * 获取Hash所有元素的key
     * @param key   键名
     * @return
     */
    public static Set<String> hkeys(String key){
        Jedis jedis = JedisConfig.getJedis();
        Set<String> hkeys = jedis.hkeys(key);
        jedis.close();
        return hkeys;
    }

    /**
     * 获取Hash所有的val
     * @param key   键名
     * @return
     */
    public static List<String> hval(String key){
        Jedis jedis = JedisConfig.getJedis();
        List<String> hvals = jedis.hvals(key);
        jedis.close();
        return hvals;
    }

    /**
     * 删除键为key的Hash元素
     * @param key   键名
     * @return
     */
    public static boolean hdel(String key){
        Jedis jedis = JedisConfig.getJedis();
        Long hdel = jedis.hdel(key);
        jedis.close();
        return hdel>0?true:false;
    }

    /**
     * 获取键为key中元素的个数
     * @param key   键名
     * @return
     */
    public static long hlen(String key){
        Jedis jedis = JedisConfig.getJedis();
        Long hlen = jedis.hlen(key);
        jedis.close();
        return hlen;
    }

    /**
     * 判断Hash是否存在mapKey对应的元素
     * @param key       键名
     * @param mapKey    mapKey
     * @return
     */
    public static boolean hexists(String key,String mapKey){
        Jedis jedis = JedisConfig.getJedis();
        Boolean hexists = jedis.hexists(key, mapKey);
        jedis.close();
        return hexists;
    }
}
