package com.service.found.controller;

import com.alibaba.fastjson.JSONObject;
import com.service.found.config.BizException;
import com.service.found.config.MainConfig;
import com.service.found.config.R;
import com.service.found.entity.UserEntity;
import com.service.found.service.UserService;
import com.service.found.util.HttpUtil;
import com.service.found.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;
    private final String APP_ID =  MainConfig.APP_ID;
    private final String APP_SECRET =  MainConfig.APP_SECRET;
    @GetMapping("/login")
    public R loginRequest(String code,String name,String image){
        try {
            JSONObject jsonObject = HttpUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code");
            String openid = jsonObject.getString("openid");
            String session_key = jsonObject.getString("session_key");
//            String token = JwtUtil.buildJWT(openid+","+session_key);
            String hashKey = UUID.randomUUID().toString();
            redisTemplate.opsForHash().put(hashKey,openid,session_key);
            UserEntity userEntity = new UserEntity().builder().image(image).name(name).openId(openid).build();
            userService.login(userEntity);
            return new R(200,hashKey);
        } catch (Exception e) {
            log.error("用户登录失败",e);
            return new R(500,"发生未知异常");
        }
    }

}

