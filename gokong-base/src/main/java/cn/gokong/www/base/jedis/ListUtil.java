package cn.gokong.www.base.jedis;

import cn.gokong.www.base.jedis.config.JedisConfig;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 说明:列表工具
 *
 * @author ikook
 * @CreateDate 2018/10/11 16:20
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class ListUtil {
    /**
     * 添加一个list
     * @param key   key名称
     * @param val   val值
     * @return
     */
    public static boolean lpush(String key, String... val) {
        Jedis jedis = JedisConfig.getJedis();
        Long lpush = jedis.lpush(key, val);
        jedis.close();
        return lpush>0?true:false;
    }

    /**
     * 往KEY对应list中插入一个元素val
     * @param key   key名称
     * @param val   val值
     * @return
     */
    public static boolean lpush(String key, String val) {
        Jedis jedis = JedisConfig.getJedis();
        Long lpush = jedis.lpush(key, val);
        jedis.close();
        return lpush>0?true:false;
    }

    /**
     * 获取key对应List区间[i,j]的元素
     * @param key   key名称
     * @param i     开始位置
     * @param j     结束位置
     * @return
     */
    public static List<String> lrange(String key, int i, int j) {
        Jedis jedis = JedisConfig.getJedis();
        List<String> lrange = jedis.lrange(key, i, j);
        return lrange;
    }

    /**
     * 指定删除多少个val元素
     * @param key   key名称
     * @param num   个数
     * @param val   值
     * @return
     */
    public static Boolean lrange(String key, int num, String val) {
        Jedis jedis = JedisConfig.getJedis();
        Long lrem = jedis.lrem(key, num, val);
        return lrem>0?true:false;
    }

    /**
     * 获取key对应list的长度
     * @param key   key名称
     * @return
     */
    public static long llen(String key) {
        Jedis jedis = JedisConfig.getJedis();
        Long lrem = jedis.llen(key);
        return lrem;
    }

    /**
     * 获取key对应list的值
     * @param key   key名称
     * @return
     */
    public static List<String> get(String key) {
        Jedis jedis = JedisConfig.getJedis();
        List<String> lrange = jedis.lrange(key, 0, llen(key));
        return lrange;
    }
}
