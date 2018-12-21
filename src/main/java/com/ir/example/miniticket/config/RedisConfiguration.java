package com.ir.example.miniticket.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {


    @Bean
    public RedissonClient createRedisClient(){
        AppProperties properties = AppPropertiesProvider.getInstance();
        Config config = new Config();
        config.useSingleServer().setAddress(properties.redisAddress().getValue());
        return Redisson.create(config);
    }

}
