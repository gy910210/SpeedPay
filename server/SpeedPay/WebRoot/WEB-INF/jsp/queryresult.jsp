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
    
    <title>查询结果</title>
     <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<script src="js/bootstrap.js"></script>
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
					查询结果
				</h1>
				<p>
					查询出你需要的结果
				</p>
<!-- 				<p> -->
<!-- 					 <a class="btn btn-primary btn-large" href="#">SPEEDPay»</a> -->
<!-- 				</p> -->
			</div>
		</div>
		
		<div class="container-fluid surroundmain">
			<div class="page-header">
				<h1><font color="#999999"><span style="font-size: 25px; font-weight: normal; line-height: 24px;">查询结果</span></font></h1>
			</div>
		<table class="table">
					<tbody>
				
				<c:choose>	
		<c:when test="${querytype == 0}">
				<thead>
					<tr>
						<th>
							<i class= "icon-th-large"></i>
						</th>
						<th>
							<i class= "icon-briefcase"></i>消费支出
						</th>
						<th>
							<i class= "icon-calendar"></i>消费时间
						</th>
						<th>
							<i class= "icon-shopping-cart"></i>消费地点
						</th>
					</tr>
				</thead>
			</c:when>
			<c:when test="${querytype == 1}">
				<thead>
					<tr>
						<th>
							<i class= "icon-th-large"></i>
						</th>
						<th>
							<i class= "icon-briefcase"></i>支出
						</th>
						<th>
							<i class= "icon-calendar"></i>时间
						</th>
						<th>
							<i class= "icon-shopping-cart"></i>消费地点
						</th>
						<th>
							<i class= "icon-tasks"></i>原因
						</th>
					</tr>
				</thead>
			</c:when>
				<c:when test="${querytype == 2}">
				<thead>
						<tr>
						<th>
						<i class= "  icon-th-large"></i>
						</th>
						<th>
							<i class= "icon-headphones"></i>转账人
						</th>
						<th>
							<i class= " icon-user"></i>收款人
						</th>
						<th>
							<i class= "icon-briefcase"></i>转账金额
						</th>
						<th>
							<i class= "icon-calendar"></i>转账时间
						</th>
						<th>
							<i class= "icon-tasks"></i>原因
						</th>
					</tr>
				</thead>
			</c:when>
				<c:when test="${querytype == 3}">
				<thead>
						<tr>
						<th>
							<i class= "  icon-th-large"></i>
						</th>
						<th>
							<i class= "icon-briefcase"></i>取款金额
						</th>
						<th>
							<i class= "icon-calendar"></i>取款时间
						</th>
						<th>
							<i class= "icon-check"></i>ATMID
						</th>
					</tr>
				</thead>
			</c:when>
				<c:otherwise>
				<thead>
					<tr>
						<th>
							<i class= "  icon-th-large"></i>
						</th>
						<th>
							<i class= "icon-headphones"></i>借款人
						</th>
						<th>
							<i class= " icon-user"></i>还款人
						</th>
						<th>
							<i class= "icon-briefcase"></i>借款金额
						</th>
						<th>
							<i class= "icon-refresh"></i>状态
						</th>
						<th>
							<i class= "icon-calendar"></i>借款时间
						</th>
						<th>
							<i class= " icon-edit"></i>还款时间
						</th>
					</tr>
					</thead>
							</c:otherwise>
							</c:choose>				
				<c:forEach var="s" items="${purchasecontentList}" begin="${pd.begin}" end="${pd.end}" >
				<tr>
						<td>
							<i class= " icon-user"></i>
						</td>
						<td>
							￥${s.purchaseContentConsumptionSum}元
						</td>
						<td>
							<fmt:formatDate pattern="yyyy/MM/dd" value="${s.purchaseContentConsumptionTime}"></fmt:formatDate>
						</td>
						<td>
							${s.userByPurchaseContentMarketId.userName}
						</td>
						
				</tr>
					</c:forEach>
				
				<c:forEach var="c" items="${consumptionproofList}" begin="${pd.begin}" end="${pd.end}">
					
					<tr class="success">
						<td>
							<i class= " icon-user"></i>
						</td>
						<td>
							${c.consumptionProofSum}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy/MM/dd" value="${c.consumptionProofTime}"></fmt:formatDate>
						</td>
						<td>
							<span>${c.userByConsumptionProofShopId.userName}</span>
						</td>
						<td>
						${c.consumptionProofCause}
						</td>
					</tr>	
				</c:forEach>
		<c:forEach var="t" items="${borrowproofList}" begin="${pd.begin}" end="${pd.end}">	
					<tr class="error">
						<td>
							<i class= " icon-user"></i>
						</td>
						<td>
							${t.userByBorrowProofBorrowUserId.userName}
						</td>
						<td>
							${t.userByBorrowProofRepayUserId.userName}
						</td>
						<td>
							${t.borrowProofSum}
						</td>
						<td>
							<span><c:choose>
							<c:when test="${t.borrowProofIsRepayed == 0}">
								未清还款
							</c:when>
							<c:otherwise>
								已还
							</c:otherwise>
							</c:choose></span>
						</td>
						<td><fmt:formatDate pattern="yyyy/MM/dd" value="${t.borrowProofBorrowTime}"></fmt:formatDate></td>
   						<td><fmt:formatDate pattern="yyyy/MM/dd" value="${t.borrowProofRepayTime}"></fmt:formatDate></td>
					</tr>
					
			</c:forEach>
			
				<c:forEach var="r" items="${transferproofList}" begin="${pd.begin}" end="${pd.end}">
					<tr class="warning">
						<td>
							<i class= " icon-user"></i>
						</td>
						<td>${r.userByTransferProofTransferUserId.userName}</td>
   						<td>${r.userByTransferProofReceiverUserId.userName}</td>
						<td>
							<span>${r.transferProofSum}</span>
						</td>
						<td>
							<span><fmt:formatDate pattern="yyyy/MM/dd" value="${r.transferProofTime}"></fmt:formatDate></span>
						</td>
						<td>
							<span>${r.transferProofCause}</span>
						</td>
					</tr>
			</c:forEach>
			 <c:forEach var="w" items="${withdrawproofList}" begin="${pd.begin}" end="${pd.end}">
					<thead>
					<tr>	
					</tr>
				</thead>
					<tr class="info">
						<td>
							<i class= " icon-user"></i>
						</td>
						<td>
							${w.withdrawProofSum}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy/MM/dd" value="${w.withdrawProofTime}"></fmt:formatDate>
						</td>
						<td>
							<span>${w.withdrawProofAtmId}</span>
						</td>
						<td>
						</td>
					</tr>
			</c:forEach>
			</tbody>
			</table>
		<div class="pagination ">
              <c:if test="${pd.page_sum!=0}">
              <ul>
              
              	<c:if test="${pd.pn!=1} ">				
                	<li><a href="userquerytype?pageNum=${pd.pn-1}">上一页</a></li>
				</c:if>
				
				<c:forEach var="a" items="${pd.aboveList }">
					<li><a href="userquerytype?pageNum=${a}">${a}</a></li>
				</c:forEach>
				
                <li  class="active"><a href="userquerytype?pageNum=${pd.pn }">${pd.pn }</a></li>
                
                <c:forEach var="b" items="${pd.belowList }">
					<li><a href="userquerytype?pageNum=${b }">${b }</a></li>
				</c:forEach>
				
				<c:if test="${pd.pn!=pd.page_sum }">
					<li><a href="userquerytype?pageNum=${pd.pn+1}">下一页</a></li>
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
  </body>
</html>
