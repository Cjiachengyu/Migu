function prizeExchange(prizeId, prizeName, prizeMoney, myMoney) {

	if (prizeMoney > myMoney) {
		Alert("对不起，您的作业币不够，可以刷题或发作业得作业币哦!");
		return;
	}

	$("#prizeExchange > div > div").text("兑换" + prizeName + "需要花费" + prizeMoney + "作业币!");
	$("#myMoney > div > div").text("我的作业币:" + myMoney);
	var prizeExchange = $("#prizeExchange").html();
	var myMoney = $("#myMoney").html();

	var object = {
		prizeId : prizeId,
		prizeMoney : prizeMoney
	};
	$("#prizeData").data(object);

	createBorderMaskLayer("prize_popup_window", prizePopupTitle, prizeExchange + '<br>' + myMoney + '<br>' + prizeExchangeButton, 300, 200);
}

function confirmExchangePrize() {
	var prizeId = $("#prizeData").data("prizeId");
	var prizeMoney = $("#prizeData").data("prizeMoney");

	$.ajax({
		type : "post",
		url : "doexchange",
		data : {
			prizeId : prizeId,
			prizeMoney : prizeMoney
		},
		success : function(result) {
			closeAllLayers();

			window.location.href = "/qzy/user/account/prizelist";
		}
	});
}

function lottery(myMoney) {

	if (10000 > myMoney) {
		Alert("对不起，您的作业币不够，可以刷题或发作业得作业币哦!");
		return;
	}

	$("#prizeExchange > div > div").text("抽奖需要花费10000作业币!");
	$("#myMoney > div > div").text("我的作业币:" + myMoney);
	var prizeExchange = $("#prizeExchange").html();
	var myMoney = $("#myMoney").html();

	createBorderMaskLayer("lottery_popup_window", lotteryPopupTitle, prizeExchange + '<br>' + myMoney + '<br>' + lotteryButton, 300, 200);
}

function confirmLottery() {
	closeAllLayers();
	window.location.href = "lottery";
}

var prizePopupTitle;
var lotteryPopupTitle;
var prizeExchangeButton;
var lotteryButton;

$(document).ready(function() {
	prizePopupTitle = "奖品兑换";
	lotteryPopupTitle = "抽奖";

	prizeExchangeButton = $("#prizeExchangeButton").html();
	$("#prizeExchangeButton").html("");

	lotteryButton = $("#lotteryButton").html();
	$("#lotteryButton").html("");
});
