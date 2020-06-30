package com.wxss.springbootrediscluster.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Getter
@Setter
public class RedisClusterProperties {

    private List<String> nodes;


}
