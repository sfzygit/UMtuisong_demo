package com.example.demo.RequestBody;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;

import com.example.demo.Utils.Constants;


public class UmPushparamAndriod implements Serializable{
	
	String appKey; // 必填，应用唯一标识
	String timestamp; // 必填，时间戳，10位或者13位均可，时间戳有效期为10分钟
	String type; // 必填，消息发送类型,其值可以为: 
			    //   unicast-单播
			    //   listcast-列播，要求不超过500个device_token
			    //   filecast-文件播，多个device_token可通过文件形式批量发送
			    //   broadcast-广播
			    //   groupcast-组播，按照filter筛选用户群, 请参照filter参数
			    //   customizedcast，通过alias进行推送，包括以下两种case:
			    //     - alias: 对单个或者多个alias进行推送
			    //     - file_id: 将alias存放到文件后，根据file_id来推送
	String device_tokens;// 当type=unicast时, 必填, 表示指定的单个设备
						// 当type=listcast时, 必填, 要求不超过500个, 以英文逗号分隔
	
	String alias_type;// 当type=customizedcast时, 必填
    				  // alias的类型, alias_type可由开发者自定义, 开发者在SDK中
    				  // 调用setAlias(alias, alias_type)时所设置的alias_type
	String alias;  // 当type=customizedcast时, 选填(此参数和file_id二选一)
    			   // 开发者填写自己的alias, 要求不超过500个alias, 多个alias以英文逗号间隔
    			   // 在SDK中调用setAlias(alias, alias_type)时所设置的alias
	String file_id;
	
	Object filter;// 当type=groupcast时，必填，用户筛选条件，如用户标签、渠道等，参考附录G。
	
	Payload payload = new Payload();// 必填，JSON格式，具体消息内容(Android最大为1840B)
	
	
	public class Payload{
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
	
	public class PayloadBody{
	    String ticker;    // 必填，通知栏提示文字
	    String title;    // 必填，通知标题
	    String text;    // 必填，通知文字描述 
	
	    				// 自定义通知图标:
	    String icon;    // 可选，状态栏图标ID，R.drawable.[smallIcon]，
					    // 如果没有，默认使用应用图标。
					    // 图片要求为24*24dp的图标，或24*24px放在drawable-mdpi下。
					    // 注意四周各留1个dp的空白像素
	    
	    String largeIcon;    // 可选，通知栏拉开后左侧图标ID，R.drawable.[largeIcon]，
	    // 图片要求为64*64dp的图标，
	    // 可设计一张64*64px放在drawable-mdpi下，
	    // 注意图片四周留空，不至于显示太拥挤
	    String img;    // 可选，通知栏大图标的URL链接。该字段的优先级大于largeIcon。
	                    // 该字段要求以http或者https开头。
	
	    // 自定义通知声音:
	    String sound;    // 可选，通知声音，R.raw.[sound]。
	                    // 如果该字段为空，采用SDK默认的声音，即res/raw/下的
	                    // umeng_push_notification_default_sound声音文件。如果
	                    // SDK默认声音文件不存在，则使用系统默认Notification提示音。
	
	    				// 自定义通知样式:
	    String builder_id;   // 可选，默认为0，用于标识该通知采用的样式。使用该参数时，
	                        // 开发者必须在SDK里面实现自定义通知栏样式。
	
	    								// 通知到达设备后的提醒方式，注意，"true/false"为字符串
	    String play_vibrate;    // 可选，收到通知是否震动，默认为"true"
	    String play_lights;        // 可选，收到通知是否闪灯，默认为"true"
	    String play_sound;        // 可选，收到通知是否发出声音，默认为"true"
	
	    						// 点击"通知"的后续行为，默认为打开app。
	    String after_open;    // 可选，默认为"go_app"，值可以为:
	                        //   "go_app": 打开应用
	                        //   "go_url": 跳转到URL
	                        //   "go_activity": 打开特定的activity
	                        //   "go_custom": 用户自定义内容。
	    String url;    // 当after_open=go_url时，必填。
	                    // 通知栏点击后跳转的URL，要求以http或者https开头
	    String activity;    // 当after_open=go_activity时，必填。
	                        // 通知栏点击后打开的Activity
	    Object custom;//{}    // 当display_type=message时, 必填
	                        // 当display_type=notification且
	                        // after_open=go_custom时，必填
	                        // 用户自定义内容，可以为字符串或者JSON格式。
	    
	    Map<String, String> extra; // 可选，JSON格式，用户自定义key-value。只对"通知"
	                               // (display_type=notification)生效。
	                               // 可以配合通知到达后，打开App/URL/Activity使用。

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

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getLargeIcon() {
			return largeIcon;
		}

		public void setLargeIcon(String largeIcon) {
			this.largeIcon = largeIcon;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getSound() {
			return sound;
		}

		public void setSound(String sound) {
			this.sound = sound;
		}

		public String getBuilder_id() {
			return builder_id;
		}

		public void setBuilder_id(String builder_id) {
			this.builder_id = builder_id;
		}

		public String getPlay_vibrate() {
			return play_vibrate;
		}

		public void setPlay_vibrate(String play_vibrate) {
			this.play_vibrate = play_vibrate;
		}

		public String getPlay_lights() {
			return play_lights;
		}

		public void setPlay_lights(String play_lights) {
			this.play_lights = play_lights;
		}

		public String getPlay_sound() {
			return play_sound;
		}

		public void setPlay_sound(String play_sound) {
			this.play_sound = play_sound;
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

		public String getActivity() {
			return activity;
		}

		public void setActivity(String activity) {
			this.activity = activity;
		}

		public Object getCustom() {
			return custom;
		}

		public void setCustom(Object custom) {
			this.custom = custom;
		}

		public Map<String, String> getExtra() {
			return extra;
		}

		public void setExtra(Map<String, String> extra) {
			this.extra = extra;
		}
	    
	    
	    
	}
	
    Object policy;// 可选，发送策略
    
//policy = {  "start_time":"xx",    // 可选，定时发送时，若不填写表示立即发送。
//    // 定时发送时间不能小于当前时间
//    // 格式: "yyyy-MM-dd HH:mm:ss"。 
//    // 注意，start_time只对任务类消息生效。
//	"expire_time":"xx",    // 可选，消息过期时间，其值不可小于发送时间或者
//	    // start_time(如果填写了的话)，
//	    // 如果不填写此参数，默认为3天后过期。格式同start_time
//	"max_send_num": xx,    // 可选，发送限速，每秒发送的最大条数。最小值1000
//	    // 开发者发送的消息如果有请求自己服务器的资源，可以考虑此参数。
//	"out_biz_no": "xx"    // 可选，开发者对消息的唯一标识，服务器会根据这个标识避免重复发送。
//	    // 有些情况下（例如网络异常）开发者可能会重复调用API导致
//	    // 消息多次下发到客户端。如果需要处理这种情况，可以考虑此参数。
//	    // 注意, out_biz_no只对任务类消息生效。
//   } 
    boolean production_mode;    // 可选，正式/测试模式。默认为true
    // 测试模式只会将消息发给测试设备。测试设备需要到web上添加。
	    // Android: 测试设备属于正式设备的一个子集。
	String description;    // 可选，发送消息描述，建议填写。  
	String mipush;    // 可选，默认为false。当为true时，表示MIUI、EMUI、Flyme系统设备离线转为系统下发
	String mi_activity;    // 可选，mipush值为true时生效，表示走系统通道时打开指定页面acitivity的完整包路径。
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDevice_tokens() {
		return device_tokens;
	}
	public void setDevice_tokens(String device_tokens) {
		this.device_tokens = device_tokens;
	}
	public String getAlias_type() {
		return alias_type;
	}
	public void setAlias_type(String alias_type) {
		this.alias_type = alias_type;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public Object getFilter() {
		return filter;
	}
	public void setFilter(Object filter) {
		this.filter = filter;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	public Object getPolicy() {
		return policy;
	}
	public void setPolicy(Object policy) {
		this.policy = policy;
	}
	public boolean getProduction_mode() {
		return production_mode;
	}
	public void setProduction_mode(boolean production_mode) {
		this.production_mode = production_mode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMipush() {
		return mipush;
	}
	public void setMipush(String mipush) {
		this.mipush = mipush;
	}
	public String getMi_activity() {
		return mi_activity;
	}
	public void setMi_activity(String mi_activity) {
		this.mi_activity = mi_activity;
	}
	
	
	// generate post body demo
	public static UmPushparamAndriod generateUmPushParamDemo() {
		UmPushparamAndriod pushparamAndriod = new UmPushparamAndriod();
		pushparamAndriod.setDescription("zhizhi测试");
		pushparamAndriod.setProduction_mode(false);
		pushparamAndriod.setAppKey(Constants.UM_APP_KEY);
		pushparamAndriod.setType("broadcast");
		// set current time
		pushparamAndriod.setTimestamp(Calendar.getInstance().getTimeInMillis()+"");
		// set message expire time
		Calendar enpireTime = Calendar.getInstance();
		enpireTime.set(2018,10,20);
		Map<String,String> expireTime = new HashMap<>();
		expireTime.put("expire_time",enpireTime.getTime().toString());
		pushparamAndriod.setPolicy(expireTime);
		// set message body
		pushparamAndriod.getPayload().setDisplay_type("notification");
		pushparamAndriod.getPayload().getBody().setTitle("测试");
		pushparamAndriod.getPayload().getBody().setTicker("测试");
		pushparamAndriod.getPayload().getBody().setText("消息来自zhizhi server");
		pushparamAndriod.getPayload().getBody().setAfter_open("go_app");
		pushparamAndriod.getPayload().getBody().setPlay_vibrate("false");
		pushparamAndriod.getPayload().getBody().setPlay_lights("false");
		pushparamAndriod.getPayload().getBody().setPlay_sound("true");
		
		return pushparamAndriod;
		
	}
	
	

	
	
}
