package top.radical.article.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.radical.article.common.ResponseResult;
import top.radical.article.common.ResultCode;
import top.radical.article.model.dto.LoginDto;
import top.radical.article.model.entity.User;
import top.radical.article.service.RedisService;
import top.radical.article.service.UserService;
import top.radical.article.util.SmsUtil;
import top.radical.article.util.StringUtil;

import javax.annotation.Resource;

/**
 * @author : radical
 * @description :UserController
 * @data : 2022/3/22
 **/
@RestController
@RequestMapping(value = "api/v1/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private SmsUtil smsUtil;

    @Resource
    private RedisService redisService;

    /**
     * 账号密码登录
     *
     * @param loginDto
     * @return
     */
    @PostMapping(value = "/login/pass")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        log.info("loginDto:" + loginDto);
        boolean flag = userService.login(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }

    @PostMapping(value = "/sms")
    public ResponseResult sendSms(@RequestParam String phone) {
        // 随机验证码
        String code = StringUtil.getVerifyCode();
        // 给入参手机号发送短信
        //smsUtil.sendSms(phone, code);
        // 验证码存入redis，1分钟有效
        redisService.set(phone, code, 1L);
        log.info(code);
        return ResponseResult.success(code);
        //if (flag) {
        //    // 结果返回给客户端
        //} else {
        //    redisService.set(phone, code, 1L);
        //
        //}
    }

    @PostMapping(value = "/login/sms")
    public ResponseResult loginByCode(@RequestBody LoginDto loginDao) {
        log.info("loginDto" + loginDao);
        boolean flag = userService.loginByCode(loginDao);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDao.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 修改过后的用户信息
     */
    @PostMapping(value = "/update")
    public User update(@RequestBody User user) {
        log.info("user:" + user);
        return userService.updateUser(user);
    }
}
