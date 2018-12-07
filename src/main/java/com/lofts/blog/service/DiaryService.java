package com.lofts.blog.service;

import com.lofts.blog.dao.IDiaryDao;
import com.lofts.blog.model.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("diaryService")
public class DiaryService {

    @Resource
    private IDiaryDao diaryDao;

    public void addDiary(Diary diary) {
        diaryDao.addDiary(diary);
    }

    public List<Diary> queryAllDiary() {
        return  diaryDao.queryAllDiary();
    }

    public Diary queryDiarybyId(String id) {
        return diaryDao.queryDiarybyId(id);
    }
}
