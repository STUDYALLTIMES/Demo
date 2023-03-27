package com.xdclass.demo.service;

import com.xdclass.demo.model.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);

    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    String findByPhoneAndPwd(String phone, String pwd);

    /**
     * 根据id查找个人信息
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);
}
