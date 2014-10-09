<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>咔嚓付</title>
    <meta name="msapplication-TileImage" content="//abs.twimg.com/favicons/win8-tile-144.png">
    <meta name="msapplication-TileColor" content="#00aced">
    <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" href="css/logged.css" type="text/css">
	<link rel="stylesheet" href="css/t1_more.bundle.css" type="text/css">
   
      </head>
  </head>
  
  <body  class="t1 logged-out asian zh-cn front-random-image-cricket front-page " dir="ltr">
   <div id="doc" class>
     <div class="topbar js-topbar">
          <div id="banners" class="js-banners">
          </div>
          <div class="global-nav" data-section-term="top_nav">
            <div class="global-nav-inner">
              <div class="container">
                <ul class="nav js-global-actions">
                  <li class="home" data-global-action="t1home">
                      <a class="nav-logo-link" href="/" data-nav="front">
                        <span class="icon bird-topbar-blue"><span class="visuallyhidden">Twitter</span></span>
                      </a>
                  </li>
                </ul>
        
                <div class="pull-right">
                  <ul class="nav secondary-nav language-dropdown">
                    <li class="dropdown js-language-dropdown">
                      <a class="dropdown-toggle" href="javascript:;">
                        <small>语言:</small>
                        <span class="js-current-language">简体中文</span>
                        <b class="caret"></b>
                      </a>
                      <ul class="dropdown-menu">
                        <li class="dropdown-caret right">
                          <span class="caret-outer"></span>
                          <span class="caret-inner"></span>
                        </li>
                      </ul> 
                    </li>          
                  </ul>
                </div> 
              </div>
            </div>
          </div>
        </div>
<div id="page-outer">
   <div id = "page-container" class = "wrapper-front white" >
     	<div class ="front-container front-container-full-sigup" id ="front-contain">  

	<div class="front-bg">
      <img class="front-image" src="img/citibank4.jpg">
    </div>
    <div class="front-card">
<!--       	<div class="front-welcome">   -->
            <div class="front-welcome-text">
              <h1>欢迎来到咔嚓付。</h1>
              <p>咔嚓即付 便捷安全 全面服务 享受生活</p>
            </div>
<!--    		</div>  -->
	
   <div class="front-signin js-front-signin">
          <form action="userlogin" class="signin" method="post">
            <div class="placeholding-input username">
              <input type="text" id="signin-email" class="text-input email-input" name="account" title="用户名或邮件地址" autocomplete="on" placeholder="注册手机">  
            </div>
		<table class="flex-table password-signin">
              <tbody>
              <tr>
                <td class="flex-table-primary">
                  <div class="placeholding-input password flex-table-form">
                    <input type="password" id="signin-password" class="text-input flex-table-input" name="password" title="密码" placeholder="密码">

                  </div>
                </td>
                <td class="flex-table-secondary">
                  <button type="submit" class="submit btn primary-btn flex-table-btn js-submit">
                    登录
                  </button>
                </td>
              </tr>
              </tbody>
            </table>
             </form>
       
            <div class="remember-forgot">
              <label class="remember">
               <s:actionerror/>
              </label>
              <span class="separator"></span>
            </div>
        </div>  
	<div class="front-signup js-front-signup">
          <h2><strong>新来SpeedPayer?</strong> 注册</h2>
            <button type="submit" class="btn signup-btn">
            <a href="userregister">注册SpeedPay</a>  
            </button>
          <form action="http://localhost:8080/SpeedPay/userregister" class="signup" method="post">
            <div class="placeholding-input">
              <input type="text" class="text-input" autocomplete="off" name="userPhoneNum" maxlength="20" placeholder="注册手机"> 
            </div>
            <div class="placeholding-input">
              <input type="text"  class="text-input email-input" autocomplete="off" placeholder="密码">  
            </div>
            <div class="placeholding-input">
              <input type="password" class="text-input"  placeholder="创建密码">   
            </div>
           </form>
         
        </div>			
	</div>
     <div class ="footer inline-list">
				<ul>
					<li>
						<a><small>Design and Build by Xi'an Jiaotong University. </small><br/>
						<small>版权所有：西安交通大学Fwin项目组&nbsp;&nbsp;&nbsp; Copyright©2013 All
							Rights reserved 
						</small></a>
					</li>
					<li><small><a href="admin">后台登陆</a></small></li>
				</ul>
			</div>
		
		</div>
	</div>
</div>
	
</div>

    
     
  </body>
</html>
