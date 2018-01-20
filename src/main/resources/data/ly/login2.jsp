<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="input_box">
		<input type="text" name="name" placeholder="账号" /> <input type="text" name="password" placeholder="密码" /> 
		<input type="hidden" name="mac" value="ck" /> 
		<img id="img"src="/loginuser/validateCode/<%=new Date().getTime()%>" width="90" height="30" style="margin-left: 10px" onclick="flushCode()">
		<button type="submit" class="submit">登 录</button>
	</div>
	<script type="text/javascript">
	function flushCode() {
            var url = $("#img").prop('src');
            url = url.substr(0,url.lastIndexOf('/')+1);
            url = url + (new Date()).valueOf();
            $("#img").prop('src',url);
        }
	/* 登录 */
	var mac = "ck";
	$(function() {
	    $('.submit').click(function() {
		$.post("/app/user/login", {
		    "mac" : $('input').val(),
		    "name" : $('input').val(),
		    "password" : $('input').val(),
		}, function(data) {
		    if (data.flag == true) {
			if (data.online == 1)
			    alert("已经有设备登陆");

		    }
		    ;
		}, 'json');
	    });
	})
    </script>
</body>
</html>