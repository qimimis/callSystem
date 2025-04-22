package com.yuanhao.manager.dao.entity;

import java.io.Serializable;

public class BusinessType implements Serializable {
    private static final long serivalVersionUID = 1L;
    private int businessTypeId;
    private String businessTypeCode;
    private String businessTypeName;
    private int businessTypeLimitCount;
    private String businessTypeDesc;

    public BusinessType() {
    }

    public BusinessType( String businessTypeCode, String businessTypeName, int businessTypeLimitCount, String businessTypeDesc) {
        this.businessTypeCode = businessTypeCode;
        this.businessTypeName = businessTypeName;
        this.businessTypeLimitCount = businessTypeLimitCount;
        this.businessTypeDesc = businessTypeDesc;
    }

    /**
     * 获取
     * @return businessTypeId
     */
    public int getBusinessTypeId() {
        return businessTypeId;
    }

    /**
     * 设置
     * @param businessTypeId
     */
    public void setBusinessTypeId(int businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    /**
     * 获取
     * @return businessTypeCode
     */
    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    /**
     * 设置
     * @param businessTypeCode
     */
    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }

    /**
     * 获取
     * @return businessTypeName
     */
    public String getBusinessTypeName() {
        return businessTypeName;
    }

    /**
     * 设置
     * @param businessTypeName
     */
    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    /**
     * 获取
     * @return businessTypeLimitCount
     */
    public int getBusinessTypeLimitCount() {
        return businessTypeLimitCount;
    }

    /**
     * 设置
     * @param businessTypeLimitCount
     */
    public void setBusinessTypeLimitCount(int businessTypeLimitCount) {
        this.businessTypeLimitCount = businessTypeLimitCount;
    }

    /**
     * 获取
     * @return businessTypeDesc
     */
    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    /**
     * 设置
     * @param businessTypeDesc
     */
    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }

    public String toString() {
        return "BusinessType{businessTypeId = " + businessTypeId + ", businessTypeCode = " + businessTypeCode + ", businessTypeName = " + businessTypeName + ", businessTypeLimitCount = " + businessTypeLimitCount + ", businessTypeDesc = " + businessTypeDesc + "}";
    }
}
