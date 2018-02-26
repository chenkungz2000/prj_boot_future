<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
.info {
	padding-left: 320px;
	padding-top: 35px;
}
 .list p{
 	width: 7%;
    margin: auto;
    padding-top: 20px;
    padding-bottom: 20px;
    font-size: 22px;
    color: coral;
    font-weight: bold;
}

table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width: 60%;margin:auto
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #whitesmoke;
}
table.gridtable td input{
	width:90px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="list">
	<button type="button" class="submit">CHAXUN</button>
	<p>用户列表</p>
	<table calss="create"></table>
		<table class='gridtable'>

		</table>
	</div>
	<script type="text/javascript">
	$('.submit').hide();
		$('.submit').click(function() {
			var url = "wms/app/user/list";
			var pageSize = 999;
			var pageNum = 5;
			var params = $(this).serialize();
			params += "&pageSize=" + pageSize + "&pageNum=" + pageNum;
				$.getJSON(url,params,function(json) {
					$(".list table").html("");
					var datasList = json.datas;
					for ( var i = 0; i < datasList.length; i++) {
						var html = "<tr><th>id</th><th>账号</th><th>密码</th><th>手机号码</th><th>归属地</th><th>状态</th><th>门店</th><th>mac地址</th><th>操作</th><th>操作</th></tr>"
								+ "<tr>"
								+ "<td>"
								+ "<input readonly='readonly' name='id' type='text' value='"+ datasList[i].id+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text' name='name'value='"+ datasList[i].name+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text'name='password' value='"+ datasList[i].password+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text'name='password' value='"+ datasList[i].phoneNumber+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text'name='password' value='"+ datasList[i].online+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text'name='password' value='"+ datasList[i].attribution+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text'name='password' value='"+ datasList[i].storeInformation+"'>"
								+ "</td>"
								+ "<td>"
								+ "<input type='text'name='password' value='"+ datasList[i].mac+"'>"
								+ "</td>"
								+ "<td>"
								+ "<button type='button' class='update' onclick='update(this);'>修改</button>"
								+ "</td>"
								+ "<td>"
								+ "<button type='button' class='delete' onclick='deleteUser(this);'>删除</button>"
								+ "</td>"
								+"</tr>";
						$('.list table').append(
								html);
		
					}
					;
				}, "json");
				return false;
			});
		$(".submit").trigger("click");
		function update(element){
			var buttonEle = $(element);
			var id = buttonEle.parents("tr").find("input[name='id']").val();
			var name = buttonEle.parents("tr").find("input[name='name']").val();
			var password = buttonEle.parents("tr").find("input[name='password']").val();
			var url="/app/user/updateUser";
				$.get(url,{"id":id,"name":name,"password":password}, function(data) {
							alert(data.massage);
				}, 'json');
		};
		function deleteUser(dleltedata){
			var buttonEle = $(dleltedata);
			var id = buttonEle.parents("tr").find("input[name='id']").val();
			var url="/app/user/deleteUser";
				$.get(url,{"id":id}, function(data) {
					if(flag=true){
						alert(data.massage);
						history.go(0);
					}
				}, 'json');
		};
		
		
		function XBWU(data){
			var buttonEle = $(dleltedata);
			var id = buttonEle.parents("tr").find("input[name='id']").val();
			var url="/app/user/deleteUser";
				$.get(url,{"id":id}, function(data) {
					if(flag=true){
						alert(data.massage);
						history.go(0);
					}
				}, 'json');
		};
	</script>
</body>
</html>