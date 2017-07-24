var operationType;// 当前操作

var popupTitle;
var zskContent;
var selectedZsdItemTemplate;

var queChoiceArr;// 选项数组

var editorTmp;
var editorContent;
var editorAnalysis;
var editorAnswer;
var editorChoiceArr = [];

$(document).ready(function() {
	editorTmp = UM.getEditor("tmp_um_editor");
	editorContent = UM.getEditor("editor_content");
	editorAnalysis = UM.getEditor("editor_analysis");
	editorAnswer = UM.getEditor("editor_answer");

	popupTitle = "知识点选择";

	selectedZsdItemTemplate = $("#selected_zsd_item_template").html();
	$("#selected_zsd_item_template").html("");

	queChoiceArr = new Array();
	for (var i = 1; i <= 9; i++) {
		queChoiceArr.push("blank_" + i);
	}

	operationType = $("#operationType").val();
	$("#operationType").val("");

	if (operationType == 1) {

		setChoiceCount(4); // 创建
	}
	else {
		if (operationType == 2) {
			$("#create_que_btn").val("确定修改");
		}
		else {
			$("#create_que_btn").val("确定另存");
		}

		initOldQue(); // 编辑/另存
	}

	zskContent = $("#zskContent").html();
	$("#zskContent").html("");

});

// 在编辑/另存的时候，把老题的信息带过来
function initOldQue() {
	var old_source = $("#old_question_source").val();
	$("#old_question_source").remove();
	var old_kaodian = $("#old_question_kaodian").val();
	$("#old_question_kaodian").remove();
	var old_contentHtml = $("#old_editor_content").html();
	$("#old_editor_content").remove();
	var old_analysisHtml = $("#old_editor_analysis").html();
	$("#old_editor_analysis").remove();
	var old_answerHtml = $("#old_editor_answer").html();
	$("#old_editor_answer").remove();

	$("#question_source").val(old_source);
	$("#question_kaodian").val(old_kaodian);
	editorContent.setContent(old_contentHtml);
	editorAnalysis.setContent(old_analysisHtml);
	editorAnswer.setContent(old_answerHtml);

	var old_choiceCount = $("#old_choice_count").val();// que的选项个数
	$("#old_choice_count").remove();
	setChoiceCount(old_choiceCount);
	for (var i = 1; i <= old_choiceCount; i++) {
		editorChoiceArr[i - 1].setContent($("#old_editor_choice_" + i).html());

		var rightAnswerNum = $("#old_editor_choice_" + i).attr("rightAnswerNum");
		if (rightAnswerNum != undefined) {
			var rightAnswer = $("#choice_holder_div_" + rightAnswerNum).find("div span").text().substring(0, 1);
			$("#right_answer_data").text(rightAnswer);
			$("#right_answer_num").val(rightAnswerNum);
		}

		$("#old_editor_choice_" + i).remove();
	}

	var oldZsdDataCount = $("#old_zsd_data_count").val();
	$("#old_zsd_data_count").remove();
	var arr = new Array();
	for (var i = 1; i <= oldZsdDataCount; i++) {
		var zsdIdData = $("#old_zsd_data_" + i).val()

		arr.push(zsdIdData);// 存入arr

		// 显示主页面知识点
		var zsdShowText = $("div[zsdstr=zsd_" + zsdIdData + "]").find("span").text();
		appendAddedZsdToMain(zsdIdData, zsdShowText);
		$("#old_zsd_data_" + i).remove();
	}
	$("#selected_zsd_list").data("zsdData", arr);
}

// 工具函数，往弹窗里的"已选择知识点"里添加一个知识点
function appendAddedZsdToPopup(zsdIdData, zsdShowText) {
	var item = selectedZsdItemTemplate;
	item = item.replace("REPLACE_ZSD_ID_DATA", zsdIdData);
	item = item.replace("REPLACE_TEXT", zsdShowText);
	item = item.replace("REPLACE_REMOVE_FUNC", "javascript:delZsdInPopup(this);");

	$("#relatedZsd").append(item);
}

// 工具函数，往主页面里的"已选择知识点"里添加一个知识点
function appendAddedZsdToMain(zsdIdData, zsdShowText) {
	var item = selectedZsdItemTemplate;
	item = item.replace("REPLACE_ZSD_ID_DATA", zsdIdData);
	item = item.replace("REPLACE_TEXT", zsdShowText);
	item = item.replace("REPLACE_REMOVE_FUNC", "javascript:delZsdInMain(this);");

	$("#select_zsd_data").append(item);
}

// 在主页点"添加"时，弹出窗口
function selectZsd() {
	createBorderMaskLayer("select_zsd_popup_window", popupTitle, zskContent, 800, 500);

	var arr = $("#selected_zsd_list").data("zsdData");
	if (arr) {
		for (var i = 0; i < arr.length; i++) {

			var divSelector = $("div[zsdstr=zsd_" + arr[i] + "]");

			divSelector.attr("isZsdAdded", "yes");
			divSelector.find("span").find("input").val("移除");

			var zsdIdData = arr[i];
			var zsdShowText = divSelector.find("span").text();
			appendAddedZsdToPopup(zsdIdData, zsdShowText);
		}
	}
}

// 点击知识库时
function clickZsk(obj, zskId) {
	$(".zsk_Content span").removeClass("selected_zsd_item");
	$(obj).addClass("selected_zsd_item");

	$("div.zsd1").hide();
	$("div.zsd1_" + zskId).show();
	$("div.zsd2").hide();
}

// 点击知识点1时，列出所属的2级知识点
function clickZsd1(obj, zsd1Id) {
	$("div.zsd2").hide();
	$("div.zsd2_" + zsd1Id).show();

	$(".zsd1 span").removeClass("selected_zsd_item");
	$(obj).addClass("selected_zsd_item");
}

// 鼠标放上去显示按钮
function displayAddButton(obj) {
	$(obj).find("input").show();
}

// 鼠标移走时隐藏按钮
function hideAddButton(obj) {
	$(obj).find("input").hide();
}

// 点击mouseenter冒出的按钮时
function addOrRemoveZsd(obj) {
	var parentSelector = $(obj).parent().parent();

	var isZsdAdded = parentSelector.attr("isZsdAdded");

	if (isZsdAdded == "no") {
		// 本来是"添加"，add完后变"移除"
		parentSelector.attr("isZsdAdded", "yes");
		$(obj).val("移除");
	}
	else {
		parentSelector.attr("isZsdAdded", "no");
		$(obj).val("添加");
	}

	// 刷新一下"已选中列表"
	var arr = new Array();
	$("#relatedZsd").empty();
	$("div[isZsdAdded=yes]").each(function(i, item) {
		var zsdIdData = $(item).find("input[type=hidden]").val();
		arr.push(zsdIdData);

		var zsdShowText = $(item).find("span").text();
		appendAddedZsdToPopup(zsdIdData, zsdShowText);
	});

	$("#selected_zsd_list").data("zsdData", arr);
}

// 弹窗里的移除
function delZsdInPopup(obj) {
	var delZsdIdData = $(obj).attr("name");

	var divSelector = $("div[zsdstr=zsd_" + delZsdIdData + "]");

	divSelector.attr("isZsdAdded", "no");
	divSelector.find("span").find("input").val("添加");

	// 取得原来的Array
	var arr = $("#selected_zsd_list").data("zsdData");
	// 删除固定值
	arr.splice($.inArray(delZsdIdData, arr), 1);
	// 存入zsdData
	$("#selected_zsd_list").data("zsdData", arr);

	// 删除li元素
	$(obj).parent().remove();
}

// 主页里的移除
function delZsdInMain(obj) {
	var delData = $(obj).attr("name");

	// 取得原来的Array
	var arr = $("#selected_zsd_list").data("zsdData");
	// 删除固定值
	arr.splice($.inArray(delData, arr), 1);
	// 存入zsdData
	$("#selected_zsd_list").data("zsdData", arr);

	// 删除li元素
	$(obj).parent().remove();
}

// 弹窗点"确定"的操作，刷一下主页里"已选择知识点"的数据
function confirmZsd() {
	$("#select_zsd_data").empty();

	var arr = $("#selected_zsd_list").data("zsdData");
	if (arr) {
		for (var i = 0; i < arr.length; i++) {

			var divSelector = $("div[zsdstr=zsd_" + arr[i] + "]");

			appendAddedZsdToMain(arr[i], divSelector.find("span").text());
		}
	}

	closeAllLayers();
}

// 过滤一个编辑器的文本，设回编辑器，进行强力过滤
function formatEditor(editor) {
	editor.setContent(filterEditorContent(editor, true));
}

// 过滤一个编辑器的文本，返回过滤后的文本
function filterEditorContent(editor, filterAll) {
	var tmpPlace = $("#filter_editor_content_tmp");
	tmpPlace.html(editor.getContent());

	// 点"格式化"的时候调用
	if (filterAll) {
		tmpPlace.find("*").removeAttr("style"); // 过滤掉所有的样式
		tmpPlace.find("img").css("vertical-align", "middle");

		// 删除空行
		tmpPlace.find("p, div, span, ul, li").each(function(i, item) {
			if ($(item).find("img").length == 0 && $.trim($(item).text()) == "") {
				$(item).remove();
			}
		});

		tmpPlace.find("br+br").remove();
	}

	// 过滤掉所有的超链接
	tmpPlace.find("*").removeAttr("href").removeAttr("_href").removeAttr("_src");
	// 过滤掉所有数学公式的固定宽度
	tmpPlace.find(".mathquill-embedded-latex").css("width", "").css("height", "");

	return tmpPlace.html();
}

// 过滤所有编辑器，设回编辑器
function formatAllEditors() {
	formatEditor(editorContent);
	formatEditor(editorAnalysis);
	formatEditor(editorAnswer);

	var choiceCount = $("#choice_count").find("option:selected").text();
	for (var i = 1; i <= choiceCount; i++) {
		formatEditor(editorChoiceArr[i - 1]);
	}
}

// 高亮图片1秒钟
function highlightImg() {
	$(".em_editor").find("img").css("border", "solid 2px red");
	$(".submit_btn_holder").find("input").attr("disabled", "true");
	setTimeout(function() {
		$(".em_editor").find("img").css("border", "");
		$(".submit_btn_holder").find("input").removeAttr("disabled");
	}, 1000);
}

// ------------------------------------------------
function checkRequired() {

	var liCount = $("#select_zsd_data span").length;
	if (liCount == 0) {
		AlertDialog("请添加知识点");
		return false;
	}

	if ($("#right_answer_data").text() == "") {
		AlertDialog("请设置正确答案");
		return false;
	}

	return true;
}

function createQue() {
	if (!checkRequired()) {
		return;
	}

	$("#create_que_btn").attr("disabled", true);

	var source = $("#question_source").val();
	var kaodian = $("#question_kaodian").val();
	var zsdValue = JSON.stringify($("#selected_zsd_list").data("zsdData"));

	var contentHtml = filterEditorContent(editorContent);
	var analysisHtml = filterEditorContent(editorAnalysis);
	var answerHtml = filterEditorContent(editorAnswer);

	var choiceCount = $("#choice_count").find("option:selected").text();
	for (var i = 1; i <= choiceCount; i++) {
		queChoiceArr[i - 1] = filterEditorContent(editorChoiceArr[i - 1]);
	}
	var answerNum = $("#right_answer_num").val();

	var imageSrcList = getImageSrcList();

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "/migu/editor/queedit/docreatequestion",
		data : {
			source : source,
			kaodian : kaodian,
			zsdValue : zsdValue,
			contentHtml : contentHtml,
			queChoiceHtml1 : queChoiceArr[0],
			queChoiceHtml2 : queChoiceArr[1],
			queChoiceHtml3 : queChoiceArr[2],
			queChoiceHtml4 : queChoiceArr[3],
			queChoiceHtml5 : queChoiceArr[4],
			queChoiceHtml6 : queChoiceArr[5],
			queChoiceHtml7 : queChoiceArr[6],
			queChoiceHtml8 : queChoiceArr[7],
			queChoiceHtml9 : queChoiceArr[8],
			analysisHtml : analysisHtml,
			answerHtml : answerHtml,
			answerNum : answerNum,
			choiceCount : choiceCount,
			imageSrcList : imageSrcList
		},
		success : function(result) {
			checkEditorTimeout(result);

			$("#question_source").val("");
			$("#question_kaodian").val("");

			// $("#select_zsd_data").empty();
			// $("#selected_zsd_list").data("zsdData", new Array());

			$("#editor_content").html("");
			$("#editor_analysis").html("");
			$("#editor_answer").html("");

			for (var i = 1; i <= 9; i++) {
				$("#editor_choice_" + i).html("");
			}
			setChoiceCount(4);

			$("#create_que_btn").val("确定创建");

			editorTmp.setContent(result);
			createBorderMaskLayer("last_que_preview", "编辑成功", $("#tmp_um_editor").html(), 700, 500);
			$("#tmp_um_editor").html("");

			$("#create_que_btn").attr("disabled", false);
		},
		error : function() {
			AlertDialog("出现错误！");
			$("#create_que_btn").attr("disabled", false);
		}
	});

}

function getImageSrcList() {
	var imgElementList = $(".em_editor").find("img");
	var imageSrcList = "";
	for (var i = 0; i < imgElementList.length; i++) {
		imageSrcList = imageSrcList + $(imgElementList[i]).attr("src") + "%";
	}

	return imageSrcList;
}

function operatingChoiceCount(obj) {
	setChoiceCount($(obj).val());
}

function setChoiceCount(choiceCount) {
	for (var i = 1, j = choiceCount; i <= 9; i++) {
		if (i <= j) {
			editorChoiceArr[i - 1] = UM.getEditor("editor_choice_" + i);
			$("#choice_holder_div_" + i).show();
		}
		else {
			$("#choice_holder_div_" + i).hide();
		}
	}

	$("#choice_count").val(choiceCount); // 设置选项个数
	$("#right_answer_data").text("");
}

function setRightAnswer(obj, answerNum) {
	var answer = $(obj).parent().find("span").text().substring(0, 1);

	$("#right_answer_data").text(answer);
	$("#right_answer_num").val(answerNum);
}

function closeLastQuePop() {
	closeAllLayers();
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "/migu/editor/queedit/resetoperationType",
		success : function(result) {
			checkEditorTimeout(result);
		},
		error : function() {
			AlertDialog("出现错误！");
		}
	});
}

function backToList() {
	window.location.href = "/migu/editor/quelist/backtolist";
}
