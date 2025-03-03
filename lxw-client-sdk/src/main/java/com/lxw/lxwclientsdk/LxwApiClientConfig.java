package com.lxw.lxwclientsdk;

import com.lxw.lxwclientsdk.client.LxwApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("lxwapi.client")
@Data
@ComponentScan
public class LxwApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public LxwApiClient lxwApiClient(){
        return new LxwApiClient(accessKey,secretKey);
    }

}
