package com.lofts.blog.controller;

import com.lofts.blog.model.Diary;
import com.lofts.blog.service.DiaryService;
import com.lofts.blog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @RequestMapping(value = "/addDiary", method = RequestMethod.POST)
    private String addDiary(@RequestParam(value = "title") String title,
                            @RequestParam(value = "type") String type,
                            @RequestParam(value = "content") String content) {

        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setType(type);
        diary.setContent(content);
        diary.setCreatedate(DateUtil.getCurrentDate());

        diaryService.addDiary(diary);

        return "forward:/diarylist";
    }


    @RequestMapping(value = "/queryAllDiary", method = RequestMethod.POST)
    private void getBirthdayList(HttpServletRequest request) {
        List<Diary> list = diaryService.queryAllDiary();
        request.setAttribute("diaryList", list);
    }

    @RequestMapping(value = "/queryDiarybyId", method = RequestMethod.POST)
    private void queryDiarybyId(HttpServletRequest request, @RequestParam(value = "id") String id) {
        Diary diary = diaryService.queryDiarybyId(id);
        request.setAttribute("diary", diary);
    }


}
