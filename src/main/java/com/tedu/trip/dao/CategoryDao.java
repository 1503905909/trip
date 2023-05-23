package com.tedu.trip.dao;

import com.tedu.trip.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> selectAll();
}
