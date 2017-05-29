package com.example.data;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.google.common.eventbus.AsyncEventBus;

public class SequenceConsumer {

  @Autowired
  StringRedisTemplate redisTemplate;

  @Autowired
  AsyncEventBus asyncEventBus;

  public String getValue() {
    asyncEventBus.post(new ConsumedEvent());
    return redisTemplate.opsForList().leftPop("reference-numbers", 10, TimeUnit.MILLISECONDS);
  }
}
