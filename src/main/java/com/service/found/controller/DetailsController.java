package com.service.found.controller;

import com.service.found.config.R;
import com.service.found.entity.CommentsEntity;
import com.service.found.entity.IndexEntity;
import com.service.found.entity.SecondCommentsEntity;
import com.service.found.entity.UserEntity;
import com.service.found.service.DetailsService;
import com.service.found.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping( "/api/details" )
public class DetailsController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    DetailsService detailsService;
    @Autowired
    UserService userService;

    @RequestMapping( "/article" )
    public R getDetails(int id) {
        try {
            IndexEntity details = detailsService.getDetails(id);
            return new R(200, "success", details);
        } catch (Exception e) {
            log.error("获取详情异常", e);
            return R.fail();
        }
    }

    @GetMapping( "/comments" )
    public R getDetailsComments(int id) {
        try {
            List<CommentsEntity> commentsEntity = detailsService.getDetailsComments(id);
            return new R(200, "success", commentsEntity);
        } catch (Exception e) {
            log.error("获取评论异常", e);
            return R.fail();
        }
    }

    /**
     * @param id      评论所在文章id
     * @param value   评论内容
     * @param request
     * @return 当请求头为application/json时，接受参数使用requestBody+对象
     * 当请求头为application/x-www-form-urlencoded，接收参数使用requestParam+非对象
     */
    @PostMapping( "/comments" )
    public R addDetailsComments(@RequestParam( "id" ) Integer id, @RequestParam( "value" ) String value, @RequestParam( "cid" ) Integer cid
            , @RequestParam( "name" ) String name, @RequestParam( "opId" ) String opId
            , HttpServletRequest request) {
        try {
            String sessionKey = request.getHeader("session_key");
            Map<String, String> entries = redisTemplate.opsForHash().entries(sessionKey);
            String openId = entries.keySet().iterator().next();
            long createTime = System.currentTimeMillis();
            if (cid == 0) {
                // 回复楼主
                CommentsEntity commentsEntity = new CommentsEntity().builder()
                        .aid(id)
                        .commentContent(value)
                        .commentCreateTime(createTime)
                        .opId(openId)
                        .build();
                detailsService.addDetailsComments(commentsEntity);
                return R.success();
            } else {
                // 回复楼中楼
                UserEntity userInfo = userService.getUserInfo(openId);
                SecondCommentsEntity secondCommentsEntity = new SecondCommentsEntity().builder()
                        .aid(id)
                        .cid(cid)
                        .secondCommentContent(value)
                        .secondCreateTime(createTime)
                        .commentatorOpId(openId)
                        .commentedName(name)
                        .commentedOpId(opId)
                        .commentatorName(userInfo.getName())
                        .build();
                System.out.println(secondCommentsEntity.toString());
                detailsService.addDetailsSecondComments(secondCommentsEntity);
                return R.success();
            }
        } catch (Exception e) {
            log.error("添加评论异常", e);
            return R.fail();
        }
    }

}
