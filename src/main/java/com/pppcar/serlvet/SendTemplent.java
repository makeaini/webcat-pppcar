package com.pppcar.serlvet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.pppcar.pojo.AccessToken;
import com.pppcar.pojo.TemplateMessage;
import com.pppcar.util.HttpRequestUtil;

public class SendTemplent {
	public static  String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    
	public void sendTmpl(String touser) {
		TemplateMessage templete = new TemplateMessage();
		templete.setTemplate_id("gshHED4JtIq7vHzbBY-C06JOTQ2_YnLJZ8x4ig8PjL4");
		templete.setTopcolor("#7b68ee");
		templete.setTouser(touser);
		templete.setUrl("http://www.baidu.com");
		Map<String,String> title=new HashMap<String, String>();
		title.put("value", "欢迎购买产品");
		title.put("color", "#743a3a");
		Map<String,String> name=new HashMap<String, String>();
		name.put("value", "apple air电脑");
		name.put("color", "#743a3a");
		Map<String,String> price=new HashMap<String, String>();
		price.put("value", "100.00");
		price.put("color", "#743a3a");
		Map<String,String> date=new HashMap<String, String>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date.put("value", format.format(new Date()));
		date.put("color", "#743a3a");
		Map<String,String> remark=new HashMap<String, String>();
		remark.put("value", "祝您购物愉快!");
		remark.put("color", "#743a3a");
		Map<String,Map<String,String>> data=new HashMap<String,Map<String,String>>();
		data.put("title", title);
		data.put("name", name);
		data.put("price", price);
		data.put("date", date);
		data.put("remark", remark);
		templete.setData(data);
		String s=JSONObject.toJSONString(templete);
		// 第三方用户唯一凭证
		String appId = "wx45671edd342311eb";
		// 第三方用户唯一凭证密钥
		String appSecret = "0b72a5e9132ed78e204e87e602c0b832";

		// 调用接口获取access_token
		AccessToken at = HttpRequestUtil.getAccessToken(appId, appSecret);
		System.out.println(at);
		String sendUrl=null;
		if(at!=null){
			sendUrl=url.replace("ACCESS_TOKEN", at.getToken());
			HttpRequestUtil.httpRequest(sendUrl, "POST", s);
			System.out.println(s);
		}
	}

}
