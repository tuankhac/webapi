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
<nav class="navbar header-navbar pcoded-header">
<div class="navbar-wrapper">
	<div class="navbar-logo">
		<a href='<c:out value="${ctxAdmin}" />index.html'> <img
			class="img-fluid" src="assets/files/assets/images/logo.png"
			alt="Theme-Logo" />
		</a> <a class="mobile-menu" style="position: relative; !important"
			id="mobile-collapse" href='<c:out value="${ctxAdmin}" />index.html#!'>
			<i class="feather icon-menu icon-toggle-right"></i>
		</a> <a class="mobile-options waves-effect waves-light"> <i
			class="feather icon-more-horizontal"></i>
		</a>
	</div>
	<div class="navbar-container container-fluid">
		<ul class="nav-left">
			<li class="header-search">
				<div class="main-search morphsearch-search">
					<div class="input-group">
						<span class="input-group-prepend search-close"> <i
							class="feather icon-x input-group-text"></i>
						</span> <input type="text" class="form-control"
							placeholder="Enter Keyword"> <span
							class="input-group-append search-btn"> <i
							class="feather icon-search input-group-text"></i>
						</span>
					</div>
				</div>
			</li>
			<li style="margin-top: 25px"><a
				href="<%=request.getAttribute("javax.servlet.forward.request_uri")%>#"
				onclick="javascript:toggleFullScreen()"
				class="waves-effect waves-light"> <i
					class="full-screen feather icon-maximize"></i>
			</a></li>
		</ul>
		<ul class="nav-right">
			<li class="user-profile header-notification">

				<div class="dropdown-primary dropdown">
					<div class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-language fa-2x"></i>&nbsp;<span><s:message
								code="label.language" /></span> <i class="feather icon-chevron-down"></i>
					</div>
					<ul class="show-notification profile-notification dropdown-menu"
						data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
						<li><a href='vi/<c:out value="${ctxAdmin}" />index.html'>
								<i class="feather icon-settings"></i> <s:message
									code="label.vietnamese" />
						</a></li>
						<li><a href='en/<c:out value="${ctxAdmin}" />index.html'>
								<i class="feather icon-user"></i> <s:message
									code="label.english" />
						</a></li>
						<li><a href='fr/<c:out value="${ctxAdmin}" />index.html'>
								<i class="feather icon-mail"></i> <s:message code="label.france" />
						</a></li>
					</ul>
				</div>
			</li>
			<li class="header-notification">
				<div class="dropdown-primary dropdown">
					<div class="dropdown-toggle" data-toggle="dropdown"
						style="position: static; !important">
						<i class="feather icon-bell"></i> <span class="badge bg-c-red">5</span>
					</div>
					<ul class="show-notification notification-view dropdown-menu"
						data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
						<li>
							<h6>Notifications</h6> <label class="label label-danger">New</label>
						</li>
						<li>
							<div class="media">
								<img class="img-radius"
									src="assets/files/assets/images/avatar-4.jpg"
									alt="Generic placeholder image">
								<div class="media-body">
									<h5 class="notification-user">John Doe</h5>
									<p class="notification-msg">Lorem ipsum dolor sit amet,
										consectetuer elit.</p>
									<span class="notification-time">30 minutes ago</span>
								</div>
							</div>
						</li>
						<li>
							<div class="media">
								<img class="img-radius"
									src="assets/files/assets/images/avatar-3.jpg"
									alt="Generic placeholder image">
								<div class="media-body">
									<h5 class="notification-user">Joseph William</h5>
									<p class="notification-msg">Lorem ipsum dolor sit amet,
										consectetuer elit.</p>
									<span class="notification-time">30 minutes ago</span>
								</div>
							</div>
						</li>
						<li>
							<div class="media">
								<img class="img-radius"
									src="assets/files/assets/images/avatar-4.jpg"
									alt="Generic placeholder image">
								<div class="media-body">
									<h5 class="notification-user">Sara Soudein</h5>
									<p class="notification-msg">Lorem ipsum dolor sit amet,
										consectetuer elit.</p>
									<span class="notification-time">30 minutes ago</span>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</li>
			<li class="header-notification">
				<div class="dropdown-primary dropdown">
					<div class="displayChatbox dropdown-toggle" data-toggle="dropdown">
						<i class="feather icon-message-square"></i> <span
							class="badge bg-c-green">3</span>
					</div>
				</div>
			</li>
			<li class="user-profile header-notification">

				<div class="dropdown-primary dropdown">
					<div class="dropdown-toggle" data-toggle="dropdown">
						<img src="assets/files/assets/images/avatar-4.jpg"
							class="img-radius" alt="User-Profile-Image"> <span><c:out
								value="${userBO.last_name} ${userBO.first_name}" /></span> <i
							class="feather icon-chevron-down"></i>
					</div>
					<ul class="show-notification profile-notification dropdown-menu"
						data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
						<li><a href='#<c:out value="${ctxAdmin}" />index.html'> <i
								class="feather icon-settings"></i> <s:message
									code="label.setting" />

						</a></li>
						<li><a href='#<c:out value="${ctxAdmin}" />index.html'> <i
								class="feather icon-user"></i> <s:message code="label.profile" />

						</a></li>
						<!-- <li><a href="#email-inbox.html"> <i
								class="feather icon-mail"></i> My Messages

						</a></li>
						<li><a href="#auth-lock-screen.html"> <i
								class="feather icon-lock"></i> Lock Screen

						</a></li> -->
						<li><a href='<c:out value="${ctxAdmin}" />logout'> <i
								class="feather icon-log-out"></i> <s:message code="label.logout" />
						</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
</div>
</nav>