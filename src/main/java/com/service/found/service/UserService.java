package com.service.found.service;

import com.service.found.entity.UserEntity;
import com.service.found.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public void login(UserEntity userEntity){
        userMapper.replaceUserInfo(userEntity);
    }
}
