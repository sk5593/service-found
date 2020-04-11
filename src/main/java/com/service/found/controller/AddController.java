package com.service.found.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.service.found.config.R;
import com.service.found.entity.IndexEntity;
import com.service.found.service.AddService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping( "/api" )
public class AddController {
    @Autowired
    AddService addService;
    @RequestMapping( "/upload" )
    public R upload(@RequestParam( "file" ) MultipartFile file) {
        log.info("进入上传...");
        String uploadPath = "D:/pic/";//存放到本地路径（示例）
        try {
            String fileName = file.getOriginalFilename();
            //判断是否有文件
            if (!StringUtils.isEmpty(fileName)) {
                String path = uploadPath + UUID.randomUUID().toString() + getFileType(fileName);
                //输出到本地路径
                File outFile = new File(path);
                file.transferTo(outFile);
                return R.success(path);
            }
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return R.fail();
        }
        return  R.fail();

    }

    public static String getFileType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jepg")) {
            return ".jpg";
        } else if (filename.endsWith(".png") || filename.endsWith(".PNG")) {
            return ".png";
        } else {
            return "application/octet-stream";
        }
    }
    @PostMapping("/add")
        public R add(@RequestBody IndexEntity indexEntity , HttpServletRequest request){
        try {
            String sessionKey = request.getHeader("session_key");
            addService.add(indexEntity,sessionKey);
            return R.success();
        }catch (Exception e) {
            log.error("发表帖子失败",e);
            return R.fail();
        }
    }
}