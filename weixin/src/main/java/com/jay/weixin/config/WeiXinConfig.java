package com.jay.weixin.config;

import com.jay.weixin.provider.WeixinClientCredentialsAccessTokenProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class WeiXinConfig {

    @Bean("wxRestTemplate")
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource(), oauth2ClientContext);
        oAuth2RestTemplate.setRetryBadAccessTokens(true);
        oAuth2RestTemplate.setAccessTokenProvider(new WeixinClientCredentialsAccessTokenProvider());
        return oAuth2RestTemplate;
    }

    @Bean
    @ConfigurationProperties("weixin-client")
    protected OAuth2ProtectedResourceDetails resource() {
        return new ClientCredentialsResourceDetails();
    }
}
