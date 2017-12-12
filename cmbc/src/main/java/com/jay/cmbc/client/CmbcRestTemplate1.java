package com.jay.cmbc.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.cmbc.cmbc.SignEncryptDncryptSignChk;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("cmbcRestTemplate")
public class CmbcRestTemplate1 {

    private static final Log logger = LogFactory.getLog(CmbcRestTemplate1.class);
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }


    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Map post(String url, Object request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String context = mapper.writeValueAsString(request);

        String sign = SignEncryptDncryptSignChk.getSign(context);
        logger.info("签名：" + sign);

        String signContext = SignEncryptDncryptSignChk.sign(sign, context);
        logger.info("加密前：" + signContext);

        String encryptContext = SignEncryptDncryptSignChk.encrypt(signContext);
        logger.info("加密后：" + encryptContext);


        Map<String, String> data = new HashMap<String, String>();

        data.put("businessContext", encryptContext);

        ResponseEntity<HashMap> hashMapResponseEntity = restTemplate.postForEntity(url, data, HashMap.class);

        HashMap body = hashMapResponseEntity.getBody();

        String businessContext = (String) body.get("businessContext");

        if (businessContext.equals("")) {
            return body;
        }
        String dncryptContext = SignEncryptDncryptSignChk.dncrypt(businessContext);
        logger.info("解密后：" + dncryptContext);

        String signChkResult = SignEncryptDncryptSignChk.signCheck(dncryptContext);
        logger.info("验证签名结果：" + signChkResult);


        HashMap hashMap = mapper.readValue(dncryptContext, HashMap.class);


        return hashMap;
    }

}
