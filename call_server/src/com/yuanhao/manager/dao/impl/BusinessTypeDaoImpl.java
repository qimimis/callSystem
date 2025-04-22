package com.yuanhao.manager.dao.impl;

import com.yuanhao.manager.dao.IBusinessTypeDao;
import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.utils.JdbcUtils;
import com.yuanhao.utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessTypeDaoImpl implements IBusinessTypeDao {
    @Override
    public int businessTypeAdd(BusinessType businessType) {
        String sql = "INSERT INTO t_business_type(bt_code,bt_name,bt_limit_count,bt_desc) VALUES(?,?,?,?)";
        Object[] objects = { businessType.getBusinessTypeCode(), businessType.getBusinessTypeName(), businessType.getBusinessTypeLimitCount(), businessType.getBusinessTypeDesc()};
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public int businessTypeDelete(String no) {
        String sql = "DELETE FROM t_business_type WHERE bt_code = ?";
        return JdbcUtils.JdbcExcuteUpdate(sql, no);
    }

    @Override
    public int businessTypeUpdate(String no, BusinessType businessType) {
        String sql = "UPDATE t_business_type SET bt_code=?,bt_name=?,bt_limit_count=?,bt_desc=? WHERE bt_code = ?";
        Object[] objects = {businessType.getBusinessTypeCode(), businessType.getBusinessTypeName(), businessType.getBusinessTypeLimitCount(), businessType.getBusinessTypeDesc(), no};
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public List<BusinessType> businessTypeList(String condition, int currentPage, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BusinessType> businessTypeList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM t_business_type WHERE bt_code LIKE ? OR bt_name LIKE ? OR bt_desc LIKE ? LIMIT ?,?";
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + condition + "%");
            preparedStatement.setString(2, "%" + condition + "%");
            preparedStatement.setString(3, "%" + condition + "%");
            preparedStatement.setInt(4, (currentPage - 1) * pageSize);
            preparedStatement.setInt(5, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BusinessType businessType = new BusinessType();
                businessType.setBusinessTypeId(resultSet.getInt(1));
                businessType.setBusinessTypeCode(resultSet.getString(2));
                businessType.setBusinessTypeName(resultSet.getString(3));
                businessType.setBusinessTypeLimitCount(resultSet.getInt(4));
                businessType.setBusinessTypeDesc(resultSet.getString(5));
                businessTypeList.add(businessType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return businessTypeList;
        }
    }

    @Override
    public Page<BusinessType> businessTypePages(String condition, int currentPage, int pageSize) {
        int totalCount = totalCount(condition);
        List<BusinessType> businessTypeList = businessTypeList(condition, currentPage, pageSize);
        Page<BusinessType> businessTypePage = new Page<BusinessType>(currentPage, pageSize, totalCount, businessTypeList);
        return businessTypePage;
    }

    @Override
    public BusinessType businessTypeGetByNo(String no) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BusinessType businessType = new BusinessType();
        try {
            String sql = "SELECT * FROM t_business_type WHERE bt_code = ? ";
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, no);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                businessType.setBusinessTypeId(resultSet.getInt(1));
                businessType.setBusinessTypeCode(resultSet.getString(2));
                businessType.setBusinessTypeName(resultSet.getString(3));
                businessType.setBusinessTypeLimitCount(resultSet.getInt(4));
                businessType.setBusinessTypeDesc(resultSet.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return businessType;
        }
    }

    @Override
    public List<BusinessType> typeList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BusinessType> businessTypeList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM t_business_type";
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BusinessType businessType = new BusinessType();
                businessType.setBusinessTypeId(resultSet.getInt(1));
                businessType.setBusinessTypeCode(resultSet.getString(2));
                businessType.setBusinessTypeName(resultSet.getString(3));
                businessType.setBusinessTypeLimitCount(resultSet.getInt(4));
                businessType.setBusinessTypeDesc(resultSet.getString(5));
                businessTypeList.add(businessType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return businessTypeList;
        }
    }

    @Override
    public BusinessType getBybtCode(String btCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BusinessType businessType = new BusinessType();
        try {
            String sql = "SELECT * FROM t_business_type WHERE bt_code = ? ";
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, btCode);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                businessType.setBusinessTypeId(resultSet.getInt(1));
                businessType.setBusinessTypeCode(resultSet.getString(2));
                businessType.setBusinessTypeName(resultSet.getString(3));
                businessType.setBusinessTypeLimitCount(resultSet.getInt(4));
                businessType.setBusinessTypeDesc(resultSet.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return businessType;
        }
    }

    private int totalCount(String condition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalCount = -1;
        try {
            String sql = "SELECT COUNT(1) FROM t_business_type WHERE bt_code LIKE ? OR bt_name LIKE ? OR bt_desc LIKE ?";
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + condition + "%");
            preparedStatement.setString(2, "%" + condition + "%");
            preparedStatement.setString(3, "%" + condition + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
