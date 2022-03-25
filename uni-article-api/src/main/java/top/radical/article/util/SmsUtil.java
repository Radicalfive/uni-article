package top.radical.article.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : radical
 * @description :发送短信工具类，用@Component标注，需要使用的地方只需注入即可
 * @data : 2022/3/23
 **/
@Component
@Slf4j
public class SmsUtil {
    /**
     * 注入阿里云配置类实例
     */
    @Resource
    private AliyunResource aliyunResource;

    /**
     * 发短信
     *
     * @param phone 手机号
     * @param code  短信JSON串
     * @return true
     */
    public boolean sendSms(String phone, String code) {
        Config config = new Config()
                .setAccessKeyId(aliyunResource.getAccessKeyId())
                .setAccessKeySecret(aliyunResource.getAccessKeySecret());
        config.endpoint = "dysmsapi.aliyuncs.com";
        try {
            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName(aliyunResource.getSignName())
                    .setTemplateCode(aliyunResource.getTemplateCode())
                    // 这里把原来写死的code换成变量，用工具类生成一个随机6位字符串
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            SendSmsResponse resp = client.sendSms(sendSmsRequest);
            log.info(resp.getBody().getCode());
            log.info(resp.getBody().getMessage());
            return true;
            //log.info(resp.toString());
            ////获得返回结果JSON串
            //String res = String.valueOf(resp.getBody());
            //log.info(res);
            ////转成JSON对象
            //JSONObject jsonObj = JSON.parseObject(res);
            ////返回发送成功与否的标记
            //if ("OK".equals(jsonObj.get("Code"))) {
            //    return true;
            //}
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}