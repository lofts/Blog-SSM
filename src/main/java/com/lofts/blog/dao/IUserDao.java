package com.lofts.blog.dao;

import com.lofts.blog.model.User;

import java.util.List;

public interface IUserDao {

    //登陆
    User login(String name, String password);

    //注册
    void register(User user);

    //检查用户名是否可用
    String checkUserName(String userName);

    //保存用户信息
    User saveUserInfo(User user);

    //查询用户信息
    User queryUserInfo(int id);

    List<String> getBirthdayList();

    List<String> getConstellationList();

    List<String> getHobbyList();
}
