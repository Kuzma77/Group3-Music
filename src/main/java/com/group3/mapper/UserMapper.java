package com.group3.mapper;


import com.group3.entity.Music;
import com.group3.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
public interface UserMapper {
    /**
     * 用户登录
     *
     * @param phoneNumber
     * @return User
     */
    @Select("SELECT * FROM t_sys_user WHERE phone_number = #{phoneNumber}")
    User userLogin(String phoneNumber);
    /**
     * 通过用户手机号查询用户
     * @param phoneNumber
     * @return User
     */
    @Select("SELECT * FROM t_sys_user WHERE phone_number = #{phoneNumber}")
    User getUserByPhoneNumber(String phoneNumber);

    /**
     * 通过用户邮箱查询用户
     *
     * @param email
     * @return User
     */
    @Select("SELECT * FROM t_sys_user WHERE email = #{email}")
    User getUserByEmail(String email);
    /**
     * 注册一个用户
     *
     * @param user
     */
    @Insert(" INSERT INTO t_sys_user (name, password, salt, email, phone_number, status, binding, credits, create_time, last_login_time)\n" +
            "        VALUES (#{userName},#{password},#{salt},#{email},#{phoneNumber},#{status},#{binding},#{credits},#{createTime},#{lastLoginTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void userSign(User user);

    /**
     *
     * 注销用户
     * @param user
     */
    void canaleUser(User user);
    /**
     * 收藏歌曲
     *
     * @param music
     */
    void loveMusic(Music music);

    /**
     * 批量收藏歌曲
     *
     * @param musicList
     */
    void batchLoveMusic(@Param("musicList") List<Music> musicList);


    /**
     * 批量取消收藏歌曲
     *
     * @param musicList
     */
    void batchcancleLoveMusic(@Param("musicList") List<Music> musicList);

    /**
     * 通过关键字搜索音乐
     *
     * @param keyWord
     * @return Music
     */
    Music getMusicByKeyWord(String keyWord);



    @Select("SELECT * FROM t_sys_user")
    List<User> selectAll();
}
