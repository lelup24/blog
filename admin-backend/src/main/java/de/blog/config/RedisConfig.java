package de.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

  @Bean
  RedisTemplate<String, Long> redisTemplate() {
    RedisTemplate<String, Long> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setPort(7000);
    redisStandaloneConfiguration.setHostName("localhost");
    redisStandaloneConfiguration.setPassword("redis");
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

}
