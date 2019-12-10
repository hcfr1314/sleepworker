package com.hhgs.kks.common;

import com.hhgs.kks.mapper.NaRiDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class NaRiDataUtil {

    @Autowired
    private NaRiDataMapper naRiDataMapper;


    public Map<String, String> getNaRiData() {
        Map<String, String> naRiMap = new HashMap<String, String>();


        List<Map<String, Object>> mapList = naRiDataMapper.selectFromDescript();

        for (Map<String, Object> map : mapList) {


            String id = map.get("ID").toString();

            String descript = map.get("DESCRIPT").toString();

            naRiMap.put(descript,id);
            }

        return naRiMap;
    }
}
