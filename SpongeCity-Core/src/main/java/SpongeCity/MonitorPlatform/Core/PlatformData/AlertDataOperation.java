package SpongeCity.MonitorPlatform.Core.PlatformData;

import SpongeCity.MonitorPlatform.DBAccess.DataAccess.AlertDA;
import SpongeCity.MonitorPlatform.DBAccess.DataAccess.AreaDA;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AlertModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AreaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabermai on 2016/1/4.
 */
public class AlertDataOperation {
    private AlertDA ad = new AlertDA();

    public List<DB_AlertModel> getAlertList(int pageIndex, int pageSize) {
        List<DB_AlertModel> alertList = new ArrayList<DB_AlertModel>();
        try {
            alertList = ad.getAlertList(pageIndex + 1, pageSize);
        } catch (Exception ex) {
            //log
        }
        return alertList;
    }

    public List<DB_AlertModel> getAlertListByDeviceId(int deviceId) {
        List<DB_AlertModel> alertList = new ArrayList<DB_AlertModel>();
        try {
            alertList = ad.getAlertListByDeviceId(deviceId);
        } catch (Exception ex) {
            //log
        }
        return alertList;
    }

    public List<DB_AlertModel> getAreaAlertList(int areaId, int pageIndex, int pageSize) {
        List<DB_AlertModel> alertList = new ArrayList<DB_AlertModel>();
        try {
            List<DB_AreaModel> areas = new ArrayList<DB_AreaModel>();
            AreaDA areaData = new AreaDA();
            areas = areaData.getAreaAllChildren(areaId);
            areas.add(areaData.getAreaById(areaId));
            alertList = ad.getAllAlertByAreaList(areas, pageIndex + 1, pageSize);
        } catch (Exception ex) {
            //log
        }
        return alertList;
    }

    public int getAreaAlertCount(int areaId) {
        int count = 0;
        try {
            List<DB_AreaModel> areas = new ArrayList<DB_AreaModel>();
            AreaDA areaData = new AreaDA();
            areas = areaData.getAreaAllChildren(areaId);
            areas.add(areaData.getAreaById(areaId));
            count = ad.getAllAlertCountByAreaList(areas);
        } catch (Exception ex) {
            //log
        }
        return count;
    }

    public DB_AlertModel getAlertInfo(int alertId) {
        DB_AlertModel alert = new DB_AlertModel();
        try {
            alert = ad.getAlertInfoByAlertId(alertId);
        } catch (Exception ex) {
            //log
        }
        return alert;
    }
}
