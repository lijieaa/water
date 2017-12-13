package com.jay.cmbc.controller;

import com.jay.cmbc.client.CmbcRestTemplate;
import com.jay.cmbc.client.CmbcRestTemplate1;
import com.jay.cmbc.resolvers.Foo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Resource(name = "cmbcRestTemplate")
    CmbcRestTemplate1 restTemplate;


    @Resource(name = "restTemplate")
    RestTemplate restTemplate1;

    @RequestMapping(value = "lcbpPay", method = RequestMethod.POST)
    private ResponseEntity lcbpPay(@RequestBody Map data) {
        logger.info(data);

        ResponseEntity<HashMap> stringResponseEntity = restTemplate.getRestTemplate().postForEntity(lcbpPay, data, HashMap.class);
        return stringResponseEntity;
    }


    /**
     * 查询商户
     * @param data
     * @return
     */
    @RequestMapping(value = "queryMchnt", method = RequestMethod.POST)
    private Map queryMchnt(Map data) throws IOException {
        return restTemplate.post(queryMchnt,data);
    }



    /**
     * 查询商户
     * @param data
     * @return
     */
    @RequestMapping(value = "queryMchnt1", method = RequestMethod.POST)
    private String queryMchnt1(HttpServletRequest request) throws IOException {
        CmbcRestTemplate a = new CmbcRestTemplate();
        return a.postForEntity(queryMchnt,request);
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String processFooBar(String data) {
        return "xxxxxxxxxxxxxxxx";
    }
}
