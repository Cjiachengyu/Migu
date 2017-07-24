var currentQueNum;
var queListCount;
var queNumAnswerMap;
var currentTime;
var intervalBase;

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

	// 保存上一题
	saveCurrentAnswerTime();

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

	// 初始化时间间隔起始值
	intervalBase = currentTime;

	// 准备初始化当前题目
	var userAnswer = queNumAnswerMap[queNum];

	// 初始化开始时间
	if (userAnswer.begAnswerTime === 0) {
		userAnswer.begAnswerTime = currentTime;
	}

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

// 保存当前题的时间数据
function saveCurrentAnswerTime() {
	if (1 <= currentQueNum && currentQueNum <= queListCount) {
		var lastAnswer = queNumAnswerMap[currentQueNum];
		lastAnswer.endAnswerTime = currentTime;
		lastAnswer.consumeTime = lastAnswer.consumeTime + currentTime - intervalBase;
	}
}

// 显示或隐藏"提交"按钮
function refreshSubmitBtn() {
	for (var i = 1; i <= queListCount; i++) {
		if (!queNumAnswerMap[i].userChoice) {
			$(".common_dock_bottom").hide();
			return;
		}
	}

	$(".common_dock_bottom").fadeIn(300);
}

// 提交
function submitBag() {
	// 保存上一题
	saveCurrentAnswerTime();

	var answerDataListStr = "";
	for (var i = 1; i <= queListCount; i++) {
		var answerData = queNumAnswerMap[i];
		var answerDataStr = i + "-" + answerData.userChoice + "-" + answerData.begAnswerTime + "-" + answerData.endAnswerTime + "-" + answerData.consumeTime;
		answerDataListStr = answerDataListStr + answerDataStr + "|";
	}

	window.location.href = "submitbag?answerDataList=" + answerDataListStr;
}

$(document).ready(function() {
	queListCount = $("#que_list_count").val();
	currentTime = parseInt($("#current_time").val());
	setInterval(function() {
		currentTime = currentTime + 1;
	}, 1000);

	queNumAnswerMap = new Array();
	for (var i = 1; i <= queListCount; i++) {
		var queNum = i;
		var answerDataHolder = $("#user_answer_" + queNum);
		if (answerDataHolder.length !== 0) {
			var userChoice = parseInt(answerDataHolder.find(".user_choice_data").val());
			queNumAnswerMap[queNum] = {
				queNum : queNum,
				userChoice : userChoice,
				begAnswerTime : parseInt(answerDataHolder.find("beg_answer_time_data").val()),
				endAnswerTime : parseInt(answerDataHolder.find("end_answer_time_data").val()),
				consumeTime : parseInt(answerDataHolder.find("consume_time_data").val())
			};
			refreshChoiceView(queNum, userChoice);
		}
		else {
			queNumAnswerMap[queNum] = {
				queNum : queNum,
				userChoice : null,
				begAnswerTime : 0,
				endAnswerTime : 0,
				consumeTime : 0
			};
		}
	}

	gotoQue(1);

	$(".common_dock_bottom #next_que_btn").hide();
	$(".common_dock_bottom #prev_que_btn").hide();

	refreshSubmitBtn();

	$(".que_list_holder").css("opacity", "1");

	/*
	 * touch.on("#queList", "swipeleft", function() { gotoNext(); });
	 * touch.on("#queList", "swiperight", function() { gotoPrev(); });
	 */
});
