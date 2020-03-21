package com.huanghe.alarm.api.mapper.huanghe;

import com.huanghe.alarm.api.model.AlarmHistorical;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("huangheSqlSessionTemplate")
@Repository
public interface AlarmHistoricalMapper {

    AlarmHistorical selectPreData(String orgId);

    String selectPrePointStatus(@Param("orgId") String id);

    int batchInsertHistory(@Param("historyList") List<AlarmHistorical> historyList);

    /**
     * Ìõ¼þ²éÑ¯
     * @param alarmHistorical
     * @return
     */
    List<AlarmHistorical> getAlarmHistoricalByCondition(@Param("alarmHistorical") AlarmHistorical alarmHistorical);
}
