package com.fwd.ctmws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fwd.ctmws.dao.FwdAgentProfileDao;
import com.fwd.ctmws.dto.RebackDto;
import com.fwd.ctmws.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "/template", description = "template API")
@RequestMapping(value = "template")
public class RestTemplateController {

	
@Autowired
	private RedisTemplate redisTemplate;  
	
	@ApiOperation(notes = " redisTemplate test .  ", httpMethod = "GET", value = "  redisTemplate test ")
	@RequestMapping(value = "/redisGet/{key}", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(GenericJsonView.Summary.class)
	public FwdAgentProfile testRedisGet(@PathVariable String key){
		 ValueOperations<String, FwdAgentProfile> valueops = redisTemplate.opsForValue();
		 FwdAgentProfile p = valueops.get(key);
		return p;
	}
	


}
