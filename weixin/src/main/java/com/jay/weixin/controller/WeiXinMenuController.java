package com.jay.weixin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 微信菜单
 */
@RestController
@RequestMapping("wx/menu")
public class WeiXinMenuController {

    private static final Log logger = LogFactory.getLog(WeiXinMenuController.class);

    @Autowired
    @Qualifier("wxRestTemplate")
    private OAuth2RestOperations restTemplate;




    /**
     * 创建菜单
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<Object> menuCreate(@RequestBody Map data){

        ResponseEntity<Object> response = restTemplate.postForEntity("https://api.weixin.qq.com/cgi-bin/menu/create", data, Object.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            logger.info(response.getBody());

        }
        return response;
    }


    /**
     * 获取菜单
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<Object> menuGet(){

        ResponseEntity<Object> response = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/menu/get", Object.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            logger.info(response.getBody());

        }
        return response;
    }


    /**
     * 删除菜单
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseEntity<Object> menuDelete(){

        ResponseEntity<Object> response = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/menu/delete", Object.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            logger.info(response.getBody());

        }
        return response;
    }

}
