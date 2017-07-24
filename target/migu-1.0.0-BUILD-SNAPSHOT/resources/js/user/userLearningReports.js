function setReportDate(dateType) {
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		url : "detail",
		data : {
			dateType : dateType
		},
		success : function(result) {
			$("#learning_reports_detail_view").html(result);
		}
	});
}

$(function() {
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		url : "detail",
		data : {
			dateType : 1
		},
		success : function(result) {
			$("#learning_reports_detail_view").html(result);
		}
	});
});
