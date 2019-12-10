package com.hhgs.kks.mapper;

import java.util.List;

/**
 * 查询南瑞
 */
public interface NaRiTableMsgMapper {

    //根据表名称获取列信息
    public List<String> getColumnName(String tableName);

}
