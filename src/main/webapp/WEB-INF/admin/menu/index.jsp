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
<jsp:include page="../pre_include.jsp"></jsp:include>
<%-- <%
	 System.out.println(request.getRequestURL());
	List<Menu> menus = (List<Menu>) request.getSession().getAttribute("menus");
	int idx = 1;
	if (menus != null) {
		for (Menu menu : menus) {
			if(menu.getDetail_file().equals(request.getRequestURI())){
				request.setAttribute("menuId", menu.getId());
				break;
			}
		}
	} 
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<head>
<meta http-equiv="refresh"
	content="<%=session.getMaxInactiveInterval()%>">
<title><s:message code="title.menu" /></title>
<!-- Data Table Css -->
<link rel="stylesheet" type="text/css"
	href="assets/files/bower_components/datatables.net-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/pages/data-table/css/buttons.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="assets/files/bower_components/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">

<!-- <link rel="stylesheet" type="text/css" href="assets/files/assets/pages/data-table/extensions/buttons/css/buttons.dataTables.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="assets/files/assets/css/style.css">
<!-- <link rel="stylesheet" type="text/css" href="assets/files/assets/css/pages.css"> -->
<!-- jquery datatable ui -->
<link
	href="//cdn.datatables.net/plug-ins/725b2a2115b/integration/jqueryui/dataTables.jqueryui.css"
	rel="stylesheet" type="text/css" />
<style>
/*right click*/
.context-menu ul {
	z-index: 1000;
	position: absolute;
	overflow: hidden;
	border: 1px solid #CCC;
	white-space: nowrap;
	font-family: sans-serif;
	background: #FFF;
	color: #333;
	border-radius: 5px;
	padding: 0;
}
/* Each of the items in the list */
.context-menu ul li {
	padding: 8px 12px;
	cursor: pointer;
	list-style-type: none;
}

.context-menu ul li:hover {
	background-color: #DEF;
}

.modal-body {
	max-height: calc(100vh - 210px);
	overflow-y: auto;
}
</style>
</head>
<div class="pcoded-content">
	<!-- [ breadcrumb ] start -->
	<div class="page-header card">
		<div class="row align-items-end">
			<div class="col-lg-8">
				<div class="page-header-title">
					<i class="feather icon-home bg-c-blue"></i>
					<div class="d-inline">
						<h5>
							<s:message code="title.menu" />
						</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="page-header-breadcrumb">
					<ul class=" breadcrumb breadcrumb-title">
						<li class="breadcrumb-item"><a href="index.html"><i
								class="feather icon-home"></i></a></li>
						<li class="breadcrumb-item"><a
							href="<%=request.getAttribute("javax.servlet.forward.request_uri")%>"><s:message
									code="label.sysadmin" /></a></li>
						<li class="breadcrumb-item"><a
							href="${ctxAdmin }menu/index.html"><s:message
									code="title.menu" /></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- [ breadcrumb ] end -->
	<div class="pcoded-inner-content">
		<div class="main-body">
			<div class="page-wrapper">
				<div class="page-body">
					<!-- [ page content ] start -->
					<div class="row">
						<div class="col-md-12 col-xl-12">
							<div class="card">
								<div class="card-header">
									<h5>
										<s:message code="label.info"></s:message>
									</h5>
									<div class="card-header-right">
										<button data-toggle="modal" data-target="#menu-modal-new"
											data-backdrop="static" data-keyboard="false"
											class="btn btn-success btn-round waves-effect waves-light">
											<s:message code="label.new"></s:message>
										</button>
									</div>
								</div>
								<div class="card-block">
									<div class="table-responsive dt-responsive">
										<table id="list-menu-dt-ajax"
											class="table table-striped table-bordered nowrap">
											<thead>
												<tr>
													<th><s:message code="menu.id"></s:message></th>
													<th><s:message code="menu.name"></s:message></th>
													<th><s:message code="menu.name_en"></s:message></th>
													<th><s:message code="menu.display_order"></s:message></th>
													<th><s:message code="menu.picture_file"></s:message></th>
													<th><s:message code="menu.detail_file"></s:message></th>
													<th><s:message code="menu.level"></s:message></th>
													<th><s:message code="menu.parent_id"></s:message></th>
													<th><s:message code="menu.publish"></s:message></th>
												</tr>
											</thead>
											<tbody
												oncontextmenu="event.preventDefault();$('#context-menu').show();$('#context-menu').offset({'top':mouseY,'left':mouseX})"></tbody>
											<%-- <tfoot>
											<tr>
													<th><s:message code="menu.id"></s:message></th>
													<th><s:message code="menu.name"></s:message></th>
													<th><s:message code="menu.name_en"></s:message></th>
													<th><s:message code="menu.display_order"></s:message></th>
													<th><s:message code="menu.picture_file"></s:message></th>
													<th><s:message code="menu.detail_file"></s:message></th>
													<th><s:message code="menu.level"></s:message></th>
													<th><s:message code="menu.parent_id"></s:message></th>
													<th><s:message code="menu.publish"></s:message></th>
												</tr>
											</tfoot> --%>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- [ page content ] end -->
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="menu-modal-view" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg" role="menu-view">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<s:message code="label.view_detail"></s:message>
				</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">ID</label>
						<div class="col-sm-8">
							<input type="text" name="id" class="form-control"
								placeholder="ID" readonly="">
						</div>
					</div>
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Tên menu</label>
						<div class="col-sm-8">
							<input type="text" name="name" class="form-control"
								placeholder="Tên menu" readonly="">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Tên tiếng anh</label>
						<div class="col-sm-8">
							<input type="text" name="name_en" class="form-control"
								placeholder="Tên tiếng anh" readonly="">
						</div>
					</div>
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Hiển thị</label>
						<div class="col-sm-8">
							<input type="text" name="display_order" class="form-control"
								placeholder="Hiển thị" readonly="">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Icon</label>
						<div class="col-sm-8">
							<input type="text" name="picture_file" class="form-control"
								placeholder="Icon" readonly="">
						</div>
					</div>
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Đường dẫn</label>
						<div class="col-sm-8">
							<input type="text" name="detail_file" class="form-control"
								placeholder="Đường dẫn" readonly="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Cấp menu</label>
						<div class="col-sm-8">
							<input type="text" name="menu_level" class="form-control"
								placeholder="Cấp menu" readonly="">
						</div>
					</div>
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Menu cha</label>
						<div class="col-sm-8">
							<input type="text" name="parent_id" class="form-control"
								placeholder="Menu cha" readonly="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group row col-sm-6">
						<label class="col-sm-4 col-form-label">Trạng thái</label>
						<div class="col-sm-8">
							<input type="text" name="publish" class="form-control"
								placeholder="Trạng thái" readonly="">
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default waves-effect "
					data-dismiss="modal">
					<s:message code="label.close"></s:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- them moi -->
<div class="modal fade" id="menu-modal-new" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg" role="menu-new">
		<form id="menuNew" action="${ctxAdmin }crud_ajax/val" method="post">
			<div class="modal-content">
				<div class="modal-header md-content">
					<h4 class="modal-title">
						<s:message code="label.new"></s:message>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- cac value support call api -->
					<input type="hidden" name="constr" value="new_menu"> <input
						type="hidden" name="crud_path" value="crud">

					<div class="form-group row">
						<label class="col-sm-4 col-form-label">ID</label>
						<div class="col-sm-8">
							<input type="text" name="id" class="form-control"
								placeholder="ID">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Tên menu</label>
						<div class="col-sm-8">
							<input type="text" name="name" class="form-control"
								placeholder="Tên menu">
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Tên tiếng anh</label>
						<div class="col-sm-8">
							<input type="text" name="name_en" class="form-control"
								placeholder="Tên tiếng anh">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Hiển thị</label>
						<div class="col-sm-8">
							<input type="text" name="display_order" class="form-control"
								placeholder="Hiển thị">
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Icon</label>
						<div class="col-sm-8">
							<input type="text" name="picture_file" class="form-control"
								placeholder="Icon">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Đường dẫn</label>
						<div class="col-sm-8">
							<input type="text" name="detail_file" class="form-control"
								placeholder="Đường dẫn">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Cấp menu</label>
						<div class="col-sm-8">
							<input type="text" name="menu_level" class="form-control"
								placeholder="Cấp menu">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Menu cha</label>
						<div class="col-sm-8">
							<input type="text" name="parent_id" class="form-control"
								placeholder="Menu cha">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Trạng thái</label>
						<div class="col-sm-8">
							<input type="text" name="publish" class="form-control"
								placeholder="Trạng thái">
						</div>
					</div>


				</div>
				<div class="modal-footer">
					<button type="submit" onclick="addEditAction(1)"
						class="btn btn-primary waves-effect waves-light ">
						<s:message code="label.new"></s:message>
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- cap nhat -->
<div class="modal fade" id="menu-modal-edit" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg" role="menu-edit">
		<form id="menuEdit" action="${ctxAdmin }crud_ajax/val" method="post">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<s:message code="label.edit"></s:message>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- cac value support call api -->
					<input type="hidden" name="constr" value="edit_menu"> <input
						type="hidden" name="crud_path" value="crud">

					<div class="form-group row">
						<label class="col-sm-4 col-form-label">ID</label>
						<div class="col-sm-8">
							<input type="text" name="id" class="form-control"
								placeholder="ID" readonly="readonly">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Tên menu</label>
						<div class="col-sm-8">
							<input type="text" name="name" class="form-control"
								placeholder="Tên menu">
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Tên tiếng anh</label>
						<div class="col-sm-8">
							<input type="text" name="name_en" class="form-control"
								placeholder="Tên tiếng anh">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Hiển thị</label>
						<div class="col-sm-8">
							<input type="text" name="display_order" class="form-control"
								placeholder="Hiển thị">
						</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Icon</label>
						<div class="col-sm-8">
							<input type="text" name="picture_file" class="form-control"
								placeholder="Icon">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Đường dẫn</label>
						<div class="col-sm-8">
							<input type="text" name="detail_file" class="form-control"
								placeholder="Đường dẫn">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Cấp menu</label>
						<div class="col-sm-8">
							<input type="text" name="menu_level" class="form-control"
								placeholder="Cấp menu">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Menu cha</label>
						<div class="col-sm-8">
							<input type="text" name="parent_id" class="form-control"
								placeholder="Menu cha">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Trạng thái</label>
						<div class="col-sm-8">
							<input type="text" name="publish" class="form-control"
								placeholder="Trạng thái">
						</div>
					</div>


				</div>
				<div class="modal-footer">
					<button type="submit" onclick="addEditAction(2)"
						class="btn btn-primary waves-effect waves-light ">
						<s:message code="label.edit"></s:message>
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="context-menu" id="context-menu"
	style="display: none; position: absolute; z-index: 99">
	<ul>
		<li onclick="javascript:action(1,dataRow,'menu-modal-view')"
			data-toggle="modal" data-target="#menu-modal"><span><i
				class="fa fa-eye"></i> <s:message code="label.view_detail"></s:message>
		</span></li>
		<li onclick="javascript:action(2,dataRow,'menu-modal-edit')"
			data-toggle="modal" data-target="#menu-modal"><span><i
				class="fa fa-edit"></i> <s:message code="label.edit"></s:message></span></li>
		<li onclick="javascript:action(3,dataRow,'')" data-toggle="modal"
			data-target="#menu-modal"><span><i class="fa fa-trash"></i>
				<s:message code="label.delete"></s:message></span></li>
		<li onclick="javascript:action(4,dataRow,'')" data-toggle="modal"
			data-target="#menu-modal"><span><i class="fa fa-files-o"></i>
				<s:message code="label.copy"></s:message></span></li>
	</ul>
</div>
<jsp:include page="../suf_include.jsp"></jsp:include>

<jsp:include page="menu-js.jsp"></jsp:include>