package com.yuanhao.manager.dao;

import com.yuanhao.manager.dao.entity.Admin;

import java.util.List;

public interface IAdminDao {
    List<Admin> showListAdmin(String username);

    boolean adminLogin(String username, String password);

    String getIp(String username);
}
