package top.radical.article.mapper;

import org.apache.ibatis.annotations.*;
import top.radical.article.model.entity.User;

/**
 * @author : radical
 * @description :UserMapper
 * @data : 2022/3/22
 **/
public interface UserMapper {
    /**
     * 新增用户,并返回自增主键
     *
     * @param user 入参user对象
     */
    @Insert("INSERT INTO t_user(phone,password,nickname,avatar,gender,birthday,address,create_time,banner)" +
            "VALUES (#{phone},#{password},#{nickname},#{avatar},#{gender},#{birthday},#{address},#{createTime},#{banner})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    /**
     * 根据手机号查询用户信息
     *
     * @param phone 手机号
     * @return User用户信息
     */
    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User findUserByPhone(@Param("phone") String phone);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     */
    @Update("UPDATE t_user SET password=#{password},nickname=#{nickname},avatar=#{avatar}," +
            "gender=#{gender},birthday=#{birthday},address=#{address},banner=#{banner} WHERE id=#{id}")
    void updateUser(User user);
}
