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
public class IndexEntity implements Serializable {
    private int id;
    private String content;
    private String images;
    private UserEntity user;
}
