package com.yuanhao.manager.dao.impl;

import com.yuanhao.manager.dao.ICallerDao;
import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.utils.JdbcUtils;
import com.yuanhao.utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CallerDaoImpl implements ICallerDao {

    @Override
    public int callerAdd(Caller caller) {
        String sql = "INSERT INTO t_caller(c_workno,c_password,c_name,c_sex,c_hiredate,c_birth," +
                "c_last_login_time,c_last_login_ip) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        Object[] objects = { caller.getCallerWorkNo(), caller.getCallerPassword(), caller.getCallerName(), caller.getCallerSex(), caller.getCallerHireDate(), caller.getCallerBrith(), caller.getCallerLastLoginTime(), caller.getCallerLastLoginIp()
        };
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public int callerDelete(String no) {
        String sql = "DELETE FROM t_caller WHERE c_workno = ?";
        return JdbcUtils.JdbcExcuteUpdate(sql, no);
    }

    @Override
    public int callerUpdate(String no, Caller caller) {
        String sql = "UPDATE t_caller SET c_workno=?,c_password=?,c_name=?,c_sex=?,c_hiredate=?,c_birth=?,c_last_login_time=?,c_last_login_ip=? WHERE c_workno = ?";
        Object[] objects = { caller.getCallerWorkNo(), caller.getCallerPassword(), caller.getCallerName(), caller.getCallerSex(), caller.getCallerHireDate(), caller.getCallerBrith(),  caller.getCallerLastLoginTime(), caller.getCallerLastLoginIp(), no
        };
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public List<Caller> callerList(String condition) {
        List<Caller> callers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_caller WHERE c_name LIKE ? OR c_workno LIKE ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "%" + condition + "%");
            preparedStatement.setObject(2, "%" + condition + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Caller caller = new Caller();
                caller.setCallerId(resultSet.getInt(1));
                caller.setCallerWorkNo(resultSet.getString(2));
                caller.setCallerPassword(resultSet.getString(3));
                caller.setCallerName(resultSet.getString(4));
                caller.setCallerSex(resultSet.getString(5));
                caller.setCallerHireDate(resultSet.getDate(6));
                caller.setCallerBrith(resultSet.getDate(7));
                caller.setcallerRemark(resultSet.getString(8));
                caller.setCallerLastLoginTime(resultSet.getDate(9));
                caller.setCallerLastLoginIp(resultSet.getString(10));
                callers.add(caller);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return callers;
        }
    }

    @Override
    public Page<Caller> callerPages(String condition, int currentPage, int pageSize) {
        int totalCount = totalCount(condition);
        List<Caller> callers = callerPagination(condition, currentPage, pageSize);
        Page<Caller> callerPage = new Page<Caller>(currentPage, pageSize, totalCount, callers);
        return callerPage;
    }

    @Override
    public Caller getByNo(String no) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Caller caller = new Caller();
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_caller WHERE c_workno LIKE ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,no);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                caller.setCallerId(resultSet.getInt(1));
                caller.setCallerWorkNo(resultSet.getString(2));
                caller.setCallerPassword(resultSet.getString(3));
                caller.setCallerName(resultSet.getString(4));
                caller.setCallerSex(resultSet.getString(5));
                caller.setCallerHireDate(resultSet.getDate(6));
                caller.setCallerBrith(resultSet.getDate(7));
                caller.setcallerRemark(resultSet.getString(8));
                caller.setCallerLastLoginTime(resultSet.getDate(9));
                caller.setCallerLastLoginIp(resultSet.getString(10));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return caller;
        }
    }

    @Override
    public boolean login(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT DISTINCT COUNT(1) AS total_count FROM t_caller WHERE c_workno = ? AND c_password = ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("total_count")==1){
                    flag = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return flag;
        }
    }

    private List<Caller> callerPagination(String condition, int currentPage, int pageSize) {
        List<Caller> callers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_caller WHERE c_name LIKE ? LIMIT ?,?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "%" + condition + "%");
            preparedStatement.setObject(2, (currentPage - 1) * pageSize);
            preparedStatement.setObject(3, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Caller caller = new Caller();
                caller.setCallerId(resultSet.getInt(1));
                caller.setCallerWorkNo(resultSet.getString(2));
                caller.setCallerPassword(resultSet.getString(3));
                caller.setCallerName(resultSet.getString(4));
                caller.setCallerSex(resultSet.getString(5));
                caller.setCallerHireDate(resultSet.getDate(6));
                caller.setCallerBrith(resultSet.getDate(7));
                caller.setcallerRemark(resultSet.getString(8));
                caller.setCallerLastLoginTime(resultSet.getDate(9));
                caller.setCallerLastLoginIp(resultSet.getString(10));
                callers.add(caller);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return callers;
        }
    }

    private int totalCount(String condition) {
        List<Caller> callers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalCount = -1;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT COUNT(1) AS total_count FROM t_caller WHERE c_name LIKE ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "%" + condition + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt("total_count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return totalCount;
        }
    }
}
