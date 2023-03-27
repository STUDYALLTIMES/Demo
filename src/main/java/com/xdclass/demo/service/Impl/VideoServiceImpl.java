package com.xdclass.demo.service.Impl;

import com.xdclass.demo.config.CacheKeyManager;
import com.xdclass.demo.model.entity.Video;
import com.xdclass.demo.model.entity.VideoBanner;
import com.xdclass.demo.mapper.VideoMapper;
import com.xdclass.demo.service.VideoService;
import com.xdclass.demo.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {
        try{
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEL_LIST,()->{
                List<Video> videoList = videoMapper.listVideo();
                System.out.println("从数据库里面找视频列表");
                return videoList;
            });
            if(cacheObj instanceof List){
                List<Video> videoList = (List<Video>)cacheObj;
                return videoList;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VideoBanner> listVideoBanner() {
        try{
            //把INDEX_BANNER_KEY拿过来查看是否有需要的，如果没有就查数据库
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
                List<VideoBanner> bannerList = videoMapper.listVideoBanner();
                System.out.println("从数据库里面找轮播图列表");
                return bannerList;
            });

            if(cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
                return bannerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Video findVideoById(int videoid) {
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoid);
        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(videoCacheKey,()->{
                Video video = videoMapper.findDetailById(videoid);
                System.out.println("从数据库里面找视频信息");
                return video;
            });

            if (cacheObj instanceof Video){
                Video video = (Video) cacheObj;
                return video;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
