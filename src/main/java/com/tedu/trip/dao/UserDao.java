package com.tedu.trip.dao;

import com.tedu.trip.entity.User;

public interface UserDao{
    User login(String username, String password);
    boolean reg(User user);
    boolean selectByCode(String code);
    boolean updateStatus(String code);

}
