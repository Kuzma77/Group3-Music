package com.group3.service.impl;

<<<<<<< HEAD
import com.group3.entity.Music;
import com.group3.mapper.MusicMapper;
import com.group3.service.MusicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

=======
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
/**
 * @author Jack
 * @Date: 2020/4/5 11:04
 * @Description:
 */
<<<<<<< HEAD
@Service
@Transactional(rollbackFor = Exception.class)
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;
    @Override
    public void batchinsertMusic(List<Music> musicList) {
        musicMapper.batchinsertMusic(musicList);
    }

    @Override
    public List<Music> selectAllMusic() {
        return musicMapper.selectAllMusic();
    }

    @Override
    public List<Music> selectMusicByKey(String key) {
        return musicMapper.selectMusicByKey(key);
    }
=======
public class MusicServiceImpl {
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
}
