package com.group3.mapper;


import com.group3.entity.Music;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
public interface MusicMapper {
    /**
     * 批量插入歌曲
     *
     * @param musicList
     */
    @Insert({"<script>" +
            "INSERT INTO t_sys_music (m_id, name, author, src, img, count, type, update_time) VALUES\n" +
            "<foreach collection=\"musicList\" item=\"music\" index=\"index\" separator=\",\">\n" +
            "(#{music.mId},#{music.name},#{music.author},#{music.src},#{music.img},#{music.count},#{music.type},#{music.updateTime})\n" +
            "</foreach>"+
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void batchinsertMusic(@Param("musicList") List<Music> musicList);

    /**
     * 查询所有音乐
     * @return
     */
    @Select("SELECT * FROM t_sys_music")
    List<Music> selectAllMusic();

    /**
     * 根据歌名查询音乐
     * @param key
     * @return
     */
    @Select("SELECT * FROM t_sys_music WHERE name LIKE #{%key%}")
    List<Music> selectMusicByKey(String key);
}
