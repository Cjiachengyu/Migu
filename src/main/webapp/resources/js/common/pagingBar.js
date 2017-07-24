var pagingBarConfig = {
	gotoPageCallback : function(result) {
	}
}

var pagingBarFn = {
	gotoPage : function(pageNum) {
		$.ajax({
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "post",
			url : "gotopage",
			data : {
				pageNum : pageNum
			},
			success : function(result) {
				pagingBarConfig.gotoPageCallback(result);
			}
		});
	},

	jumpToPage : function(jumpBtnElement) {
		console.log(jumpBtnElement);
		var destPage = $(jumpBtnElement).parent().find("input").val();
		var pageCount = $(jumpBtnElement).parent().find("span:first").html();
		console.log(destPage);
		console.log(pageCount);

		var positiveInteger = /^[1-9]\d*$/;
		if (!positiveInteger.test(destPage)) {
			AlertDialog("请输入正确页数后跳转");
			return;
		}

		if (parseInt(destPage) > parseInt(pageCount)) {
			destPage = pageCount;
		}

		this.gotoPage(destPage);
	},

	inputKeyDown : function(e) {
		if (e.which == 13) {
			this.jumpToPage(getTargetFromEvent(e));
		}
	}
};
