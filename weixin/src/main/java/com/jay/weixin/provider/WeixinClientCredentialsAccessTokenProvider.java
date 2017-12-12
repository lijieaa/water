package com.jay.weixin.provider;

import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.RequestEnhancer;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;


public class WeixinClientCredentialsAccessTokenProvider extends ClientCredentialsAccessTokenProvider {

    public WeixinClientCredentialsAccessTokenProvider() {

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

        converters.add(new MappingJackson2HttpMessageConverter());

        this.setMessageConverters(converters);

        this.setTokenRequestEnhancer(new RequestEnhancer() {

            @Override

            public void enhance(AccessTokenRequest request, OAuth2ProtectedResourceDetails resource,



                                MultiValueMap<String, String> form, HttpHeaders headers) {

                form.set("appid", resource.getClientId());

                form.set("secret", resource.getClientSecret());

                form.set("grant_type", resource.getGrantType());

            }

        });

    }

}
