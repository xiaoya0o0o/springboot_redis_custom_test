package com.demo.serviceImpl;

import com.demo.cache.MyReidsAn;
import com.demo.dao.GetDicZoneAllDao;
import com.demo.entity.DicZoneAllEntity;
import com.demo.service.GetDicZoneAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetDicZoneAllServiceImpl implements GetDicZoneAllService {

    @Autowired
    private GetDicZoneAllDao getDicZoneAllDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<DicZoneAllEntity> getDicZoneAllList(Map map) {

//        redisTemplate.setBeanClassLoader();
//        stringRedisTemplate.opsForValue().set
        List<DicZoneAllEntity> list = new ArrayList<DicZoneAllEntity>();
        String id = (String) map.get("id");
        if(id !=null && !"".equals(id)){
            list =(List<DicZoneAllEntity>) redisTemplate.opsForValue().get(id);
            if (list!=null && list.size()>0){
                System.out.println("---query redis----"+id);
                return list;
            }else {
                System.out.println("---query sql----"+id);
                list = getDicZoneAllDao.getDicZoneAllList(map);
                if (list!=null && list.size()>0){
                    redisTemplate.opsForValue().set(id,list);
                }
                return list;
            }
        }
        return null;
    }

    @Override
    @MyReidsAn(key = "'myredis_test:'+#id")
//    @MyReidsAn(key = "#id")
    public List<DicZoneAllEntity> getDicZoneAllList_SENSOR1A(String id) {
        Map map = new HashMap();
        map.put("id",id);
        System.out.println("query from sql---");
        return getDicZoneAllDao.getDicZoneAllList(map);
//        return getDicZoneAllDao.getDicZoneAllList_SENSOR1A(map);
    }

    @Override
    public List<DicZoneAllEntity> getDicZoneAllList_SENSOR2A(Map map) {
        return getDicZoneAllDao.getDicZoneAllList_SENSOR2A(map);
    }
}
