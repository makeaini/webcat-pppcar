package com.pppcar.test;

import com.alibaba.fastjson.JSONObject;
import com.pppcar.pojo.AccessToken;
import com.pppcar.util.HttpRequestUtil;

public class JsApiTicket {
	public static final String TICKETURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	public String jsApiTicket() {
		// 第三方用户唯一凭证
		String appId = "wx45671edd342311eb";
		// 第三方用户唯一凭证密钥
		String appSecret = "0b72a5e9132ed78e204e87e602c0b832";

		// 调用接口获取access_token
		AccessToken at = HttpRequestUtil.getAccessToken(appId, appSecret);
		System.out.println(at);
		String sendUrl = null;
		if (at != null) {
			sendUrl = TICKETURL.replace("ACCESS_TOKEN", at.getToken());
			JSONObject object=HttpRequestUtil.httpRequest(sendUrl, "GET", null);
			return object.get("ticket").toString();
		}
		return null;
	}
}
