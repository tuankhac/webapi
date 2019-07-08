<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<!-- Required Jquery -->
<script src="assets/files/bower_components/jquery/js/jquery.min.js"></script>
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
	
<!-- Float Chart js -->
<!-- <script src="assets/files/assets/pages/chart/float/jquery.flot.js"></script> -->
<!-- <script
	src="assets/files/assets/pages/chart/float/jquery.flot.categories.js"></script> -->
<!-- <script src="assets/files/assets/pages/chart/float/curvedLines.js"></script> -->
<!-- <script
	src="assets/files/assets/pages/chart/float/jquery.flot.tooltip.min.js"></script> -->
	
<!-- Chartlist charts -->
<!-- <script src="assets/files/bower_components/chartist/js/chartist.js"></script> -->

<!-- amchart js -->
<script src="assets/files/assets/pages/widget/amchart/amcharts.js"></script>
<script src="assets/files/assets/pages/widget/amchart/serial.js"></script>
<script src="assets/files/assets/pages/widget/amchart/light.js"></script>

<!-- Custom js -->
<script src="assets/files/assets/js/pcoded.min.js"></script>
<script src="assets/files/assets/js/vertical/vertical-layout.min.js"></script>
<!-- <script type="text/javascript"
	src="assets/files/assets/pages/dashboard/custom-dashboard.min.js"></script> -->
<script type="text/javascript"
	src="assets/files/assets/js/script.min.js"></script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="assets/files/gtag/js-id=UA-23581568-13.htm"></script>
<script>

	activeThis =  function(obj){
		$("div.nav-list li").removeClass("active");
		var parent = $("div.nav-list li.pcoded-trigger").attr("id");
		var cur = $(obj).parent().attr("id");
		if (typeof(Storage) !== "undefined") {
			sessionStorage.setItem("keyMenuParent", parent);
			sessionStorage.setItem("keyMenuCur", cur);
		} else {
		    alert("Browser not support");
		}
	}
	$('body').on('click', function(){
		sessionStorage.removeItem("keyMenuParent");
		sessionStorage.removeItem("keyMenuParent");
    });
	window.onload = function(){
		var parent = sessionStorage.getItem("keyMenuParent");
		var cur = sessionStorage.getItem("keyMenuCur");
		$("div.nav-list li#"+parent).addClass("active").addClass("pcoded-trigger");
		$("div.nav-list li#"+cur).addClass("active");
	}
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());

	gtag('config', 'UA-23581568-13');
</script>