function deletePrize(prizeId) {
	ConfirmDialog("确定删除?", function yesFunc() {
		$.ajax({
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "post",
			url : "deleteprize",
			data : {
				prizeId : prizeId
			},
			success : function(result) {
				checkEditorTimeout(result);
				$("#prize_list").html(result);
			}
		});
	}, function() {
	});
}
