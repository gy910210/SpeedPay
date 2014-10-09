<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员管理界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/db.css" rel="stylesheet" media="screen">
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
              <li class="active"><a><strong><i class="icon-ok-circle icon-white"></i>用户审核</strong></a></li>    
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
					银行：你好二维码
				</h1>
				<p>
					银行管理人员可点击审核使用户通过审核
				</p>
				<p>
					 <a class="btn btn-primary btn-large" href="index">SpeedPay»</a>
				</p>
			</div>
		</div>
		<div class="container-fluid surroundmain">
			<div class="page-header">
				<h1>
					<small>管理员审核</small>
				</h1>
			</div>
			<table class="table table-striped">
				<thead>
					<tr >
						<th>
							编号
						</th>
						<th>
							姓名
						</th>
						<th>
							手机号
						</th>
						<th>
							身份证号
						</th>
						<th>
							用户类型
						</th>
						<th>
							用户信用
						</th>
						<th>
							审核状态
						</th>
					</tr>
				</thead>
				<c:forEach var="s" items="${userlist}" begin="${pd.begin}" end="${pd.end}">
				<tbody>
					<tr>
						<td>
							${s.userId}
						</td>
						<td>
							${s.userName}
						</td>
						<td>
							${s.userPhoneNum}
						</td>
						<td>
							${s.userIdnum}
						</td>
						<td>
						<c:choose>
							<c:when test="${s.userType == 0}">
								顾客
							</c:when>
							<c:when test="${s.userType == 1}">
								超市
							</c:when>
							<c:otherwise>
								商户
							</c:otherwise>
						</c:choose>
						</td>
						<td>
							${s.userLevel}
						</td>
						<td>
							<span>
							<c:choose>
							<c:when test="${s.userIsChecked == 0}">
								<a href="admincheck?userId=${s.userId}">未审核</a>
							</c:when>
							<c:otherwise>
								<a href="admincheck?userId=${s.userId}">已审核</a>
							</c:otherwise>
							</c:choose>
							</span>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table> 
			<div class="pagination ">
              <c:if test="${pd.page_sum!=0}">
              <ul>
              
              	<c:if test="${pd.pn!=1} ">				
                	<li><a href="adminlogin?pageNum=${pd.pn-1 }">上一页</a></li>
				</c:if>
				
				<c:forEach var="a" items="${pd.aboveList }">
					<li><a href="adminlogin?pageNum=${a }">${a }</a></li>
				</c:forEach>
				
                <li  class="active"><a href="adminlogin?pageNum=${pd.pn }">${pd.pn }</a></li>
                
                <c:forEach var="b" items="${pd.belowList }">
					<li><a href="adminlogin?pageNum=${b }">${b }</a></li>
				</c:forEach>
				
				<c:if test="${pd.pn!=pd.page_sum }">
					<li><a href="adminlogin?pageNum=${pd.pn+1 }">下一页</a></li>
				</c:if>
				
              </ul>
              </c:if>
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
						<a><small>Design and Build by Xi'an Jiaotong University. </small><br/>
						<small>版权所有：西安交通大学Fwin项目组&nbsp;&nbsp;&nbsp; Copyright©2013 All
							Rights reserved 
						</small></a>
						</p>
					</li>
				</ul>
	  </div>
		</div>
	</div>

   
  </body>
</html>
