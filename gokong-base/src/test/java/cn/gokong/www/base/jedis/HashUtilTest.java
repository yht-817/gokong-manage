package cn.gokong.www.base.jedis;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashUtilTest {

    @Test
    public void hset() {
        HashUtil.hset("activity_tabloid_成都市","activity_no153863555271585928","宏廷 刚刚报名参加了位于 成都的活动《欧洲中心聚餐》");
    }
}