function refreshQueListView(result) {
	checkEditorTimeout(result);

	$("#que_list_view").html(result);
	refreshMathquill();
}

function changeFromType(fromType) {

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "changefromtype",
		data : {
			fromType : fromType,
		},
		success : function(result) {
			refreshQueListView(result);
		}
	});
}

function changeFromEditor(editorId) {

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "changefromeditor",
		data : {
			editorId : editorId,
		},
		success : function(result) {
			refreshQueListView(result);
		}
	});
}

function changeFromStatus(queStatus) {

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "changefromstatus",
		data : {
			queStatus : queStatus,
		},
		success : function(result) {
			refreshQueListView(result);
		}
	});
}

function changeFromZsk(zskId) {

	$(".que_zsd1_selector").find("option[value!=0]").remove();
	$(".que_zsd2_selector").find("option[value!=0]").remove();

	$(".zsd1_list_copy").html($(".zsd1_list").html());
	$(".zsd1_list_copy").find("option[zskId!=" + zskId + "]").remove();

	$(".que_zsd1_selector").append($(".zsd1_list_copy .zsd1_item").html());

	$(".zsd1_list_copy").html("");

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "changefromzsk",
		data : {
			zskId : zskId,
		},
		success : function(result) {
			refreshQueListView(result);
		}
	});
}
function changeFromZsd1(zsd1Id) {
	$(".que_zsd2_selector").find("option[value!=0]").remove();

	$(".zsd2_list_copy").html($(".zsd2_list").html());
	$(".zsd2_list_copy").find("option[zsd1Id!=" + zsd1Id + "]").remove();

	$(".que_zsd2_selector").append($(".zsd2_list_copy .zsd2_item").html());

	$(".zsd2_list_copy").html("");

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "changefromzsd1",
		data : {
			zsd1Id : zsd1Id,
		},
		success : function(result) {
			refreshQueListView(result);
		}
	});
}
function changeFromZsd2(zsd2Id) {
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "changefromzsd2",
		data : {
			zsd2Id : zsd2Id,
		},
		success : function(result) {
			refreshQueListView(result);
		}
	});
}
