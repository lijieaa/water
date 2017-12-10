package com.jay.cmbc.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.cmbc.cmbc.SignEncryptDncryptSignChk;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CmbcRequestBodyAdvice implements RequestBodyAdvice{

    private static final Log logger = LogFactory.getLog(CmbcRequestBodyAdvice.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getContainingClass().getName().equals("com.jay.cmbc.controller.CmbcController");
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {

        ObjectMapper mapper = new ObjectMapper();

        String context=null;

        try {
            context = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String sign = SignEncryptDncryptSignChk.getSign(context);
        logger.info("签名："+sign);

        String signContext = SignEncryptDncryptSignChk.sign(sign, context);
        logger.info("加密前：" + signContext);

        String encryptContext = SignEncryptDncryptSignChk.encrypt(signContext);
        logger.info("加密后：" + encryptContext);


        Map<String,String> data =new HashMap<String, String>();

        data.put("businessContext", encryptContext);

        return data;
    }
}
