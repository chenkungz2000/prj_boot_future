<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script> -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="chuangjian">
		<div class="input_box">
			<input id="name" type="text" name="name" value="账号" />
			<input id="password" type="password" name="password" value="密码" />
			<input id="phone" type="text" name="phone" value="手机号码" />
			<button type="submit" class="submit">创建</button>
		</div>
		<script type="text/javascript">
			var url = "/loginuser/create";
			$(function() {
				$('.submit').click(function() {
                    $.get(url, {
                        "name" : $('#name').val(),
                        "phone" : $('#phone').val(),
                        "password" : $('#password').val()
                    }, function(data) {
                        alert(data.flag);
                    }, 'json');
                });
			});
		</script>
	</div>
	<div class="loginfrom">
		<form id="login_form" action="/app/user/loginTest" method="post" onsubmit="formSubmit(); return false;">
			<div class="input_box">
				<input type="text" name="name" placeholder="账号" />
				<input type="text" name="password" placeholder="密码" />
				<input type="hidden" name="mac" value="XXX" />
				<button type="submit" class="login_btn">登 录</button>
			</div>
		</form>
		<table>
			<tr>
				<td class="text-right"><strong>验证码:</strong>&nbsp;&nbsp;</td>
				<td><input type="text" class="form-control" required name="codevalidate" style="width: 40%; display: inline">
					<img id="codevalidate" src="" width="90" height="30" style="margin-left: 10px" onclick="flushCode()"></td>
			</tr>
		</table>
		<script type="text/javascript">
			$(document).ready(function() {
				flushCode()
			})
			// 刷新图片
			function flushCode() {
				/*        window.location.host;
				 var url = $("#img").prop('src');
				 url = url.substr(0, url.lastIndexOf('/') + 1);
				 url = url + (new Date()).valueOf();*/
				var url = "/loginuser/validateCode/" + new Date();
				$("#codevalidate").prop('src', url);
			}
			/* 登录 */
			var mac = "XXX";

			function formSubmit() {
				$.get($("#login_form").attr("action"), $("#login_form")
						.serialize(), function(data) {
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
	</div>
	<div class="login2">
		<div class="input_box">
			<input type="text" name="name" placeholder="账号" />
			<input type="text" name="password" placeholder="密码" />
			<input type="hidden" name="mac" value="ck" />
			<img id="img" src="" width="90" height="30" style="margin-left: 10px" onclick="flushCode()">
			<button type="submit" class="submit">登 录</button>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				flushCode()
			})
			// 刷新图片
			function flushCode() {
				/*        window.location.host;
				 var url = $("#img").prop('src');
				 url = url.substr(0, url.lastIndexOf('/') + 1);
				 url = url + (new Date()).valueOf();*/
				var url = "/loginuser/validateCode/" + new Date();
				$("#img").prop('src', url);
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
	</div>
	<div class="">
		<div class="list"></div>
		<button type="button" class="submit">退出 即清空mac</button>
		<script type="text/javascript">
			$('.submit').click(function() {
				$.getJSON("/app/user/signoutmacandonline", function(data) {
					if (data.flag == true) {
						alert(data.msg);
					} else {
						alert(data.msg);
					}
				}, "json");
				return false;
			});
		</script>
	</div>
	<div class="import">
		<form id="login_form" action="/prj/upload" method="post" enctype="multipart/form-data">
			<div class="input_box">
				<input type="file" name="file" />
				<button type="submit" class="login_btn">上传</button>
			</div>
		</form>
	</div>

	<h2>上传多个文件 实例</h2>
	<form action="/prj/upload/uploadlist" method="post" enctype="multipart/form-data">
		<p>选择文件:</p>

		<input type="file" name="files">
		<p>选择文件:</p>
		<input type="file" name="files">
		<input type="submit" value="提交">
	</form>
</body>
</html>