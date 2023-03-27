package com.xdclass.demo.controller;

import com.xdclass.demo.model.entity.VideoOrder;
import com.xdclass.demo.model.request.VideoOrderRequest;
import com.xdclass.demo.service.VideoOrderService;
import com.xdclass.demo.utils.JsonData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     * @param videoOrderRequest
     * @param request
     * @return
     */
    @PostMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        System.out.println();

        int rows = videoOrderService.save(userId,videoOrderRequest.getVideoId());

        return rows == 0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess();
    }


    /**
     * 订单列表
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer)request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrderList);
    }
}
