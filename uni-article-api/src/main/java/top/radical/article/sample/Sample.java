package top.radical.article.sample;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;

/**
 * @author : radical
 * @description :
 * @data : 2022/3/23
 **/

public class Sample {
    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId     accessKeyId
     * @param accessKeySecret accessKeySecret
     * @return Client
     * @throws Exception Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 你的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 你的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
    public static void main(String[] args) throws Exception {
        Client client = Sample.createClient("你的AccessKey ID", "你的AccessKey Secret");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("你的短信签名模板")
                .setPhoneNumbers("手机号")
                .setTemplateCode("你的短信模板代号")
                .setTemplateParam("{\"code\":\"123456\"}");
        SendSmsResponse resp = client.sendSms(sendSmsRequest);
        System.out.println(resp);
    }
}
