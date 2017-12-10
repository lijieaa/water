package com.jay.weixin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 微信用户
 */
@RestController
@RequestMapping("wx/user")
public class WeiXinUserController {

    private static final Log logger = LogFactory.getLog(WeiXinUserController.class);

    @Autowired
    private OAuth2RestOperations restTemplate;


    /**
     * 获取微信用户
     * @return
     */
    @RequestMapping("/get")
    public ResponseEntity<Object> getUser(){

        ResponseEntity<Object> response = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/user/get", Object.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            logger.info(response.getBody());

        }
        return response;
    }
}
