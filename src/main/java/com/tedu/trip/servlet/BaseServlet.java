package com.tedu.trip.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "/BaseServlet/*")
public class BaseServlet extends HttpServlet{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException{
//获取请求路径
        String uri = req.getRequestURI();
//截取方法的名字
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
//根据反射获取该方法的对象
        try {
            Method method =
                    this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void write(Object obj,HttpServletResponse resq){
        String json = JSON.toJSONString(obj);
        resq.setContentType("application/json;charset=utf-8");
        try {
            resq.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
