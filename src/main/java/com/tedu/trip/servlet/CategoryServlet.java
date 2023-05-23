package com.tedu.trip.servlet;

import com.tedu.trip.entity.Category;
import com.tedu.trip.entity.ResultInfo;
import com.tedu.trip.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cate/*")
public class CategoryServlet extends BaseServlet{
    public void list(HttpServletRequest req, HttpServletResponse resp){
        List<Category> clist=new CategoryServiceImpl().findAll();
        ResultInfo resultInfo=new ResultInfo();
        if (!clist.isEmpty()){
            resultInfo.setData(clist);
            resultInfo.setFlag(true);
        }
        write(resultInfo,resp);
    }

}
