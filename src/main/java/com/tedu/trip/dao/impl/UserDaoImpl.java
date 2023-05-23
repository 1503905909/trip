package com.tedu.trip.dao.impl;

import com.tedu.trip.dao.UserDao;
import com.tedu.trip.entity.User;
import com.tedu.trip.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String username, String password) {
        User user =null;
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql ="select * from tab_user where username=? and password=?";
        try {
            user= queryRunner.query(sql, new BeanHandler<User>(User.class),username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean reg(User user) {
        int i=0;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="insert into tab_user values(null,?,?,?,?,?,?,?,?,?)";
        Object [] obj=
    {user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),
     user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode()};
        try {
            i = queryRunner.update(sql,obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return i>0?true:false;
    }

    @Override
    public boolean selectByCode(String code) {
        User user=null;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="select * from tab_user where code =?";
        try {
            user=queryRunner.query(sql,new BeanHandler<User>(User.class),code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return user!=null?true:false;
    }

    @Override
    public boolean updateStatus(String code) {
        int i=0;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="update tab_user set status=? where code=?";
        try {
            i = queryRunner.update(sql,"Y",code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i>0?true:false;
    }

}
