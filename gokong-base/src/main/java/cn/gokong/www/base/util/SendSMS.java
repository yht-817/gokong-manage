package cn.gokong.www.base.util;


import cn.gokong.www.base.sms.SmsDao;
import cn.hutool.core.util.ObjectUtil;
import com.aliyuncs.exceptions.ClientException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于短信的发送
 */
public class SendSMS {

    public static void sendSms(String phone, BigDecimal residueAmount) {
        try {
            if (ObjectUtil.isNotNull(phone)) {
                // 判断当前商户的余额大小，然后进行短信提醒
                if (residueAmount.compareTo(new BigDecimal("0")) == -1) {
                    Map<String, String> map = new HashMap<>();
                    map.put("code", String.valueOf(residueAmount));
                    boolean SMS_150183463 = SmsDao.sendSms(phone, map, "SMS_150183463");
                    System.out.println("短信发送结果" + SMS_150183463);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // 判断当前商户的余额大小，然后进行短信提醒
        Map<String, String> map = new HashMap<>();
        map.put("code", "5541");
        boolean SMS_150183463 = false;
        try {
            SMS_150183463 = SmsDao.sendSms("17708021335", map, "SMS_150183463");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println("短信发送结果" + SMS_150183463);

    }
}
