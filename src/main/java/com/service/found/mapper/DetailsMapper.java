package com.service.found.mapper;

import com.service.found.entity.CommentsEntity;
import com.service.found.entity.IndexEntity;
import com.service.found.entity.SecondCommentsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DetailsMapper {
    IndexEntity getDeatils(int id);

    List<CommentsEntity> getDeatilsComments(int id);

    void addDeatilsComments(@Param("commentsEntity") CommentsEntity commentsEntity);
    void addDeatilsSecondComments(@Param("secondCommentsEntity") SecondCommentsEntity secondCommentsEntity);
}
