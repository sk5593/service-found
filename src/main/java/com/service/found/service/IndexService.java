package com.service.found.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.found.entity.IndexEntity;
import com.service.found.mapper.IndexMapper;
import com.service.found.util.TimeUtil;
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
        List<IndexEntity> formatIndexEntities = formatEntity(indexEntities);
        PageInfo<IndexEntity> page = new PageInfo(formatIndexEntities);
        return page;
    }
    public List<IndexEntity> getLatestData(long latestTime){
        List<IndexEntity> indexEntities = indexMapper.getLatestData(latestTime);
        List<IndexEntity> formatIndexEntities = formatEntity(indexEntities);
        return formatIndexEntities;
    }
    private List<IndexEntity> formatEntity(List<IndexEntity> indexEntities){
        indexEntities.stream().forEach(indexEntity -> {
            String commentNum = indexEntity.getCommentNum();
            if (commentNum.equals("0")){
                indexEntity.setCommentNum("评论");
            }
            String s = TimeUtil.formatDistanceTime(indexEntity.getCreateTime());
            indexEntity.setFormatTime(s);
            if (indexEntity.getContent().length() > 60){
                indexEntity.setShortContent(indexEntity.getContent().substring(0, 60)+"...");
            }else {
                indexEntity.setShortContent(indexEntity.getContent());
            }
        });
        return indexEntities;
    }
}
