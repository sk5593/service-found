package com.service.found.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.found.entity.IndexEntity;
import com.service.found.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    IndexMapper indexMapper;

    public  PageInfo<IndexEntity> selectIndex(int current, int size) {
        PageHelper.startPage(current, size);
        List<IndexEntity> indexEntities = indexMapper.selectIndex();
        indexEntities.stream().forEach(indexEntity -> {
            if (indexEntity.getContent().length() > 60){
                 indexEntity.setContent(indexEntity.getContent().substring(0, 60)+"...");
            }
        });
        PageInfo<IndexEntity> page = new PageInfo(indexEntities);
        return page;
    }
}
