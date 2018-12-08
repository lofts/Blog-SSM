package com.lofts.blog.controller;


import com.lofts.blog.model.User;
import com.lofts.blog.service.UserService;
import com.lofts.blog.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    private String userLogin(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "verifycode") String verifycode) {

        String sessionCode = (String) getRequest().getSession().getAttribute("verifycode");
        if (!verifycode.toUpperCase().equals(sessionCode)) {
            getRequest().getSession().setAttribute("loginresult", "验证码错误");
            return "forward:/user/login";
        }

        User user = userService.login(username, password);
        if (user != null) {
            getRequest().getSession().setAttribute("user", user);
            return "redirect:/main/index";
        } else {
            getRequest().getSession().setAttribute("loginresult", "用户名或密码错误");
            return "forward:/user/login";
        }
    }

    @RequestMapping(value = "/login")
    private String login() {
        return "login";
    }

    @RequestMapping(value = "/register")
    private String register() {
        return "register";
    }

    @RequestMapping(value = "/addUser")
    private String addUser(@RequestParam(value = "username") String username,
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


    @RequestMapping(value = "/editUser")
    private String editUser(HttpServletRequest request) {
        List<String> birthdaylist = userService.getBirthdayList();
        request.setAttribute("birthdaylist", birthdaylist);
        List<String> constellationlist = userService.getConstellationList();
        request.setAttribute("constellationlist", constellationlist);
        List<String> hobbylist = userService.getHobbyList();
        request.setAttribute("hobbylist", hobbylist);

        return "edituserinfo";
    }




    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    private String saveUserInfo(@RequestParam(value = "imagepath") String imagepath,
                                @RequestParam(value = "nickname") String nickname,
                                @RequestParam(value = "sex") String sex,
                                @RequestParam(value = "birthday") String birthday,
                                @RequestParam(value = "constellation") String constellation,
                                @RequestParam(value = "hobby") String hobby,
                                @RequestParam(value = "address") String address,
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

        userService.saveUserInfo(user);
        User resultUser = userService.queryUserInfo(id);

        getRequest().getSession().setAttribute("user", resultUser);
        return "dynamiclist";
    }

    private HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
