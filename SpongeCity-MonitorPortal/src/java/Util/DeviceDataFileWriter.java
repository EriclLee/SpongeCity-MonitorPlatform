package Util;

import models.DataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabermai on 2016/1/12.
 */
public class DeviceDataFileWriter {
    public void writeCSV(String[] heads, List<DataModel> dataModels, String filePath, String fileName) {
        try {
            File csv = new File(filePath + fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            bw.newLine();
            String strHead = "";
            for (String head : heads) {
                strHead += head + ",";
            }
            bw.write(strHead.substring(0, strHead.length() - 1));

            for (DataModel dataModel : dataModels) {
                // 新增一行数据
                bw.newLine();
                StringBuilder sb = new StringBuilder();
                sb.append(dataModel.getDatatime().toString() + ",");
                sb.append(dataModel.getAreaName() + ",");
                sb.append(dataModel.getBlockName() + ",");
                sb.append(dataModel.getMeasureName() + ",");
                sb.append(dataModel.getDevice_id() + ",");
                sb.append(dataModel.getDatatype() + ",");
                sb.append(dataModel.getDatavalue() + ",");
                sb.append(dataModel.getUnit());
                bw.write(sb.toString());
            }
            bw.close();
        } catch (FileNotFoundException e) {
            // 捕获File对象生成时的异常
            e.printStackTrace();
        } catch (IOException e) {
            // 捕获BufferedWriter对象关闭时的异常
            e.printStackTrace();
        }
    }
}
