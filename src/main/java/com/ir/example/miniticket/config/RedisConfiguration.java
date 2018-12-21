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
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379");
        return Redisson.create(config);
    }

}
