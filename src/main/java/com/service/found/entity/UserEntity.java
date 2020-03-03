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
public class UserEntity implements Serializable{
    private int id;
    private String name;
    private String image;
    private String openId;
}
