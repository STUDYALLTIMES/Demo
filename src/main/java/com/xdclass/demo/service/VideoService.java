package com.xdclass.demo.service;

import com.xdclass.demo.model.entity.Video;
import com.xdclass.demo.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    /*
    * 视频列表
    */
    List<Video> listVideo();

    /*
    * 轮播图
    */
    List<VideoBanner> listVideoBanner();

    /*
    * 视频详情
    */
    Video findVideoById(int videoid);
}
