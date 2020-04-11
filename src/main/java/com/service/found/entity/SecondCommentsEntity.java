package com.service.found.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondCommentsEntity implements Serializable {
    private int scId;
    private int aid;
    private int cid;
    private String secondCommentContent;
    private long secondCreateTime;
    private String commentatorOpId;
    private String commentatorName;
    private String commentedOpId;
    private String commentedName;
}
