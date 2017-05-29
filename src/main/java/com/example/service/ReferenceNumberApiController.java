package com.example.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.data.SequenceConsumer;

@Controller
public class ReferenceNumberApiController implements ReferenceNumberApi {

  @Autowired
  SequenceConsumer consumer;

  @Override
  public Map<String, String> reference() {
    return Collections.singletonMap("referenceNumber", consumer.getValue());
  }

}
