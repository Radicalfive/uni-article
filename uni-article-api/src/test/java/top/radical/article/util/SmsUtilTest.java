package top.radical.article.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class SmsUtilTest {
    @Resource
    private SmsUtil smsUtil;

    @Test
    void sendSms() {
        boolean b = smsUtil.sendSms("13995113788", StringUtil.getVerifyCode());
        log.info(String.valueOf(b));
    }
}