package SpongeCity.MonitorPlatform.DBAccess.DataAccess;

import SpongeCity.MonitorPlatform.DBAccess.Common.SqlConnection;
import SpongeCity.MonitorPlatform.DBAccess.Interface.IDataOperation;
import SpongeCity.MonitorPlatform.DBAccess.Interface.IDataTypeOperation;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AreaModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DataModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DataTypeModel;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sabermai on 2016/1/8.
 */
public class DataDA {
    public List<DB_DataModel> getDataByDataypeandArea(int dataTypeid, List<DB_AreaModel> areas) {
        SqlSession session = SqlConnection.getSession();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            String strAreaIds = "";
            for (DB_AreaModel area : areas) {
                strAreaIds += area.getId() + ",";
            }
            params.put("dataTypeid", dataTypeid);
            params.put("areaIds", strAreaIds.substring(0, strAreaIds.length() - 1));
            IDataOperation dataOperation = session.getMapper(IDataOperation.class);
            List<DB_DataModel> datas = dataOperation.getDataByDataTypeandArea(params);
            return datas;
        } finally {
            session.close();
        }
    }

    public List<DB_DataModel> getDataByDataTypeAreaAndTime(int dataTypeid, List<DB_AreaModel> areas, Date startTime, Date endTime) {
        SqlSession session = SqlConnection.getSession();
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
            Map<String, Object> params = new HashMap<String, Object>();
            String strAreaIds = "";
            for (DB_AreaModel area : areas) {
                strAreaIds += area.getId() + ",";
            }
            params.put("dataTypeid", dataTypeid);
            params.put("areaIds", strAreaIds.substring(0, strAreaIds.length() - 1));
            params.put("startTime", "'" + ft.format(startTime) + "'");
            params.put("endTime", "'" + ft.format(endTime) + "'");
            IDataOperation dataOperation = session.getMapper(IDataOperation.class);
            List<DB_DataModel> datas = dataOperation.getDataByDataTypeAreaAndTime(params);
            return datas;
        } finally {
            session.close();
        }
    }

    public List<DB_DataTypeModel> getDataTypeList(){
        SqlSession session = SqlConnection.getSession();
        try {
            IDataTypeOperation dataTypeOperation = session.getMapper(IDataTypeOperation.class);
            List<DB_DataTypeModel> datatypes = dataTypeOperation.getDataTypeList();
            return datatypes;
        } finally {
            session.close();
        }
    }

    public List<DB_DataModel> getDataByArea(List<DB_AreaModel> areas) {
        SqlSession session = SqlConnection.getSession();
        try {
            String strAreaIds = "";
            for (DB_AreaModel area : areas) {
                strAreaIds += area.getId() + ",";
            }
            IDataOperation dataOperation = session.getMapper(IDataOperation.class);
            List<DB_DataModel> datas = dataOperation.getDataByArea(strAreaIds.substring(0, strAreaIds.length() - 1));
            return datas;
        } finally {
            session.close();
        }
    }
}
