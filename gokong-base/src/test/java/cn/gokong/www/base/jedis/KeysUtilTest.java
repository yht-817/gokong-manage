package cn.gokong.www.base.jedis;

import org.junit.Test;


public class KeysUtilTest {

    @Test
    public void set() {
    }

    @Test
    public void set1() {
        KeysUtil.set("111","222",60*2);
    }

    @Test
    public void get() {
        String s = KeysUtil.get("111");
        System.out.println(s);
    }


    @Test
    public void flushDb() {
        boolean b = KeysUtil.flushDb();
        System.out.println(b);
    }

    @Test
    public void del() {
        KeysUtil.del("activity_tabloid_成都市");
    }

    @Test
    public void exists() {
        boolean exists = KeysUtil.exists("111");
        System.out.println(exists);
    }
}