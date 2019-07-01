<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<c:if test="${!empty sessionScope.userBO}">
	<c:redirect url="/${ctxAdmin }index.html" />
</c:if>
<head>
<title><s:message code="label.login_title" /></title>
<base href="${pageContext.servletContext.contextPath }/">

<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description"
	content="Admindek Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
<meta name="keywords"
	content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
<meta name="author" content="colorlib" />
<!-- Favicon icon -->

<link rel="icon"
	href="../colorlib.com/polygon/admindek/files/assets/images/favicon.ico"
	type="image/x-icon">
<!-- Google font-->
<link
	href="assets/files/fonts.lug.ustc.edu.cn/css-family=Open+Sans-300,400,600,700,800.htm"
	rel="stylesheet">
<link
	href="assets/files/fonts.lug.ustc.edu.cn/css-family=Quicksand-500,700.htm"
	rel="stylesheet">
<!-- Required Fremwork -->
<link rel="stylesheet" type="text/css"
	href="assets/files/bower_components/bootstrap/css/bootstrap.min.css">
<!-- waves.css -->
<link rel="stylesheet"
	href="assets/files/assets/pages/waves/css/waves.min.css"
	type="text/css" media="all">
<!-- feather icon -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/icon/feather/css/feather.css">
<!-- themify-icons line icon -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/icon/themify-icons/themify-icons.css">
<!-- ico font -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/icon/icofont/css/icofont.css">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/icon/font-awesome/css/font-awesome.min.css">
<!-- Style.css -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/css/style.css">
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/css/pages.css">
</head>
<%
	boolean autoLogin = false;
	Cookie[] cookies = request.getCookies();
	String userName = "", password = "", rememberVal = "";
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userName = cookie.getValue();
			}
			if (cookie.getName().equals("userPass")) {
				password = cookie.getValue();
			}
			if (cookie.getName().equals("userRemember")) {
				rememberVal = cookie.getValue();
			}
			//out.print(cookie.getName()+"|");
		}
		//gui du lieu toi server de dang nhap luon
		//out.print(userName + "|" + password + "|" + rememberVal);
	}
	if (!userName.equals("") && !password.equals("") && !rememberVal.equals("")) {
		autoLogin = true;
	}
%>
<body themebg-pattern="theme1">
	<!-- Pre-loader start -->
	<div class="theme-loader">
		<div class="loader-track">
			<div class="preloader-wrapper">
				<div class="spinner-layer spinner-blue">
					<div class="circle-clipper left">
						<div class="circle"></div>
					</div>
					<div class="gap-patch">
						<div class="circle"></div>
					</div>
					<div class="circle-clipper right">
						<div class="circle"></div>
					</div>
				</div>
				<div class="spinner-layer spinner-red">
					<div class="circle-clipper left">
						<div class="circle"></div>
					</div>
					<div class="gap-patch">
						<div class="circle"></div>
					</div>
					<div class="circle-clipper right">
						<div class="circle"></div>
					</div>
				</div>

				<div class="spinner-layer spinner-yellow">
					<div class="circle-clipper left">
						<div class="circle"></div>
					</div>
					<div class="gap-patch">
						<div class="circle"></div>
					</div>
					<div class="circle-clipper right">
						<div class="circle"></div>
					</div>
				</div>

				<div class="spinner-layer spinner-green">
					<div class="circle-clipper left">
						<div class="circle"></div>
					</div>
					<div class="gap-patch">
						<div class="circle"></div>
					</div>
					<div class="circle-clipper right">
						<div class="circle"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Pre-loader end -->
	<section class="login-block"> <!-- Container-fluid starts -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<!-- Authentication card start -->
				<form:form class="md-float-material form-material"
					action="neo/login.html" method="post" commandName="loginForm"
					autocomplete="off">
					<div class="text-center">
						<!-- <img src="assets/files/assets/images/logo.png" alt="logo.png"> -->
					</div>
					<div class="auth-box card">
						<div class="card-block">
							<div class="row m-b-20">
								<div class="col-md-12">
									<h3 class="text-center txt-primary">
										<s:message code="label.signin" />
									</h3>
								</div>
							</div>
							<div class="row m-b-20">
								<div class="col-md-6">
									<button class="btn btn-facebook m-b-20 btn-block">
										<i class="icofont icofont-social-facebook"></i>
										<s:message code="label.facebook" />
									</button>
								</div>
								<div class="col-md-6">
									<button class="btn btn-twitter m-b-20 btn-block">
										<i class="icofont icofont-social-twitter"></i>
										<s:message code="label.twitter" />
									</button>
								</div>
							</div>
							<p class="text-muted text-center p-b-5">
								<s:message code="label.signinwithyouraccount" />
							</p>
							<div class="form-group form-primary">
								<input type="text" name="username" class="form-control"
									required="true" autocomplete="off" value="<%=userName%>" /> <span
									class="form-bar"></span> <label class="float-label"><s:message
										code="label.username" /></label>
							</div>
							<div class="form-group form-primary">
								<input type="password" name="password" autocomplete="off"
									class="form-control" required="true" value="<%=password%>" />
								<span class="form-bar"></span> <label class="float-label"><s:message
										code="label.password" /></label>
							</div>
							<font color="red"> <form:errors path="reasonFail" /></font>
							<div class="row m-t-25 text-left">
								<div class="col-12">
									<div class="checkbox-fade fade-in-primary">
										<label> <input type="checkbox" name="rememberme"
											<%if (!rememberVal.equals("")) {%> checked <%}%> /> <span
											class="cr"><i
												class="cr-icon icofont icofont-ui-check txt-primary"></i></span> <span
											class="text-inverse"><s:message
													code="label.rememberme" /></span>
										</label>
									</div>
									<div class="forgot-phone text-right float-right">
										<a href="auth-reset-password.html" class="text-right f-w-600">
											<s:message code="label.forgotpass" />
										</a>
									</div>
								</div>
							</div>
							<div class="row m-t-30">
								<div class="col-md-12">
									<button type="submit"
										class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20">
										<s:message code="label.login" />
									</button>

								</div>
							</div>
							<p class="text-inverse text-left">
								<s:message code="label.donthaveaccount" />
								<a href="auth-sign-up-social.html"> <b><s:message
											code="label.register" /></b></a>
							</p>
							<div class="dropdown" style="float: right;">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown">
									<s:message code="label.language" />
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="vi/neo/login.html"><s:message
											code="label.vietnamese" /></a> <a class="dropdown-item"
										href="en/neo/login.html"><s:message code="label.english" /></a>
									<a class="dropdown-item" href="fr/neo/login.html"><s:message
											code="label.france" /></a>
								</div>
							</div>
						</div>
					</div>
				</form:form>
				<!-- end of form -->
			</div>
			<!-- Authentication card end -->
		</div>
		<!-- end of col-sm-12 -->
	</div>
	<!-- end of row -->
	</div>
	</section>
	<!-- Warning Section Ends -->
	<!-- Required Jquery -->
	<script type="text/javascript"
		src="assets/files/bower_components/jquery/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="assets/files/bower_components/jquery-ui/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="assets/files/bower_components/popper.js/js/popper.min.js"></script>
	<script type="text/javascript"
		src="assets/files/bower_components/bootstrap/js/bootstrap.min.js"></script>
	<!-- waves js -->
	<script src="assets/files/assets/pages/waves/js/waves.min.js"></script>
	<!-- jquery slimscroll js -->
	<script type="text/javascript"
		src="assets/files/bower_components/jquery-slimscroll/js/jquery.slimscroll.js"></script>
	<!-- modernizr js -->
	<script type="text/javascript"
		src="assets/files/bower_components/modernizr/js/modernizr.js"></script>
	<script type="text/javascript"
		src="assets/files/bower_components/modernizr/js/css-scrollbars.js"></script>
	<script type="text/javascript"
		src="assets/files/assets/js/common-pages.js"></script>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="assets/files/gtag/js-id=UA-23581568-13.htm"></script>
	<script>
		window.onload = function(){
			var autoLogin =	"<%=autoLogin%>";
			if(autoLogin === "true"){
		  		document.forms['loginForm'].submit();
			}else{
				$("input").attr("autocomplete","off");
			}
		}
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-23581568-13');
	</script>
</body>

</html>
