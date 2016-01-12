package models;

import java.util.Date;
import java.util.List;

/**
 * Created by EriclLee on 15/12/30.
 */
public class DeviceModel {
    private int id;
    private String device_id;
    private DeviceState state;
    private DeviceTypeModel deviceType;
    //private String deviceTypeName;
    private String areaName;
    private String blockName;
    private String measureName;
    //private AreaModel area;
    private String model;
    private String comments;
    private Date firstUpTime;
    private Date updateTime;
    private int alertCount;
    private Coordinate coordinate;
    private List<DeviceLogModel> deviceLogList;

    public List<DeviceLogModel> getDeviceLogList() {
        return deviceLogList;
    }

    public void setDeviceLogList(List<DeviceLogModel> deviceLogList) {
        this.deviceLogList = deviceLogList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public DeviceState getState() {
        return state;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public DeviceTypeModel getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypeModel deviceType) {
        this.deviceType = deviceType;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    //    public DeviceTypeModel getDeviceType() {
//        return deviceType;
//    }
//
//    public void setDeviceType(DeviceTypeModel deviceType) {
//        this.deviceType = deviceType;
//    }
//
//    public AreaModel getArea() {
//        return area;
//    }
//
//    public void setArea(AreaModel area) {
//        this.area = area;
//    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getFirstUpTime() {
        return firstUpTime;
    }

    public void setFirstUpTime(Date firstUpTime) {
        this.firstUpTime = firstUpTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
