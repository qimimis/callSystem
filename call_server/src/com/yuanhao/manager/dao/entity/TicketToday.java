package com.yuanhao.manager.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class TicketToday implements Serializable {
    private int ticketId;
    private int ticketNo;
    private String ticketBusinessCode;
    private String ticketBusinessName;
    private Date ticketTakeTime;
    private String ticketTakeIp;
    private int ticketWaitCount;
    private int ticketCallCount;
    private Date ticketCallTime;
    private String ticketCallIp;
    private int ticketCallWindow;
    private String ticketCallerWorkno;
    private byte ticketIsSuccess;
    private String ticketDesc;

    public TicketToday() {
    }

    public TicketToday(int ticketId, int ticketNo, String ticketBusinessCode, String ticketBusinessName, Date ticketTakeTime, String ticketTakeIp, int ticketWaitCount, Date ticketCallTime, int ticketCallCount,String ticketCallIp, int ticketCallWindow, String ticketCallerWorkno, byte ticketIsSuccess, String ticketDesc) {
        this.ticketId = ticketId;
        this.ticketNo = ticketNo;
        this.ticketBusinessCode = ticketBusinessCode;
        this.ticketBusinessName = ticketBusinessName;
        this.ticketTakeTime = ticketTakeTime;
        this.ticketTakeIp = ticketTakeIp;
        this.ticketWaitCount = ticketWaitCount;
        this.ticketCallCount = ticketCallCount;
        this.ticketCallTime = ticketCallTime;
        this.ticketCallIp = ticketCallIp;
        this.ticketCallWindow = ticketCallWindow;
        this.ticketCallerWorkno = ticketCallerWorkno;
        this.ticketIsSuccess = ticketIsSuccess;
        this.ticketDesc = ticketDesc;
    }

    /**
     * 获取
     * @return ticketId
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * 设置
     * @param ticketId
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * 获取
     * @return ticketNo
     */
    public int getTicketNo() {
        return ticketNo;
    }

    /**
     * 设置
     * @param ticketNo
     */
    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    /**
     * 获取
     * @return ticketBusinessCode
     */
    public String getTicketBusinessCode() {
        return ticketBusinessCode;
    }

    /**
     * 设置
     * @param ticketBusinessCode
     */
    public void setTicketBusinessCode(String ticketBusinessCode) {
        this.ticketBusinessCode = ticketBusinessCode;
    }

    /**
     * 获取
     * @return ticketBusinessName
     */
    public String getTicketBusinessName() {
        return ticketBusinessName;
    }

    /**
     * 设置
     * @param ticketBusinessName
     */
    public void setTicketBusinessName(String ticketBusinessName) {
        this.ticketBusinessName = ticketBusinessName;
    }

    /**
     * 获取
     * @return ticketTakeTime
     */
    public Date getTicketTakeTime() {
        return ticketTakeTime;
    }

    /**
     * 设置
     * @param ticketTakeTime
     */
    public void setTicketTakeTime(Date ticketTakeTime) {
        this.ticketTakeTime = ticketTakeTime;
    }

    /**
     * 获取
     * @return ticketTakeIp
     */
    public String getTicketTakeIp() {
        return ticketTakeIp;
    }

    /**
     * 设置
     * @param ticketTakeIp
     */
    public void setTicketTakeIp(String ticketTakeIp) {
        this.ticketTakeIp = ticketTakeIp;
    }

    /**
     * 获取
     * @return ticketWaitCount
     */
    public int getTicketWaitCount() {
        return ticketWaitCount;
    }

    /**
     * 设置
     * @param ticketWaitCount
     */
    public void setTicketWaitCount(int ticketWaitCount) {
        this.ticketWaitCount = ticketWaitCount;
    }

    /**
     * 获取
     * @return ticketCallCount
     */
    public int getTicketCallCount() {
        return ticketCallCount;
    }

    /**
     * 设置
     * @param ticketCallCount
     */
    public void setTicketCallCount(int ticketCallCount) {
        this.ticketCallCount = ticketCallCount;
    }

    /**
     * 获取
     * @return ticketCallTime
     */
    public Date getTicketCallTime() {
        return ticketCallTime;
    }

    /**
     * 设置
     * @param ticketCallTime
     */
    public void setTicketCallTime(Date ticketCallTime) {
        this.ticketCallTime = ticketCallTime;
    }

    /**
     * 获取
     * @return ticketCallIp
     */
    public String getTicketCallIp() {
        return ticketCallIp;
    }

    /**
     * 设置
     * @param ticketCallIp
     */
    public void setTicketCallIp(String ticketCallIp) {
        this.ticketCallIp = ticketCallIp;
    }

    /**
     * 获取
     * @return ticketCallWindow
     */
    public int getTicketCallWindow() {
        return ticketCallWindow;
    }

    /**
     * 设置
     * @param ticketCallWindow
     */
    public void setTicketCallWindow(int ticketCallWindow) {
        this.ticketCallWindow = ticketCallWindow;
    }

    /**
     * 获取
     * @return ticketCallerWorkno
     */
    public String getTicketCallerWorkno() {
        return ticketCallerWorkno;
    }

    /**
     * 设置
     * @param ticketCallerWorkno
     */
    public void setTicketCallerWorkno(String ticketCallerWorkno) {
        this.ticketCallerWorkno = ticketCallerWorkno;
    }

    /**
     * 获取
     * @return ticketIsSuccess
     */
    public byte getTicketIsSuccess() {
        return ticketIsSuccess;
    }

    /**
     * 设置
     * @param ticketIsSuccess
     */
    public void setTicketIsSuccess(byte ticketIsSuccess) {
        this.ticketIsSuccess = ticketIsSuccess;
    }

    /**
     * 获取
     * @return ticketDesc
     */
    public String getTicketDesc() {
        return ticketDesc;
    }

    /**
     * 设置
     * @param ticketDesc
     */
    public void setTicketDesc(String ticketDesc) {
        this.ticketDesc = ticketDesc;
    }

    public String toString() {
        return "TicketToday{ticketId = " + ticketId + ", ticketNo = " + ticketNo + ", ticketBusinessCode = " + ticketBusinessCode + ", ticketBusinessName = " + ticketBusinessName + ", ticketTakeTime = " + ticketTakeTime + ", ticketTakeIp = " + ticketTakeIp + ", ticketWaitCount = " + ticketWaitCount + ", ticketCallCount = " + ticketCallCount + ", ticketCallTime = " + ticketCallTime + ", ticketCallIp = " + ticketCallIp + ", ticketCallWindow = " + ticketCallWindow + ", ticketCallerWorkno = " + ticketCallerWorkno + ", ticketIsSuccess = " + ticketIsSuccess + ", ticketDesc = " + ticketDesc + "}";
    }
}
