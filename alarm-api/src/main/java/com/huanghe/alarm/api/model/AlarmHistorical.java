package com.huanghe.alarm.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AlarmHistorical {

    //ԭʼid
    private String orgId;

    //�澯����
    private String alarmCode;

    //�澯����
    private String alarmName;

    //�豸����
    private String deviceName;

    //�澯���
    private String alarmClass;

    /**
     * �澯�ȼ���1:һ�� 2������ 3������ 4���ļ���
     */
    private String alarmLevel;

    //�糡����
    private String plantName;

    //״̬
    private String alarmActive;

    /**
     * ��ʼʱ��
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * ����ʱ��
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Date endTime;

    //״̬���ʱ��
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actChTime;

    //�澯����
    private String signType;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(String alarmClass) {
        this.alarmClass = alarmClass;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getAlarmActive() {
        return alarmActive;
    }

    public void setAlarmActive(String alarmActive) {
        this.alarmActive = alarmActive;
    }

    public Date getActChTime() {
        return actChTime;
    }

    public void setActChTime(Date actChTime) {
        this.actChTime = actChTime;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
