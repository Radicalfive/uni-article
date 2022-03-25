package top.radical.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.radical.article.common.Gender;
import top.radical.article.model.dto.LoginDto;
import top.radical.article.model.entity.User;
import top.radical.article.service.UserService;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder()
                .phone("19908324671")
                //.password("123123")
                .build();
        boolean flag = userService.login(loginDto);
        assertTrue(flag);
    }

    @Test
    void getUser() {
        User user = userService.getUser("19908324671");
        assertNotNull(user);
        log.info(String.valueOf(user));
    }

    @Test
    void updateUser() {
        User user = User.builder()
                .phone("19908324672")
                .password(DigestUtils.md5Hex("123123"))
                .nickname("radicalFive")
                .avatar("https://cdn.jsdelivr.net/gh/Radicalfive/MK-image@master/09.6x5zl5on75o0.webp")
                .gender(Gender.SECRET.getKey())
                .birthday(LocalDate.of(1998,3,23))
                .address("江苏淮安")
                .banner("https://cdn.jsdelivr.net/gh/Radicalfive/MK-image@master/banner17.1xnzqrt8dgqo.webp")
                .build();
        userService.updateUser(user);
    }
}