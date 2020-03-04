package com.service.found.mapper;

import com.service.found.entity.IndexEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IndexMapper {
    List<IndexEntity> selectIndex();

    void add(@Param("indexEntity") IndexEntity indexEntityBuild);
    List<IndexEntity> getLatestData(long latestTime);
//    void add(String );
}
