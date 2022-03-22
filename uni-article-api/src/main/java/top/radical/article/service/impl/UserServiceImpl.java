package top.radical.article.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import top.radical.article.mapper.UserMapper;
import top.radical.article.model.dto.LoginDto;
import top.radical.article.model.entity.User;
import top.radical.article.service.UserService;

import javax.annotation.Resource;

/**
 * @author : radical
 * @description :UserServiceImpl
 * @data : 2022/3/22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(LoginDto loginDto) {
        User user = getUser((loginDto.getPhone()));
        if (user != null) {
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        return userMapper.findUserByPhone(phone);
    }
}
