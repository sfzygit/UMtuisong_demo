package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.example.demo.RequestBody.AndroidPushParam;
import com.example.demo.UM.android.UmPushparamAndriod;
import com.example.demo.Utils.Constants;
import com.example.demo.Utils.MD5Util;

@RestController
@RequestMapping(value="/push")
public class PushController {
	
	@Autowired
	RestTemplate mRestTemplate;
	
	@PostMapping(value="/send")
	public String sendUnicast(
			@RequestBody AndroidPushParam pushParam
			) {
		// The user agent
		String USER_AGENT = "Mozilla/5.0";
		UmPushparamAndriod pushparamAndriod = new UmPushparamAndriod();
		pushparamAndriod.setPushParam(pushParam);
		String method = "POST";
		String url = Constants.UM_HOST+Constants.UM_SEND_API_PATH;
		String postBodyStr = JSON.toJSON(pushparamAndriod).toString();
		// md5encode $method$url$requestBody$app_master_secret
		String toEncodeMsg =  method+url+postBodyStr+Constants.UM_APP_MASTER_SECRET;
		String mysign="";
		
		try {
			mysign = MD5Util.md5Encode(toEncodeMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		url += "?sign="+mysign;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
        StringEntity se = new StringEntity(postBodyStr, "UTF-8");
        post.setEntity(se);
        // Send the post request and get the response
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        StringBuffer result = new StringBuffer();
		try {
			response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        
	        String line = "";
	        while ((line = rd.readLine()) != null) {
	            result.append(line);
	        }
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return result.toString();
	}
	
}
