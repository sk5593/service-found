package com.service.found.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexEntity implements Serializable {
    private int id;
    private String opId;
    private String content;
    private String images;
    private UserEntity user;
    private ImageEntity[] imgArr;
    private long createTime;
    private String shortContent;
    private List<CommentsEntity> commentsEntityList;
    private String formatTime;
    private String commentNum;
 }
