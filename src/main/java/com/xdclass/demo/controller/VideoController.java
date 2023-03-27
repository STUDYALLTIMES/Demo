package com.xdclass.demo.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.xdclass.demo.model.entity.Video;
import com.xdclass.demo.model.entity.VideoBanner;
import com.xdclass.demo.service.VideoService;
import com.xdclass.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mysql.cj.util.StringUtils.getBytes;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /*
    * 轮播图
    */
    @GetMapping("banner")
    public JsonData listVideoBanner(){
        List<VideoBanner> videoBanner = videoService.listVideoBanner();
        return JsonData.buildSuccess(videoBanner);
    }

    /*
    * 视频列表
    */
    @RequestMapping("list")
    public JsonData listVideo(){
        List<Video> videolist = videoService.listVideo();
        return JsonData.buildSuccess(videolist);
    }

    /*
    * 查询视频详情，包含章，集信息
    */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true)int videoid){
        Video video = videoService.findVideoById(videoid);
        return JsonData.buildSuccess(video);
    }

    /*
     * 查询视频详情，包含章，集信息
     */
    @GetMapping("demo")
    public JsonData demo(@RequestParam(value = "str",required = true)String str){
        AES aes = SecureUtil.aes(str.getBytes());

        return JsonData.buildSuccess(aes.decryptStr(aes.encryptHex(str), CharsetUtil.CHARSET_UTF_8));
    }
}
