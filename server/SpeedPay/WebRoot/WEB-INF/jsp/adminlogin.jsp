<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员登录</title>
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color:#ffffff ;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #f5f5f5;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/db.css" rel="stylesheet" media="screen">
  <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>

	 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li style= "font-size:16px">
              
               <a class="brand" href="index"><strong>咔嚓付</strong></a>
               </li>
              <li class="active"><a href="index"><i class="icon-home"></i> <strong>首页</strong></a></li>     
            </ul>  
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
 
<div class="jumbotron-cy">
<div class="container" >
	<div class="hero-unit">
        <h1><div style="font-size:70px">银行：你好，二维码</div></h1>
        <p class="lead">咔嚓即付 便捷安全 全面服务 享受生活</p>
        <p><a href="#" class="btn btn-primary btn-large">SpeedPay &raquo;&raquo;</a></p>	
   </div> 
 </div> 
 </div>
<div class="container-fluid surroundmain">
	<form class="form-signin" action="adminloginlogin" method="post">
        <h2 class="form-signin-heading">管理员登录</h2>
        <input type="text" class="input-block-level" placeholder="管理员账号" name="account">
        <input type="password" class="input-block-level" placeholder="Password" name="password">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-large btn-primary" data-loading-text="Loading..." type="submit">登录</button>
      </form>
 </div>
	
	
	  <div class ="footer inline-list" align="center">
				<ul>
					<li>
						<p class="text-info">
						<a><small>Design and Build by Xi'an Jiaotong University. </small><br/>
						<small>版权所有：西安交通大学Fwin项目组&nbsp;&nbsp;&nbsp; Copyright©2013 All
							Rights reserved 
						</small></a>
						</p>
					</li>
				</ul>
	  </div>

		


	
	<script src="js/jquery.js"></script>
	 <script src="js/bootstrap.js"></script> 
	 <script src="js/bootstrap.min。js"></script>
    <script src="js/bootstrap-transition.js"></script>
    <script src="js/bootstrap-alert.js"></script>
    <script src="js/bootstrap-modal.js"></script>
    <script src="js/bootstrap-dropdown.js"></script>
    <script src="js/bootstrap-scrollspy.js"></script>
    <script src="js/bootstrap-tab.js"></script>
    <script src="js/bootstrap-tooltip.js"></script>
    <script src="js/bootstrap-popover.js"></script>
    <script src="js/bootstrap-button.js"></script>
    <script src="js/bootstrap-collapse.js"></script>
    <script src="js/bootstrap-carousel.js"></script>
    <script src="js/bootstrap-typeahead.js"></script>
</body>
</html>
