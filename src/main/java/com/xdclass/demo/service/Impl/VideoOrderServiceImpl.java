package com.xdclass.demo.service.Impl;

import com.xdclass.demo.exception.XDException;
import com.xdclass.demo.mapper.EpisodeMapper;
import com.xdclass.demo.mapper.PlayRecordMapper;
import com.xdclass.demo.mapper.VideoMapper;
import com.xdclass.demo.mapper.VideoOrderMapper;
import com.xdclass.demo.model.entity.Episode;
import com.xdclass.demo.model.entity.PlayRecord;
import com.xdclass.demo.model.entity.Video;
import com.xdclass.demo.model.entity.VideoOrder;
import com.xdclass.demo.service.VideoOrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Override
    @Transactional
    public int save(int userId, int videoId) {
        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

        if(videoOrder != null){return 0;}

        Video video = videoMapper.findById(videoId);
        VideoOrder newVideoOrder = new VideoOrder();

        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        if(rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);

            if (episode == null){
                throw  new XDException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);

            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        List<VideoOrder> videoOrderList = videoOrderMapper.listOrderByUserId(userId);
        return videoOrderList;
    }
}
