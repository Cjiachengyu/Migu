var currentQueNum;
var queListCount;

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
	// 设置当前题号
	currentQueNum = queNum;

	// 设置上下页按钮显示
	if (currentQueNum == queListCount) {
		$(".common_dock_bottom #next_que_btn").hide();
		$(".common_dock_bottom #prev_que_btn").show();
	}
	else {
		$(".common_dock_bottom #next_que_btn").show();
		$(".common_dock_bottom #prev_que_btn").hide();
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

	if (currentQueNum === 3) {
		// $("#guan_zhu_wo_men").show();
		$("#guan_zhu_wo_men").hide();
	}
	else {
		$("#guan_zhu_wo_men").hide();
	}
}

$(document).ready(function() {
	queListCount = $("#que_list_count").val();

	for (var i = 1; i <= queListCount; i++) {
		var queNum = i;
		var answerDataHolder = $("#user_answer_" + queNum);
		var userChoice = parseInt(answerDataHolder.find(".user_choice_data").val());

		var queChoiceItems = $("#que_holder_" + queNum).find(".que_choice_item");
		for (var j = 0; j < queChoiceItems.length; j++) {
			var queChoiceItem = $(queChoiceItems[j]);
			var queChoiceId = parseInt(queChoiceItem.attr("queChoiceId"));
			var queChoiceIsRight = (queChoiceItem.attr("isRightAnswer") === "true");

			if (userChoice === queChoiceId && queChoiceIsRight) {
				$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_user_right");
			}

			if (userChoice === queChoiceId && !queChoiceIsRight) {
				$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_user_wrong");
			}

			if (userChoice !== queChoiceId && queChoiceIsRight) {
				$("#que_choice_title_" + queChoiceId).addClass("que_choice_title_right_answer");
			}
		}
	}

	gotoQue(1);
	$(".que_list_holder").css("opacity", "1");

	/*
	 * touch.on("#queList", "swipeleft", function() { gotoNext(); });
	 * touch.on("#queList", "swiperight", function() { gotoPrev(); });
	 */
});
