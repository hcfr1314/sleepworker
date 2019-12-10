package com.hhgs.kks.mapper;


import java.util.List;
import java.util.Map;

/**
 * 查询南瑞库中的数据
 */
public interface NaRiDataMapper {

    //将查询的最新测点id和原始
    public List<Map<String,Object>> selectFromDescript ();

    //根据场站编号查询数据
    public List<Map<String, Object>> getNaRiDataFromScheduler(String scheduler_name);


    //根据列集合获取想要的数据
    public List<Map<String, Object>> getNaRiDataWithColumnList(List<String> columnList);

    //获取YX的数据
    public List<Map<String, Object>> getYXDataWithScheduler(String scheduler_name);

    //获取YC的数据
    public List<Map<String, Object>> getYCDataWithScheduler(String scheduler_name);




}
