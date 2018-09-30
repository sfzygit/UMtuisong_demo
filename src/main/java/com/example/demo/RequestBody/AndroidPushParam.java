package com.example.demo.RequestBody;

import java.io.Serializable;
import java.util.List;

public class AndroidPushParam implements Serializable{
	
	String device_tokens;	// 必填, 要求不超过500个, 以英文逗号分隔
	
	String display_type;// 必填，消息类型: notification(通知)、message(消息)
	
 	String ticker;    // 必填，通知栏提示文字
    String title;     // 必填，通知标题
    String text;      // 必填，通知文字描述 
	
    String after_open;  // 可选，默认为"go_app"，值可以为:
                        //   "go_app": 打开应用
                        //   "go_url": 跳转到URL
                        //   "go_activity": 打开特定的activity
                        //   "go_custom": 用户自定义内容。
    String url;    // 当after_open=go_url时，必填。
                   // 通知栏点击后跳转的URL，要求以http或者https开头
    
    String expire_time; // 推送消息过期时间，最多 7天,格式：yyyy-MM-dd HH:mm:ss
    
	String description;    // 可选，发送消息描述，建议填写。  
	
	String production_mode;    // 可选，正式/测试模式。默认为true
								// 测试模式只会将消息发给测试设备。测试设备需要到web上添加。
							    // Android: 测试设备属于正式设备的一个子集。

	public String getDevice_tokens() {
		return device_tokens;
	}

	public void setDevice_tokens(String device_tokens) {
		this.device_tokens = device_tokens;
	}

	public String getDisplay_type() {
		return display_type;
	}

	public void setDisplay_type(String display_type) {
		this.display_type = display_type;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAfter_open() {
		return after_open;
	}

	public void setAfter_open(String after_open) {
		this.after_open = after_open;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduction_mode() {
		return production_mode;
	}

	public void setProduction_mode(String production_mode) {
		this.production_mode = production_mode;
	}
	
	
	
}
