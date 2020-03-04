package com.service.found.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.service.found.entity.ImageEntity;
import com.service.found.entity.IndexEntity;
import com.service.found.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AddService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    IndexMapper indexMapper;
    public void add(IndexEntity indexEntity, String sessionKey){
        Map<String,String> entries = redisTemplate.opsForHash().entries(sessionKey);
        String openId = entries.keySet().iterator().next();
        String content = indexEntity.getContent();
        ImageEntity[] imgArr = indexEntity.getImgArr();
        String url = "";
        for (ImageEntity img:imgArr
             ) {
             url += img.getUrl()+",";
        }
        IndexEntity indexEntityBuild = new IndexEntity().builder().content(content).opId(openId).images(url).createTime(System.currentTimeMillis()).build();
//        redisTemplate.opsForSet().add("latestData",indexEntityBuild);
        indexMapper.add(indexEntityBuild);
    }
}
