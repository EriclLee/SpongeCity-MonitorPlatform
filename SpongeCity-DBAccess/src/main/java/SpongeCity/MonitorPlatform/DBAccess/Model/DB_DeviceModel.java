package SpongeCity.MonitorPlatform.DBAccess.Model;

import java.util.Date;

/**
 * Created by sabermai on 2016/1/4.
 */
public class DB_DeviceModel {
    private int id;
    private String deviceid;
    private int devicetype_id;
    private DB_DeviceTypeModel devicetype;
    private String model;
    private String comments;
    private String longitude;
    private String latitude;
    private int area_id;
    private DB_AreaModel area;
    private Date firstuptime;
    private Date updatetime;
    private String state;
    private int datastatus;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public DB_DeviceTypeModel getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(DB_DeviceTypeModel devicetype) {
        this.devicetype = devicetype;
    }

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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public DB_AreaModel getArea() {
        return area;
    }

    public void setArea(DB_AreaModel area) {
        this.area = area;
    }

    public Date getFirstuptime() {
        return firstuptime;
    }

    public void setFirstuptime(Date firstuptime) {
        this.firstuptime = firstuptime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDatastatus() {
        return datastatus;
    }

    public void setDatastatus(int datastatus) {
        this.datastatus = datastatus;
    }

    public int getDevicetype_id() {
        return devicetype_id;
    }

    public void setDevicetype_id(int devicetype_id) {
        this.devicetype_id = devicetype_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }
}
