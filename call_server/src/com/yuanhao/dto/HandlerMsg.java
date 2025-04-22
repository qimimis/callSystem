package com.yuanhao.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HandlerMsg implements Serializable {
    private String msg;
    private static final long serivalVersionUID = 1L;
    private Map<String,Object> dataMap = new HashMap<String,Object>();
    private boolean flag;


    /**
     * 获取
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取
     * @return dateMap
     */
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    /**
     * 设置
     * @param dateMap
     */
    public void setDataMap(Map<String, Object> dateMap) {
        this.dataMap = dateMap;
    }

    /**
     * 获取
     * @return flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * 设置
     * @param flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
