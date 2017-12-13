package com.jay.cmbc.config;

import com.jay.cmbc.interceptor.CmbcInterceptorHandler;
import com.jay.cmbc.resolvers.argument.A;
import com.jay.cmbc.resolvers.returnvalue.B;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
   /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CmbcInterceptorHandler()).addPathPatterns("/cmbc*//**");
        super.addInterceptors(registry);
    }*/

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new A());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(0,new B());
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return super.getMessageCodesResolver();
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
