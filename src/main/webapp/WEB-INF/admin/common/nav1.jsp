<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="com.neo.app.model.Menu"%>
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
<nav class="pcoded-navbar">
<div class="nav-list">
	<div class="pcoded-inner-navbar main-menu">
		<%-- <div class="pcoded-navigation-label">
                <s:message code="navigation" />
            </div> --%>
		<%
				StringBuilder treeMenu = new StringBuilder("<ul class='pcoded-item pcoded-left-item'>");
				List<Menu> menus = (List<Menu>)request.getSession().getAttribute("menus");
				int idx = 1;
				for(Menu menu : menus){
					//neu detail_file null nghia no la menu cha va can xuat cac menu con
					if(idx == 1){
						if((menu.getDetailFile() == null) || (menu.getDetailFile() != null && "".equals(menu.getDetailFile()))){
							treeMenu.append("<li class='pcoded-hasmenu'>");
							treeMenu.append("<a href='javascript:void(0)' class='waves-effect waves-dark'>");
							treeMenu.append("<span class='pcoded-micon'>").append("<i class='").append(menu.getPictureFile()).append("'></i></span>");
							treeMenu.append("<span class='pcoded-mtext'>");
							if(pageContext.getRequest().getLocale().getLanguage().equals("vi")||pageContext.getRequest().getLocale().getLanguage().equals("vi_VN")){
								treeMenu.append(menu.getName()!=null ? menu.getName():"");
							}else{
								treeMenu.append(menu.getName_en() != null ? menu.getName_en():"");
							}
							treeMenu.append("</span></a>");
							
							treeMenu.append("<ul class='pcoded-submenu'>");
						}
						idx ++;
					}else{
						if((menu.getDetailFile() == null) || (menu.getDetailFile() != null && "".equals(menu.getDetailFile()))){
							treeMenu.append("</ul></li>");
							treeMenu.append("<li class='pcoded-hasmenu'>");
							treeMenu.append("<a href='javascript:void(0)' class='waves-effect waves-dark'>");
							treeMenu.append("<span class='pcoded-micon'>").append("<i class='").append(menu.getPictureFile()).append("'></i></span>");
							treeMenu.append("<span class='pcoded-mtext'>");
							if(pageContext.getRequest().getLocale().getLanguage().equals("vi")||pageContext.getRequest().getLocale().getLanguage().equals("vi_VN")){
								treeMenu.append(menu.getName()!=null ? menu.getName():"");
							}else{
								treeMenu.append(menu.getName_en() != null ? menu.getName_en():"");
							}
							treeMenu.append("</span></a>");
							
							treeMenu.append("<ul class='pcoded-submenu'>");
						}else{
							treeMenu.append("<li class=''><a href='").append(menu.getDetailFile()).append("class='waves-effect waves-dark'> <span class='pcoded-mtext'>");
							if(pageContext.getRequest().getLocale().getLanguage().equals("vi")||pageContext.getRequest().getLocale().getLanguage().equals("vi_VN")){
								treeMenu.append(menu.getName()!=null ? menu.getName():"");
							}else{
								treeMenu.append(menu.getName_en() != null ? menu.getName_en():"");
							}
							treeMenu.append("</span></a></li>");
						}
					}
				}
				treeMenu.append("ngonngu"+pageContext.getRequest().getLocale().getLanguage());
				treeMenu.append("</ul></li>");

				out.print(treeMenu.toString());
			%>
		<c:out value="${pageContext.request.locale.language}" />
	</div>
</div>
</nav>