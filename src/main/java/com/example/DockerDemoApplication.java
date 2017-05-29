package com.example;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.example.data.SequenceConsumer;
import com.example.data.SequenceProducer;
import com.google.common.eventbus.AsyncEventBus;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.example.service", "com.example.swagger" })
@PropertySource("classpath:application.properties")
public class DockerDemoApplication {

  @Value("${spring.redis.host}")
  private String redisHostName;

  @Value("${spring.redis.port}")
  private int redisPort;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    final JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(redisHostName);
    factory.setPort(redisPort);
    factory.setUsePool(true);
    return factory;
  }

  @Bean
  StringRedisTemplate redisTemplate() {
    final StringRedisTemplate redisTemplate = new StringRedisTemplate();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }

  @Bean
  RedisCacheManager cacheManager() {
    final RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
    return redisCacheManager;
  }

  @Bean
  AsyncEventBus asyncEventBus() {
    final AsyncEventBus aeb = new AsyncEventBus(Executors.newSingleThreadExecutor());
    aeb.register(producer());
    return aeb;
  }

  @Bean
  SequenceConsumer consumer() {
    return new SequenceConsumer();
  }

  @Bean
  SequenceProducer producer() {
    return new SequenceProducer();
  }

  public static void main(String[] args) {
    SpringApplication.run(DockerDemoApplication.class, args);
  }
}
