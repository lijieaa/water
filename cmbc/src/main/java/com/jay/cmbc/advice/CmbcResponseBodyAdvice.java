package com.jay.cmbc.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.cmbc.cmbc.SignEncryptDncryptSignChk;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice

public class CmbcResponseBodyAdvice implements ResponseBodyAdvice {

    private static final Log logger = LogFactory.getLog(CmbcResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getContainingClass().getName().equals("com.jay.cmbc.controller.CmbcController");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        body="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        return body;
    }



    /*@Override
    public Map beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //String businessContext = (String) body.get("businessContext");
        String businessContext = (String) body;
        ObjectMapper mapper = new ObjectMapper();

        HashMap map = null;

        try {
            map = mapper.readValue(businessContext, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;


        /*if (body.get("businessContext").equals("")) {
            return body;
        }else{
            String dncryptContext = SignEncryptDncryptSignChk.dncrypt(businessContext);
            logger.info("解密后：" + dncryptContext);

            String signChkResult = SignEncryptDncryptSignChk.signCheck(dncryptContext);
            logger.info("验证签名结果：" + signChkResult);



            try {
                map = mapper.readValue(dncryptContext, HashMap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }
    }*/
}
