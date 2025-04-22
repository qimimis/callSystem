package com.yuanhao.manager.service.impl;

import com.yuanhao.manager.dao.IAdminDao;
import com.yuanhao.manager.dao.entity.Admin;
import com.yuanhao.manager.dao.impl.AdminDaoImpl;
import com.yuanhao.manager.service.IAdminService;

import java.util.List;

public class AdminServiceImpl implements IAdminService {
    IAdminDao adminDao = new AdminDaoImpl();
    @Override
    public List<Admin> showListAdmin(String username) {
        return adminDao.showListAdmin(username);
    }

    @Override
    public boolean adminLogin(String username, String password) {
        return adminDao.adminLogin(username,password);
    }

    @Override
    public String getIp(String username) {
        return adminDao.getIp(username);
    }
}
