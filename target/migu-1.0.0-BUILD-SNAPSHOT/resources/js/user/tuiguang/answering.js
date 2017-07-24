var currentQueNum;
var queListCount;
var queNumAnswerMap;

// 下一题
function gotoNext() {
	if (currentQueNum == queListCount) {
		return;
	}

	gotoQue(currentQueNum + 1);
}

// 上一题
function gotoPrev() {
	if (currentQueNum == 1) {
		return;
	}

	gotoQue(currentQueNum - 1);
}

// 跳转到某一题
function gotoQue(queNum) {
	if (currentQueNum === queNum) {
		return;
	}

	// 显示导航栏
	$(".que_num").removeClass("current_que_num_nav");
	$("#que_num_nav_" + queNum).addClass("current_que_num_nav");

	// 显示题目
	if (currentQueNum) {
		$("#que_holder_" + currentQueNum).fadeOut(200, function() {
			$(".que_holder").hide();
			$("#que_holder_" + queNum).fadeIn(100);
		});
	}
	else {
		$(".que_holder").hide();
		$("#que_holder_" + queNum).show();
	}

	// 设置当前题号
	currentQueNum = queNum;

	// 准备初始化当前题目
	var userAnswer = queNumAnswerMap[queNum];

	// 初始化选项
	if (userAnswer.userChoice) {
		selectChoice(userAnswer.userChoice);
	}
}

// 答题时，更新用户选项样式
function refreshChoiceView(queNum, queChoiceId) {
	$("#que_holder_" + queNum).find(".que_choice_title").removeClass("que_choice_title_selected");
	$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_selected");
}

// 用户点击选项
function selectChoice(queChoiceId, navToNext) {
	refreshChoiceView(currentQueNum, queChoiceId);
	var userAnswer = queNumAnswerMap[currentQueNum];
	userAnswer.userChoice = queChoiceId;

	if (navToNext) {
		for (var i = 1; i <= queListCount; i++) {
			if (!queNumAnswerMap[i].userChoice) {
				gotoQue(i);
				break;
			}
		}
	}

	refreshSubmitBtn();
}

// 显示或隐藏"提交"按钮
function refreshSubmitBtn() {
	for (var i = 1; i <= queListCount; i++) {
		if (!queNumAnswerMap[i].userChoice) {
			$(".common_dock_bottom").hide();
			return;
		}
	}

	if (!$("#mydiv").is(":visible")) {
		$(".common_dock_bottom").fadeIn(300);
	}
}

// 提交
function submitBag() {
	$("[class^='que_analysis_']").removeClass("hidden");// 显示所有解析
	// $(".common_dock_bottom").hide();// 隐藏提交按钮
	$(".common_dock_bottom").remove();// 删除提交按钮
	$(".que_choice_item").attr("onclick", "return false;");// 让选项按钮不能进行选择

	$(".answer_and_analysis_holder").hide();

	$.ajax({
		type : "post",
		url : "submittuiguangbag",
		success : function(result) {
			$(".money_info_include_holder").html(result);
		}
	});

	var rightAnswerCount = 0;
	for (var i = 1; i <= queListCount; i++) {
		var rightQueChoiceId = parseInt($("#que_holder_" + i).find("[isRightAnswer='true']").attr("queChoiceId"));

		var answerData = queNumAnswerMap[i];
		var userChoice = parseInt(answerData.userChoice);
		if (rightQueChoiceId == userChoice) {
			rightAnswerCount++;
		}
	}

	if (rightAnswerCount == 3) {
		$(".answer_all_right").show();
	}
	else {
		$(".wrong_answer_count").text(3 - rightAnswerCount);
		$(".answer_not_right").show();
	}
}

function goToAnalysis() {
	$(".answer_all_right").hide();
	$(".answer_not_right").hide();
	$(".answer_and_analysis_holder").show();

	for (var i = 1; i <= queListCount; i++) {

		var answerData = queNumAnswerMap[i];
		var userChoice = parseInt(answerData.userChoice);

		var queChoiceItems = $("#que_holder_" + i).find(".que_choice_item");
		for (var j = 0; j < queChoiceItems.length; j++) {
			var queChoiceItem = $(queChoiceItems[j]);
			var queChoiceId = parseInt(queChoiceItem.attr("queChoiceId"));
			var queChoiceIsRight = (queChoiceItem.attr("isRightAnswer") === "true");

			if (userChoice == queChoiceId && queChoiceIsRight) {
				$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_user_right");
			}

			if (userChoice == queChoiceId && !queChoiceIsRight) {
				$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_user_wrong");
			}

			if (userChoice != queChoiceId && queChoiceIsRight) {
				$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_right_answer");
			}
		}

	}

	gotoQue(1);
}

$(document).ready(function() {
	queListCount = 3;

	queNumAnswerMap = new Array();
	for (var i = 1; i <= queListCount; i++) {
		var queNum = i;

		queNumAnswerMap[queNum] = {
			queNum : queNum,
			userChoice : null
		};
	}

	gotoQue(1);
	refreshSubmitBtn();

	$(".que_list_holder").css("opacity", "1");

	/*
	 * touch.on("#queList", "swipeleft", function() { gotoNext(); });
	 * touch.on("#queList", "swiperight", function() { gotoPrev(); });
	 */
});
