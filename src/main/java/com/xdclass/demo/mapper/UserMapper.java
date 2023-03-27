package com.xdclass.demo.mapper;

import com.xdclass.demo.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 用户注册
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 用户登录
     * @param phone
     * @param pwd
     * @return
     */
    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    /**
     * 根据id查找个人信息
     * @param userId
     * @return
     */
    User findByUserId(@Param("user_id") Integer userId);
}
