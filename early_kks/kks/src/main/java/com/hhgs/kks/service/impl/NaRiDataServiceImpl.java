package com.hhgs.kks.service.impl;

import com.hhgs.kks.common.ExcelReaderUtil;
import com.hhgs.kks.common.ExcelWriterUtil;
import com.hhgs.kks.common.ZIPUtil;
import com.hhgs.kks.mapper.NaRiDataMapper;
import com.hhgs.kks.mapper.NaRiTableMsgMapper;
import com.hhgs.kks.pojo.NaRiNewData;
import com.hhgs.kks.service.INaRiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Author hancong
 * 查询南瑞数据库，并处理数据
 */
@Service("naRiDataService")
public class NaRiDataServiceImpl implements INaRiDataService {

    @Autowired
    private NaRiDataMapper naRiDataMapper;

    @Value("${outputPath}")
    private String outputPath;

    /**
     * 更新数据
     *
     * @param inputPath
     * @param outputPath
     * @return
     */
    @Override
    public Boolean updateData(String inputPath, String outputPath) {

        try {
            //获取nari数据库的前两列数据
            Map<String, String> naRiDataMap = getNaRiData();

            //获取新的匹配结果集
            List<String[]> newNariList = getNewNariList(inputPath, naRiDataMap);

            //将新的结果集输出到Excel文件中
            insertDataToExcel(newNariList, outputPath, inputPath);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取新增数据
     * @param inputPathList
     * @param outputFileName
     * @param scheduler_name
     * @return
     */
    @Override
    public Boolean outputNewAddData(List<String> inputPathList, String outputFileName, String scheduler_name) {

        try {
           /*
           //根据scheduler_name获取nari数据库的前两列数据
            Map<String, String> dataFromScheduler = getNaRiDataFromScheduler(scheduler_name);

            //获取nari数据库中新增加的数据
            List<String[]> newAddDataList = getNewAddData(inputPath, dataFromScheduler);

            //将南瑞新增加的数据写入Excel
            insertNewAddDataToExcel(newAddDataList, outputPath);
          */

            Map<String, List<String[]>> newAddDataMap = getNewAddData(inputPathList, scheduler_name);

            Set<String> keySet = newAddDataMap.keySet();


            //设置输出路径.
            String path = outputPath+scheduler_name+File.separator;

            //检查该路径对应的目录是否存在. 如果不存在则创建目录
            File dir=new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            for (String key : keySet) {

                //判断
                if(key=="YC") {
                    insertNewAddDataToExcel(newAddDataMap.get(key),path+"YC"+outputFileName+".xlsx",key);
                }else {
                    insertNewAddDataToExcel(newAddDataMap.get(key),path+"YX"+outputFileName+".xlsx",key);

                }
            }


            //压缩文件夹
            ZIPUtil.compress(outputPath+scheduler_name,outputPath+scheduler_name+".zip");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //利用多线程分别输出新增数据
    public class Runable extends Thread {

        private List<String []> newAddDataList;
        private String outputFileName;
        private String tableName;

        public Runable(List<String[]> newAddDataList,String outputFileName,String tableName) {
            this.newAddDataList = newAddDataList;
            this.outputFileName=outputFileName;
            this.tableName=tableName;
        }

        @Override
        public void run() {
            try {
                insertNewAddDataToExcel(newAddDataList,outputFileName,tableName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





    /**
     * 获取南瑞数据库中前两列数据，并将结果封装到List<Map<String,Object>>中返回
     *
     * @return
     */
    public Map<String, String> getNaRiData() {
        Map<String, String> naRiMap = new HashMap<String, String>();

        List<Map<String, Object>> mapList = naRiDataMapper.selectFromDescript();

        for (Map<String, Object> map : mapList) {

            String id = map.get("ID").toString();

            String descript = map.get("DESCRIPT").toString();

            naRiMap.put(descript, id);
        }

        return naRiMap;
    }

    /**
     * 获取南瑞数据库中前两列数据，并将结果封装到List<Map<String,Object>>中返回
     *
     * @return
     */
    public Map<String, String> getNaRiDataFromScheduler(String scheduler_name) {
        Map<String, String> naRiMap = new HashMap<String, String>();

        List<Map<String, Object>> mapList = naRiDataMapper.getNaRiDataFromScheduler(scheduler_name);

        for (Map<String, Object> map : mapList) {

            String id = map.get("ID").toString();

            String descript = map.get("DESCRIPT").toString();

            naRiMap.put(descript, id);
        }

        return naRiMap;
    }

    public List<NaRiNewData> getNaRiNewDataFromScheduler(String scheduler_name) {
        List<NaRiNewData> addNewList = new ArrayList<>();

        List<Map<String, Object>> mapList = naRiDataMapper.getNaRiDataFromScheduler(scheduler_name);

        for (Map<String, Object> map : mapList) {

            NaRiNewData naRiNewData = new NaRiNewData();

            String id = map.get("ID").toString();

            String descript = map.get("DESCRIPT").toString();

            String lcuid = map.get("LCUID").toString();

            naRiNewData.setId(id);
        }

        return addNewList;
    }




    /**
     * 将读取到的源数据和南瑞数据库中的数据进行匹配,更新数据,返回最新的数据集合。
     *
     * @param inputPath
     * @param naRiDataMap
     * @return
     */
    public List<String[]> getNewNariList(String inputPath, Map<String, String> naRiDataMap) {

        List<String[]> fileList = ExcelReaderUtil.readExcelServlet(inputPath, 0, 0);

        for (String[] rows : fileList) {
            String id = naRiDataMap.get(rows[1]);
            if (id == null || id == "") {
                rows[0] = "unknown";
            } else {
                rows[0] = id;
            }
        }
        return fileList;
    }

    /**
     * 将已经更新好的文件输出到Excel文件中
     *
     * @param outputPath
     */
    public void insertDataToExcel(List<String[]> fileList, String outputPath, String inputPath) throws IOException {

        //设置列名称
        //String[] fields = new String[]{"原始测点ID", "原始测点名称", "设备编码", "设备名称", "逻辑设备路径", "BCS标准属性英文名称", "BCS标准属性中文名称", "属性分类", "属性类别", "是否必选(M/O)", "测点对标状态", "测点全局编号", "REMARK"};

        //获取原始数据的列名
        List<String[]> fileListOriginal = ExcelReaderUtil.readExcelServlet(inputPath, 0, 0);
        String[] fields = fileListOriginal.get(0);

        //输出数据
        ExcelWriterUtil.writerExcel(fileList, fields, outputPath);
    }


    /**
     * 获取数据库中新增加的数据集合
     *
     * @return
     */
    public List<String[]> getNewAddData(String inputPath, Map<String, String> naRiDataMap) {
        //创建集合用来封装文件中第二列数据
        List<String> row2List = new ArrayList<>();

        //创建集合用来封装数据库中新增加的数据
        List<String[]> addNewList = new ArrayList<>();

        Set<String> keyset = naRiDataMap.keySet();

        List<String[]> list = ExcelReaderUtil.readExcelServlet(inputPath, 0, 0);


        for (String[] rows : list) {
            //将第二列数据放入集合中
            row2List.add(rows[1]);
        }


        for (String key : keyset) {
            //判断集合中是否包含map的key
            if (!row2List.contains(key)) {
                String[] addNewRow = new String[2];
                String id = naRiDataMap.get(key);
                addNewRow[0] = id;
                addNewRow[1] = key;
                //将新增加的行数组加入新的集合
                addNewList.add(addNewRow);
            }
        }

        return addNewList;
    }

    @Autowired
    private NaRiTableMsgMapper naRiTableMsgMapper;

    /**
     * 获取YX,YC总的新增数据
     * @param inputPathList
     * @param scheduler_name
     * @return
     */
    public Map<String,List<String[]>> getNewAddData(List<String> inputPathList , String scheduler_name) {

        //创建map集合封装新增数据
        Map<String,List<String[]>> finalAddDataMap = new HashMap<>();
        List<List<String[]>> finalAddDataList = new ArrayList<>();

        List<String> ycColumnName = naRiTableMsgMapper.getColumnName("YC");

        //创建集合用来封装文件中第二列数据
        List<String> row2List = new ArrayList<>();

        //创建集合用来封装数据库中新增加的数据
        List<String[]> addNewList = new ArrayList<>();

        //创建集合用来封装每个Excel读取到的数据
        List<String[]> list = new ArrayList<>();



        for (String inputPath : inputPathList) {
            System.out.println();
            //读取Excel文件
            list.addAll(ExcelReaderUtil.readExcelServlet(inputPath, 0, 0));
        }

        for (String[] rows : list) {
            //将第二列数据放入集合中
            row2List.add(rows[1]);
        }

        //分别获取YC,YX的信新增数据
        List<String[]> ycAddData = getYCAddData(row2List, scheduler_name);
        List<String[]> yxAddData = getYXAddData(row2List, scheduler_name);

        //将新增数据放入map集合
        finalAddDataMap.put("YC",ycAddData);
        finalAddDataMap.put("YX",yxAddData);

        return finalAddDataMap;



       /* //获取查询结果
        List<Map<String, Object>> mapList = naRiDataMapper.getNaRiDataFromScheduler(scheduler_name);

        Map<String, Object> stringObjectMap = mapList.get(0);
        //目标列宽度
        int size = stringObjectMap.keySet().size();

        for (Map<String, Object> objectMap : mapList) {

            //创建集合，将行数据放入集合中
            List<String> addNewRowList = new ArrayList<String>();

            //for (int i = 0; i < keySet.size(); i++) {
            if(!row2List.contains(objectMap.get("DESCRIPT").toString())) {
                addNewRowList.add(objectMap.get("ID").toString());
                addNewRowList.add(objectMap.get("DESCRIPT").toString());
                addNewRowList.add(objectMap.get("LCUID").toString());
            }
            //}

            //将集合转换为数组
            String[] addNewRow = addNewRowList.toArray(new String[size]);

            //String[] addNewRow = new String[size];
            *//*for (int i = 0; i < addNewRow.length; i++) {
                addNewRow[i] = addNewRowList.get(i);
            }*//*

            addNewList.add(addNewRow);
        }

        return addNewList;*/
    }

    /**
     * 获取YX的新增数据
     * @param column2List
     * @param scheduler_name
     * @return
     */
    public List<String[]> getYXAddData(List<String> column2List, String scheduler_name) {
        //创建集合，存放新增结果集
        List<String[]> yxAddDataList = new ArrayList<>();
        //从数据库获取Y表的列信息
        List<String> columnNames = naRiTableMsgMapper.getColumnName("YX");
        List<Map<String, Object>> yxDataList = naRiDataMapper.getYXDataWithScheduler(scheduler_name);
        for (Map<String, Object> stringObjectMap : yxDataList) {

            if(!column2List.contains(stringObjectMap.get("DESCRIPT"))) {
                //创建集合，将新增的行数据放入集合中
                List<String> addNewRowList = new ArrayList<>();
                for (String columnName : columnNames) {
                    addNewRowList.add(stringObjectMap.get(columnName).toString());
                }
                String[] yxAddRow = addNewRowList.toArray(new String[yxDataList.get(0).size()]);
                yxAddDataList.add(yxAddRow);
            }
        }
        return yxAddDataList;
    }

    /**
     * 获取YC的新增数据
     * @param column2List
     * @param scheduler_name
     * @return
     */
    public List<String[]> getYCAddData(List<String> column2List, String scheduler_name) {
        //创建集合，存放新增结果集
        List<String[]> ycAddDataList = new ArrayList<>();
        //从数据库获取YC表的列信息
        List<String> columnNames = naRiTableMsgMapper.getColumnName("YC");
        List<Map<String, Object>> ycDataList = naRiDataMapper.getYCDataWithScheduler(scheduler_name);
        for (Map<String, Object> stringObjectMap : ycDataList) {

            if(!column2List.contains(stringObjectMap.get("DESCRIPT"))) {
                //创建集合，将新增的行数据放入集合中
                List<String> addNewRowList = new ArrayList<>();
                for (String columnName : columnNames) {
                    addNewRowList.add(stringObjectMap.get(columnName).toString());
                }
                String[] ycAddRow = addNewRowList.toArray(new String[ycDataList.get(0).size()]);
                ycAddDataList.add(ycAddRow);
            }
        }
        return ycAddDataList;
    }






    /**
     * 将已经更新好的文件输出到Excel文件中
     *
     * @param outputPath
     */
    public void insertNewAddDataToExcel(List<String[]> fileList, String outputPath,String tableName) throws IOException {

        List<String> columnNames = naRiTableMsgMapper.getColumnName(tableName);

        String[] fields = columnNames.toArray(new String[columnNames.size()]);
        //设置列名称
        //String[] fields = new String[]{"原始测点ID", "原始测点名称","LCUID"};
        ExcelWriterUtil.writerExcel(fileList, fields, outputPath);
    }

}
