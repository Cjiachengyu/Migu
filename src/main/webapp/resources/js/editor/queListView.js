function deleteQue(queId) {

	ConfirmDialog("确定删除?", function yesFunc() {
		$.ajax({
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "post",
			url : "deleteque",
			data : {
				queId : queId
			},
			success : function(result) {
				checkEditorTimeout(result);
				$("#que_list_view").html(result);
				refreshMathquill();
			}
		});
	}, function() {
	});
}

function checkQue(queId) {
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "checkque",
		data : {
			queId : queId
		},
		success : function(result) {
			checkEditorTimeout(result);
			$("#que_list_view").html(result);
			refreshMathquill();
		}
	});
}

$(function() {
	var fromType = $(".que_from_type_selector").find("option:selected").val();
	if (fromType == 2) {
		$(".operatingQue").hide();
	}

});
