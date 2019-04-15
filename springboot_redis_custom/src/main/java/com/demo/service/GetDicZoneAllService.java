package com.demo.service;

import com.demo.entity.DicZoneAllEntity;

import java.util.List;
import java.util.Map;

public interface GetDicZoneAllService {
    public List<DicZoneAllEntity> getDicZoneAllList(Map map);
//    public void redisTest();
    public List<DicZoneAllEntity> getDicZoneAllList_SENSOR1A(String id);
    public List<DicZoneAllEntity> getDicZoneAllList_SENSOR2A(Map map);
}
