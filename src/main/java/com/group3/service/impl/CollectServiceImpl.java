package com.group3.service.impl;

import com.group3.entity.Collect;
import com.group3.entity.Music;
import com.group3.entity.User;
import com.group3.mapper.CollectMapper;
import com.group3.service.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jack
 * @Date: 2020/4/5 11:03
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;

    @Override
    public List<User> getUserByMusicId(int musicId) {
        return collectMapper.getUserByMusicId(musicId);
    }

    @Override
    public void insertCollect(Collect collect) {
        collectMapper.insertCollect(collect);
    }

    @Override
    public List<Music> getMusicByUserId(int userId) {
        return collectMapper.getMusicByUserId(userId);
    }

    @Override
    public void deleteCollect(Collect collect) {
        collectMapper.deleteCollect(collect);
    }
}
