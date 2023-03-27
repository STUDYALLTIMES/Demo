package com.xdclass.demo.mapper;

import com.xdclass.demo.model.entity.VideoOrder;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {

    /**
     * 查询用户是否购买过此商品
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") int userId,
                                              @Param("video_id") int videoId,
                                              @Param("state") int state);

    /**
     * 下单
     * @param newVideoOrder
     * @return
     */
    int saveOrder(VideoOrder newVideoOrder);

    /**
     * 订单列表
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);
}
