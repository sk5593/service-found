package com.service.found.controller;

import com.github.pagehelper.PageInfo;
import com.service.found.entity.IndexEntity;
import com.service.found.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/index")
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/index")
    public PageInfo<IndexEntity> getIndexPage(int current,int size){
        PageInfo<IndexEntity> indexEntityPageInfo = indexService.selectIndex(current, size);
        return indexEntityPageInfo;
    }
    @RequestMapping("/latest")
    public List<IndexEntity> getLatestData(@RequestParam("latestTime") long latestTime){
        List<IndexEntity> latestData = indexService.getLatestData(latestTime);

        System.out.println(latestData);
        return latestData;
    }
}
