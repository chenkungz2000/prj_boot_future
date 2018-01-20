<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
    <link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

    <title>后台登录 - H-ui.admin 3.0</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">

        <div class="row cl">
            <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
            <div class="formControls col-xs-8">
                <input id="value" name="" type="text" placeholder="账户" class="input-text size-L">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
            <div class="formControls col-xs-8">
                <input id="password" name="" type="password" placeholder="密码" class="input-text size-L">
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <input id="code" class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:"
                       style="width: 150px;"> <img id="img" src="/loginuser/validateCode" width="90" height="30" style="margin-left: 10px" onclick="flushCode()"><a
                    id="change" href="javascript:;" onclick="flushCode()">看不清，换一张</a>
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <label for="online"> <input type="checkbox" name="online" id="online" value=""> 使我保持登录状态
                </label>
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <input id="submit" name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> <input name="" type="reset"
                                                                                                                                                      class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
            </div>
        </div>

    </div>
</div>
<div class="footer">Copyright 你的公司名称 by H-ui.admin 3.0</div>
<script type="text/javascript">
    // 刷新图片
    function flushCode() {
        var url = $("#img").prop('src');
        url = url.substr(0, url.lastIndexOf('/') + 1);
        url = url + (new Date()).valueOf();
        $("#img").prop('src', url);
    }

    $(function() {
        var url = "/loginuser/login";
        $('#submit').click(function() {
            $.get(url, {
                "value" : $('#value').val(),
                "password" : $('#password').val(),
                "code" : $('#code').val(),
            }, function(data) {
                alert(data.flag);
                if (data.flag == true){
                    location.href ="/ly/data/index.jsp"
                }
                else{

                }


            }, 'json');
        });
    });

    $(function() {
        var url = "/loginuser/savecookie";
        $('#online').click(function() {
            $.get(url, {
                "value" : $('#value').val()
            }, function(data) {
                alert(data.flag);
            }, 'json');
        });
    });
</script>
</body>
</html>