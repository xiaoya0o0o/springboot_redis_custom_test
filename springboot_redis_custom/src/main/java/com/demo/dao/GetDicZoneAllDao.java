package com.demo.dao;

import com.demo.entity.DicZoneAllEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GetDicZoneAllDao {


    public List<DicZoneAllEntity> getDicZoneAllList(Map map);

    public List<DicZoneAllEntity> getDicZoneAllList_SENSOR1A(Map map);

    public List<DicZoneAllEntity> getDicZoneAllList_SENSOR2A(Map map);

}
