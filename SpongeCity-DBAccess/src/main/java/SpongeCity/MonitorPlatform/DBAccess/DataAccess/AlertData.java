package SpongeCity.MonitorPlatform.DBAccess.DataAccess;

import SpongeCity.MonitorPlatform.DBAccess.Common.SqlConnection;
import SpongeCity.MonitorPlatform.DBAccess.Interface.IAlertOperation;
import SpongeCity.MonitorPlatform.DBAccess.Interface.IDeviceOperation;
import SpongeCity.MonitorPlatform.DBAccess.Interface.IDeviceTypeOperation;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AlertModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceTypeModel;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sabermai on 2016/1/5.
 */
public class AlertData {
    private static SqlSession session = SqlConnection.getSession().openSession();

    public List<DB_AlertModel> getAlertList(int pageIndex, int pageSize) {
        try {
            Map<String, Integer> params = new HashMap<String, Integer>();
            params.put("pageSize", pageSize);
            params.put("itemIndex", (pageIndex - 1) * pageSize);
            IAlertOperation alertOperation = session.getMapper(IAlertOperation.class);
            List<DB_AlertModel> alerts = alertOperation.getAlertList(params);
            return alerts;
        } finally {
            session.close();
        }
    }

    public List<DB_AlertModel> getAlertListByDeviceId(int deviceId, int pageIndex, int pageSize) {
        try {
            Map<String, Integer> params = new HashMap<String, Integer>();
            params.put("deviceId", deviceId);
            params.put("pageSize", pageSize);
            params.put("itemIndex", (pageIndex - 1) * pageSize);
            IAlertOperation alertOperation = session.getMapper(IAlertOperation.class);
            List<DB_AlertModel> alerts = alertOperation.getAlertListByDeviceId(params);
            return alerts;
        } finally {
            session.close();
        }
    }

    public DB_AlertModel getAlertInfoByAlertId(int alertId) {
        try {
            IAlertOperation alertOperation = session.getMapper(IAlertOperation.class);
            DB_AlertModel alert = alertOperation.getAlertInfo(alertId);
            return alert;
        } finally {
            session.close();
        }
    }

    public List<DB_AlertModel> getAllAlertByAreaId(List<String> areaIds, int pageIndex, int pageSize) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            String strAreaIds = "";
            for (String areaId : areaIds) {
                strAreaIds += areaId + ",";
            }
            params.put("areaIds", strAreaIds.substring(0, strAreaIds.lastIndexOf(",") - 1));
            params.put("pageSize", pageSize);
            params.put("itemIndex", (pageIndex - 1) * pageSize);
            IAlertOperation alertOperation = session.getMapper(IAlertOperation.class);
            List<DB_AlertModel> alerts = alertOperation.getAlertListByDeviceId(params);
            return alerts;
        } finally {
            session.close();
        }
    }
}
