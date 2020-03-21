package com.huanghe.alarm.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AlarmHistorical {

    //原始id
    private String orgId;

    //告警编码
    private String alarmCode;

    //告警名称
    private String alarmName;

    //设备名称
    private String deviceName;

    //告警类别
    private String alarmClass;

    /**
     * 告警等级（1:一级 2：二级 3：三级 4：四级）
     */
    private String alarmLevel;

    //电场名称
    private String plantName;

    //状态
    private String alarmActive;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Date endTime;

    //状态变更时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actChTime;

    //告警类型
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
