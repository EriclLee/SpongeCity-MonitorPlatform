package SpongeCity.MonitorPlatform.Core.PlatformData;

import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabermai on 2016/1/4.
 */
public class DeviceData {
    public List<DB_DeviceTypeModel> getAllDeviceType() {
        List<DB_DeviceTypeModel> deviceTypeList = new ArrayList<DB_DeviceTypeModel>();

        return deviceTypeList;
    }

    public List<DB_DeviceModel> getAllDevice(){
        List<DB_DeviceModel> deviceList = new ArrayList<DB_DeviceModel>();

        return deviceList;
    }

    public DB_DeviceModel getDeviceInfo(int deviceId){
        DB_DeviceModel device = new DB_DeviceModel();

        return device;
    }
}
