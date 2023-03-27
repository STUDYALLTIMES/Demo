package com.xdclass.demo.mapper;

import java.util.List;
import com.xdclass.demo.model.entity.Video;
import com.xdclass.demo.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;


public interface VideoMapper {

    List<Video> listVideo();

    List<VideoBanner> listVideoBanner();

    Video findDetailById(@Param("video_id") int videoid);

    Video findById(@Param("video_id") int videoId);
}
