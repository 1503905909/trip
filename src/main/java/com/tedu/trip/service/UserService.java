package com.tedu.trip.service;

import com.tedu.trip.entity.User;

public interface UserService {
    User login(String username, String password);
    boolean reg(User user);
    boolean findByCode(String code);
}
