var queFeedBackContent;
$(document).ready(function() {
	queFeedBackContent = $("#queFeedBack").html();
	$("#queFeedBack").html("");
});

function selectFeedback(srcElement) {
	$(".feedback_title").removeClass("feedback_title_selected");
	$(srcElement).find(".feedback_title").addClass("feedback_title_selected");
}

function startFeedback(queId) {
	$("#queFeedBack").data("queId", queId);
	$("#queFeedBack").data("msg", "");
	createBorderMaskLayer("que_feedback_window", "问题反馈", queFeedBackContent, 300, 235);
}

function confirmFeedBack() {
	var queId = $("#queFeedBack").data("queId");

	var feedBackMsg = "";
	var selectedFeedbackTitle = $(".feedback_title_selected");
	if (selectedFeedbackTitle.length === 0) {
		Alert("请选择反馈类型");
		return;
	}

	var feedbackItem = selectedFeedbackTitle.parent();
	var inputMsg = feedbackItem.find("#feedback_msg");
	if (inputMsg.length === 0) {
		feedBackMsg = feedbackItem.find(".feedback_content").html();
	}
	else {
		feedBackMsg = inputMsg.val();
		if (!feedBackMsg) {
			Alert("请填写反馈");
			return;
		}
	}

	$.ajax({
		type : "post",
		url : "quefeedback",
		data : {
			queId : queId,
			feedBackMsg : feedBackMsg
		},
		success : function(result) {
			Alert("反馈已提交，感谢您的参与！");
		}
	});
	closeAllLayers();
}


