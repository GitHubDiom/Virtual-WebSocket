<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"
	import="servlets.WebSocketMessageServlet"%>
<%
	String user = (String) session.getAttribute("user");
	if (user == null) {//为用户生成昵称
		user = "访客" + WebSocketMessageServlet.ONLINE_USER_COUNT;
		WebSocketMessageServlet.ONLINE_USER_COUNT++;
		session.setAttribute("user", user);
	}
	pageContext.setAttribute("user", user);
%>

<!-- 想跟我一起玩吗？   WeChat||Phone:13650645106 -->
<html lang="en">
<head>
<meta charset="utf-8" />
<title>聊天界面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- basic styles -->

<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet" href="assets\css\cyrillic.css" />

<!-- ace styles -->

<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
<!-- 引入CSS文件 -->
<link rel="stylesheet" type="text/css"
	href="ext4/resources/css/ext-all.css">
<!-- 		<link rel="stylesheet" type="text/css" href="ext4/shared/example.css" /> -->
<link rel="stylesheet" type="text/css" href="css/websocket.css" />



<!-- 引入webscoket js脚本文件. -->

<!-- 引入jquery库。 -->
<script type="text/javascript" src="js/jquery_1.8.3.js"></script>
<script type="text/javascript" src="js/websocket.js"></script>

<!-- 引入Ext库。-->
<script type="text/javascript" src="ext4/ext-all-debug.js"></script>
<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	

	<div id="modal-table-man" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						用户修改
					</div>
				</div>

				<div class="modal-body no-padding">
					<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
						<thead>
							<tr>
								<th>用户</th>
								<th>分组</th>

							</tr>
						</thead>

						<tbody>
							<tr>
								<td><select style="width: 100%" id="friend-list">

								</select></td>

								<td><select style="width: 100%" id="group-list">

								</select></td>

							</tr>
						</tbody>
					</table>
				</div>
				

				<div class="modal-footer no-margin-top">
					<button class="btn btn-sm btn-danger pull-left"
						data-dismiss="modal">

						<i class="icon-remove"></i> Close
					</button>
					<button class="btn btn-sm btn-danger pull-right"
						onclick="alertManFriend()">
						<i class="icon-ok"></i>commit
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	


	<div id="modal-table-group" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						添加自定义分组
					</div>
				</div>

				<div class="modal-body no-padding">
					<table
						class="table table-striped table-bordered table-hover no-margin-bottom no-border-top col-md-12">
						<tr>
							<input id="addContext" type="text" class="form-control"
								placeholder="请输入自定义分组名称" />
						</tr>
					</table>
				</div>

				<div class="modal-footer no-margin-top">
					<button class="btn btn-sm btn-danger pull-left"
						data-dismiss="modal">
						<i class="icon-remove"></i> 关闭
					</button>
					<button class="btn btn-sm btn-danger pull-right"
						onclick="appendGroup()">
						<i class="icon-ok"></i>添加
					</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div id="modal-table-History" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						用户修改
					</div>
				</div>

				<div class="modal-body no-padding">
					<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
						<thead>
							<tr>
								<th>选择用户</th>
								

							</tr>
						</thead>

						<tbody>
							<tr>
								<td><select style="width: 100%" id="friend-list-History">

								</select></td>
							</tr>
							
						</tbody>
					</table>
				</div>
				

				<div class="modal-footer no-margin-top">
					<button class="btn btn-sm btn-danger pull-left"
						data-dismiss="modal">

						<i class="icon-remove"></i> Close
					</button>
					<button class="btn btn-sm btn-danger pull-right"
						onclick="ShowHistory()">
						<i class="icon-ok"></i>commit
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
		<div id="modal-table" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						添加好友
					</div>
				</div>

				<div class="modal-body no-padding">
					<table
						class="table table-striped table-bordered table-hover no-margin-bottom no-border-top col-md-12"
						id="rhiden">
						<thead>
							<tr>
								<th>电话号码</th>
								<th>用户名</th>
								<th>邮箱</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>
						</tbody>
					</table>
				</div>

				<div class="modal-footer no-margin-top">
					<button class="btn btn-sm btn-danger pull-left"
						data-dismiss="modal">
						<i class="icon-remove"></i> 关闭
					</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<div id="modal-table-HistoryTable" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						
					</div>
				</div>
										 <div class="widget-body">
												<div class="widget-main no-padding">
													<div class="wysiwyg-editor" id="editor2"  contenteditable="false" style="height: 200px;"><br>
													
													</div>
												</div>

											</div> 
											<!-- <div class="col-xs-12"style="height: 150px; border: 0px lightgrey solid">
															<textarea id="editor2" style="height: 100%; width: 100%; overflow-y:scroll" disabled="disabled">
															
															</textarea>	
															</div> -->
				
				
							<div class="modal-footer no-margin-top">
					<button class="btn btn-sm btn-danger pull-left"
						data-dismiss="modal">
						<i class="icon-remove"></i> 关闭
					</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> Diom聊天室
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i class="icon-tasks"></i> <span
							class="badge badge-grey">4</span>
					</a>

						<ul
							class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-ok"></i> 4 Tasks
								to complete</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Software Update</span> <span
											class="pull-right">65%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width: 65%" class="progress-bar "></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Hardware Upgrade</span> <span
											class="pull-right">35%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width: 35%"
											class="progress-bar progress-bar-danger"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Unit Testing</span> <span
											class="pull-right">15%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width: 15%"
											class="progress-bar progress-bar-warning"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Bug Fixes</span> <span
											class="pull-right">90%</span>
									</div>

									<div class="progress progress-mini progress-striped active">
										<div style="width: 90%"
											class="progress-bar progress-bar-success"></div>
									</div>
							</a></li>

							<li><a href="#"> See tasks with details <i
									class="icon-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="purple"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="icon-bell-alt icon-animated-bell"></i> <span
							class="badge badge-important">8</span>
					</a>

						<ul
							class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-warning-sign"></i>
								8 Notifications</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i
											class="btn btn-xs no-hover btn-pink icon-comment"></i> New
											Comments
										</span> <span class="pull-right badge badge-info">+12</span>
									</div>
							</a></li>

							<li><a href="#"> <i
									class="btn btn-xs btn-primary icon-user"></i> Bob just signed
									up as an editor ...
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i
											class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
											New Orders
										</span> <span class="pull-right badge badge-success">+8</span>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i
											class="btn btn-xs no-hover btn-info icon-twitter"></i>
											Followers
										</span> <span class="pull-right badge badge-info">+11</span>
									</div>
							</a></li>

							<li><a href="#"> See all notifications <i
									class="icon-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="green"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="icon-envelope icon-animated-vertical"></i> <span
							class="badge badge-success">5</span>
					</a>

						<ul
							class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-envelope-alt"></i>
								5 Messages</li>

							<li><a href="#"> <img src="assets/avatars/avatar.png"
									class="msg-photo" alt="Alex's Avatar" /> <span
									class="msg-body"> <span class="msg-title"> <span
											class="blue">Alex:</span> Ciao sociis natoque penatibus et
											auctor ...
									</span> <span class="msg-time"> <i class="icon-time"></i> <span>a
												moment ago</span>
									</span>
								</span>
							</a></li>

							<li><a href="#"> <img src="assets/avatars/avatar3.png"
									class="msg-photo" alt="Susan's Avatar" /> <span
									class="msg-body"> <span class="msg-title"> <span
											class="blue">Susan:</span> Vestibulum id ligula porta felis
											euismod ...
									</span> <span class="msg-time"> <i class="icon-time"></i> <span>20
												minutes ago</span>
									</span>
								</span>
							</a></li>

							<li><a href="#"> <img src="assets/avatars/avatar4.png"
									class="msg-photo" alt="Bob's Avatar" /> <span class="msg-body">
										<span class="msg-title"> <span class="blue">Bob:</span>
											Nullam quis risus eget urna mollis ornare ...
									</span> <span class="msg-time"> <i class="icon-time"></i> <span>3:15
												pm</span>
									</span>
								</span>
							</a></li>

							<li><a href="inbox.html"> See all messages <i
									class="icon-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="assets/avatars/user.jpg" alt="Jason's Photo" /> <span
							class="user-info" id="currentUser"> </span> <i
							class="icon-caret-down"></i>
					</a>

						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="icon-cog"></i> Settings
							</a></li>

							<li><a href="#"> <i class="icon-user"></i> Profile
							</a></li>

							<li class="divider"></li>

							<li><a href="login.html"> <i class="icon-off"></i>
									Logout
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success" onclick="findFriend()">
							<i class="icon-signal"> </i>
						</button>

						<button class="btn btn-info" onclick="GetStranger()">
							<i class="icon-pencil"></i>
						</button>

						<!-- <button class="btn btn-warning" onclick="manFriend()">
							<i class="icon-group"></i>
						</button>
 -->
						<button class="btn btn-danger" onclick="ManageFriend()">
							<i class="icon-cogs"></i>
						</button>

						<button class="btn btn-danger" onclick="addGroup()">
							<i class="glyphicon glyphicon-plus"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list" id='mynav-list'>






				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#" id="who">Home</a>
							<input type="hidden" id="whoId"></li>
					</ul>
					<!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-lg btn-success"
								onclick="javascript:getWindow();">Line</button>

						</div>


					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="error-container">
								<div class="well col-xs-12">
									<div id="text_content"
										style="height: 300px; overflow-y: auto; border-radius: 10px; border: 2px grey solid">

									</div>


									<div class="col-xs-12"
										style="height: 150px; border: 0px lightgrey solid">
										<!--<input type="text" style="height: 100%;width: 100%">-->
										<textarea style="height: 100%; width: 100%" id="gettext"></textarea>
									</div>
									<button class="btn btn-app btn-light btn-sm" onclick="send()">
										<i class="icon-share-alt bigger-100"></i> Send
									</button>
									<button class="btn btn-app btn-light btn-sm"
										onclick="UserHistory()">
										<i class="icon-cloud-upload bigger-100"></i> History
									</button>
								</div>
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>

	</div>
	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<!-- 
	<script src="assets\js\jquery-2.0.3.min.js"></script>
 -->
	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets\js\jquery-1.10.2.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<" + "/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<" + "/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->


	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?a43c39da34531f4da694d4499c5614d4";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
	<script>
		$("#currentUser").html(window.sessionStorage.getItem("userName"));
		//console.info(window.sessionStorage.getItem("userPhone"));
		function showFriend() {

			$
					.ajax({
						url : "GetFriends",
						type : "get",
						data : {
							"phone" : window.sessionStorage
									.getItem("userPhone")

						},
						success : function(data) {
							console.info(data);
							$("#mynav-list").empty();
							//$("#out"+out).empty();
							//$("#con"+out).empty();
							var json = jQuery.parseJSON(data);
							var group = json.group;
							var friend = json.data;
							// 							alert(group[0].groupName);							

							var control = '<li class=""><a href="#"><i class="icon-dashboard"></i><span class="menu-text">发送给所有联系人</span></a></li>'
							$(control).appendTo($("#mynav-list"));
							$("#mynav-list li ").click(
									function() {
										$("#mynav-list li ").removeClass(
												"active");
										// 										$(this).addClass("active");
										//getWindow();
										$("#who").html(
												$(this).find("a").find("span")
														.first().html());
										$("#whoId").val(
												$(this).find("a").find("input")
														.val())
									});
							//第一次 显示默认列表 好友列表
							var wai1 = '<li id="outt">'
									+ '<a href="#" class="dropdown-toggle">'
									+ '<i class="icon-list"></i>'
									+ '<span class="menu-text">我的好友</span>'
									+ '<b class="arrow icon-angle-down"></b>'
									+ '</a><ul id="conn" class="submenu">'
							$(wai1).appendTo($("#mynav-list"));

							if (friend != null) {
								$
										.each(
												friend,
												function(inner, fri) {
													if (fri.flag == 0) {
														var nei1 = '<li>'
																+ '<a href="#">'
																+ '<i class="icon-double-angle-right"></i>'
																+ fri.userName
																+ '<input type="hidden" value="'+fri.phone+'">'
																+ '</a>'
																+ '</li>'

														$(nei1).appendTo(
																$("#conn"));
													}
												});

							}
							var li1 = '</ul></li>'
							$(li1).appendTo($("#outt"));

							if (group != null) {
								$.each(
												group,
												function(out, gg) {
													var wai = '<li id="out'+out+'">'
															+ '<a href="#" class="dropdown-toggle">'
															+ '<i class="icon-list"></i>'
															+ '<span class="menu-text">'
															+ gg.groupName
															+ '</span>'
															+ '<b class="arrow icon-angle-down"></b>'
															+ '</a><ul id="con'+out+'" class="submenu">'
													$(wai).appendTo(
															$("#mynav-list"));

													if (friend != null) {
														$.each(friend,function(inner,fri) {
																			if (fri.flag == gg.groupID) {
																				var nei = '<li>'
																						+ '<a href="#">'
																						+ '<i class="icon-double-angle-right"></i>'
																						+ fri.userName
																						+ '<input type="hidden" value="'+fri.phone+'">'
																						+ '</a>'
																						+ '</li>'

																				$(nei).appendTo($("#con"+ out));
																			}
																		});
													}

													var li = '</ul></li>'
													$(li).appendTo(
															$("#out" + out));
												});
							}
						

							$("#mynav-list li ul li").click(
									function() {
										$("#mynav-list li ul li").removeClass(
												"active");
										$(this).addClass("active");
										//getWindow();
										$("#who").html(
												$(this).find("a").first()
														.html());
										$("#whoId").val(
												$(this).find("a").find("input")
														.val())
									});
						},
						error : function() {
							alert("ShowFriend访问失败");
						}
					});
		}
		
		if (window.sessionStorage.getItem("userPhone") == null)
			window.location.href = "login.html";
		showFriend();
		var LoginName = window.sessionStorage.getItem("userName");
		function getWindow() {
			if ($.browser.opera) {//如果为谷歌浏览器，则去掉后面两个<br>。注意：在使用键盘提交信息时，谷歌浏览器会自动在原有信息的后面添加两个<br>。
				Ext.onReady(function() {
					Ext.MessageBox.show({
						title : "友情提示",
						msg : "当前浏览器为Opera，使用快捷键功能前需将鼠标焦点移出文本编辑域，否则功能失效！！！",
						buttons : Ext.Msg.OKCANCEL,
						width : 700,
						icon : Ext.MessageBox.WARNING,
						closable : false,
						fn : function(e) {
							if (e == 'ok') {
								getWebSocketWindow(LoginName);
							}
						}
					});
				});
			} else {
				getWebSocketWindow(LoginName);
			}

		}
		function findFriend() {
			var findfriendID = prompt("请输入您要查找的好友phone", "");
			if (findfriendID != "" && findfriendID != null) {
				$.ajax({
					url : "GetFriends",
					type : "post",
					async : true,
					data : {
						"findfriendID" : findfriendID,
					},
					success : function(data) {
						var json = jQuery.parseJSON(data);	
						if (json.flag) {
							var r = confirm("需要将 \n名字 ：" + json.userName
									+ "\n电话：" + json.phone + "\n邮件："
									+ json.email + "\n加为好友吗？");
							if (r == true) {

								findPerson(findfriendID);

							} else {
								alert("取消添加");
							}
						} else {
							alert("查找失败!");
						}
					},
					error : function() {
						alert("连接失败");
					}

				});

			} else {
				alert("查找不能为空");
			}
		}
		function findPerson(phone) {
			alert(phone);
			$.ajax({
				url : "AddPersion",
				type : "post",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"addPersionPhone" : phone,
					"hostUserPhone" : window.sessionStorage
							.getItem("userPhone"),

				},
				success : function(data) {
					var json = jQuery.parseJSON(data);
					if (json.flag) {
						alert("添加成功");
						$("#modal-table").modal('hide');
						showFriend();
					} else {
						alert("添加失败");
					}

				},
				error : function() {
					//æ§è¡å¤±è´¥ä¹åçä»£ç 
					alert("findPerson访问失败");
				},
			});

		}

		function GetStranger() {
			$("#modal-table tbody").empty();
			$
					.ajax({
						url : "GetStranger",
						type : "get",
						async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
						data : {
							"userPhone" : window.sessionStorage
									.getItem("userPhone"),

						},
						success : function(data) {
							console.info(data);
							//è¥åå°ä¼ jsonå¯¹è±¡ï¼ådataä¸ºjsonå¯¹è±¡
							//åä¸é jQuery.parseJSON(data)
							var json = jQuery.parseJSON(data);
							var users = json.data;
							//jq循环
							$.each(users,function(i, item) {
												$("<tr><td>"
																+ item.phone
																+ "</td><td>"
																+ item.userName
																+ "</td><td>"
																+ item.email
																+ "</td><td align='center'><button class='btn btn-xs btn-info' onclick='addPerson("
																+ item.phone
																+ ")'><i class='icon-edit bigger-50'>添加</i></button></td></tr>").appendTo($("#modal-table tbody"));
												//appendTo将前面个的jq对象添加到后面的容器
											});
						},
						error : function() {
							//æ§è¡å¤±è´¥ä¹åçä»£ç 
							alert("GetStranger访问失败");
						},
					});
			$(".table-header").html("陌生人列表");
			$("#modal-table").modal('show');
		}
		function manFriend() {
			$(".table-header").html("移动好友");
			$("#modal-table").modal('toggle');
			$("#group-list").empty();
			$("#friend-list").empty();
			$.ajax({
				url : "ManFri",
				type : "get",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"userPhone" : window.sessionStorage.getItem("userPhone"),
				},
				success : function(data) {
					//è¥åå°ä¼ jsonå¯¹è±¡ï¼ådataä¸ºjsonå¯¹è±¡
					// jQuery.parseJSON(data)
					var json = jQuery.parseJSON(data);
					var group = json.group;
					var friend = json.friend;
					$.each(group, function(i, item) {
						$('<option  value="'+item.groupID+'">'+ item.groupName + '</option>').appendTo($("#group-list"));
					});
					$.each(friend, function(i, item) {
						$('<option  value="'+item.phone+'">'	+ item.userName + '</option>').appendTo($("#friend-list"));
					});
				},
				error : function() {
					alert("manFriend访问失败");
				}
			});

			$("#modal-table-man").modal('show');
		}

		function alertManFriend() {
			var friend = $("#friend-list option:selected").val();
			var group = $("#group-list option:selected").val();

			$.ajax({
				url : "ManFri",
				type : "post",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"userPhone" : window.sessionStorage.getItem("userPhone"),
					"friend" : friend,
					"group" : group,
				},
				success : function(data) {
					//è¥åå°ä¼ jsonå¯¹è±¡ï¼ådataä¸ºjsonå¯¹è±¡
					// jQuery.parseJSON(data)
					var json = jQuery.parseJSON(data);
					if (json.flag) {
						$("#modal-table-man").modal('hide');
						showFriend();
					} else {
						alert("修改失败");
					}

				},
				error : function() {
					alert("alertManFriend访问失败");
				}
			});

		}
		function ManageFriend() {
			$("#modal-table tbody").empty();
			for (var i = 2; i < 6; i++)
				$("#modal-table #rhiden thead tr th:nth-child(" + i + ")").show();
			//-----------------導入好友列表-------------

			$
					.ajax({
						url : "GetFriends",
						type : "get",
						async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
						data : {
							"phone" : window.sessionStorage
									.getItem("userPhone"),
						},
						success : function(data) {
							console.info(data);
							var json = jQuery.parseJSON(data);
							var users = json.data;
							//jq循环
							$.each(users,function(i, item) {	$("<tr><td>"
																+ item.phone
																+ "</td><td>"
																+ item.userName
																+ "</td><td>"
																+ item.email
																+ "<td align='center'><button class='btn btn-xs btn-info' onclick='manFriend()'><i class='icon-edit bigger-120'></i></button>"
																+ "&nbsp;&nbsp;&nbsp;<button class='btn btn-xs btn-danger' onclick='deleteFunction("
																+ i
																+ ")'><i class='icon-trash bigger-120'></i></button></td>"
																+ "</td></tr>")
														.appendTo($("#rhiden"));
												//appendTo将前面个的jq对象添加到后面的容器
											});
						},
						error : function() {
							//æ§è¡å¤±è´¥ä¹åçä»£ç 
							alert("ManageFriend访问失败");
						},
					});

			$(".table-header").html("好友管理");
			$("#modal-table").modal('show');
		}
		function deleteFunction(i) {
			//$("#sample-table-2 tr")拿到对应容器 
			//eq拿到对应下标的对象
			//find("rd") 拿到这行的的所有列
			//html()拿到该对象的innerHtml
			var DeletePhone = $("#rhiden tr").eq(i + 1).find("td").eq(0).html();
			var UserPhone = window.sessionStorage.getItem("userPhone");
			$.ajax({
				url : "DeleteFriends",
				type : "post",
				async : true,
				data : {
					"DeletePhone" : DeletePhone,
					"UserPhone" : UserPhone
				},
				success : function(data) {
					var json = jQuery.parseJSON(data);
					if (json.flag) {
						search();
						window.location.reload();
					} else {
						alert("删除失败");
					}
				},
				error : function() {
					alert("deleteFunction 访问失败");
				}
			});
		}
		function addGroup() {
			$("#modal-table-group").modal('show');
			$(".table-header").html("添加分组");

		}

		function appendGroup() {
			var context = $("#addContext").val();
			$.ajax({
				url : "AddGroup",
				type : "post",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"groupName" : context,
					"userPhone" : window.sessionStorage.getItem("userPhone"),
				},
				success : function(data) {
					var json = jQuery.parseJSON(data);
					if (json.flag) {
						alert("添加成功");
						showFriend();
					} else {
						alert("添加失败");
					}
				},
				error : function() {
					alert("appendGroup访问失败");
				}
			});
			$("#addContext").val("");
			$("#modal-table-group").modal('hide');
		}
		$("body").keydown(function(e) {
			var curKey = e.which;
			if (curKey == 13) {
				send();
				return false;
			}
		});
		//选择用户查看聊天记录
		function UserHistory()
		{
			$("#friend-list-History").empty();
			$.ajax({
				url : "ManFri",
				type : "get",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"userPhone" : window.sessionStorage.getItem("userPhone"),
				},
				success : function(data) {
					//è¥åå°ä¼ jsonå¯¹è±¡ï¼ådataä¸ºjsonå¯¹è±¡
					// jQuery.parseJSON(data)
					var json = jQuery.parseJSON(data);
					var group = json.group;
					var friend = json.friend;

					$.each(friend, function(i, item) {
						$('<option  value="'+item.userName+'">'+ item.userName + '</option>').appendTo($("#friend-list-History"));
						//appendTo将前面个的jq对象添加到后面的容器
					});
				},
				error : function() {
					alert("manFriend访问失败");
				}
			});
			$(".table-header").html("选择好友");
			$("#modal-table-History").modal('show');
			
		}
		//展示聊天记录
		function ShowHistory() {
			$("#modal-table-History").modal('hide');
			$(".table-header").html("聊天记录");
			$("#modal-table-HistoryTable").modal('toggle');
  			$("#editor2").empty();
			$.ajax({
				url : "ShowHistoryServlet",
				type : "get",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"FromUserId" : window.sessionStorage.getItem("userName"),
					"UserPhone" :  window.sessionStorage.getItem("userPhone"),
					"ToUserId" : $("#friend-list-History option:selected").text(),//没有解决中文编码问题
				},
				success : function(data) {//logs:can not catch the json data
					var json = jQuery.parseJSON(data);
					var friend = json.friend_list;
					var measage_list = json.message_list;
					$.each(measage_list, function(i, item) {
 						$("#editor2").append('<div id='+i+'>'+item.fromeuser+":("+item.datetime+")\n\t"+item.message+"</div>");
					});
					
				
				},
				error : function() {
					alert("ShowHistoryServlet访问失败");
				}
			});

		}
		
		//发送消息
		function send() {
			var message = $("#gettext").val();
			var towho = $("#who").text();//用户名
			$.ajax({
				url : "SaveContentServlet",
				type : "post",
				async : true,
				data : {
					"towho" : towho,
					"fromewho" : window.sessionStorage.getItem("userName"),
					"message" : message,
				},
				success : function(data) {
					var json = jQuery.parseJSON(data);
					if (json.flag) {
						// 					alert("添加成功");
						showFriend();
					} else {
						alert("添加失败");
					}
				},
				error : function() {
					alert("send访问失败");
				}
			});
			if (message == "")
				{
				alert("请输入信息！");
				showFriend();
				}
				
			if (towho == "") {
				websocket.send(message);
			} else {
				//alert(towho);
				websocket.send(towho + "::" + message);
			}
			$("#gettext").val("");

		}

		function addPerson(phone) {
			alert(phone);
			$.ajax({
				url : "AddPersion",
				type : "post",
				async : true,//trueæ¯å¼æ­¥ï¼é»è®¤ï¼ï¼falseæ¯åæ­¥
				data : {
					"addPersionPhone" : phone,
					"hostUserPhone" : window.sessionStorage
							.getItem("userPhone"),
				},
				success : function(data) {
					var json = jQuery.parseJSON(data);
					if (json.flag) {
						alert("添加成功");
						$("#modal-table").modal('hide');
						showFriend();
					} else {
						alert("添加失败");
					}

				},
				error : function() {
					//æ§è¡å¤±è´¥ä¹åçä»£ç 
					alert("AddPersion访问失败");
				},
			});
		}

		//判断当前浏览器是否支持WebSocket
		/*   	var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_=' + Math.random();
		$.getJSON(url, function (data) {
		    alert(data.Ip);//弹出本地ip
		});
		
		 */

		//websocket聊天
		if ('WebSocket' in window) {
			if (window.sessionStorage.getItem("userPhone") != null) {
				websocket = new WebSocket(
						"ws://123.207.93.178:8080/WebProject2/message?name='"
								+ window.sessionStorage.getItem("userName")
								+ "'");
			} else {
				window.location.href = "login.html";
			}

		} else {
			alert('当前浏览器 Not support websocket')
		}
		//连接发生错误的回调方法
		websocket.onerror = function() {
			setMessageInnerHTML("WebSocket连接发生错误");
		};

		//连接成功建立的回调方法
		websocket.onopen = function() {
			setMessageInnerHTML("WebSocket连接成功");
		};

		//连接关闭的回调方法
		websocket.onclose = function() {
			setMessageInnerHTML("WebSocket连接关闭");
		};

		//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		window.onbeforeunload = function() {
			closeWebSocket();
		}

		//接收到消息的回调方法
		websocket.onmessage = function(event) {
			setMessageInnerHTML(event.data);
		};
		//将消息显示在网页上
		function setMessageInnerHTML(innerHTML) {
			
			$("#text_content").html(
					$("#text_content").html() + innerHTML + '<br/>');
			// 				document.getElementById('message').innerHTML += innerHTML + '<br/>';
		}

		//关闭WebSocket连接
		function closeWebSocket() {
			websocket.close();
		}
	</script>
</body>
</html>