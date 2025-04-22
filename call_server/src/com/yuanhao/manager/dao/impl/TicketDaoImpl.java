package com.yuanhao.manager.dao.impl;

import com.yuanhao.manager.dao.ITicketDao;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.utils.JdbcUtils;
import com.yuanhao.utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements ITicketDao {

    @Override
    public int ticketAdd(Ticket ticket) {
        System.out.println(ticket.toString());
        String sql = "INSERT INTO t_ticket(ticket_no,ticket_business_code,ticket_business_name,ticket_take_time,ticket_take_ip,ticket_wait_count,ticket_call_time,ticket_call_count,ticket_call_ip,ticket_call_window,ticket_caller_workno,ticket_is_success,ticket_desc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] objects = {ticket.getTicketNo(), ticket.getTicketBusinessCode(), ticket.getTicketBusinessName(), ticket.getTicketTakeTime(), ticket.getTicketTakeIp(), ticket.getTicketWaitCount(), ticket.getTicketCallTime(), ticket.getTicketCallCount(), ticket.getTicketCallIp(), ticket.getTicketCallWindow(), ticket.getTicketCallerWorkno(), ticket.getTicketIsSuccess(), ticket.getTicketDesc()};
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public int ticketDelete(int id) {
        String sql = "DELETE FROM t_ticket WHERE ticket_id = ?";
        return JdbcUtils.JdbcExcuteUpdate(sql, id);
    }

    @Override
    public int ticketUpdate(int id, Ticket ticket) {
        String sql = "UPDATE t_ticket SET ticket_no=?,ticket_business_code=?,ticket_business_name=?,ticket_take_time=?,ticket_take_ip=?,ticket_wait_count=?,ticket_call_time=?,ticket_call_count=?,ticket_call_ip=?,ticket_call_window=?,ticket_caller_workno=?,ticket_is_success=?,ticket_desc=? WHERE ticket_id = ?";
        Object[] objects = {ticket.getTicketNo(), ticket.getTicketBusinessCode(), ticket.getTicketBusinessName(), ticket.getTicketTakeTime(), ticket.getTicketTakeIp(), ticket.getTicketWaitCount(), ticket.getTicketCallTime(), ticket.getTicketCallCount(), ticket.getTicketCallIp(), ticket.getTicketCallWindow(), ticket.getTicketCallerWorkno(), ticket.getTicketIsSuccess(), ticket.getTicketDesc(), id};
        return JdbcUtils.JdbcExcuteUpdate(sql, objects);
    }

    @Override
    public List<Ticket> ticketList(String condition, int currentPage, int pageSize) {
        List<Ticket> ticketList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_ticket WHERE ticket_business_code LIKE ? OR ticket_business_name LIKE ? LIMIT ?,?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "%" + condition + "%");
            preparedStatement.setObject(2, "%" + condition + "%");
            preparedStatement.setInt(3, (currentPage - 1) * pageSize);
            preparedStatement.setInt(4, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Ticket ticket = new Ticket();
               ticket.setTicketId(resultSet.getInt(1));
               ticket.setTicketNo(resultSet.getInt(2));
               ticket.setTicketBusinessCode(resultSet.getString(3));
               ticket.setTicketBusinessName(resultSet.getString(4));
               ticket.setTicketTakeTime(resultSet.getDate(5));
               ticket.setTicketTakeIp(resultSet.getString(6));
               ticket.setTicketWaitCount(resultSet.getInt(7));
               ticket.setTicketCallTime(resultSet.getDate(8));
               ticket.setTicketCallCount(resultSet.getInt(9));
               ticket.setTicketCallIp(resultSet.getString(10));
               ticket.setTicketCallWindow(resultSet.getInt(11));
               ticket.setTicketCallerWorkno(resultSet.getString(12));
               ticket.setTicketIsSuccess(resultSet.getByte(13));
               ticket.setTicketDesc(resultSet.getString(14));
               ticketList.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return ticketList;
        }
    }

    @Override
    public Page<Ticket> ticketPages(String condition, int currentPage, int pageSize) {
        int totalCount = totalCount(condition);
        List<Ticket> ticketList = ticketList(condition,currentPage,pageSize);
        Page<Ticket> ticketPage = new Page<Ticket>(currentPage,pageSize,totalCount,ticketList);
        return ticketPage;
    }

    @Override
    public Ticket ticketGetById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Ticket ticket = new Ticket();
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT * FROM t_ticket WHERE ticket_id = ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                ticket.setTicketId(resultSet.getInt(1));
                ticket.setTicketNo(resultSet.getInt(2));
                ticket.setTicketBusinessCode(resultSet.getString(3));
                ticket.setTicketBusinessName(resultSet.getString(4));
                ticket.setTicketTakeTime(resultSet.getDate(5));
                ticket.setTicketTakeIp(resultSet.getString(6));
                ticket.setTicketWaitCount(resultSet.getInt(7));
                ticket.setTicketCallTime(resultSet.getDate(8));
                ticket.setTicketCallCount(resultSet.getInt(9));
                ticket.setTicketCallIp(resultSet.getString(10));
                ticket.setTicketCallWindow(resultSet.getInt(11));
                ticket.setTicketCallerWorkno(resultSet.getString(12));
                ticket.setTicketIsSuccess(resultSet.getByte(13));
                ticket.setTicketDesc(resultSet.getString(14));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResoure(resultSet, preparedStatement, connection);
            return ticket;
        }
    }

    private int totalCount(String condition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalCount = -1;
        try {
            connection = JdbcUtils.openConn();
            String sql = "SELECT COUNT(1) FROM t_ticket WHERE ticket_business_code LIKE ? OR ticket_business_name LIKE ? ";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "%" + condition + "%");
            preparedStatement.setObject(2, "%" + condition + "%");
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
