package com.yuanhao.manager.dao.impl;

import com.yuanhao.manager.dao.IBusinessWindowDao;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.utils.JdbcUtils;
import com.yuanhao.utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessWindowDaoImpl implements IBusinessWindowDao {
    @Override
    public int businessWindowAdd(BusinessWindow businessWindow) {
        String sql = "INSERT INTO t_business_window(bw_no,bw_type_code,bw_type_name)VALUES(?,?,?)";
        Object[] objects = { businessWindow.getBwNo(), businessWindow.getBwTypeCode(), businessWindow.getBwTypeName()};
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public int businessWindowDelete(String no) {
        String sql = "DELETE FROM t_business_window WHERE bw_no = ?";
        return JdbcUtils.JdbcExcuteUpdate(sql, no);
    }

    @Override
    public int businessWindowUpdate(String no, BusinessWindow businessWindow) {
        String sql = "UPDATE t_business_window SET bw_no=?,bw_type_code=?,bw_type_name=? WHERE bw_no=?";
        Object[] objects = {businessWindow.getBwNo(), businessWindow.getBwTypeCode(), businessWindow.getBwTypeName(), no};
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public List<BusinessWindow> businessWindowList(String condition,int currentPage,int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BusinessWindow> businessWindowList = new ArrayList<>();
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_business_window WHERE bw_no LIKE ? OR bw_type_code LIKE ? OR bw_type_name LIKE ? LIMIT ?,?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + condition + "%");
            preparedStatement.setString(2, "%" + condition + "%");
            preparedStatement.setString(3, "%" + condition + "%");
            preparedStatement.setInt(4,(currentPage-1)*pageSize);
            preparedStatement.setInt(5,pageSize);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BusinessWindow businessWindow = new BusinessWindow();
                businessWindow.setBwId(resultSet.getInt(1));
                businessWindow.setBwNo(resultSet.getString(2));
                businessWindow.setBwTypeCode(resultSet.getString(3));
                businessWindow.setBwTypeName(resultSet.getString(4));
                businessWindowList.add(businessWindow);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return businessWindowList;
        }
    }

    @Override
    public Page<BusinessWindow> businessWindowPages(String condition, int currentPage, int pageSize) {
        int totalPage = totalConunt(condition);
        List<BusinessWindow> businessWindowLists = businessWindowList(condition,currentPage,pageSize);
        Page<BusinessWindow> businessWindowPage = new Page<BusinessWindow>(currentPage, pageSize, totalPage, businessWindowLists);
        return businessWindowPage;
    }

    @Override
    public BusinessWindow businessWindowGetByNo(String no) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BusinessWindow businessWindow = new BusinessWindow();
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_business_window WHERE bw_no = ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, no);


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                businessWindow.setBwId(resultSet.getInt(1));
                businessWindow.setBwNo(resultSet.getString(2));
                businessWindow.setBwTypeCode(resultSet.getString(3));
                businessWindow.setBwTypeName(resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return businessWindow;
        }
    }

    private int totalConunt(String condition) {
        String sql = "SELECT COUNT(1) AS totalCount FROM t_business_window WHERE bw_no LIKE ?OR bw_type_code LIKE ? OR bw_type_name LIKE ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalCount = -1;
        try {
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + condition + "%");
            preparedStatement.setString(2, "%" + condition + "%");
            preparedStatement.setString(3, "%" + condition + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return totalCount;
        }
    }

}
