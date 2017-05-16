package com.pppcar.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pppcar.pojo.Article;
import com.pppcar.pojo.NewsMessage;
import com.pppcar.pojo.TextMessage;
import com.pppcar.util.MessageUtil;
@WebServlet(urlPatterns="/servlet/shining")
public class WeChat extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = "shining";
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params);
		String temp = SHA1
				.encode(params.get(0) + params.get(1) + params.get(2));
		if (temp.equals(signature)) {
			response.getWriter().write(echostr);
		}
		System.out.println("进来了");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestMap = null;
		try {
			requestMap = MessageUtil.parseXml(request.getInputStream());
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			System.out.println(fromUserName);
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			String respContent = null;
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				textMessage.setContent("reponse:" + requestMap.get("Content"));
				if (requestMap.get("Content").equals("1")) {
					SendTemplent templent = new SendTemplent();
					templent.sendTmpl(fromUserName);
					return;
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				System.out.println("又进来了");
				// 事件KEY值，与创建自定义菜单时指定的KEY值对应
				String eventKey = requestMap.get("EventKey");
				if (eventKey != null) {
					if (eventKey.equals("11")) {
						NewsMessage message = new NewsMessage();
						message.setToUserName(fromUserName);
						message.setFromUserName(toUserName);
						message.setCreateTime(System.currentTimeMillis());
						message.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						List<Article> articles = new ArrayList<Article>();
						Article article = new Article();
						article.setDescription("原来我不帅");
						article.setTitle("原来我不帅");
						article.setUrl("http://www.pppcar.com");
						article.setPicUrl("http://shining.ngrok.cc/webcat-pppcar/static/1.jpg");
						Article article2 = new Article();
						article2.setDescription("原来我不帅2");
						article2.setTitle("原来我不帅2");
						article2.setUrl("http://www.pppcar.com");
						article2.setPicUrl("http://shining.ngrok.cc/webcat-pppcar/static/2.jpg");
						Article article3 = new Article();
						article3.setDescription("原来我不帅3");
						article3.setTitle("原来我不帅3");
						article3.setUrl("http://www.pppcar.com");
						article3.setPicUrl("http://shining.ngrok.cc/webcat-pppcar/static/3.jpg");
						Article article4 = new Article();
						article4.setDescription("原来我不帅4");
						article4.setTitle("原来我不帅4");
						article4.setUrl("http://www.pppcar.com");
						article4.setPicUrl("http://shining.ngrok.cc/webcat-pppcar/static/4.jpg");
						articles.add(article);
						articles.add(article2);
						articles.add(article3);
						articles.add(article4);
						message.setArticleCount(articles.size());
						message.setArticles(articles);
						String newMessage = MessageUtil
								.newsMessageToXml(message);
						PrintWriter out = response.getWriter();
						out.print(newMessage);
						out.flush();
						out.close();
						return;
					} else if (eventKey.equals("12")) {
						respContent = "<a href=\"www.pppcar.com\">天气</a>";
					} else if (eventKey.equals("14")) {
						respContent = "历史上的今天菜单项被点击！";
					} else if (eventKey.equals("21")) {
						respContent = "歌曲点播菜单项被点击！";
					} else if (eventKey.equals("22")) {
						respContent = "经典游戏菜单项被点击！";
					} else if (eventKey.equals("23")) {
						respContent = "美女电台菜单项被点击！";
					} else if (eventKey.equals("24")) {
						respContent = "人脸识别菜单项被点击！";
					} else if (eventKey.equals("25")) {
						respContent = "聊天唠嗑菜单项被点击！";
					} else if (eventKey.equals("31")) {
						respContent = "Q友圈菜单项被点击！";
					} else if (eventKey.equals("32")) {
						respContent = "电影排行榜菜单项被点击！";
					} else if (eventKey.equals("33")) {
						respContent = "幽默笑话菜单项被点击！";
					}
				}
			}
			textMessage.setContent(respContent);
			String newMessage = MessageUtil.textMessageToXml(textMessage);
			PrintWriter out = response.getWriter();
			out.print("response:" + newMessage);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
