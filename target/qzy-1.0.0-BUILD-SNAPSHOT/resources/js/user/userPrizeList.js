function showPrize(prizeId) {
	/*
	 * var myMoney = $("#myMoney").val(); if (myMoney < 1) { Alert("您的作业币不够"); }
	 * Confirm("花费1个作业币，确定要显摆？", function() { $("#myMoney").val(myMoney - 1);
	 * window.location.href = "/qzy/user/prize/showprize?prizeId=" + prizeId;
	 * });
	 */
	window.location.href = "/qzy/user/account/showprize?prizeId=" + prizeId;
}

function sendGift(prizeId) {
	Confirm("确定要将此奖品送出一份？", function() {
		window.location.href = "/qzy/user/account/sendgift?prizeId=" + prizeId;
	});
}

function reSendGift(giftId) {
	window.location.href = "/qzy/user/account/resendgift?giftId=" + giftId;
}

function showGift(giftId, flag) {
	/*
	 * var myMoney = $("#myMoney").val(); if (myMoney < 1) { Alert("您的作业币不够"); }
	 * Confirm("花费1个作业币，确定要显摆？", function() { $("#myMoney").val(myMoney - 1); if
	 * (flag == 1) { window.location.href =
	 * "/qzy/user/prize/showsendgift?giftId=" + giftId; } else if (flag == 0) {
	 * window.location.href = "/qzy/user/prize/showreceivegift?giftId=" +
	 * giftId; } });
	 */
	if (flag == 1) {
		window.location.href = "/qzy/user/account/showsendgift?giftId=" + giftId;
	}
	else if (flag == 0) {
		window.location.href = "/qzy/user/account/showreceivegift?giftId=" + giftId;
	}
}
