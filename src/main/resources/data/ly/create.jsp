<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script> -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="input_box">
		<input id="name" type="text" name="name" value="账号" /> <input
			id="password" type="password" name="password" value="密码" /> <input
			id="phone" type="text" name="phone" value="手机号码" />

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
</body>
</html>