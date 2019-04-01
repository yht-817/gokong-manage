package cn.gokong.www.base.sms;

import cn.gokong.www.base.sms.config.SMSConfig;
import cn.gokong.www.base.util.JSONUtil;
import cn.hutool.log.StaticLog;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;

/**
 * 说明:短信服务接口
 *
 * @author ikook
 * @CreateDate 2018/9/5 14:45
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class SmsDao {
    /**
     * 发送短信
     * @param phones    短信接收号码,支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码
     * @param content   通知内容
     * @param code      模板ID
     * @return  响应短信发送结果
     * @throws ClientException  异常处理
     */
    public static boolean sendSms(String phones, Map<String,String> content, String code) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMSConfig.getProduct(), SMSConfig.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(SMSConfig.getIClientProfile());

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phones);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SMSConfig.getSign());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(code);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(JSONUtil.map2json(content));

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);


        StaticLog.error("发送短信内容：{}",request.getTemplateParam());
        StaticLog.error("发送短信状态：{}",sendSmsResponse.getMessage());
        return "ok".equalsIgnoreCase(sendSmsResponse.getMessage());
    }
}
