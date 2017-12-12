package com.jay.cmbc.servlet;

import com.jay.cmbc.cmbc.SignEncryptDncryptSignChk;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/12/12.
 */
public class CmbcHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Log logger = LogFactory.getLog(CmbcHttpServletRequestWrapper.class);
    private  String context=null;

    public CmbcHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        context = IOUtils.toString(request.getInputStream(), "utf-8");
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        String sign = SignEncryptDncryptSignChk.getSign(context);
        logger.info("签名："+sign);

        String signContext = SignEncryptDncryptSignChk.sign(sign, context);
        logger.info("加密前：" + signContext);

        String encryptContext = SignEncryptDncryptSignChk.encrypt(signContext);
        logger.info("加密后：" + encryptContext);

        String data = "{\"businessContext\":\""+encryptContext+"\",\"merchantNo\":\"\",\"merchantSeq\":\"\",\"reserve1\":\"\",\"reserve2\":\"\",\"reserve3\":\"\",\"reserve4\":\"\",\"reserve5\":\"\",\"reserveJson\":\"\",\"securityType\":\"\",\"sessionId\":\"\",\"source\":\"\",\"transCode\":\"\",\"transDate\":\"\",\"transTime\":\"\",\"version\":\"\"}";

        final ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
