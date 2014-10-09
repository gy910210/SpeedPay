<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询界面</title>
      <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
	<link href="glDatePicker-2.0/styles/glDatePicker.darkneon.css" rel="stylesheet" type="text/css">
	<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.js"></script>
	<script src="glDatePicker-2.0/glDatePicker.min.js" type="text/javascript"></script>
	<script src="glDatePicker-2.0/glDatePicker.js" type="text/javascript"></script>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="css/db.css" rel="stylesheet" media="screen">
	<link href="css/scojs.css" type="text/css" rel="stylesheet">
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
              <li ><a href="index"><i class="icon-home"></i><strong>首页</strong></a></li>
              <li><a href="usercenter"><i class="icon-user"></i><strong>个人中心</strong></a></li>
              <li class="active"><a href="userquery"><i class=" icon-search"></i><strong>用户查询</strong></a></li>              
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
					用户查询
				</h1>
				<p>
					可以查询账户的使用情况
				</p>
<!-- 				<p> -->
<!-- 					 <a class="btn btn-primary btn-large" href="#">SPEEDPay»</a> -->
<!-- 				</p> -->
			</div>
		</div>
	<div class="container-fluid surroundmain">
			<div class="page-header">
				<h1><font color="#999999"><span style="font-size: 25px; font-weight: normal; line-height: 24px;">用户查询</span></font></h1>
			</div>
			<s:actionerror />
<form class="form-horizontal" action="userquerytype" method="post" >
    <fieldset>
    <div class="control-group">
          <!-- Text input-->
          <label class="control-label" for="input01" >起始时间</label>
          <div class="controls">
            <input type="text" placeholder="2013-8-15" class="input-xlarge" id="startdate" name="startdate">
            <p class="help-block">请选择时间</p>
          </div>
        </div>

    <div class="control-group">
          <!-- Text input-->
          <label class="control-label" for="input01">截止时间</label>
          <div class="controls">
            <input type="text" placeholder="2013-8-16" class="input-xlarge" id="enddate" name="enddate">
            <p class="help-block">请选择时间</p>
          </div>
        </div>


    <div class="control-group">

          <!-- Select Basic -->
          <label class="control-label">查询类型</label>
          <div class="controls">
            <select class="input-xlarge" name="querytype">
    			<option value="0">超市消费</option>
				<option value="1">商户消费</option>
				<option value="2">转账凭证</option>
				<option value="3">取款查询</option>
				<option value="4">借款凭据</option></select>
          </div>

        </div>

    <div class="control-group">
          <label class="control-label"></label>

          <!-- Button -->
          <div class="controls">
            <button class="btn btn-info" >提交</button>
            </div>
         </div>  
    </fieldset>
  </form>

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
    <table  id="gradient-style">
    <thead>
      
    </thead>
   	
<!--    		<tr> -->
<!--    			<td>交易序号</td> -->
<!--    			<td>交易金额</td> -->
<!--    			<td>交易时间</td> -->
<!--    			<td>消费地点</td> -->
<!--    		</tr> -->
   	</table>
  <script type="text/javascript">  
   $(window).load(function()
        {
        	
            $('#startdate').glDatePicker(
            );
            $('#enddate').glDatePicker(
            );
			format : 'yyyy-mm-dd';
        });
    </script>
  </body>
</html>
