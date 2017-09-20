$(document).ready(function() {
	$("#submit-btn:eq(0)").click(function() {
		if(!confirm("确定要修改吗？")) {
			$(this).preventDefault();
			return;
		}else {
			$("#mainForm").submit();
		}
	});
});