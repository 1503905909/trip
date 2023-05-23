package com.tedu.trip.servlet;

import com.tedu.trip.entity.ResultInfo;
import com.tedu.trip.entity.User;
import com.tedu.trip.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    public void login(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        String code = (String)session.getAttribute("CHECKCODE_SERVER");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String check = req.getParameter("check");
        ResultInfo resultInfo = new ResultInfo();

        if(check.equalsIgnoreCase(code)){
            User user = new UserServiceImpl().login(username,password);
            if (user!=null && user.getUid() > 0){
                if(user.getStatus().equals("Y")){
                    resultInfo.setFlag(true);
                    session.setAttribute("user",user);
                }else{
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("账号未激活");
                }
            }else{
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号或密码有误");

            }
        }else{
            session.removeAttribute("CHECKCODE_SERVER");
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码有误");
        }
        write(resultInfo,resp);
    }
    public void getUser(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        if (user != null){
            resultInfo.setFlag(true);
            resultInfo.setData(user);
        }
        write(resultInfo, resp);
    }

    //退出
    public  void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect(req.getContextPath()+"/index.html");
    }
    //注册
    public  void reg(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
        User user = new User();
        Map<String,String[]> map = request.getParameterMap();
        BeanUtils.populate(user,map);
        UserServiceImpl userService = new UserServiceImpl();
        boolean b = userService.reg(user);

        ResultInfo info = new ResultInfo();
        info.setFlag(b);
        write(info,response);
    }

    //激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        boolean bo = new UserServiceImpl().findByCode(code);
        if (bo){
            response.sendRedirect(request.getContextPath() + "/login.html");
        }else {
            response.sendRedirect(request.getContextPath() + "/error/active_error.html");
        }

    }
}



