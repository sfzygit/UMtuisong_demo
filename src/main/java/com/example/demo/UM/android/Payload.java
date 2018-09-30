package com.example.demo.UM.android;

import java.io.Serializable;

import com.example.demo.UM.android.PayloadBody;

public class Payload implements Serializable{
	
	String display_type;// 必填，消息类型: notification(通知)、message(消息)
	
	PayloadBody body = new PayloadBody(); // 必填，消息体。
											// 当display_type=message时，body的内容只需填写custom字段。
											// 当display_type=notification时，body包含如下参数:
											// 通知展现内容:

	public String getDisplay_type() {
		return display_type;
	}

	public void setDisplay_type(String display_type) {
		this.display_type = display_type;
	}

	public PayloadBody getBody() {
		return body;
	}

	public void setBody(PayloadBody body) {
		this.body = body;
	}

}
