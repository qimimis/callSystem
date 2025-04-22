package com.yuanhao.manager.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Caller implements Serializable {
    private static final long serivalVersionUID = 1L;
    private int callerId;
    private String callerWorkNo;
    private String callerPassword;
    private String callerName;
    private String callerSex;
    private Date callerHireDate;
    private Date callerBrith;
    private String callerRemark;
    private Date callerLastLoginTime;
    private String callerLastLoginIp;

    public Caller() {
    }

    public Caller(int callerId, String callerWorkNo, String callerPassword, String callerName, String callerSex, Date callerHireDate, Date callerBrith, String callerRemark, Date callerLastLoginTime, String callerLastLoginIp) {
        this.callerId = callerId;
        this.callerWorkNo = callerWorkNo;
        this.callerPassword = callerPassword;
        this.callerName = callerName;
        this.callerSex = callerSex;
        this.callerHireDate = callerHireDate;
        this.callerBrith = callerBrith;
        this.callerRemark = callerRemark;
        this.callerLastLoginTime = callerLastLoginTime;
        this.callerLastLoginIp = callerLastLoginIp;
    }

    public Caller(String callerWorkNo, String callerPassword, String callerName, String callerSex, Date callerHireDate, Date callerBrith, Date callerLastLoginTime, String callerLastLoginIp) {
        this.callerWorkNo = callerWorkNo;
        this.callerPassword = callerPassword;
        this.callerName = callerName;
        this.callerSex = callerSex;
        this.callerHireDate = callerHireDate;
        this.callerBrith = callerBrith;
        this.callerLastLoginTime = callerLastLoginTime;
        this.callerLastLoginIp = callerLastLoginIp;
    }

    /**
     * 获取
     * @return callerId
     */
    public int getCallerId() {
        return callerId;
    }

    /**
     * 设置
     * @param callerId
     */
    public void setCallerId(int callerId) {
        this.callerId = callerId;
    }

    /**
     * 获取
     * @return callerWorkNo
     */
    public String getCallerWorkNo() {
        return callerWorkNo;
    }

    /**
     * 设置
     * @param callerWorkNo
     */
    public void setCallerWorkNo(String callerWorkNo) {
        this.callerWorkNo = callerWorkNo;
    }

    /**
     * 获取
     * @return callerPassword
     */
    public String getCallerPassword() {
        return callerPassword;
    }

    /**
     * 设置
     * @param callerPassword
     */
    public void setCallerPassword(String callerPassword) {
        this.callerPassword = callerPassword;
    }

    /**
     * 获取
     * @return callerName
     */
    public String getCallerName() {
        return callerName;
    }

    /**
     * 设置
     * @param callerName
     */
    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    /**
     * 获取
     * @return callerSex
     */
    public String getCallerSex() {
        return callerSex;
    }

    /**
     * 设置
     * @param callerSex
     */
    public void setCallerSex(String callerSex) {
        this.callerSex = callerSex;
    }

    /**
     * 获取
     * @return callerHireDate
     */
    public Date getCallerHireDate() {
        return callerHireDate;
    }

    /**
     * 设置
     * @param callerHireDate
     */
    public void setCallerHireDate(Date callerHireDate) {
        this.callerHireDate = callerHireDate;
    }

    /**
     * 获取
     * @return callerBrith
     */
    public Date getCallerBrith() {
        return callerBrith;
    }

    /**
     * 设置
     * @param callerBrith
     */
    public void setCallerBrith(Date callerBrith) {
        this.callerBrith = callerBrith;
    }

    /**
     * 获取
     * @return callerRemark
     */
    public String getcallerRemark() {
        return callerRemark;
    }

    /**
     * 设置
     * @param callerRemark
     */
    public void setcallerRemark(String callerRemark) {
        this.callerRemark = callerRemark;
    }

    /**
     * 获取
     * @return callerLastLoginTime
     */
    public Date getCallerLastLoginTime() {
        return callerLastLoginTime;
    }

    /**
     * 设置
     * @param callerLastLoginTime
     */
    public void setCallerLastLoginTime(Date callerLastLoginTime) {
        this.callerLastLoginTime = callerLastLoginTime;
    }

    /**
     * 获取
     * @return callerLastLoginIp
     */
    public String getCallerLastLoginIp() {
        return callerLastLoginIp;
    }

    /**
     * 设置
     * @param callerLastLoginIp
     */
    public void setCallerLastLoginIp(String callerLastLoginIp) {
        this.callerLastLoginIp = callerLastLoginIp;
    }

    public String toString() {
        return "Caller{callerId = " + callerId + ", callerWorkNo = " + callerWorkNo + ", callerPassword = " + callerPassword + ", callerName = " + callerName + ", callerSex = " + callerSex + ", callerHireDate = " + callerHireDate + ", callerBrith = " + callerBrith + ", callerRemark = " + callerRemark + ", callerLastLoginTime = " + callerLastLoginTime + ", callerLastLoginIp = " + callerLastLoginIp + "}";
    }
}
