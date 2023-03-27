package com.xdclass.demo.service;

import com.xdclass.demo.model.entity.VideoOrder;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
