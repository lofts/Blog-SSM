package com.lofts.blog.controller;

import com.lofts.blog.model.Diary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping(value = "/index")
    private String index() {
        return "main";
    }

    @RequestMapping(value = "/left")
    private String left() {
        return "mainleft";
    }

    @RequestMapping(value = "/top")
    private String top() {
        return "maintop";
    }

    @RequestMapping(value = "/empty")
    private String login() {
        return "mainempty";
    }

    @RequestMapping(value = "/dynamic")
    private String dynamic() {
        return "dynamiclist";
    }

    @RequestMapping(value = "/photo")
    private String photo() {
        return "photogrid";
    }

    @RequestMapping(value = "/friend")
    private String friend() {
        return "friendlist";
    }
}
