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
<c:if test="${empty sessionScope.userBO}">
	<c:redirect url="/neo/login.html" />
</c:if>
<head>

<meta http-equiv="refresh"
	content="<%=session.getMaxInactiveInterval()%>">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description"
	content="Admindek Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
<meta name="keywords"
	content="flat ui, admin Admin , Responsive, Landing, Bootstrap, App, Template, Mobile, iOS, Android, apple, creative app">
<meta name="author" content="colorlib" />
<base href="${pageContext.servletContext.contextPath }/">
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
<!-- <link rel="stylesheet"
	href="assets/files/assets/pages/waves/css/waves.min.css"
	type="text/css" media="all"> -->
<!-- feather icon -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/icon/feather/css/feather.css">
<!-- font-awesome-n -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/css/font-awesome-n.min.css">
<!-- Chartlist chart css -->
<!-- <link rel="stylesheet"
	href="assets/files/bower_components/chartist/css/chartist.css"
	type="text/css" media="all"> -->
<!-- Style.css -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/css/style.css">
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/css/widget.css">
</head>