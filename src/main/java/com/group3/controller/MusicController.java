package com.group3.controller;

import com.alibaba.fastjson.JSON;
import com.group3.service.MusicService;
import com.group3.util.ResponseObject;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
<<<<<<< HEAD
 * @author wl_sun
 * @description TODO
 * @create Date
=======
 * @author Jack
 * @Date: 2020/4/5 11:39
 * @Description:
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
 */
@RestController
@RequestMapping(value = "/music")
public class MusicController {
    @Resource
    private MusicService musicService;

    @RequestMapping(value = "all",method = RequestMethod.POST,produces = "application/jon;charset=utf-8")
<<<<<<< HEAD
    @ResponseBody
=======
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
    public String allMusic(){
        ResponseObject ro = new ResponseObject(1, "成功", musicService.selectAllMusic());
        return JSON.toJSONString(ro);
    }

    @RequestMapping(value = "select",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
<<<<<<< HEAD
    @ResponseBody
=======
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
    public String selectMusic(@RequestParam(value = "key") String key){
        ResponseObject ro = new ResponseObject(1, "成功", musicService.selectMusicByKey(key));
        return JSON.toJSONString(ro);
    }
<<<<<<< HEAD
=======

>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
}
