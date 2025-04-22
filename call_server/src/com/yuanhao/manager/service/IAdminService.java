package com.yuanhao.manager.service;

import com.yuanhao.manager.dao.entity.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> showListAdmin(String username);
    public boolean adminLogin(String username,String password);
    public String getIp(String username);
}
