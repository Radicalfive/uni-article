package top.radical.article.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.radical.article.common.ResponseResult;
import top.radical.article.common.ResultCode;
import top.radical.article.model.dto.LoginDto;
import top.radical.article.service.UserService;

import javax.annotation.Resource;

/**
 * @author : radical
 * @description :
 * @data : 2022/3/22
 **/
@RestController
@RequestMapping(value = "api/v1/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        log.info("loginDto:" + loginDto);
        boolean flag = userService.login(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }
}
