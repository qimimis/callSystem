package com.yuanhao.manager.dao.entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private static final long serivalVersionUID = 1L;
    private int adminId;
    private String adminUserName;
    private String adminPassword;
    private String adminIp;
    public Admin() {
    }


    public Admin(int adminId, String adminUserName, String adminPassword, String adminIp) {
        this.adminId = adminId;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
        this.adminIp = adminIp;
    }

    /**
     * 获取
     * @return adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * 设置
     * @param adminId
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取
     * @return adminUserName
     */
    public String getAdminUserName() {
        return adminUserName;
    }

    /**
     * 设置
     * @param adminUserName
     */
    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    /**
     * 获取
     * @return adminPassword
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * 设置
     * @param adminPassword
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String toString() {
        return "Admin{adminId = " + adminId + ", adminUserName = " + adminUserName + ", adminPassword = " + adminPassword + "}";
    }

    /**
     * 获取
     * @return adminIp
     */
    public String getAdminIp() {
        return adminIp;
    }

    /**
     * 设置
     * @param adminIp
     */
    public void setAdminIp(String adminIp) {
        this.adminIp = adminIp;
    }
}
