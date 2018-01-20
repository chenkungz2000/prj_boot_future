<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>上传多个文件 实例</h2>
	<form action="/prj/upload/uploadlist" method="post" enctype="multipart/form-data">
		<p>
			选择文件:
			<input type="file" name="files">
		<p>
			选择文件:
			<input type="file" name="files">
			<input type="submit" value="提交">
	</form>
</body>

</html>