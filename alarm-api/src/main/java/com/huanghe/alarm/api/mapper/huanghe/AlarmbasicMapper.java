package com.huanghe.alarm.api.mapper.huanghe;

import com.huanghe.alarm.api.model.AlarmDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("huangheSqlSessionTemplate")
@Repository
public interface AlarmbasicMapper {

    List<String> queryAllId();

    /**
     * 通过id查询前面的状态
     * @param orgId
     * @return
     */
    String selectPointStatus(String orgId);

    /**
     * 批量更新基础表状态
     * @param alarmDefinitionList
     * @return
     */
    int batchUpdateByOrgId(@Param("alarmDefinitionList") List<AlarmDefinition> alarmDefinitionList);

    //通过id查询
    AlarmDefinition selectBasicById(@Param("id") String id);


    /**
     * 条件查询
     * @param alarmDefinition
     * @return
     */
    List<AlarmDefinition> selectByCondiction(@Param("alarmDefinition") AlarmDefinition alarmDefinition,@Param("pointStatus") String pointStatus);

    AlarmDefinition selectDataById(String id);

    /**
     * 通过条件查询
     * @param alarmDefinition
     * @return
     */
    List<AlarmDefinition> queryByCondition(@Param("entity") AlarmDefinition alarmDefinition);

    void addAlarm(@Param("alarm") AlarmDefinition alarmDefinition);

    int editAlarm(@Param("alarm") AlarmDefinition alarmDefinition);

    int delAlarm(@Param("list") List<String> ids);

    List<String> getAllPantName();
}
