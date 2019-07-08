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

<!-- jquery form -->
<script
	src="assets/files/bower_components/jquery.form/jquery.form.min.js"></script>
	
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

<!-- extension -->

<!-- Custom js -->
<script src="assets/files/pages/data-table-custom.js"></script>
<script
	src="assets/files/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script
	src="https://cdn.datatables.net/fixedheader/3.1.5/js/dataTables.fixedHeader.min.js"></script>
<script type="text/javascript">
	var langugage = '<%=response.getLocale().getLanguage()%>';
	var mouseX, mouseY;
	var table, dataRow;
	
	var data_menu,columnsMenu;
	
	$(document).mousemove(function(e) {
		mouseX = e.pageX;
		mouseY = e.pageY;
	});
	
	//jQuery.noConflict();
	$(document).ready(function() {

		// neo/ref/dt_ajax.html dt-json-data/objects.txt +"?constr=search_menu"
		// Setup - add a text input to each footer cell
		$('#list-menu-dt-ajax thead tr').clone(true).appendTo(
				'#list-menu-dt-ajax thead');
		$('#list-menu-dt-ajax thead tr:eq(1) th').each(function(i) {
			var title = $(this).text();
			$(this).html('<input type="text" class="form-control" placeholder="'+title+'" />');

			$('input', this).on('keyup change',	function() {
				if (table.column(i).search() !== this.value) {
					table.column(i).search(this.value).draw();
				}
			});
		});
		
		data_menu = {
			"constr" : "search_menu"
		};
		columnsMenu = [ {"data" : "ID"}, {"data" : "NAME"}, {"data" : "NAME_EN"}, {"data" : "DISPLAY_ORDER"}
			, {"data" : "PICTURE_FILE"}, {"data" : "DETAIL_FILE"}, {"data" : "MENU_LEVEL"}, {"data" : "PARENT_ID"}, {"data" : "PUBLISH"
		} ];
		/* table = getDataTableAjaxObject("list-menu-dt-ajax",
				"${ctxAdmin}ref/dt_ajax.html", data_menu, columnsMenu); */
		table = getDataTableAjaxObjectFtExport("list-menu-dt-ajax",
				"${ctxAdmin}ref/dt_ajax.html", data_menu, columnsMenu);

        
        addEditAction = function(type){
        	var form = type === 1 ? $("#menuNew"):$("#menuEdit");

            form.submit(function (e) {
            	e.preventDefault();
            	if(!validate(form)){
            		return false;
            	}
                $.ajax({
                    type: 'POST',
                    url: form.attr('action'),
                    data: form.serialize(),
                    success: function (response) {
                        if(response === 1){
                        	if(type === 1){
                        		alert('<s:message code="action.new_success"></s:message>');
                        		$("#menu-modal-new").modal('toggle');
                        	}else{
                        		alert('<s:message code="action.edit_success"></s:message>');
                        		$("#menu-modal-edit").modal('toggle');
                        	}
                        	
                        	table = getDataTableAjaxObjectFtExport("list-menu-dt-ajax",
                    				"${ctxAdmin}ref/dt_ajax.html", data_menu, columnsMenu);
                        }
                        
                    },
                    error: function (response){
                    	alert(response);
                    }
                });

                return false;
            });
        }
        validate = function(form){
        	//alert(form);
        	if(form[0].elements["id"] === null){
        		
        		return false;
        	}
        	
			if(form[0].elements["name"] === null){
			        		
        		return false;
        	}
			if(form[0].elements["name_en"] === null){
				
				return false;
			}
        	return true;
        }
	});

	$(document).bind("mousedown", function(e) {
		// If the clicked element is not the menu
		if (!$(e.target).parents(".context-menu").length > 0) {
			// Hide it
			$(".context-menu").hide(100);
		}
		console.log(table.row($(e.target).parent()).data());
		if ($(e.target).parent().is("tr")) {
			dataRow = table.row($(e.target).parent()).data();
		}
	});

	action = function(index, obj, modal) {
		$(".context-menu").hide(100);
		if(modal !== ""){
			var arraysName = ["id","name","name_en","display_order","picture_file","detail_file","menu_level","parent_id","publish"];
			for(var item in arraysName){
				$("#"+modal+" input[name='"+arraysName[item]+"']").val(decodeHtml(obj[arraysName[item].toUpperCase()]));
			}
		}
		switch (index) {
		case 1:
			$('#' + modal).modal({
				backdrop : 'static',
				keyboard : false
			});
			break;
		case 2:
			$('#' + modal).modal({
				backdrop : 'static',
				keyboard : false
			});
			break;
		case 3:
			ajaxCall(obj);
			break;
		case 4:
			break;
		}
	}
	
	ajaxCall = function(obj){
		var url_ = "${ctxAdmin }crud_ajax/val";
		var data_ = {"constr":"del_menu","crud_path":"crud","id":obj["ID"]};
		$.ajax({
            type: 'POST',
            url: url_,
            data: data_,
            success: function (response) {
                if(response === 1){
                	alert('<s:message code="action.delete_success"></s:message>');
                	table = getDataTableAjaxObjectFtExport("list-menu-dt-ajax",
            				"${ctxAdmin}ref/dt_ajax.html", data_menu, columnsMenu);
                }
                
            },
            error: function (response){
            	alert(response);
            }
        });
	}
	
</script>