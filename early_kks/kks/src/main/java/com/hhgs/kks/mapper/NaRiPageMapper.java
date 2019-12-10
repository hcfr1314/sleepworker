package com.hhgs.kks.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NaRiPageMapper {

    //分页查询nari数据
     public List<Map<String,Object>> getNaRiDataFromRownum(@Param("startRownum") int startRownum,@Param("endRownum") int endRownum,@Param("scheduler_name") String scheduler_name);
}
