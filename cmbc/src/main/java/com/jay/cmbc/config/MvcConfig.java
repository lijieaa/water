package com.jay.cmbc.config;

import com.jay.cmbc.interceptor.CmbcInterceptorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;

//@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CmbcInterceptorHandler()).addPathPatterns("/cmbc/**");
        super.addInterceptors(registry);
    }

   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new HttpMessageConverter<Object>() {
            @Override
            public boolean canRead(Class<?> aClass, MediaType mediaType) {
                return false;
            }

            @Override
            public boolean canWrite(Class<?> aClass, MediaType mediaType) {
                return false;
            }

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                return null;
            }

            @Override
            public Object read(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
                return httpInputMessage;
            }

            @Override
            public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
                System.out.println(o);
            }
        });
        super.configureMessageConverters(converters);
    }*/
}
