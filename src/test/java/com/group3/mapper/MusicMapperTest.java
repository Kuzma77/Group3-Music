package com.group3.mapper;


import com.group3.config.MapperConfig;
import com.group3.util.SpilderMusic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MapperConfig.class})
public class MusicMapperTest {
@Resource
private MusicMapper musicMapper;
    @Test
    public void batchinsertMusic() {
        musicMapper.batchinsertMusic(SpilderMusic.getContent());
    }
}
