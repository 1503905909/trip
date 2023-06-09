package com.tedu.trip.entity;


import java.io.Serializable;
/**
 * 用于封装后端返回前端数据对象
 */

public class ResultInfo implements Serializable {
    private boolean flag;//后端返回结果正常为true 发生异常返回false
    private Object data;//后端返回结果数据对象
    private String errorMsg;//发生异常的错误信息

    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
