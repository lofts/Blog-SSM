package com.lofts.blog.dao;

import com.lofts.blog.model.Photo;

import java.util.List;

public interface IPhotoDao {

    void addPhoto(Photo photo);

    List<Photo> queryAllPhoto();
}
