package com.yuanhao.manager.dao.impl;

import com.yuanhao.manager.dao.IAdminDao;
import com.yuanhao.manager.dao.entity.Admin;
import com.yuanhao.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements IAdminDao {
    @Override
    public List<Admin> showListAdmin(String username) {
        List<Admin> admins = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_admin WHERE adm_username =?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "%" + username + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getInt(1));
                admin.setAdminUserName(resultSet.getString(2));
                admin.setAdminPassword(resultSet.getString(3));
                admin.setAdminIp(resultSet.getString(4));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return admins;
        }
    }

    @Override
    public boolean adminLogin(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_admin WHERE adm_username =? && adm_password = ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);

        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return flag;
        }
    }

    @Override
    public String getIp(String username) {
        String adm_ip = null;
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT adm_ip FROM t_admin WHERE adm_username =?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                adm_ip = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return adm_ip;
        }
    }
}
