<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<%@ page session="true"%>
<jsp:include page="../pre_include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<head>
<meta http-equiv="refresh"
	content="<%=session.getMaxInactiveInterval()%>">
<title><s:message code="title.menu" /></title>
 
<!-- Data Table Css -->
<link rel="stylesheet" type="text/css" href="assets/files/bower_components/datatables.net-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="assets/files/assets/pages/data-table/css/buttons.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="assets/files/bower_components/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="assets/files/assets/pages/data-table/extensions/buttons/css/buttons.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="assets/files/assets/css/style.css">
<link rel="stylesheet" type="text/css" href="assets/files/assets/css/pages.css">
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
								</div>
								<div class="card-block">
									<div class="table-responsive dt-responsive">
										<table id="list-menu-dt-ajax-export"
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

<jsp:include page="../suf_include.jsp"></jsp:include>

<!-- modernizr js -->
<script type="text/javascript"
	src="assets/files/bower_components/modernizr/js/modernizr.js"></script>
<script type="text/javascript"
	src="assets/files/bower_components/modernizr/js/css-scrollbars.js"></script>
<!-- data-table js -->
<script src="assets/files/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="assets/files/bower_components/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="assets/files/assets/pages/data-table/js/jszip.min.js"></script>
<script src="assets/files/assets/pages/data-table/js/pdfmake.min.js"></script>
<script src="assets/files/assets/pages/data-table/js/vfs_fonts.js"></script>
<script src="assets/files/assets/pages/data-table/extensions/buttons/js/dataTables.buttons.min.js"></script>
<script src="assets/files/assets/pages/data-table/extensions/buttons/js/buttons.flash.min.js"></script>
<script src="assets/files/assets/pages/data-table/extensions/buttons/js/jszip.min.js"></script>
<script src="assets/files/assets/pages/data-table/extensions/buttons/js/vfs_fonts.js"></script>
<script src="assets/files/assets/pages/data-table/extensions/buttons/js/buttons.colVis.min.js"></script>
<script src="assets/files/bower_components/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="assets/files/bower_components/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="assets/files/bower_components/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="assets/files/bower_components/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="assets/files/bower_components/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>

<script src="assets/files/pages/data-table-custom.js"></script>
<%-- <script src="assets/files/assets/pages/data-table/js/data-table-custom.js"></script> --%>
<script src="assets/files/assets/pages/data-table/extensions/buttons/js/extension-btns-custom.js"></script>
<script src="assets/files/assets/js/pcoded.min.js"></script>
<script src="assets/files/assets/js/vertical/vertical-layout.min.js"></script>
<script src="assets/files/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="assets/files/assets/js/script.js"></script>

<!-- Custom js -->
<script type="text/javascript">
	var langugage = '<%=response.getLocale().getLanguage()%>';
	$('#list-menu-dt-ajax-export thead tr').clone(true).appendTo(
	'#list-menu-dt-ajax-export thead');
$('#list-menu-dt-ajax thead tr:eq(1) th').each(function(i) {
var title = $(this).text();
$(this).html('<input type="text" class="form-control" placeholder="'+title+'" />');

$('input', this).on('keyup change',	function() {
	if (table.column(i).search() !== this.value) {
		table.column(i).search(this.value).draw();
	}
});
});
var data_menu = {
"constr" : "search_menu"
};
var columnsMenu = [ {"data" : "ID"}, {"data" : "NAME"}, {"data" : "NAME_EN"}, {"data" : "DISPLAY_ORDER"}
, {"data" : "PICTURE_FILE"}, {"data" : "DETAIL_FILE"}, {"data" : "MENU_LEVEL"}, {"data" : "PARENT_ID"}, {"data" : "PUBLISH"
} ];
table = getDataTableAjaxObjectFtExport("list-menu-dt-ajax-export",
	"${ctxAdmin}ref/dt_ajax.html", data_menu, columnsMenu);
</script>
