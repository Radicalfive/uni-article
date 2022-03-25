package top.radical.article.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void getVerifyCode() {
        String code = StringUtil.getVerifyCode();
        System.out.println(code);
    }
}