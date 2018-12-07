package com.lofts.blog.controller;


import com.lofts.blog.model.Diary;
import com.lofts.blog.model.Photo;
import com.lofts.blog.service.PhotoService;
import com.lofts.blog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    private String addPhoto(HttpServletRequest request) {
        int index = 0;
        String imagePath = null;
        String remark = null;

        while (request.getParameter("imagepath" + index) != null && !request.getParameter("imagepath" + index).isEmpty()) {
            Photo photo = new Photo();
            imagePath = request.getParameter("imagepath" + index);
            remark = request.getParameter("remark" + index);

            photo.setImagepath(imagePath);
            photo.setRemark(remark == null || remark.isEmpty() ? "未命名" : remark);
            photo.setUploaddate(DateUtil.getCurrentDate());

            photoService.addPhoto(photo);

            index++;
        }
        return "forward:/photo/photoList";
    }

    @RequestMapping(value = "/photoList")
    private String photoList(HttpServletRequest request) {
        List<Photo> list = photoService.queryAllPhoto();
        request.setAttribute("photolist", list);
        return "photogrid";
    }

    @RequestMapping(value = "/uploadPhoto")
    private String uploadPhoto(HttpServletRequest request) {
        return "uploadphoto";
    }

}
