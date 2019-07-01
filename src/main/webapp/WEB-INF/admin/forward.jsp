<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">

<jsp:include page="common/init.jsp"></jsp:include>

<body>
	<!--. [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-bar"></div>
	</div>
	<!-- [ Pre-loader ] end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<!-- [ Header ] start -->
			<jsp:include page="common/header.jsp"></jsp:include>
			<!--[ Header ] end -->

			<!-- [ chat user list ] start -->
			<jsp:include page="extension/chat_user_list.jsp"></jsp:include>
			<!-- [ chat user list ] end -->
			
			<!-- [ chat message ] start -->
			<jsp:include page="extension/message.jsp"></jsp:include>
			<!-- [ chat message ] end -->

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<!-- [ navigation menu ] start -->
					<jsp:include page="common/nav.jsp"></jsp:include>
					<!-- [ navigation menu ] end -->
					<jsp:include page="content.jsp"></jsp:include>
					<!-- [ style Customizer ] start -->
					<div id="styleSelector"></div>
					<!-- [ style Customizer ] end -->
				</div>
			</div>
		</div>
	</div>
	<!-- Warning Section Starts -->
	<!-- Older IE warning message -->
	<!--[if lt IE 10]>
    <div class="ie-warning">
        <h1>Warning!!</h1>
        <p>You are using an outdated version of Internet Explorer, please upgrade
            <br/>to any of the following web browsers to access this website.
        </p>
        <div class="iew-container">
            <ul class="iew-download">
                <li>
                    <a href="javascript:if(confirm('http://www.google.com/chrome/\n\nThis file was not retrieved because it was filtered out by your project settings.\n\nWould you like to open it from the server?'))window.location='http://www.google.com/chrome/'">
                        <img src="files/assets/images/browser/chrome.png" alt="Chrome">
                        <div>Chrome</div>
                    </a>
                </li>
                <li>
                    <a href="javascript:if(confirm('https://www.mozilla.org/en-US/firefox/new/\n\nThis file was not retrieved because it was filtered out by your project settings.\n\nWould you like to open it from the server?'))window.location='https://www.mozilla.org/en-US/firefox/new/'">
                        <img src="files/assets/images/browser/firefox.png" alt="Firefox">
                        <div>Firefox</div>
                    </a>
                </li>
                <li>
                    <a href="javascript:if(confirm('http://www.opera.com\n\nThis file was not retrieved because it was filtered out by your project settings.\n\nWould you like to open it from the server?'))window.location='http://www.opera.com'">
                        <img src="files/assets/images/browser/opera.png" alt="Opera">
                        <div>Opera</div>
                    </a>
                </li>
                <li>
                    <a href="javascript:if(confirm('https://www.apple.com/safari/\n\nThis file was not retrieved because it was filtered out by your project settings.\n\nWould you like to open it from the server?'))window.location='https://www.apple.com/safari/'">
                        <img src="files/assets/images/browser/safari.png" alt="Safari">
                        <div>Safari</div>
                    </a>
                </li>
                <li>
                    <a href="javascript:if(confirm('http://windows.microsoft.com/en-us/internet-explorer/download-ie\n\nThis file was not retrieved because it was filtered out by your project settings.\n\nWould you like to open it from the server?'))window.location='http://windows.microsoft.com/en-us/internet-explorer/download-ie'">
                        <img src="files/assets/images/browser/ie.png" alt="">
                        <div>IE (9 & above)</div>
                    </a>
                </li>
            </ul>
        </div>
        <p>Sorry for the inconvenience!</p>
    </div>
<![endif]-->
	<!-- Warning Section Ends -->
	<!-- Required Jquery -->
	<%-- <script data-cfasync="false"
		src="cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script> --%>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>

</html>
