package com.lofts.blog.dao;

import com.lofts.blog.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {

    User login(@Param("username") String username, @Param("password") String password);

    void register(User user);

    String checkUserName(String userName);

    void saveUserInfo(User user);

    User queryUserInfo(int id);

    List<String> getBirthdayList();

    List<String> getConstellationList();

    List<String> getHobbyList();
}
