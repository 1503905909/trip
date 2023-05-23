package com.tedu.trip.service;

import com.tedu.trip.entity.Favorite;
import com.tedu.trip.entity.PageBean;
import com.tedu.trip.entity.Route;

public interface RouteService {
    PageBean<Route> findByPage(int currentPage, int cid, int pageNum, String rname);
    Route findByRid(String rid);
    Favorite findByFav(int uid, String rid);
    boolean addFav(int uid, String rid);
}
