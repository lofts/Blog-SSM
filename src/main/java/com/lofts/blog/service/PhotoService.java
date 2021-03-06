package com.lofts.blog.service;

import com.lofts.blog.dao.IPhotoDao;
import com.lofts.blog.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("photoService")
public class PhotoService {

    @Resource
    private IPhotoDao photoDao;

    public void addPhoto(Photo photo){
        photoDao.addPhoto(photo);
    }

    public List<Photo> queryAllPhoto(){
        return photoDao.queryAllPhoto();
    }
}
