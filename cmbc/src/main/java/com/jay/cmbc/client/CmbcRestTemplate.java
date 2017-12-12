package com.jay.cmbc.client;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/12.
 */
public class CmbcRestTemplate extends RestTemplate {

    public String postForEntity(String url, HttpServletRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        String s = IOUtils.toString(request.getInputStream());
        HttpEntity entity= new HttpEntity( s, headers );
        ResponseEntity<String> stringResponseEntity = this.postForEntity(url, entity, String.class);
        String body = stringResponseEntity.getBody();
        return body;
    }
}
