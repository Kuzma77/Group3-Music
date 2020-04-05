package com.group3.mapper;

import com.group3.config.MapperConfig;
import com.group3.entity.Collect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MapperConfig.class})
public class CollectMapperTest {

    @Resource private  CollectMapper collectMapper;
    @Test
    public void getUserByMusicId() {
        collectMapper.getUserByMusicId(1).forEach(System.out::println);
    }

    @Test
    public void insertCollect() {
        collectMapper.insertCollect(Collect.builder().mId(1).uId(1).build());
    }

    @Test
    public void getMusicByUserId() {
        collectMapper.getMusicByUserId(1).forEach(System.out::println);
    }

    @Test
    public void deleteCollect() {
        collectMapper.deleteCollect(Collect.builder().uId(2).mId(2).build());
    }
}