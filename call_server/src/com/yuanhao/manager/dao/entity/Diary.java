package com.yuanhao.manager.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Diary implements Serializable {
    private int diaryId;
    private int diaryWindow;
    private String diaryIp;
    private String diaryClient;
    private Date diaryTime;
    private String diaryAction;
    private String diaryCaller;
    private byte diarySuccess;
    private String diaryVachar;

    public Diary() {
    }

    public Diary(int diaryId, int diaryWindow, String diaryIp, String diaryClient, Date diaryTime, String diaryAction, String diaryCaller, byte diarySuccess, String diaryVachar) {
        this.diaryId = diaryId;
        this.diaryWindow = diaryWindow;
        this.diaryIp = diaryIp;
        this.diaryClient = diaryClient;
        this.diaryTime = diaryTime;
        this.diaryAction = diaryAction;
        this.diaryCaller = diaryCaller;
        this.diarySuccess = diarySuccess;
        this.diaryVachar = diaryVachar;
    }

    /**
     * 获取
     * @return diaryId
     */
    public int getDiaryId() {
        return diaryId;
    }

    /**
     * 设置
     * @param diaryId
     */
    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    /**
     * 获取
     * @return diaryWindow
     */
    public int getDiaryWindow() {
        return diaryWindow;
    }

    /**
     * 设置
     * @param diaryWindow
     */
    public void setDiaryWindow(int diaryWindow) {
        this.diaryWindow = diaryWindow;
    }

    /**
     * 获取
     * @return diaryIp
     */
    public String getDiaryIp() {
        return diaryIp;
    }

    /**
     * 设置
     * @param diaryIp
     */
    public void setDiaryIp(String diaryIp) {
        this.diaryIp = diaryIp;
    }

    /**
     * 获取
     * @return diaryClient
     */
    public String getDiaryClient() {
        return diaryClient;
    }

    /**
     * 设置
     * @param diaryClient
     */
    public void setDiaryClient(String diaryClient) {
        this.diaryClient = diaryClient;
    }

    /**
     * 获取
     * @return diaryTime
     */
    public Date getDiaryTime() {
        return diaryTime;
    }

    /**
     * 设置
     * @param diaryTime
     */
    public void setDiaryTime(Date diaryTime) {
        this.diaryTime = diaryTime;
    }

    /**
     * 获取
     * @return diaryAction
     */
    public String getDiaryAction() {
        return diaryAction;
    }

    /**
     * 设置
     * @param diaryAction
     */
    public void setDiaryAction(String diaryAction) {
        this.diaryAction = diaryAction;
    }

    /**
     * 获取
     * @return diaryCaller
     */
    public String getDiaryCaller() {
        return diaryCaller;
    }

    /**
     * 设置
     * @param diaryCaller
     */
    public void setDiaryCaller(String diaryCaller) {
        this.diaryCaller = diaryCaller;
    }

    /**
     * 获取
     * @return diarySuccess
     */
    public byte getDiarySuccess() {
        return diarySuccess;
    }

    /**
     * 设置
     * @param diarySuccess
     */
    public void setDiarySuccess(byte diarySuccess) {
        this.diarySuccess = diarySuccess;
    }

    /**
     * 获取
     * @return diaryVachar
     */
    public String getDiaryVachar() {
        return diaryVachar;
    }

    /**
     * 设置
     * @param diaryVachar
     */
    public void setDiaryVachar(String diaryVachar) {
        this.diaryVachar = diaryVachar;
    }

    public String toString() {
        return "Diary{diaryId = " + diaryId + ", diaryWindow = " + diaryWindow + ", diaryIp = " + diaryIp + ", diaryClient = " + diaryClient + ", diaryTime = " + diaryTime + ", diaryAction = " + diaryAction + ", diaryCaller = " + diaryCaller + ", diarySuccess = " + diarySuccess + ", diaryVachar = " + diaryVachar + "}";
    }
}
