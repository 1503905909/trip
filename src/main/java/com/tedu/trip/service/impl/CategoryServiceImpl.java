package com.tedu.trip.service.impl;

import com.tedu.trip.dao.impl.CaCategoryDaoImpl;
import com.tedu.trip.entity.Category;
import com.tedu.trip.service.CategoryService;

import java.util.List;


public class CategoryServiceImpl implements CategoryService {
    CaCategoryDaoImpl categoryDao =new CaCategoryDaoImpl();
    public List<Category> findAll(){
        return categoryDao.selectAll();
    }


}
