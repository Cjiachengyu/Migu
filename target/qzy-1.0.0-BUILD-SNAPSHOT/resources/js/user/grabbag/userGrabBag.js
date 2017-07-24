var hasPaidAnalysis = null;
var wrongCount = 0;

function showMy() {
	/*
	 * var myMoney = $("#myMoney").val(); if (myMoney < 1) { Alert("您的作业币不够");
	 * return; } Confirm("花费1个作业币，确定要显摆？", function() {
	 * $("#myMoney").val(myMoney - 1); window.location.href = "showmy"; });
	 */
	window.location.href = "showmy";
}

function praiseUser(praiseUserId, praiseUserName) {
	var myMoney = $("#myMoney").val();
	if (myMoney < 10) {
		Alert("您的作业币不够");
		return;
	}
	Confirm("花费10个作业币赞美一下" + praiseUserName + "？", function() {
		$("#myMoney").val(myMoney - 10);
		window.location.href = "praiseuser?userId=" + praiseUserId;
	});
}

function laughUser(laughUserId, laughUserName) {
	var myMoney = $("#myMoney").val();
	if (myMoney < 10) {
		Alert("您的作业币不够");
		return;
	}
	Confirm("花费10个作业币嘲笑一下" + laughUserName + "？", function() {
		$("#myMoney").val(myMoney - 10);
		window.location.href = "laughuser?userId=" + laughUserId;
	});
}

function cheatGrab() {
	if (hasPaidAnalysis === null) {
		getPayInfo(cheatGrab);
		return;
	}

	if (wrongCount === 0) {
		window.location.href = "cheatgrab";
	}
	else {
		var myMoney = $("#myMoney").val();
		var needMoney = 100 * wrongCount;
		if (myMoney < needMoney) {
			Alert("您的作业币不够");
			return;
		}
		Confirm("花费" + needMoney + "个作业币作一下弊？", function() {
			$("#myMoney").val(myMoney - needMoney);
			window.location.href = "cheatgrab";
		});
	}
}

function viewAnalysis() {
	if (hasPaidAnalysis === null) {
		getPayInfo(viewAnalysis);
		return;
	}

	if (hasPaidAnalysis || wrongCount === 0) {
		window.location.href = "gotoanalysis";
	}
	else {
		var myMoney = $("#myMoney").val();
		var needMoney = 10 * wrongCount;
		if (myMoney < needMoney) {
			Alert("查看解析需要" + needMoney + "个作业币，您的作业币不够哦！");
			return;
		}
		Confirm("花费" + needMoney + "个作业币查看解析？", function() {
			$("#myMoney").val(myMoney - needMoney);
			window.location.href = "gotoanalysis";
		})
	}
}

function getPayInfo(callback) {
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		dataType : "json",
		url : "getpayanalysisinfo",
		success : function(result) {
			if (hasPaidAnalysis === null) {
				hasPaidAnalysis = result.hasPaid;
				wrongCount = result.wrongCount;
				if (callback) {
					callback();
				}
			}
		}
	});
}

