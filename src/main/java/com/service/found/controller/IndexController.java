package com.service.found.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/api/index")
public class IndexController {
    @RequestMapping("/index")
    public void getIndexPage(HttpServletRequest request){

    }
}
