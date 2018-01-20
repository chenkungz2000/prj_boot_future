<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="login_form" action="/prj/upload" method="post" enctype="multipart/form-data" >
		<div class="input_box">
			<input type="file" name="file"/>
			<button type="submit" class="login_btn">上传</button>
		</div>
	</form>
</body>
</html>