package com.lofts.blog.dao;

import com.lofts.blog.model.Diary;

import java.util.List;

public interface IDiaryDao {

    void addDiary(Diary diary);

    List<Diary> queryAllDiary();

    Diary queryDiarybyId(String id);
}
