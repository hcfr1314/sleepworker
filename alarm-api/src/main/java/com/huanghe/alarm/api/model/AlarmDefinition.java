package com.huanghe.alarm.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 告警定义表
 */
public class AlarmDefinition {

    /**
     * 告警编码
     */
    private String alarmCode;


    /**
     * 测点原始id
     */
    private String orgId;

    /**
     * 测点全局编码
     */
    private String globalCode;

    /**
     * 告警名称
     */
    private String alarmName;

    /**
     * 告警标记（3，4，5）
     */
    private String alarmTag;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备路径
     */
    private String devicePath;

    /**
     * 场站单位名称
     */
    private String plantName;

    /**
     * 告警等级（1:一级 2：二级 3：三级 4：四级）
     */
    private String alarmLevel;

    /**
     * 告警类别（）
     */
    private String alarmClass;

    /**
     * 告警颜色(1-4:红，黄，蓝，绿)
     */
    private String alarmColor;

    /**
     * 测点状态
     */
    private String pointStatus;

    /**
     * 有效性（0:有效，1：无效）
     */
    private String effectiveness;

    /**
     * 信号类型（YX：遥测，YC:遥信）
     */
    private String signalType;

    /**
     * 测点描述
     */
    private String descript;

    /**
     * 场站类型
     */
    private String plantType;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifyTime;


    public String getDevicePath() {
        return devicePath;
    }

    public void setDevicePath(String devicePath) {
        this.devicePath = devicePath;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getGlobalCode() {
        return globalCode;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getAlarmTag() {
        return alarmTag;
    }

    public void setAlarmTag(String alarmTag) {
        this.alarmTag = alarmTag;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setGlobalCode(String globalCode) {
        this.globalCode = globalCode;
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

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(String alarmClass) {
        this.alarmClass = alarmClass;
    }

    public String getAlarmColor() {
        return alarmColor;
    }

    public void setAlarmColor(String alarmColor) {
        this.alarmColor = alarmColor;
    }

    public String getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }

    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }

    public String getPointStatus() {
        return pointStatus;
    }

    public void setPointStatus(String pointStatus) {
        this.pointStatus = pointStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
