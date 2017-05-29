package com.example.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "awesome")
public interface AwesomeApi {

  @ApiOperation(value = "Just responds with \"Awesome!\"", notes = "", response = String.class, tags = { "awesome" })
  @RequestMapping(value = "/awesome", produces = { "application/json" }, method = RequestMethod.GET)
  public @ResponseBody Map<String, String> awesome();

}
