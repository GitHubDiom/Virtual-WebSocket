<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<input type="text" value="你好" id="hello">
	<input type="text" id="userName">
	<input type="button" value="提交">

	<table>
		<tr>
			<td>用户名:</td>
			<td><input type="text" id="userName"></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" id="pwd"></td>
		</tr>
		<tr>
			<td><input type="button" onclick="submintFunction()" value="提交">
			</td>
		</tr>
	</table>

</body>
<script type="text/javascript">
	//选择器
	//#id   对应id的元素
	//.calss    返回数组，对应class中包含class的所有元素
	//div	返回数组，对应div所有元素
	//var hello = document.getElementById("hello");
	//取值
	//alert(hello.value);
	//hello.value = "不是很好";
	//function submintFunction() {
	//	alert("1");
	//}
	//jq获取值
	$("#hello").val();
	//jq设值
	$("#hello").val("不是很好");
</script>
//导入jq
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
</html>