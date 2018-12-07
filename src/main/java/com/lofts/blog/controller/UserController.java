package com.lofts.blog.controller;


import com.alibaba.fastjson.JSON;
import com.lofts.blog.model.User;
import com.lofts.blog.service.UserService;
import com.lofts.blog.utils.DateUtil;
import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String login(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "verifycode") String verifycode) {

        String sessionCode = (String) getRequest().getSession().getAttribute("verifycode");
        if (!verifycode.toUpperCase().equals(sessionCode)) {
            getRequest().getSession().setAttribute("loginresult", "验证码错误");
            return "forward:/login";
        }

        User user = userService.login(username, password);
        if (user != null) {
            getRequest().getSession().setAttribute("user", user);
            return "main";
        } else {
            getRequest().getSession().setAttribute("loginresult", "用户名或密码错误");
            return "forward:/login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String register(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "verifycode") String verifycode) {

        String sessionCode = (String) getRequest().getSession().getAttribute("verifycode");
        if (!verifycode.toUpperCase().equals(sessionCode)) {
            getRequest().getSession().setAttribute("registerresult", "验证码错误");
            return "forward:/register";
        }

        String result = userService.checkUserName(username);
        if (result != null && !result.isEmpty()) {
            getRequest().getSession().setAttribute("registerresult", "该用户名已被注册");
            return "forward:/register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRegisterdate(DateUtil.getCurrentDate());
        userService.register(user);

        getRequest().getSession().setAttribute("registerresult", "");
        return "login";
    }


    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    private String saveUserInfo(@RequestParam(value = "imagepath") String imagepath,
                                @RequestParam(value = "nickname") String nickname,
                                @RequestParam(value = "sex") String sex,
                                @RequestParam(value = "birthday") String birthday,
                                @RequestParam(value = "constellation") String constellation,
                                @RequestParam(value = "hobby") String hobby,
                                @RequestParam(value = "hobby") String address,
                                @RequestParam(value = "email") String email) {

        int id = ((User) getRequest().getSession().getAttribute("user")).getId();
        User user = new User();
        user.setId(id);
        user.setHeadimage(imagepath);
        user.setSex(sex);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setConstellation(constellation);
        user.setHobby(StringUtils.join(hobby, ","));
        user.setEmail(email);
        user.setAddress(address);

        User resultUser = userService.saveUserInfo(user);
        if (resultUser != null) {
            getRequest().getSession().setAttribute("user", resultUser);
            return "dynamiclist";
        } else {
            getRequest().getSession().setAttribute("saveResult", "修改失败");
            return "forword:edituserinfo";
        }
    }

    @RequestMapping(value = "/getBirthdayList", method = RequestMethod.POST)
    private void getBirthdayList(HttpServletRequest request) {
        List<String> list = this.userService.getBirthdayList();
        request.setAttribute("birthdayList", list);
    }

    @RequestMapping(value = "/getConstellationList", method = RequestMethod.POST)
    private void getConstellationList(HttpServletRequest request) {
        List<String> list = this.userService.getConstellationList();
        request.setAttribute("constellationList", list);
    }

    @RequestMapping(value = "/getHobbyList", method = RequestMethod.POST)
    private void getHobbyList(HttpServletRequest request) {
        List<String> list = this.userService.getHobbyList();
        request.setAttribute("hobbyList", list);
    }


    private HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
