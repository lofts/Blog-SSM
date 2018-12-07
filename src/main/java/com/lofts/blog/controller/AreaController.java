package com.lofts.blog.controller;


import com.alibaba.fastjson.JSON;
import com.lofts.blog.model.City;
import com.lofts.blog.model.County;
import com.lofts.blog.model.Province;
import com.lofts.blog.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getProvinceList", method = RequestMethod.POST)
    private void getProvinceList(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<Province> list = areaService.getProvinceList();
        out.print(JSON.toJSONString(list));
    }

    @RequestMapping(value = "/getCityList", method = RequestMethod.POST)
    private void getCityList(HttpServletResponse response, @RequestParam(value = "provinceid") String provinceid) throws IOException {
        PrintWriter out = response.getWriter();
        List<City> list = areaService.getCityListById(provinceid);
        out.print(JSON.toJSONString(list));
    }

    @RequestMapping(value = "/getCountyList", method = RequestMethod.POST)
    private void getCountyList(HttpServletResponse response, @RequestParam(value = "cityid") String cityid) throws IOException {
        PrintWriter out = response.getWriter();
        List<County> list = areaService.getCountyListById(cityid);
        out.print(JSON.toJSONString(list));
    }

}
