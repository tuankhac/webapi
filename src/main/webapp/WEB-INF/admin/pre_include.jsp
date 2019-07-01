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
			<div class="showChat_inner">
				<div class="media chat-inner-header">
					<a class="back_chatBox"> <i class="feather icon-x"></i>
						Josephin Doe
					</a>
				</div>
				<div class="main-friend-chat">
					<div class="media chat-messages">
						<a class="media-left photo-table" href="index.html#!"> <img
							class="media-object img-radius img-radius m-t-5"
							src="assets/files/assets/images/avatar-2.jpg"
							alt="Generic placeholder image">
						</a>
						<div class="media-body chat-menu-content">
							<div class="">
								<p class="chat-cont">I'm just looking around. Will you tell
									me something about yourself?</p>
							</div>
							<p class="chat-time">8:20 a.m.</p>
						</div>
					</div>
					<div class="media chat-messages">
						<div class="media-body chat-menu-reply">
							<div class="">
								<p class="chat-cont">Ohh! very nice</p>
							</div>
							<p class="chat-time">8:22 a.m.</p>
						</div>
					</div>
					<div class="media chat-messages">
						<a class="media-left photo-table" href="index.html#!"> <img
							class="media-object img-radius img-radius m-t-5"
							src="assets/files/assets/images/avatar-2.jpg"
							alt="Generic placeholder image">
						</a>
						<div class="media-body chat-menu-content">
							<div class="">
								<p class="chat-cont">can you come with me?</p>
							</div>
							<p class="chat-time">8:20 a.m.</p>
						</div>
					</div>
				</div>
				<div class="chat-reply-box">
					<div class="right-icon-control">
						<div class="input-group input-group-button">
							<input type="text" class="form-control"
								placeholder="Write hear . . ">
							<div class="input-group-append">
								<button class="btn btn-primary waves-effect waves-light"
									type="button">
									<i class="feather icon-message-circle"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ chat message ] end -->


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<!-- [ navigation menu ] start -->
					<jsp:include page="common/nav.jsp"></jsp:include>