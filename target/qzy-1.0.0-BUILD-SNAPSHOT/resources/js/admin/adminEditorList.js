function changePrivilege() {
	var privilegeDataArray = new Array();

	$("div.editor_item").each(function(i, item) {
		var privilegeStr = "";
		$(item).find("input").each(function(j, subItem) {
			if ($(subItem).is(':checked')) {
				privilegeStr = privilegeStr + 1;
			}
			else {
				privilegeStr = privilegeStr + 0;
			}

		});

		privilegeDataArray.push(privilegeStr);

	});

	var privilegeData = JSON.stringify(privilegeDataArray);

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "/qzy/admin/list/changeeditorprivilege",
		data : {
			privilegeData : privilegeData,
		},
		success : function(result) {
			checkAdminTimeout(result);

			AlertDialog("改变权限成功！");
		},
		error : function() {
			AlertDialog("出现错误！");
		}
	});
}
