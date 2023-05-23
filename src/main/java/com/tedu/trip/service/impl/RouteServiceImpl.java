package com.tedu.trip.service.impl;

import com.tedu.trip.dao.RouteDao;
import com.tedu.trip.dao.impl.RouteDaoImpl;
import com.tedu.trip.entity.Favorite;
import com.tedu.trip.entity.PageBean;
import com.tedu.trip.entity.Route;
import com.tedu.trip.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    @Override
    public PageBean<Route> findByPage(int currentPage, int cid, int pageNum, String rname) {
        PageBean<Route> pageBean = new PageBean<>();
        //设置总记录数
        long count = routeDao.selectCount(cid, rname);
        //设置总页数
        int cou=(int)count;
        pageBean.setTotalNum(cou);
        int totalPage=cou/pageNum;
        if(cou%pageNum>0){
            totalPage+=1;
        }
        pageBean.setTotalNum(totalPage);
        //设置当前页
        pageBean.setCurrentPage(currentPage);
        //每页显示的个数
        pageBean.setPageNum(pageNum);
        //每页显示的数据
        List<Route> list=routeDao.selectList(currentPage,cid,pageNum,rname);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Route findByRid(String rid) {
        return routeDao.findByRid(Integer.parseInt(rid));
    }

    @Override
    public Favorite findByFav(int uid, String rid) {
        return routeDao.selectFavByRid(uid,rid);
    }

    @Override
    public boolean addFav(int uid, String rid) {
        return routeDao.addFav(uid,rid);
    }
}
