<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("utf-8");
%>
<head>
<link href="css/lanrenzhijia.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<style>
 　::-webkit-input-placeholder{
     font-size: 14px;
     color:#999;
  }
    input::-webkit-input-placeholder{
     font-size: 14px;
     color:#999;
    }
</style>
</head>
<body>
	<div class="wrap">
		<div class="banner-show" id="js_ban_content">
			<div class="cell bns-01">
				<div class="con"></div>
			</div>
			<div class="cell bns-02" style="display: none;">
				<div class="con">
					<a href="http://www.lanrenzhijia.com" target="_blank"
						class="banner-link"> <i>圈子</i></a>
				</div>
			</div>
			<div class="cell bns-03" style="display: none;">
				<div class="con">
					<a href="http://www.lanrenzhijia.com" target="_blank"
						class="banner-link"> <i>企业云</i></a>
				</div>
			</div>
		</div>
		<div class="banner-control" id="js_ban_button_box">
			<a href="javascript:;" class="left">左</a> <a href="javascript:;"
				class="right">右</a>
		</div>

		<div class="container">
			<div class="register-box">
				<div class="reg-slogan">用户注册</div>
				<form name="form1" class="reg-form" id="js-form-mobile"
					action="register.do" method="post">
					<br> <br>
					<div class="cell">
						<input type="text" name="user" id="js-mobile_ipt" class="text"  placeholder="请输入用户名"/>
						<%
							String msg = (String) request.getAttribute("msg");
						%>
						<span style="color: red; font-size: 14px;position: absolute;top: 40px;left: 10px;"><%=msg == null ? "" : msg%></span>
					</div>
					<div class="cell">
						<input type="password" name="pwd" id="js-mobile_pwd_ipt" placeholder="请输入密码"
							class="text" />
					</div>
					<div class="cell">
						<input type="password" name="rpwd" id="js-mobile_pwd_ipt" placeholder="请再次输入密码"
							class="text" />
					</div>
					<div class="cell">
						<input type="email" name="email" id="js-mobile_pwd_ipt" placeholder="请输入有效邮箱"
							class="text" />
					</div>
					<div class="bottom">
						<input type="submit" value="登陆" id="js-mobile_btn"
							onclick="return check(form1)" class="button btn-green">
					</div>
				</form>
				<a href="login.jsp"><div style="text-align: center;color:skyblue">登陆</div></a>
			</div>
		</div>
	</div>
</body>
<script src="js/jquery-2.1.1.min.js"></script>
<script>
	function check(form) {
		if (form.user.value == '') {
			alert("用户名不能为空");
			form.user.focus();
			return false;
		}
		if (form.pwd.value == '') {
			alert("密码不能为空");
			form.pwd.focus();
			return false;
		}
		if(form.pwd.value!=form.rpwd.value){
			alert("两次输入密码不一致");
			form.pwd.focus();
			return false;
		}
	}
	var defaultInd = 0;
	var list = $('#js_ban_content').children();
	var count = 0;
	var change = function(newInd, callback) {
		if (count)
			return;
		count = 2;
		$(list[defaultInd]).fadeOut(400, function() {
			count--;
			if (count <= 0) {
				if (start.timer)
					window.clearTimeout(start.timer);
				callback && callback();
			}
		});
		$(list[newInd]).fadeIn(400, function() {
			defaultInd = newInd;
			count--;
			if (count <= 0) {
				if (start.timer)
					window.clearTimeout(start.timer);
				callback && callback();
			}
		});
	}

	var next = function(callback) {
		var newInd = defaultInd + 1;
		if (newInd >= list.length) {
			newInd = 0;
		}
		change(newInd, callback);
	}

	var start = function() {
		if (start.timer)
			window.clearTimeout(start.timer);
		start.timer = window.setTimeout(function() {
			next(function() {
				start();
			});
		}, 8000);
	}

	start();

	$('#js_ban_button_box').on('click', 'a', function() {
		var btn = $(this);
		if (btn.hasClass('right')) {
			//next
			next(function() {
				start();
			});
		} else {
			//prev
			var newInd = defaultInd - 1;
			if (newInd < 0) {
				newInd = list.length - 1;
			}
			change(newInd, function() {
				start();
			});
		}
		return false;
	});
</script>
</html>