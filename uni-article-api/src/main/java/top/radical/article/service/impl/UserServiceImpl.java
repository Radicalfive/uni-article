package top.radical.article.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.radical.article.mapper.UserMapper;
import top.radical.article.model.dto.LoginDto;
import top.radical.article.model.entity.User;
import top.radical.article.service.RedisService;
import top.radical.article.service.UserService;
import top.radical.article.util.AliyunResource;
import top.radical.article.util.FileResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author : radical
 * @description :UserServiceImpl
 * @data : 2022/3/22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private AliyunResource aliyunResource;

    @Resource
    private FileResource fileResource;

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

    @Override
    public boolean loginByCode(LoginDto loginDto) {
        //检查redis中是否存在这个手机号
        boolean flag = redisService.existsKey(loginDto.getPhone());
        if (flag) {
            // 取出redis中之前存储的验证码
            String saveCode = redisService.getValue(loginDto.getPhone(), String.class);
            // 和前端传的验证码进行比对
            if (saveCode.equalsIgnoreCase(loginDto.getCode())) {
                // 查找数据库该手机号用户是否存在
                User user = getUser(loginDto.getPhone());
                //不存在则注册为新用户
                if (user == null) {
                    //不存在手机号，就构建新用户记录，补充必备字段写入数据库，一键注册并登录
                    User saveUser = User.builder()
                            .phone(loginDto.getPhone())
                            .nickname("新用户")
                            .avatar("/static/img/nologin.jpeg")
                            .createTime(new Date())
                            .build();
                    userMapper.insert(saveUser);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return saveUser
     */
    @Override
    public User updateUser(User user) {
        //现根据手机号查出数据库原用户信息
        User saveUser = getUser(user.getPhone());
        //如果是修改密码的请求，需要将传来的密码加密
        if (!user.getPassword().equals(saveUser.getPassword())) {
            saveUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        } else {
            saveUser.setPassword(user.getPassword());
        }
        saveUser.setNickname(user.getNickname());
        saveUser.setAvatar(user.getAvatar());
        saveUser.setGender(user.getGender());
        saveUser.setBirthday(user.getBirthday());
        saveUser.setAddress(user.getAddress());
        saveUser.setBanner(user.getBanner());
        //更新数据
        userMapper.updateUser(saveUser);
        // 将修改后的用户信息返回
        return saveUser;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        //读入配置文件信息
        String endpoint = fileResource.getEndPoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String fileName = file.getOriginalFilename();
        // 分割文件名，获取文件后缀名
        assert fileName != null;
        String[] fileNameArr = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        //拼接得到新的上传文件名
        String uploadFileName = fileResource.getObjectName() + UUID.randomUUID().toString() + "." + suffix;
        // 上传网络需要用的字节流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            System.err.println("上传文件出现异常");
        }
        //执行阿里云上传文件操作
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        // 关闭OSSClient
        ossClient.shutdown();
        return uploadFileName;
    }
}
