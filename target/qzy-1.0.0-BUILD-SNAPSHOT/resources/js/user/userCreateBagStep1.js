var selectedZsk;
var selectedZsd1;
var selectedZsd2 = "0";
var selectedDifficulty = "0";

var zskListContent;
var zsd1ListContent;
var zsd2ListContent;

$(document).ready(function() {
	zskListContent = $("#zsk_list").html();
	zsd1ListContent = $("#zsd1_list").html();
	zsd2ListContent = $("#zsd2_list").html();

	var createBagZskId = $("#createBagZskId").val();
	// if (createBagZskId == 0) {
	var firstZsk = $("#zsk_list").find("a:eq(0)");
	setZsk(firstZsk);
	var firstZsd1 = $("#zsd1_list").find("a:first-child");
	setZsd1(firstZsd1);

	openSelectZskPopup();
	// }
	// else {
	// selectZsk(createBagZskId);
	// openSelectZskPopup();
	// }

	$("#zsk_list").html("");
	$("#zsd1_list").html("");
	$("#zsd2_list").html("");
});

// 弹出窗口
function openSelectZskPopup() {
	createBorderMaskLayer("select_zsk_popup", "选择知识库", zskListContent, 300, 300);
}

function openSelectZsd1Popup() {
	createBorderMaskLayer("select_zsd1_popup", "选择一级知识点", zsd1ListContent, 300, 260);
	$(".zsd_btn").hide();
	$(".zsk_" + selectedZsk + "_btn").show();
}

function openSelectZsd2Popup() {
	createBorderMaskLayer("select_zsd2_popup", "选择二级知识点", zsd2ListContent, 300, 260);
	$(".zsd_btn").hide();
	$(".zsk_" + selectedZsk + "_zsd1_" + selectedZsd1 + "_btn").show();
}

// 设置输入框里的内容
function setZsk(zskElement) {
	selectedZsk = zskElement.attr("zskId");
	$("#selected_zsk_content").html(zskElement.text());
}

function setZsd1(zsd1Element) {
	selectedZsd1 = zsd1Element.attr("zsd1Id");
	$("#selected_zsd1_content").html(zsd1Element.text());
}

function setZsd2(zsd2Element) {
	selectedZsd2 = zsd2Element.attr("zsd2Id");
	$("#selected_zsd2_content").html(zsd2Element.text());
}

// 重置知识点输入框
function resetZsd1() {
	selectedZsd1 = "0";
	$("#selected_zsd1_content").html("随机");

	resetZsd2();
	$("#select_zsd2_input_row").hide();
}

function resetZsd2() {
	selectedZsd2 = "0";
	$("#selected_zsd2_content").html("随机");

	$("#select_zsd2_input_row").show();
}

// 在弹窗里点击知识点
function selectZsk(zskId) {
	var zskElement = $("#zsk_" + zskId);
	setZsk(zskElement);

	resetZsd1();

	// 将选择的zskId存入user表
	$.ajax({
		type : "post",
		url : "recordcreatebagzskid",
		data : {
			zskId : zskId,
		},
		success : function(result) {
		}
	});

	closeAllLayers();
}

function selectZsd1(zskId, zsd1Id) {
	if (zsd1Id === "0") {
		resetZsd1();
	}
	else {
		var zsd1Element = $("#zsk_" + zskId + "_zsd1_" + zsd1Id);
		setZsd1(zsd1Element);
		resetZsd2();
	}
	closeAllLayers();
}

function selectZsd2(zskId, zsd1Id, zsd2Id) {
	var zsd2Element = $("#zsk_" + zskId + "_zsd1_" + zsd1Id + "_zsd2_" + zsd2Id);
	setZsd2(zsd2Element);

	closeAllLayers();
}

// 选择难度
function selectDifficulty(difficulty) {
	$(".btn_difficulty").removeClass("btn_difficulty_selected");
	$("#btn_difficulty_" + difficulty).addClass("btn_difficulty_selected");

	selectedDifficulty = difficulty;
}

function nextStep() {
	window.location.href = "step2" + "?zsk=" + selectedZsk + "&zsd1=" + selectedZsd1 + "&zsd2=" + selectedZsd2 + "&difficulty=" + selectedDifficulty;
}

function gotoShuati() {
	window.location.href = "gotoshuati" + "?zsk=" + selectedZsk + "&zsd1=" + selectedZsd1 + "&zsd2=" + selectedZsd2 + "&difficulty=" + selectedDifficulty;
}
