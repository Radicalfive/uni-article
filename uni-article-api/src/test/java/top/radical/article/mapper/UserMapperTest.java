package top.radical.article.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.radical.article.common.Gender;
import top.radical.article.model.entity.User;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void insert() throws Exception{
        User user = User.builder()
                .phone("19908324671")
                .wxOpenId("radical")
                .password("123456")
                .nickname("radical")
                .avatar("https://cdn.jsdelivr.net/gh/Radicalfive/MK-image@master/10.7argb7dvn700.webp")
                .gender(0)
                .birthday(LocalDate.now())
                .address("江苏南京")
                .createTime(new Date())
                .build();
        userMapper.insert(user);
    }

    @Test
    void findUserByPhone() throws Exception{
        User user = userMapper.findUserByPhone("19908324671");
        assertNotNull(user);
        log.info(String.valueOf(user));
    }

    @Test
    void updateUser() throws Exception{
        User user = userMapper.findUserByPhone("19908324672");
        user.setPassword(DigestUtils.md5Hex("123456"));
        user.setNickname("诗酒趁年华");
        user.setAvatar("https://cdn.jsdelivr.net/gh/Radicalfive/MK-image@master/10.7argb7dvn700.webp");
        user.setGender(Gender.FEMALE.getKey());
        user.setBirthday(LocalDate.of(2000,6,18));
        user.setAddress("南工院");
        userMapper.updateUser(user);
    }
}