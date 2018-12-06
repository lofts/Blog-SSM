package com.lofts.blog.dao;

import com.lofts.blog.model.City;
import com.lofts.blog.model.County;
import com.lofts.blog.model.Province;

import java.util.List;

public interface IAreaDao {

    List<Province> getProvinceList();

    List<City> getCityListById(String provinceid);

    List<County> getCountyListById(String cityid);
}
