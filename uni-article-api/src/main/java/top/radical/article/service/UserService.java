package top.radical.article.service;

import top.radical.article.model.dto.LoginDto;
import top.radical.article.model.entity.User;

/**
 * @author : radical
 * @description :UserService接口
 * @data : 2022/3/22
 **/
public interface UserService {
    /**
     * 登录
     *
     * @param loginDto 登录dto对象
     * @return 登录结果
     */
    boolean login(LoginDto loginDto);

    /**
     * 根据手机号查找用户
     *
     * @param phone 手机号
     * @return user
     */
    User getUser(String phone);
}
