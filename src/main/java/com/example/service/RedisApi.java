package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "redis")
public interface RedisApi {

    @ApiOperation(value = "Adds/Updates simple key + value", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/simple/{key}/{stale}", produces = { "application/json" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, String> addSimple(@PathVariable(value = "key") String key, @PathVariable(value = "stale") Integer stale, @RequestBody String value);

    @ApiOperation(value = "Adds/Updates simple key + value", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/simple/{key}", produces = { "application/json" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, String> addSimple(@PathVariable(value = "key") String key, @RequestBody String value);

    @ApiOperation(value = "Retrieves value for simple key", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/simple/{key}", produces = { "application/json" }, method = RequestMethod.GET)
    public @ResponseBody Map<String, String> getSimple(@PathVariable(value = "key") String key);

    @ApiOperation(value = "Adds values to list", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/list/{key}", produces = { "application/json" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, String> addToList(@PathVariable(value = "key") String key, @RequestBody List<String> values);

    @ApiOperation(value = "Gets range of values from list", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/list/{key}", produces = { "application/json" }, method = RequestMethod.GET)
    public @ResponseBody Map<String, List<String>> getListRange(@PathVariable(value = "key") String key, @RequestParam(name = "start") Long start,
        @RequestParam(name = "end") Long end);

    @ApiOperation(value = "Pops and removes a value from list", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/list/{key}/pop", produces = { "application/json" }, method = RequestMethod.GET)
    public @ResponseBody Map<String, String> popListValue(@PathVariable(value = "key") String key);

    @ApiOperation(value = "Retrieves all values for hash key", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/hash/{key}", produces = { "application/json" }, method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> getHash(@PathVariable(value = "key") String key);

    @ApiOperation(value = "Retrieves specific value for hash key", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/hash/{key}/{name}", produces = { "application/json" }, method = RequestMethod.GET)
    public @ResponseBody Map<String, String> getHashValue(@PathVariable(value = "key") String key, @PathVariable(value = "name") String name);

    @ApiOperation(value = "Adds values to hash", notes = "", response = String.class, tags = { "redis" })
    @RequestMapping(value = "/hash/{key}", produces = { "application/json" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, String> addHashValues(@PathVariable(value = "key") String key, @RequestBody Map<String, String> values);

}
