<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册页面</title>
    
	 <meta charset="utf-8">
      <title>Twitter / 创建账号</title>
      <meta name="description" content="只需要一分钟即可注册 Twitter。随时了解你最关心的事物。关注你的朋友、专家、最爱的名人和突发新闻。"> 
      <link href="img/citi.jpg" rel="shortcut icon" type="image/x-icon">
      <link rel="stylesheet" href="./css/t1_core_logged_out.bundle.css" type="text/css">
	  <link rel="stylesheet" href="./css/t1_more.bundle.css" type="text/css">
      <link type="text/css" rel="stylesheet" charset="UTF-8" href="./css/translateelement.css">
      <script type="text/javascript" charset="UTF-8" src="js/main_zh-CN.js"></script></head>
	  <link href="css/scojs.css" type="text/css" rel="stylesheet">
	  <script src="js/jquery.js"></script>
	  <link href="./css/ti_sigup.bundle.css" rel="stylesheet" type="text/css">
	  <script type="text/javascript" src="js/messenger.min.js"></script>
	  <script type="text/javascript" src="js/messenger.js"></script>
	  <link href="css/messenger.css" rel="stylesheet" type="text/css">  
 	  <link href="css/messenger-theme-future.css"  rel="stylesheet" type="text/css">  
  </head>
  
  <body class="t1 logged-out ms-windows asian zh-cn phx-signup" dir="ltr">
  	<div id = "doc" class>
  		<div class="topbar js-topbar">
  			<div id="banners" class="js-banners">
          	</div>
          	<div class="global-nav" data-section-term="top_nav">
          		 <div class="global-nav-inner">
          		 	<div class="container">
          		 		<ul class="nav js-global-actions">
                 	 		<li class="home" data-global-action="t1home">
            			<a class="nav-logo-link" href="https://twitter.com/" data-nav="front">
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
                     		 <div class="js-front-language">
                        		<form action="https://twitter.com/sessions/change_locale" class="language" method="POST">
                         		 	<input type="hidden" name="lang">
                          			<input type="hidden" name="redirect">
                         			 <input type="hidden" name="authenticity_token" value="20595119f22239e6cd6bcd413447c39627cb5893">
                        		</form>
                      		</div>
                    		</li>
                 		 </ul>

							<ul class="nav secondary-nav session-dropdown" id="session">
								<li class="dropdown js-session"><a
									class="dropdown-toggle dropdown-signin" id="signin-link"
									href="#" data-nav="login"> <small>已有账号?</small> 登录<span
										class="caret"></span> </a> <a class="dropdown-signup"
									id="signup-link"
									href="https://twitter.com/signup?context=login"
									data-nav="signup"> <small>新来 Twitter?</small><span
										class="emphasize"> 现在就加入 &raquo;</span> </a>
									<ul class="dropdown-menu dropdown-form" id="signin-dropdown">
										<li class="dropdown-caret right"><span
											class="caret-outer"></span> <span class="caret-inner"></span>
										</li>
										<li>
											<form action="https://twitter.com/sessions"
												class="js-signin signin" method="post">
												<fieldset class="textbox">
													<label class="username js-username"> <span>用户名或邮件地址</span>
														<input
														class="js-username-field email-input js-initial-focus"
														type="text" name="session[username_or_email]"
														autocomplete="on"> </label> <label
														class="password js-password"> <span>密码</span> <input
														class="js-password-field" type="password" value=""
														name="session[password]"> </label>
												</fieldset>
												<fieldset class="subchck">
													<label class="remember"> <input type="checkbox"
														value="1" name="remember_me" checked="checked"> <span>记住我</span>
													</label>
													<button type="submit" class="btn submit">登录</button>
												</fieldset>

												<input type="hidden" name="scribe_log"> <input
													type="hidden" name="redirect_after_login" value="/signup">
												<input type="hidden"
													value="bef5a012e8e28addadb7ea3097fb223baaa11929"
													name="authenticity_token" />
												<div class="divider"></div>
												<p class="footer-links">

													<a class="forgot" href="/account/resend_password">忘记密码?</a><br />
													<a class="mobile has-sms" href="/account/complete">已经在通过短信使用
														Twitter?</a>
												</p>
											</form></li>
									</ul></li>
							</ul>


						</div>
          		 	</div>
          		 </div>
          	</div>
  		</div>
  		
 
  		
  	<div id="page-outer">
  		<div id="page-container" class="wrapper wrapper-signup white">
  	
  		
  		<div class="page-canvas" > 
  		<div class="signup-wrapper">
  			<legend><h1>
  				<font>
  					<font>
  					现在就加入SpeedPay
  					</font>
  				</font>
  			</h1></legend>
  			
  			
  			
  			
    		<s:actionerror />
    		<form action="userregisteradd" method="post" id="phx-signup-form" class="">
    			<fieldset class = "textbox">
    			
    			<div class="prompt name" >
    			 <div class="field-name">手机号码</div>
          			<div class="holding " data-fieldname="name">
    			  <input type="text" autocomplete="off"  name="userPhoneNum" maxlength="15" aria-required="true" class=""><br/> 
    			</div>
    			</div>
    			
    		 <div class="prompt password">
          	 <div class="field-name">创建密码</div>
             <div class="holding " data-fieldname="password">
            <input type="password" value="" name="password1" aria-required="true" />
          	</div>
            <div class="score"><span><b class="fill"></b></span></div>
        	</div>
        	
        	 <div class="prompt password">
    			 <div class="field-name">请再次输入密码</div>
    			   <div class="holding " data-fieldname="password">
    			<input type="password" name ="password2" aria-required="true" /><br/>
    			</div>
    		</div>
    			
    		<div class="prompt name">
          	<div class="field-name" >全名</div>
          	<div class="holding " data-fieldname="name">
            	<input type="text" autocomplete="off" value="" name="userName" maxlength="20" aria-required="true" />
          		</div>
        	</div>

			<div class="prompt name">
          	<div class="field-name">银行卡号</div>
          	<div class="holding " data-fieldname="name">
            	<input type="text" autocomplete="off" value="" name="userBankcardNum" maxlength="20" aria-required="true" />
          		</div>
        	</div>
        	
        	<div class="prompt name">
          	<div class="field-name">身份证号</div>
          	<div class="holding " data-fieldname="name">
            	<input type="text" autocomplete="off" value="" name="userIdnum" maxlength="20" aria-required="true" />
          		</div>
        	</div>
    		
    		<div class="prompt rememberme">
    		<div class="field-name">选择类型</div>
          		<label class="radio inline">
            	<input type="radio"  name="user.userType" value="0" />
            	<span>顾客</span>
            	</label>
            	<label class="radio inline">
            	<input type="radio" name="user.userType" value="1" />
            	<span>超市</span>
            	</label>
            	<label class="radio inline">
            	<input type="radio" name="user.userType" value="2" />
            	<span>商户</span>
          		</label>
       		 </div>
    			</fieldset>
    			<div class = "tos">
      				<div class = "scroller" >
      				 <p class="header">点击按钮意味着你同意下述条款:</p>
      				<div class="import"> 
  <h2>咔嚓付服务条款</h2>


<p>咔嚓付服务条款<br/>
1　用户应当同意本协议的条款并按照页面上的提示完成全部的注册程序。用户在进行注册程序过程中点击"同意"按钮即表示用户与咔嚓付公司达成协议，完全接受本协议项下的全部条款。<br/>
2　用户注册成功后，咔嚓付将给予每个用户一个用户帐号及相应的密码，该用户帐号和密码由用户负责保管；用户应当对以其用户帐号进行的所有活动和事件负法律责任。<br/>
3　用户可以使用咔嚓付各个频道单项服务，当用户使用咔嚓付各单项服务时，用户的使用行为视为其对该单项服务的服务条款以及咔嚓付在该单项服务中发出的各类公告的同意。<br/>
4　咔嚓付会员服务协议以及各个频道单项服务条款和公告可由咔嚓付公司随时更新，且无需另行通知。您在使用相关服务时,应关注并遵守其所适用的相关条款。<br/>
您在使用咔嚓付提供的各项服务之前，应仔细阅读本服务协议。如您不同意本服务协议及/或随时对其的修改，您可以主动取消咔嚓付提供的服务；您一旦使用咔嚓付服务，即视为您已了解并完全同意本服务协议各项内容，包括咔嚓付对服务协议随时所做的任何修改，并成为咔嚓付用户。<br/>
</p>
      				 
      				 </div>
      				 
      				 
      				 
      				 
      				</div>
      			</div>
    			
    			<fieldset class="doit">
        			<div class="sign-up-box"><input class="submit button promotional" type="submit" onclick="show_confirm()" value="创建我的账号" id="button" /></div>
    				<div class="befound"><p> 注: 其他用户能根据用户名或手机号找到你。你可以随时更改隐私设置。</p>
        		</div>
      			</fieldset>
      			
    		</form>
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
    </div>
    </div>
 <script type="text/javascript">
function show_confirm()
{
var r=confirm("你是否确认注册");
if (r==true)
  {
  alert("注册成功");
  }
else
  {
  alert("注册失败");
  }
}
</script>



  </body>
</html>
