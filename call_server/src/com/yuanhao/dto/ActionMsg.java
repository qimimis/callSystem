package com.yuanhao.dto;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ActionMsg implements Serializable {

    private static final long serivalVersionUID = 1L;
    private Map<String,Object> dataMap = new HashMap<String,Object>();
    private boolean flag;
    private ClientEnum clientEnum;
    private ActionEnum actionEnum;
    private InetAddress clientIp;



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
        this.dataMap = dataMap;
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

    /**
     * 获取
     * @return clientEnum
     */
    public ClientEnum getClientEnum() {
        return clientEnum;
    }

    /**
     * 设置
     * @param clientEnum
     */
    public void setClientEnum(ClientEnum clientEnum) {
        this.clientEnum = clientEnum;
    }

    /**
     * 获取
     * @return actionEnum
     */
    public ActionEnum getActionEnum() {
        return actionEnum;
    }

    /**
     * 设置
     * @param actionEnum
     */
    public void setActionEnum(ActionEnum actionEnum) {
        this.actionEnum = actionEnum;
    }
    public InetAddress getClientIp() {
        return clientIp;
    }
    public void setClientIp(InetAddress clientIp) {
        this.clientIp = clientIp;
    }
}
