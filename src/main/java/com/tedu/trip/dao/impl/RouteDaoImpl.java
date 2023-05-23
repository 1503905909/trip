package com.tedu.trip.dao.impl;

import com.tedu.trip.dao.RouteDao;
import com.tedu.trip.entity.Favorite;
import com.tedu.trip.entity.Route;
import com.tedu.trip.entity.RouteImg;
import com.tedu.trip.entity.Seller;
import com.tedu.trip.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    @Override
    public long selectCount(int cid, String rname) {
        long count=0;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        ArrayList arr=new ArrayList();
        String sql=" select count(*) from tab_route where 1=1 ";
        if (cid!=0){
            sql+=" and cid=?";
            arr.add(cid);
        }
        if (rname!=null&&!rname.equals("null")&&!rname.equals("")){
            sql+=" and rname like \"%\"?\"%\"";
            arr.add(rname);
        }
        try {
            count=queryRunner.query(sql,new ScalarHandler<Long>(),arr.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Route> selectList(int currentPage, int cid, int pageNum, String rname) {
        List<Route> list=null;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="select * from tab_route where 1=1";
        ArrayList arr=new ArrayList();
        if (cid!=0){
            sql+=" and cid=?";
            arr.add(cid);
        }
        if (rname!=null&&!rname.equals("null")&&!rname.equals("")){
            sql+=" and rname like \"%\"?\"%\"";
            arr.add(rname);
        }
        sql+=" limit ?,?";
        arr.add((currentPage-1)*pageNum);
        arr.add(pageNum);
        try {
            list=queryRunner.query(sql,new BeanListHandler<Route>(Route.class),arr.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public Route findByRid(int rid) {
        Route route=null;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="select * from tab_route where rid=? ";
        String sql01="select * from tab_route_img where rid=? ";
        String sql02="select * from tab_seller where sid=1";
        try {
            route = queryRunner.query(sql, new BeanHandler<Route>(Route.class), rid);
            List<RouteImg> routeImgList = queryRunner.query(sql01, new BeanListHandler<RouteImg>(RouteImg.class), rid);
            Seller seller = queryRunner.query(sql02, new BeanHandler<Seller>(Seller.class));
            route.setRouteImgList(routeImgList);
            route.setSeller(seller);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return route;
    }

    @Override
    public Favorite selectFavByRid(int uid, String rid) {
        Favorite favorite=null;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="select * from tab_favorite where rid=? and uid=?";
        try {
            favorite=queryRunner.query(sql,new BeanHandler<Favorite>(Favorite.class),rid,uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return favorite;
    }

    @Override
    public boolean addFav(int uid, String rid) {
        int i=0;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        String sql="insert into tab_favorite values(?,?,?)";
        try {
            i=queryRunner.update(sql,rid,new Date(),uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return i>0?true:false;

    }
    public static void main(String[] args) {
        Route route=new RouteDaoImpl().findByRid(1);
        System.out.println(route);
    }

}
