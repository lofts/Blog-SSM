package com.lofts.blog.dao;

import com.lofts.blog.model.User;

import java.util.List;

public interface IUserDao {

    User login(String name, String password);

    void register(User user);

    String checkUserName(String userName);

    User saveUserInfo(User user);

    User queryUserInfo(int id);

    List<String> getBirthdayList();

    List<String> getConstellationList();

    List<String> getHobbyList();
}
