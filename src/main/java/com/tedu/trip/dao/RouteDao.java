package com.tedu.trip.dao;

import com.tedu.trip.entity.Favorite;
import com.tedu.trip.entity.Route;

import java.util.List;

public interface RouteDao {
    long selectCount(int cid, String rname);
    List<Route> selectList(int currentPage, int cid, int pageNum, String rname);
    Route findByRid(int rid);
    Favorite selectFavByRid(int uid, String rid);
    boolean addFav(int uid, String rid);

}

