package com.huanghe.alarm.api.mapper.nari;


import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("nariSqlSessionTemplate")
public interface NariYXMapper {
    int queryCount();
}
