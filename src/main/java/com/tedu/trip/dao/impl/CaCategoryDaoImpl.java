package com.tedu.trip.dao.impl;

import com.tedu.trip.dao.CategoryDao;
import com.tedu.trip.entity.Category;
import com.tedu.trip.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CaCategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> selectAll() {
        List<Category> list = null;
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql ="select * from tab_category";
        try {
            list=queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}