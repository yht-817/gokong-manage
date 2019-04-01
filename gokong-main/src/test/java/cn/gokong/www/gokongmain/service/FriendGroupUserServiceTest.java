package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.base.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FriendGroupUserServiceTest extends BaseJunit {
    @Autowired
    FriendGroupUserService friendGroupUserService;

    @Test
    public void inviteJoin() {
        Map<String,String> map = new HashMap<>();
        map.put("userNo","yh15367311621097");
        boolean yh15367309978639 = friendGroupUserService.inviteJoin("yh15367309978639", "60426784145410", Arrays.asList(map));
        System.out.println(yh15367309978639);
    }

    @Test
    public void applyJoin() {
    }
}