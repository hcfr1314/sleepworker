package com.hhgs.kks.service;

import java.util.List;
import java.util.Map;

/**
 * Author hancong
 * 查询南瑞数据库,同时,根据查询结果处理源数据
 */
public interface INaRiDataService {

    //更新数据
    public Boolean updateData(String inputPath,String outputPath);


    //获取新增数据
    public Boolean outputNewAddData(List<String> inputPathList,String outputPath,String scheduler_name);
}
