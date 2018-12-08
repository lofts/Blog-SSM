package com.lofts.blog.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/upload")
public class UploadImgController {

    private static final String IMAGE_UPLOAD_DIR = "upload/image/";

    private static final String TEMP_UPLOAD_DIR = "upload/temp/";

    private static final Long TOTAL_FILE_MAXSIZE = 10000000L;

    private static final int SINGLE_FILE_MAXSIZE = 2 * 1024 * 1024;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    private void uploadImage(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("imagefiles") MultipartFile[] multipartFiles) throws IOException {

        ServletContext context = request.getServletContext();
        PrintWriter out = response.getWriter();
        String realPath = context.getRealPath(IMAGE_UPLOAD_DIR) + "/";
        File realPathFile = new File(realPath);
        if (!realPathFile.exists()) {
            realPathFile.mkdirs();
        }

        List<Map<String, String>> imageList = new ArrayList<>();
        if (multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                if (multipartFiles != null) {
                    String fileName = UUID.randomUUID() + ".jpg";
                    String path = realPath + fileName;

                    multipartFile.transferTo(new File(path));

                    Map<String, String> map = new HashMap<>();
                    map.put("imagepath", IMAGE_UPLOAD_DIR + fileName);
                    imageList.add(map);
                }
            }
        }
        out.print(JSON.toJSONString(imageList));

        out.close();
        out.flush();

    }

}
