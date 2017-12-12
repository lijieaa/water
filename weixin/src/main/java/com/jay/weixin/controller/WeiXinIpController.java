package com.jay.weixin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信IP
 */
@RestController
@RequestMapping("wx/")
public class WeiXinIpController {

    private static final Log logger = LogFactory.getLog(WeiXinIpController.class);

    @Autowired
    @Qualifier("wxRestTemplate")
    private OAuth2RestOperations restTemplate;



    /**
     * 获致ip地址
     * @return
     */
    @RequestMapping("/getcallbackip")
    public ResponseEntity<Object> getcallbackip(){

        ResponseEntity<Object> response = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/getcallbackip", Object.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            logger.info(response.getBody());

        }
        return response;
    }
}
