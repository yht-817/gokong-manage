package cn.gokong.www.gokongmain.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    private final Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Test
    public void demo() {
        logger.error("hjsdchkshdckjddhskj");
    }

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("47.96.98.127", 7200);
        jedis.auth("goKongqaz");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务器正在运行: " + jedis.ping());
    }

}
