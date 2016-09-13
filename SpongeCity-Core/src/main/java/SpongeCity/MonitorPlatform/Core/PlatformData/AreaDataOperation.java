package SpongeCity.MonitorPlatform.Core.PlatformData;

import SpongeCity.MonitorPlatform.DBAccess.DataAccess.*;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AreaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabermai on 2016/1/4.
 */
public class AreaDataOperation {
    private AreaDA ad = new AreaDA();
    public List<DB_AreaModel> getAllArea() {
        List<DB_AreaModel> areaList = new ArrayList<DB_AreaModel>();
        try {
            areaList = ad.getAllArea();
        }catch (Exception ex){
            //log
        }
        return areaList;
    }

    public DB_AreaModel getAreaInfo(int areaId) {
        DB_AreaModel area = new DB_AreaModel();
        try {
            area = ad.getAreaById(areaId);
        }catch (Exception ex){
            //log
        }
        return area;
    }

    public List<DB_AreaModel> getAreaByParentID(int parentID) {
        List<DB_AreaModel> areaList = new ArrayList<DB_AreaModel>();
        try {
            areaList = ad.getAreaByParentID(parentID);
        }catch (Exception ex){
            //log
        }
        return areaList;
    }
}
