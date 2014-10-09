<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/db.css" rel="stylesheet" media="screen">
<link href="css/scojs.css" type="text/css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
 <script type="text/javascript" src="js/messenger.min.js"></script>
	  <script type="text/javascript" src="js/messenger.js"></script>
	  <link href="css/messenger.css" rel="stylesheet" type="text/css">  
 	  <link href="css/messenger-theme-future.css"  rel="stylesheet" type="text/css"> 
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
              <li><a href="index"><i class="icon-home"></i><strong>首页</strong></a></li>
              <li class="active"><a href="usercenter"><i class="icon-user"></i><strong>个人中心</strong></a></li>
              <li><a href="userquery"><i class=" icon-search"></i><strong>用户查询</strong></a></li>    
            </ul>  
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
		
	<div class="row-fluid">
		<div class="span12">
		<div class="jumbotron-cy">
			<div class="hero-unit">
				<h1>
					个人中心
				</h1>
				<p>
					查看个人信息、修改密码、绑定银行卡、修改支付密码！！！
				</p>
				<p>
					<p><a class="btn btn-primary btn-large" href="file/SpeedPay.docx"><i class=" icon-download-alt icon-white"></i> SpeedPay下载»</a>
				</p>

			</div>
		</div>
		
		<div class="container-fluid surroundmain">
			<div class="page-header">
				<h1>
					<span>个人信息</span>
				</h1>
			</div>


			<!-- Button to trigger modal -->
		<a data-target="#myModal" role="button" class="btn btn-primary" data-toggle="modal"><i class="icon-pencil icon-white"></i>修改密码</a>
 
			<!-- Modal -->
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 			 <div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   				 <h3 id="myModalLabel">修改密码</h3>
  			</div>
 		 	<div class="modal-body">
    			<form  action="usercenterpassworded" id="valid_form" method="post" >
<!--     			<form action="valid.json" > -->
  				<label><div>请输入初始密码</div> <input type="password"  name="oldpd"></label>
  				<label><div>请输入新密码</div> <input type="password" name="newpd1"></label>
 				 <label><div>请再次输入新密码</div> <input type="password" name="newpd2"></label>
 				<button type="submit" class="btn btn-primary" onclick="show_conpass()">保存设置</button>		
  			 </form>
  			  </div>
		</div>
		
				<!-- Button to trigger modal -->
		<a data-target="#myModal1" role="button" class="btn btn-primary" data-toggle="modal"><i class="icon-wrench icon-white"></i>绑定银行卡</a>
 
			<!-- Modal -->
		<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 			 <div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   				 <h3 id="myModalLabel">绑定银行卡</h3>
  			</div>
 		 <div class="modal-body">
    		<form action="usercenterinfoed">
  	   			<label><div>请输入绑定的银行卡</div><input type="text" name="banknum1"></label>
 	   			<label><div> 请再次输入：</div><input type="text" name="banknum2"></label>
     			<button class="btn btn-primary" onclick="show_con()">绑定银行卡</button>
   			</form>
  		</div>
		</div>
		<a data-target="#myModal2" role="button" class="btn btn-primary" data-toggle="modal"><i class="icon-ok icon-white"></i>修改支付密码</a>
			<div id="myModal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 			 <div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   				 <h3 id="myModalLabel">修改支付密码</h3>
  			</div>
 		 <div class="modal-body">
    		<form action="usercenterpaypsd" method="post">
  	   			<label><div>请输入支付初始密码</div> <input type="password"  name="oldpaypd" placeholder="初始密码为111111"><p class="text-info"><small></small></p></label>
  				<label><div>请输入新支付密码</div> <input type="password" name="newpaypd1"></label>
 				 <label><div>请再次输入新支付密码</div> <input type="password" name="newpaypd2"></label>
     			<button class="btn btn-primary" onclick="show_conpass()">保存设置</button>
   			</form>
  		</div>
		</div>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>
							用户姓名
						</td>
						<td>
							${user.userName}
						</td>
					</tr>
					<tr>
						<td>
							注册手机
						</td>
						<td>
							${user.userPhoneNum}
						</td>
					</tr>
					<tr class="success">
						<td>
							注册身份证
						</td>
						<td>
							${user.userIdnum}
						</td>
					</tr>
					<tr class="error">
						<td>
							用户类型
						</td>
						<td>
							${type}
						</td>
					</tr>
					<tr class="warning">
						<td>
							审核状态
						</td>
						<td>
							${check}
						</td>
					</tr>
					<tr class="info">
						<td>
							用户信用等级
						</td>
						<td>
							${user.userLevel}
						</td>
					</tr>
					<tr>
						<td>
							绑定银行卡号
						</td>
						<td>
							${banknum}
						</td>
					</tr>
				</tbody>
			</table>
			<div class="alert alert-info">
				 <button type="button" class="close" data-dismiss="alert">×</button>
				<h4>
					提示!
				</h4> <strong>警告!</strong> 请注意你的个人隐私安全.
			</div>
		</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			 <div class ="footer inline-list" align="center">
				<ul>
					<li>
						<p class="text-info">
						<small>Design and Build by Xi'an Jiaotong University. </small><br/>
						<small>版权所有：西安交通大学Fwin项目组&nbsp;&nbsp;&nbsp; Copyright © 2013 All
							Rights reserved 
						</small>
						</p>
					</li>
				</ul>
	  </div>
		</div>
	</div>
<script type="text/javascript">
function show_conpass(){
$._messengerDefaults = {
	extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom'
	};
var r=confirm("你是否确认修改");
if (r==true)
{
   var s = confirm("修改成功");
  $.globalMessenger().post("修改成功");
  }
else
  {
  alert("修改失败失败");
  }
}
</script>

<script type="text/javascript">
function show_con(){
$._messengerDefaults = {
	extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom'
	};
var r=confirm("你是否确认绑定");
if (r==true)
{
   var s = confirm("绑定成功");
  $.globalMessenger().post("绑定成功");
  }
else
  {
  alert("绑定失败");
  }
}
</script>
  </body>
</html>
