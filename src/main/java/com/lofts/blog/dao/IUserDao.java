package com.lofts.blog.dao;

import com.lofts.blog.model.User;

import java.util.List;

public interface IUserDao {

    //��½
    User login(String name, String password);

    //ע��
    void register(User user);

    //����û����Ƿ����
    String checkUserName(String userName);

    //�����û���Ϣ
    User saveUserInfo(User user);

    //��ѯ�û���Ϣ
    User queryUserInfo(int id);

    List<String> getBirthdayList();

    List<String> getConstellationList();

    List<String> getHobbyList();
}
