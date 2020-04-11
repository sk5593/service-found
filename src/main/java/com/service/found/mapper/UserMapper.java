package com.service.found.mapper;

import com.service.found.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    void replaceUserInfo(@Param("userEntity") UserEntity userEntity);
    UserEntity getUserInfo(String openid);
}
