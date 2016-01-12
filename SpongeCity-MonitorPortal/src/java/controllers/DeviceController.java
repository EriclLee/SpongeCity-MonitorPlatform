package controllers;

import SpongeCity.MonitorPlatform.Core.PlatformData.*;
import SpongeCity.MonitorPlatform.DBAccess.DataAccess.LogDA;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AlertModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AreaModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceLogModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceModel;
import Util.ModelConverter;
import Util.SortList;
import models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EriclLee on 15/12/29.
 */
@Controller
@RequestMapping(value = "/devices")
public class DeviceController {
    private final int DEVICE_LOG_COUNT = 10;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(int pageIndex, int areaId, String sortKey, String sortDes) {
        ModelAndView modelAndView = new ModelAndView("/devices/index");
        PageDivisionModel<DeviceModel> deviceModelPageDivisionModel = new PageDivisionModel<DeviceModel>();
        List<DeviceModel> deviceModels = new ArrayList<DeviceModel>();
        //TODO: get device list;
        List<DeviceModel> allDevices = devices(areaId, null);
        deviceModelPageDivisionModel.setRecordCount(allDevices.size());
        deviceModelPageDivisionModel.setCurrentPageIndex(pageIndex);
        if (sortKey == null || sortKey == "") {
            sortKey = "getDeviceTypeName";
        }
        if (sortDes == null || sortDes == "") {
            sortDes = "desc";
        }
        SortList<DeviceModel> sortList = new SortList<DeviceModel>();
        sortList.Sort(allDevices, sortKey, sortDes);
        Integer pageSize = deviceModelPageDivisionModel.getPageSize();
        Integer toIndex = pageIndex * pageSize + pageSize;
        if (allDevices.size() - pageIndex * pageSize < pageSize) {
            toIndex = allDevices.size();
        }
        deviceModels = allDevices.subList(pageIndex * pageSize, toIndex);
        deviceModelPageDivisionModel.setData(deviceModels);
        modelAndView.addObject("devices", deviceModelPageDivisionModel);
        modelAndView.addObject("sortKey", sortKey);
        modelAndView.addObject("sortDes", sortDes);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(int deviceId) {
        ModelAndView modelAndView = new ModelAndView("/devices/detail");
        DeviceModel deviceModel = new DeviceModel();
        ModelConverter converter = new ModelConverter();

        //get device info
        DeviceDataOperation deviceDataOperation = new DeviceDataOperation();
        DB_DeviceModel db_deviceModel = deviceDataOperation.getDeviceInfo(deviceId);
        deviceModel = converter.convertDBDeviceModel2PortalDeviceModel(db_deviceModel);

        //get device data
        /*DataOperation dataOperation = new DataOperation();
        dataOperation.getData()*/

        //get device log
        LogDataOperation logDataOperation = new LogDataOperation();
        List<DB_DeviceLogModel> db_deviceLogModels = logDataOperation.getLastDeviceLogList(deviceId, DEVICE_LOG_COUNT);
        List<DeviceLogModel> deviceLogModels = new ArrayList<DeviceLogModel>();
        if (db_deviceLogModels != null) {
            for (DB_DeviceLogModel db_deviceLogModel : db_deviceLogModels) {
                deviceLogModels.add(converter.convertDBDeviceLog2PortalDeviceLog(db_deviceLogModel));
            }
        }
        deviceModel.setDeviceLogList(deviceLogModels);

        modelAndView.addObject("device", deviceModel);
        return modelAndView;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceModel> devices(@RequestParam int areaId, int[] deviceTypeIds) {
        List<DeviceModel> deviceModels = new ArrayList<DeviceModel>();
        AreaDataOperation areaDataOperation = new AreaDataOperation();
        List<DB_AreaModel> db_areaModels = areaDataOperation.getAllArea();
        List<Integer> allAreaIds = getAllSubAreaId(areaId, db_areaModels);
        DeviceDataOperation deviceDataOperation = new DeviceDataOperation();
        List<DB_DeviceModel> deviceModelList = deviceDataOperation.getAllDevice();
        List<DB_DeviceModel> deviceModelListByTypes = getDevicesByGTypes(deviceModelList, deviceTypeIds);
        AlertDataOperation alertDataOperation = new AlertDataOperation();

        for (Integer aId : allAreaIds) {
            for (DB_DeviceModel db_deviceModel : deviceModelListByTypes) {
                if (db_deviceModel.getArea().getId() == aId) {
                    DeviceModel deviceModel = new DeviceModel();

                    //region Unused. Already extract to Util.ModelConverter class
                    /*deviceModel.setId(db_deviceModel.getId());
                    List<DB_AlertModel> alerts = alertDataOperation.getAlertListByDeviceId(deviceModel.getId());
                    deviceModel.setAlertCount(alerts.size());
                    deviceModel.setDevice_id(db_deviceModel.getDeviceid());
                    deviceModel.setAreaName(getDeviceArea(db_deviceModel, db_areaModels));
                    deviceModel.setBlockName(getDeviceBlock(db_deviceModel, db_areaModels));
                    deviceModel.setState(DeviceState.fromString(db_deviceModel.getState()));
                    deviceModel.setMeasureName(getDeviceMeasureName(db_deviceModel, db_areaModels));
                    deviceModel.setCoordinate(new Coordinate(db_deviceModel.getLatitude(), db_deviceModel.getLongitude()));
                    DeviceTypeModel dtModel = new DeviceTypeModel(db_deviceModel.getDevicetype().getId(),
                            db_deviceModel.getDevicetype().getName(),db_deviceModel.getDevicetype().getName());
                    deviceModel.setDeviceType(dtModel);
                    deviceModel.setComments(db_deviceModel.getComments());*/
                    //endregion

                    ModelConverter converter = new ModelConverter();
                    deviceModel = converter.convertDBDeviceModel2PortalDeviceModel(db_deviceModel);
                    deviceModels.add(deviceModel);
                }
            }
        }
        return deviceModels;
    }

    private List<Integer> getAllSubAreaId(int aid, List<DB_AreaModel> db_areaModelList) {
        List<Integer> allSubAreaId = new ArrayList<Integer>();
        allSubAreaId.add(aid);
        for (DB_AreaModel areaDbModel : db_areaModelList) {
            if (areaDbModel.getParentarea_id() == aid) {
                allSubAreaId.addAll(getAllSubAreaId(areaDbModel.getId(), db_areaModelList));
            }
        }
        return allSubAreaId;
    }

    private List<DB_DeviceModel> getDevicesByGTypes(List<DB_DeviceModel> deviceModels, int[] deviceTypeIds) {
        List<DB_DeviceModel> result = new ArrayList<DB_DeviceModel>();
        if (deviceTypeIds != null) {
            for (int typeId : deviceTypeIds) {
                for (DB_DeviceModel db_deviceModel : deviceModels) {
                    if (db_deviceModel.getDevicetype().getId() == typeId) {
                        result.add(db_deviceModel);
                    }
                }
            }
        } else {
            result = deviceModels;
        }
        return result;
    }

    //region Unused. Already extract to Util.ModelConverter class
    private String getDeviceArea(DB_DeviceModel deviceModel, List<DB_AreaModel> db_areaModels) {
        DB_AreaModel areaModel = deviceModel.getArea();
        while (areaModel.getParentarea_id() != 0) {
            for (DB_AreaModel db_areaModel : db_areaModels) {
                if (db_areaModel.getId() == areaModel.getParentarea_id()) {
                    areaModel = db_areaModel;
                }
            }
        }
        return areaModel.getName();
    }

    private String getDeviceBlock(DB_DeviceModel deviceModel, List<DB_AreaModel> db_areaModels) {
        DB_AreaModel areaModel = deviceModel.getArea();
        if (areaModel.getParentarea_id() == 0) {
            return null;
        }
        for (DB_AreaModel db_areaModel : db_areaModels) {
            if (db_areaModel.getId() == areaModel.getParentarea_id()) {
                if (db_areaModel.getParentarea_id() == 0) {
                    break;
                } else {
                    areaModel = db_areaModel;
                }
            }
        }
        return areaModel.getName();
    }

    private String getDeviceMeasureName(DB_DeviceModel deviceModel, List<DB_AreaModel> db_areaModels) {
        DB_AreaModel areaModel = deviceModel.getArea();
        if (areaModel.getParentarea_id() == 0) {
            return null;
        }
        for (DB_AreaModel db_areaModel : db_areaModels) {
            if (db_areaModel.getId() == areaModel.getParentarea_id()) {
                areaModel = db_areaModel;
            }
        }
        if (areaModel.getParentarea_id() == 0) {
            return null;
        } else {
            return deviceModel.getArea().getName();
        }
    }
    //endregion
}
