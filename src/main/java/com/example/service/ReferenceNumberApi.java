package com.example.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "reference")
public interface ReferenceNumberApi {

  @ApiOperation(value = "Responds with a unique 6 character alphanumeric reference number", notes = "", response = String.class, tags = {
      "reference" })
  @RequestMapping(value = "/reference", produces = { "application/json" }, method = RequestMethod.GET)
  public @ResponseBody Map<String, String> reference();

}
