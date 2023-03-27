package com.xdclass.demo.mapper;

import com.xdclass.demo.model.entity.Episode;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {

    /**
     *
     * @param videoId
     * @return
     */
    Episode findFirstEpisodeByVideoId(@Param("video_id") int videoId);
}
