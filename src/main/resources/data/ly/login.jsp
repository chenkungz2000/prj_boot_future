<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="login_form" action="/app/user/loginTest" method="post" onsubmit="formSubmit(); return false;">
		<div class="input_box">
			<input type="text" name="name" placeholder="账号" /> <input type="text" name="password" placeholder="密码" /> <input type="hidden" name="mac" value="XXX" />
			<button type="submit" class="login_btn">登 录</button>
		</div>
	</form>
	
				<table>
				<tr>
					<td class="text-right"><strong>验证码:</strong>&nbsp;&nbsp;</td>
					<td><input type="text" class="form-control" required name="codevalidate" style="width: 40%; display: inline"> <img id="codevalidate"
						src="/loginuser/code/<%=new Date().getTime()%>" width="90" height="30" style="margin-left: 10px" onclick="changeUrl()"></td>
				</tr>
			</table>
	<script type="text/javascript">
	
	
	function changeUrl() {
            var url = $("#codevalidate").prop('src');
            url = url.substr(0,url.lastIndexOf('/')+1);
            url = url + (new Date()).valueOf();
            $("#codevalidate").prop('src',url);
        }
	/* 登录 */
	var mac = "XXX";
	function formSubmit() {
	    $.get($("#login_form").attr("action"), $("#login_form").serialize(), function(data) {
		if (data.flag == true) {
		    if (data.online == 1)
			alert("已经有设备登陆过");

		    if (data.mac != mac) {
			alert("XXX");
			//跳到登陆界面
		    }
		    window.location.href = "/data/tms/wy/list.html";
		}
	    }, 'json');
	    return false;
	}
    </script>
</body>
</html>