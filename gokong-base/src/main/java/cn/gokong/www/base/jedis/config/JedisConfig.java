package cn.gokong.www.base.jedis.config;

import cn.hutool.log.StaticLog;
import cn.hutool.setting.dialect.Props;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * 说明:Jedis配置类
 *
 * @author ideac
 * @CreateDate 2018/9/9 15:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class JedisConfig {
    /**
     * jedis服务器地址
     */
    private static String HOST;

    /**
     * 连接端口
     */
    private static int PORT;

    /**
     * 超时时间
     */
    private static int TIME_OUT;

    /**
     * 最大空闲连接数, 默认8个
     */
    private static int MAX_IDLE = 200;

    /**
     * 最大连接数, 默认8个
     */
    private static int MAX_ACTIVE = 1024;

    /**
     * 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
     */
    private static int MAX_WAIT_MILLIS = -1;

    /**
     * 重试数量
     */
    private static int RETRY_NUM = 5;

    /**
     * 在获取连接的时候检查有效性, 默认false
     */
    private static boolean TEST_ON_BORROW = true;

    /**
     * 在空闲时检查有效性, 默认false
     */
    private static boolean TEST_WHILE_IDLE = true;

    /**
     * 连接密码
     */
    private static String PASSWORD;

    /**
     * jedis连接实例
     */
    private static JedisPool jedisPool = null;

    static {
        Properties prop = Props.getProp("jedis.properties");
        HOST = prop.getProperty("jedis.host");
        TIME_OUT = Integer.parseInt(prop.getProperty("jedis.timeout"));
        PORT = Integer.parseInt(prop.getProperty("jedis.port"));
        PASSWORD = prop.getProperty("jedis.password");

        initialPool();
    }

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大空闲连接数
            config.setMaxIdle(JedisConfig.MAX_IDLE);
            //最大连接数
            config.setMaxTotal(JedisConfig.MAX_ACTIVE);
            //设置永不超时
            config.setMaxWaitMillis(JedisConfig.MAX_WAIT_MILLIS);
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(JedisConfig.TEST_ON_BORROW);
            //在空闲时检查有效性, 默认false
            config.setTestWhileIdle(JedisConfig.TEST_WHILE_IDLE);
            jedisPool = new JedisPool(config, HOST, PORT, TIME_OUT,PASSWORD);
        } catch (Exception e) {
            StaticLog.error("First create JedisPool error : " + e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (null == jedisPool) {
            initialPool();
        }
    }

    /**
     * 同步获取Jedis实例
     * @return Jedis
     */
    public static synchronized Jedis getJedis() {
        poolInit();
        Jedis jedis = null;
        try {
            if (null != jedisPool) {
                jedis = jedisPool.getResource();
                try {
                    jedis.auth(PASSWORD);
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            StaticLog.error("Get jedis error : " + e);
        }
        return jedis;
    }
}
