package cn.gokong.www.base.jedis;

import cn.gokong.www.base.jedis.config.JedisConfig;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 说明:Jedis工具类
 *
 * @author ideac
 * @CreateDate 2018/9/9 15:07
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class KeysUtil {
    /**
     * 添加键值对数据
     * @param key   key名称
     * @param val   val值
     * @return
     */
    public static boolean set(String key, String val) {
        Jedis jedis = JedisConfig.getJedis();
        String isok = jedis.set(key, val);
        jedis.close();
        return "ok".equalsIgnoreCase(isok)?true:false;
    }

    /**
     * 添加键值对数据
     * @param key   key名称
     * @param val   val值
     * @param exp   有效时间
     * @return
     */
    public static boolean set(String key, String val, int exp) {
        Jedis jedis = JedisConfig.getJedis();
        String isok = jedis.set(key, val);
        boolean expire = expire(key, exp);
        jedis.close();
        return "ok".equalsIgnoreCase(isok)&&expire?true:false;
    }

    /**
     * 获取key所对于的val
     * @param key   key名称
     * @return
     */
    public static String get(String key){
        Jedis jedis = JedisConfig.getJedis();
        String val = jedis.get(key);
        jedis.close();
        return val;
    }

    /**
     * 清楚数据
     * @return
     */
    public static boolean flushDb(){
        Jedis jedis = JedisConfig.getJedis();
        String isok = jedis.flushDB();
        jedis.close();
        return "ok".equalsIgnoreCase(isok)?true:false;
    }

    /**
     * 判断某个键是否存在
     * @param key   键名
     * @return
     */
    public static boolean exists(String key){
        Jedis jedis = JedisConfig.getJedis();
        Boolean isok = jedis.exists(key);
        jedis.close();
        return isok;
    }

    /**
     * 获取所有的键值
     * @return
     */
    public static Set<String> keys(){
        Jedis jedis = JedisConfig.getJedis();
        Set<String> keys = jedis.keys("*");
        jedis.close();
        return keys;
    }

    /**
     * 删除键为key的数据项
     * @param key   键名
     * @return
     */
    public static boolean del(String key){
        Jedis jedis = JedisConfig.getJedis();
        Long del = jedis.del(key);
        jedis.close();
        return del>0?true:false;
    }

    /**
     * 设置键未key的过期时间
     * @param key   键名
     * @param exp   有效时间
     * @return
     */
    public static boolean expire(String key, int exp) {
        Jedis jedis = JedisConfig.getJedis();
        Long expire = jedis.expire(key, exp);
        jedis.close();
        return expire>0?true:false;
    }

    /**
     * 获取key的剩余生存时间
     * @param key   键名
     * @return
     */
    public static long ttl(String key){
        Jedis jedis = JedisConfig.getJedis();
        Long ttl = jedis.ttl(key);
        jedis.close();
        return ttl;
    }

    /**
     * 查看键为key的val所对应的数据类型
     * @param key   键名
     * @return
     */
    public static String type(String key){
        Jedis jedis = JedisConfig.getJedis();
        String type = jedis.type(key);
        jedis.close();
        return type;
    }
}
