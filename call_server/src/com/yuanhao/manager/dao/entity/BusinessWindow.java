package com.yuanhao.manager.dao.entity;

import java.io.Serializable;

public class BusinessWindow implements Serializable {

    private int bwId;
    private String bwNo;
    private String bwTypeCode;
    private String bwTypeName;

    public BusinessWindow() {
    }

    public BusinessWindow(String bwNo, String bwTypeCode, String bwTypeName) {
        this.bwNo = bwNo;
        this.bwTypeCode = bwTypeCode;
        this.bwTypeName = bwTypeName;
    }


    /**
     * 获取
     * @return bwId
     */
    public int getBwId() {
        return bwId;
    }

    /**
     * 设置
     * @param bwId
     */
    public void setBwId(int bwId) {
        this.bwId = bwId;
    }

    /**
     * 获取
     * @return bwNo
     */
    public String getBwNo() {
        return bwNo;
    }

    /**
     * 设置
     * @param bwNo
     */
    public void setBwNo(String bwNo) {
        this.bwNo = bwNo;
    }

    /**
     * 获取
     * @return bwTypeCode
     */
    public String getBwTypeCode() {
        return bwTypeCode;
    }

    /**
     * 设置
     * @param bwTypeCode
     */
    public void setBwTypeCode(String bwTypeCode) {
        this.bwTypeCode = bwTypeCode;
    }

    /**
     * 获取
     * @return bwTypeName
     */
    public String getBwTypeName() {
        return bwTypeName;
    }

    /**
     * 设置
     * @param bwTypeName
     */
    public void setBwTypeName(String bwTypeName) {
        this.bwTypeName = bwTypeName;
    }

    public String toString() {
        return "BusinessWindow{bwId = " + bwId + ", bwNo = " + bwNo + ", bwTypeCode = " + bwTypeCode + ", bwTypeName = " + bwTypeName + "}";
    }
}
