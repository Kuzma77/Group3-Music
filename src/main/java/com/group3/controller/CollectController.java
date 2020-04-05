package com.group3.controller;

import com.alibaba.fastjson.JSON;
import com.group3.entity.Collect;
import com.group3.service.CollectService;
import com.group3.util.ResponseObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jack
 * @Date: 2020/4/5 12:20
 * @Description:
 */
@RestController
@RequestMapping(value = "collect")
public class CollectController {
    @Resource
    private CollectService collectService;

    @RequestMapping(value = "user",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String getUserByMusicId(@RequestParam(value="musicId")int musicId){
        ResponseObject ro = new ResponseObject(1, "成功", collectService.getUserByMusicId(musicId));
        return JSON.toJSONString(ro);
    }

    @RequestMapping(value = "music",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String getMusicByUserId(@RequestParam(value = "userId") int userId){
        ResponseObject ro = new ResponseObject(1, "成功", collectService.getMusicByUserId(userId));
        return JSON.toJSONString(ro);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
<<<<<<< HEAD
    public String insertCollect(@RequestParam(value = "collect") Collect collect){
=======
    public String insertCollect(@RequestParam(value = "collect")Collect collect){
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
        collectService.insertCollect(collect);
        ResponseObject ro = new ResponseObject(1,"成功",200);
        return JSON.toJSONString(ro);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
<<<<<<< HEAD
    public String deleteCollect(@RequestParam(value = "collect") Collect collect){
=======
    public String deleteCollect(@RequestParam(value = "collect")Collect collect){
>>>>>>> 7230b25bf4685e16b47abf345b5d7ed8096ba536
        collectService.deleteCollect(collect);
        ResponseObject ro = new ResponseObject(1,"成功",200);
        return JSON.toJSONString(ro);
    }



}
