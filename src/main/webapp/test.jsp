<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var timestamp = '${timestamp}';
var nonceStr = '${noncestr}';
var signature = '${signature}';
var appId = '${appId}';
wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: appId, // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: ["onMenuShareTimeline",
                "onMenuShareAppMessage",
                "onMenuShareQQ",
                "onMenuShareWeibo",
                "onMenuShareQZone","hideOptionMenu"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
var imgUrl='http://1.appshining.sinaapp.com/webcat-pppcar/static/2.jpg';

wx.ready(function () {
	wx.hideOptionMenu();
	
	
/* 	wx.onMenuShareAppMessage({
	    title: '这是第一个网站', // 分享标题
	    desc: '你看这个不错吧', // 分享描述
	    link: 'http://www.pppcar.com', // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    type: '', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	        alert("11");
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	wx.onMenuShareQQ({
	    title: '这是第一个网站', // 分享标题
	    desc: '你看这个不错吧', // 分享描述
	    link: 'http://www.pppcar.com', // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	       alert('分享成功');
	    },
	    cancel: function () { 
	       // 用户取消分享后执行的回调函数
	    }
	}); */

	
});

</script>
<body>
你看这个页面怎么样
</body>
</html>