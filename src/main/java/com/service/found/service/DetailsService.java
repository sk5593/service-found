package com.service.found.service;

import com.service.found.entity.CommentsEntity;
import com.service.found.entity.IndexEntity;
import com.service.found.entity.SecondCommentsEntity;
import com.service.found.entity.UserEntity;
import com.service.found.mapper.DetailsMapper;
import com.service.found.mapper.IndexMapper;
import com.service.found.mapper.UserMapper;
import com.service.found.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DetailsService {
    @Autowired
    IndexMapper indexMapper;
    @Autowired
    DetailsMapper detailsMapper;
    @Autowired
    UserMapper userMapper;

    public IndexEntity getDetails(int id) {
        IndexEntity deatils = detailsMapper.getDeatils(id);
        String opId = deatils.getOpId();
        UserEntity userInfo = userMapper.getUserInfo(opId);
        deatils.setUser(userInfo);
        deatils.setFormatTime(TimeUtil.formatDistanceTime(deatils.getCreateTime()));
        List<CommentsEntity> detailsComments = getDetailsComments(id);
        deatils.setCommentsEntityList(detailsComments);
        return deatils;
    }

    public List<CommentsEntity> getDetailsComments(int id) {
        List<CommentsEntity> commentsEntities = detailsMapper.getDeatilsComments(id);
        commentsEntities.stream().forEach(commentsEntity -> {
            commentsEntity.setFormatCommentCreateTime(TimeUtil.formatDistanceTime(commentsEntity.getCommentCreateTime()));
        });
        return commentsEntities;
    }

    public void addDetailsComments(CommentsEntity commentsEntity) {
        detailsMapper.addDeatilsComments(commentsEntity);
    }
    public void addDetailsSecondComments(SecondCommentsEntity secondcommentsEntity) {
        detailsMapper.addDeatilsSecondComments(secondcommentsEntity);
    }

}
