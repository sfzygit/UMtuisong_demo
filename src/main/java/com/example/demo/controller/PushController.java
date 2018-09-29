package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.example.demo.RequestBody.UmPushparamAndriod;
import com.example.demo.Utils.Constants;

@RestController
@RequestMapping(value="/push")
public class PushController {
	
	@Autowired
	RestTemplate mRestTemplate;
	
	@PostMapping(value="/single")
	public Map<String, Object> pushSingle(){
		Map<String, Object> result = new HashMap<>();
		//mRestTemplate.get
		
		return result;
	}
	
	@PostMapping(value="/broadcast")
	public String pushBroadcasr() {
		String method = "POST";
		String host = "http://msg.umeng.com";
		String apiPath = "/api/send";
		String umpushUrl=host+apiPath;
		UmPushparamAndriod pushparamAndriod = UmPushparamAndriod.generateUmPushParamDemo();
		String encryptionStr =  method+host+apiPath+JSON.toJSON(pushparamAndriod).toString()+Constants.UM_APP_MASTER_SECRET;
		String mysign="";
		try {
			mysign = DigestUtils.md5Digest(encryptionStr.getBytes("utf8")).toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		umpushUrl += "?sign="+mysign;
		ResponseEntity<String> resContent = mRestTemplate.postForEntity(umpushUrl, pushparamAndriod, String.class);
		
		return resContent.getBody();
	}
	
	@GetMapping("/getbaidu")
	public String getBaidu() {
		ResponseEntity<String> resCotent =  mRestTemplate.getForEntity("http://baidu.com", String.class);
		return  resCotent.getBody();
	}

}
