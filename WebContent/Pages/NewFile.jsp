<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>用户名:</td>
			<td><input type="text" id="userName"></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="text" id="pwd"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="提交" id="commit"></td>
		</tr>

	</table>
	<script type="text/javascript">
		var commit = document.getElementById("commit");
		var userName = document.getElementById("userName");
		var pwd = document.getElementById("pwd");
		//userName.value="Lin";
		//pwd.value = "123";
		commit.onclick = function() {
			var userStr = userName.value;
			var pwdStr = pwd.value;
			//alert("用户名："+userName.value+"密码:"+pwd.value);
			if(userStr==""){
				alert("用户名为控股");
				}
			if(pwdStr==""){
				alert("密码为空");
				}
			
		};
		commit.onfocus  = function(){
			};
	</script>
</body>
</html>