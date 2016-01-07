package controllers;

import SpongeCity.MonitorPlatform.Core.PlatformData.AlertDataOperation;
import SpongeCity.MonitorPlatform.Core.PlatformData.AreaDataOperation;
import SpongeCity.MonitorPlatform.Core.PlatformData.DeviceDataOperation;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AlertModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_AreaModel;
import SpongeCity.MonitorPlatform.DBAccess.Model.DB_DeviceModel;
import Util.SortList;
import models.AreaModel;
import models.Coordinate;
import models.DeviceModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EriclLee on 15/12/29.
 */
@Controller
@RequestMapping(value = "/devices/")
public class DeviceController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(String sortKey, String sortDes)
    {
        ModelAndView modelAndView = new ModelAndView("/devices/index");
        List<DeviceModel> deviceModels = new ArrayList<DeviceModel>();
        //TODO: get device list;
        if(sortKey== null || sortKey=="")
        {
            sortKey="getDeviceTypeName";
        }
        if(sortDes==null||sortDes=="")
        {
            sortDes="desc";
        }
        SortList<DeviceModel> sortList = new SortList<DeviceModel>();
        sortList.Sort(deviceModels, sortKey, sortDes);
        modelAndView.addObject("devices", deviceModels);
        modelAndView.addObject("sortKey",sortKey);
        modelAndView.addObject("sortDes",sortDes);
        return modelAndView;
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public ModelAndView detail()
    {
        ModelAndView modelAndView = new ModelAndView("/devices/index");
        DeviceModel deviceModel = new DeviceModel();
        //TODO: get device;
        modelAndView.addObject("device", deviceModel);
        return modelAndView;
    }

    @RequestMapping(value="/devices",method = RequestMethod.GET)
    public Map<String,Object> devices(@RequestParam int areaId, int[] deviceTypeIds)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        List<DeviceModel> deviceModels = new ArrayList<DeviceModel>();
        AreaDataOperation areaDataOperation = new AreaDataOperation();
        List<DB_AreaModel> db_areaModels = areaDataOperation.getAllArea();
        List<Integer> allAreaIds= getAllSubAreaId(areaId, db_areaModels);
        DeviceDataOperation deviceDataOperation = new DeviceDataOperation();
        List<DB_DeviceModel> deviceModelList = deviceDataOperation.getAllDevice();
        AlertDataOperation alertDataOperation = new AlertDataOperation();

        for(Integer aId :allAreaIds)
        {
            for(DB_DeviceModel db_deviceModel : deviceModelList)
            {
                if(db_deviceModel.getArea().getId() == aId)
                {
                    DeviceModel deviceModel = new DeviceModel();
                    deviceModel.setId(db_deviceModel.getId());
                    //alertDataOperation.getAlertListByDeviceId(deviceModel.getId());
                    //TODO get alert count
                    deviceModel.setAlertCount(0);
                    deviceModel.setDevice_id(db_deviceModel.getDeviceid());
                    deviceModel.setCoordinate(new Coordinate(db_deviceModel.getLatitude(), db_deviceModel.getLongitude()));
                    deviceModels.add(deviceModel);
                }
            }
        }
        return result;
    }

    private List<Integer> getAllSubAreaId(int aid, List<DB_AreaModel> db_areaModelList)
    {
        List<Integer> allSubAreaId = new ArrayList<Integer>();
        allSubAreaId.add(aid);
        for(DB_AreaModel areaDbModel : db_areaModelList){
            if(areaDbModel.getParentarea_id() == aid)
            {
                allSubAreaId.addAll(getAllSubAreaId(areaDbModel.getId(), db_areaModelList));
            }
        }
        return allSubAreaId;
    }
}
