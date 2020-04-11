package com.service.found.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentsEntity implements Serializable {
    private int cid;
    private String commentContent;
    private int aid;
    private long commentCreateTime;
    private String formatCommentCreateTime;
    private String opId;
    private UserEntity user;
    private List<SecondCommentsEntity> secondCommentsEntities;
}
