package com.lofts.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/check")
public class CheckCodeController {

    @RequestMapping(value = "/getCheckImage", method = RequestMethod.POST)
    private void getCheckImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 创建图像缓冲区
        BufferedImage bi = new BufferedImage(165, 40, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics(); // 通过缓冲区创建一个画布
        /*
         * 根据背景画了一个矩形框
         */
        g.setColor(new Color(200, 150, 255));// 为画布创建背景颜色
        g.fillRect(0, 0, 165, 40); // fillRect:填充指定的矩形

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();// 转化为字符型的数组
        Random r = new Random();
        int len = ch.length;
        int index; // index用于存放随机数字
        StringBuffer sb = new StringBuffer();
        Font font = new Font("Consolas", Font.ITALIC, 30);
        g.setFont(font);
        for (int i = 0; i < 4; i++) {
            // 产生随机数字
            index = r.nextInt(len);
            // 设置颜色和字体
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            // 画数字以及数字的位置
            g.drawString(ch[index] + "", (i * 40) + 5, 30);
            sb.append(ch[index]);
        }
        g.dispose();
        // 将数字保留在session中，便于后续的使用
        request.getSession().setAttribute("verifycode", sb.toString());
        ImageIO.write(bi, "JPG", response.getOutputStream());

    }


}
