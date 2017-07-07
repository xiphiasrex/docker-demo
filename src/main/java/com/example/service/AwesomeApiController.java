package com.example.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Controller;

@Controller
public class AwesomeApiController implements AwesomeApi {

  @Override
  public Map<String, String> awesome() {
    System.out.println("docker-demo v1.0 Awesome controller hit!");
    return Collections.singletonMap("message", "Awesome! (v1.0)");
  }
}
