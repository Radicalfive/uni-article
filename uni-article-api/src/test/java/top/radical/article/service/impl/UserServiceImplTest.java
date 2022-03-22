package top.radical.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.radical.article.model.dto.LoginDto;
import top.radical.article.model.entity.User;
import top.radical.article.service.UserService;

import javax.annotation.Resource;

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
                .password("123123")
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
}