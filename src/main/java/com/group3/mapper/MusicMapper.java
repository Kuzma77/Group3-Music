package com.group3.mapper;


import com.group3.entity.Music;
import org.apache.ibatis.annotations.Param;

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
    void batchinsertMusic(@Param("musicList") List<Music> musicList);
}
