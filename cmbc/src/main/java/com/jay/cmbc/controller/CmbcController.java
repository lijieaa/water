package com.jay.cmbc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.cmbc.client.CmbcRestTemplate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
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
    CmbcRestTemplate restTemplate;

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
    private Map queryMchnt(@RequestBody Map data) throws IOException {
        return restTemplate.post(queryMchnt,data);
    }
}
