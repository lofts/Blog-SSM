package com.lofts.blog.service;

import com.lofts.blog.dao.IAreaDao;
import com.lofts.blog.model.City;
import com.lofts.blog.model.County;
import com.lofts.blog.model.Province;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("areaService")
public class AreaService {

    @Resource
    private IAreaDao areaDao;

    public List<Province> getProvinceList() {
        return areaDao.getProvinceList();
    }


    List<City> getCityListById(String provinceid) {
        return areaDao.getCityListById(provinceid);
    }

    List<County> getCountyListById(String cityid) {
        return areaDao.getCountyListById(cityid);
    }

}
