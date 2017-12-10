package com.jay.cmbc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 民生接口
 */
@RequestMapping("cmbc")
@RestController
public class CmbcController {

    private static final Log logger = LogFactory.getLog(CmbcController.class);

    @Value("${lcbpPay}")
    private String lcbpPay;

    @Value("${queryMchnt}")
    private String queryMchnt;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "lcbpPay",method = RequestMethod.POST)
    private ResponseEntity lcbpPay(@RequestBody Map data){
        logger.info(data);
        ResponseEntity<HashMap> stringResponseEntity = restTemplate.postForEntity(lcbpPay, data, HashMap.class);
        return stringResponseEntity;
    }


    /**
     * 查询商户
     * @param data
     * @return
     */
    @RequestMapping(value = "queryMchnt",method = RequestMethod.POST)
    private ResponseEntity queryMchnt(@RequestBody Map data){
        logger.info(data);
        ResponseEntity<HashMap> stringResponseEntity = restTemplate.postForEntity(queryMchnt, data, HashMap.class);
        return stringResponseEntity;
    }

}
