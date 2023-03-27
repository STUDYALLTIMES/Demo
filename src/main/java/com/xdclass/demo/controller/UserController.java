package com.xdclass.demo.controller;

import com.xdclass.demo.model.entity.User;
import com.xdclass.demo.model.request.LoginRequest;
import com.xdclass.demo.service.UserService;
import com.xdclass.demo.utils.JsonData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     * @param userInfo
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo){
        int rows = userService.save(userInfo);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildError("注册失败，请重试");
    }

    /**
     * 注册接口
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
        return token == null ? JsonData.buildError("登录失败，账密错误") : JsonData.buildSuccess(token);
    }

    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        if (userId == null){
            return JsonData.buildError("查询失败");
        }

        User user = userService.findByUserId(userId);

        return JsonData.buildSuccess(user);
    }
}
