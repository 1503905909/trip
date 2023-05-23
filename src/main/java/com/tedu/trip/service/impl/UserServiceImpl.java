package com.tedu.trip.service.impl;

import com.tedu.trip.dao.UserDao;
import com.tedu.trip.dao.impl.UserDaoImpl;
import com.tedu.trip.entity.User;
import com.tedu.trip.service.UserService;
import com.tedu.trip.util.MailUtils;
import com.tedu.trip.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public boolean reg(User user) {
        user.setStatus("N");//当前账号的状态
        user.setCode(UuidUtil.getUuid());//设置账号的激活的code
        boolean bo=userDao.reg(user);
        if (bo){
            String email = user.getEmail();
//            String content="恭喜您成功注册达内旅游在线账号,<a herf='http://localhost:8080/trip_war_exploded/user/active?code="+user.getCode()+"'>点击激活" + "</a>";
            String content="恭喜您成功注册达内旅游在线账号,<a href='http://localhost:8080/trip_war_exploded/user/active?code="+user.getCode()+"'>点击激活 </a>";
            String title="达内旅游在线账号激活";
            return    MailUtils.sendMail(email,content,title)?true:false;

        }

        return false;
    }

    @Override
    public boolean findByCode(String code) {
        boolean b=userDao.selectByCode(code);
        if (b) {
            //修改状态码
            return userDao.updateStatus(code);
        }
        return b;
    }
}
