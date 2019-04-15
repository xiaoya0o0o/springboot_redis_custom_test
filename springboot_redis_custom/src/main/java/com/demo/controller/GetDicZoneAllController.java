package com.demo.controller;

import com.demo.entity.DicZoneAllEntity;
import com.demo.service.GetDicZoneAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetDicZoneAllController {

    @Autowired
    private GetDicZoneAllService getDicZoneAllService;

    @RequestMapping(value = "/getlist")
    public List<DicZoneAllEntity> getList() {
        Map map = new HashMap();
        map.put("test","1");
        List<DicZoneAllEntity> list = getDicZoneAllService.getDicZoneAllList(map);
//        DicZoneAllEntity d = new DicZoneAllEntity();
//        d.setCityId("1");

        return list;
    }
    @RequestMapping(value = "/getlist2/{id}")
    public List<DicZoneAllEntity> getList2(@PathVariable("id") long id) {
        Map map = new HashMap();
        map.put("test","1");
        map.put("id",String.valueOf(id));
        List<DicZoneAllEntity> list = getDicZoneAllService.getDicZoneAllList(map);
//        DicZoneAllEntity d = new DicZoneAllEntity();
//        d.setCityId("1");

        return list;
    }
    @RequestMapping(value = "/getlist3/{id}")
    public List<DicZoneAllEntity> getList3(@PathVariable("id") long id) {
        List<DicZoneAllEntity> list = getDicZoneAllService.getDicZoneAllList_SENSOR1A(String.valueOf(id));
//        DicZoneAllEntity d = new DicZoneAllEntity();
//        d.setCityId("1");

        return list;
    }
}
