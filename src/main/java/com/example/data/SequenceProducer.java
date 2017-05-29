package com.example.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.google.common.eventbus.Subscribe;

public class SequenceProducer {

  @Autowired
  StringRedisTemplate redisTemplate;

  @Subscribe
  public void handleEvent(ConsumedEvent event) {
    try {
      if (redisTemplate.opsForList().size("reference-numbers") < 10) {
        long block = redisTemplate.opsForValue().increment("sequence-number", 1) * 100l;
        final long end = block + 100l;
        for (; block < end; block++) {
          redisTemplate.opsForList().rightPush("reference-numbers", getRefVal(block));
        }
      }

    } catch (final Exception e) {
      System.out.println(e.toString());
    }
  }

  private String getRefVal(long val) {
    return StringUtils.leftPad(StringUtils.upperCase(Long.toString(val, 36)), 6, "0");
  }
}