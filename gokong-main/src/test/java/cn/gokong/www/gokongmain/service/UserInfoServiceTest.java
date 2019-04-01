package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.base.BaseJunit;
import cn.gokong.www.gokongmain.vo.user.UserInfoOutput;
import com.aliyuncs.exceptions.ClientException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoServiceTest extends BaseJunit {
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void findByPhoneNoAndPassword() {
        UserInfoOutput byPhoneNoAndPassword = userInfoService.findByPhoneNoAndPassword("18728578786", "13as1da");
        System.out.println(byPhoneNoAndPassword);
    }

    @Test
    public void sendSecurityCode() throws ClientException {
        boolean b = userInfoService.sendSecurityCode("18116750097", "0086");
        System.out.println(b);
    }

    @Test
    public void checkSecurityCode() {
        boolean b = userInfoService.checkSecurityCode("18116750097", "0086", "425765");
        System.out.println(b);
    }

    @Test
    public void findByUserNoInfo() {
        UserInfoOutput byUserNoInfo = userInfoService.findByUserNoInfo("123");
        System.out.println(byUserNoInfo);
    }

    @Test
    public void thirdLogin() {
        userInfoService.thirdLogin("123","QQ","头像","名称","男");
    }
}