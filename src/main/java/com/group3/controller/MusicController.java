package com.group3.controller;

import com.alibaba.fastjson.JSON;
import com.group3.service.MusicService;
import com.group3.util.ResponseObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Jack
 * @Date: 2020/4/5 11:39
 * @Description:
 */
@RestController
@RequestMapping(value = "/music")
public class MusicController {
    @Resource
    private MusicService musicService;

    @RequestMapping(value = "all",method = RequestMethod.POST,produces = "application/jon;charset=utf-8")
    public String allMusic(){
        ResponseObject ro = new ResponseObject(1, "成功", musicService.selectAllMusic());
        return JSON.toJSONString(ro);
    }

    @RequestMapping(value = "select",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String selectMusic(@RequestParam(value = "key") String key){
        ResponseObject ro = new ResponseObject(1, "成功", musicService.selectMusicByKey(key));
        return JSON.toJSONString(ro);
    }

}
