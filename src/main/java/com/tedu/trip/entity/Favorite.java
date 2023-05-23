package com.tedu.trip.entity;

import java.io.Serializable;
/**
 * 收藏实体类
 */
public class Favorite implements Serializable {

    private Route route;//旅游线路对象
    private String date;//收藏时间
    private User user;//所属用户

    @Override
    public String toString() {
        return "Favorite{" +
                "route=" + route +
                ", date='" + date + '\'' +
                ", user=" + user +
                '}';
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
