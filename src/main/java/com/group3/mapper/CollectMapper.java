package com.group3.mapper;



import com.group3.entity.Collect;
import com.group3.entity.Music;
import com.group3.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Jack
 * @Date: 2020/4/3 13:01
 * @Description:
 */
public interface CollectMapper {
    /**
     * 根据音乐id，查询对应的收藏用户
     * @param musicId
     * @return
     */
    @Select("SELECT * FROM t_sys_user WHERE id IN (SELECT u_id FROM t_sys_collect WHERE m_id=#{musicId})")
    List<User> getUserByMusicId(int musicId);


    /**
     * 根据用户id，实现音乐的收藏功能
     * @param  collect
     */
    @Insert("INSERT INTO t_sys_collect (m_id,u_id) VALUES(#{mId},#{uId}) ")
    void insertCollect(Collect collect);


    /**
     * 根据用户id查询音乐
     * @param userId
     * @return
     */
    @Select("SELECT * FROM t_sys_music WHERE id IN (SELECT m_id FROM t_sys_collect WHERE u_id=#{userId})")
    List<Music> getMusicByUserId(int userId);

    /**
     * 取消收藏
     * @param collect
     */
    @Delete("DELETE  FROM t_sys_collect WHERE (m_id=#{mId} AND u_id=#{uId})")
    void deleteCollect(Collect collect);
}
