function roundDouble(doubleValue) {
	return Math.round(doubleValue);
}

function computeMinGrabCount(bagCount) {
	if (bagCount < 3) {
		return 0;
	}
	else {
		return bagCount;
	}
}

/*
 * function computeTempMoney(bagCount, bagMoney) { if (bagMoney > 400) { var
 * tempData = bagMoney / bagCount; if (tempData > 0 && tempData <= 40) { return
 * bagMoney; } else if (tempData > 40 && tempData <= 60) { return
 * roundDouble((tempData - 40) / 1.25 + 40) * bagCount; } else if (tempData > 60 &&
 * tempData <= 80) { return roundDouble((tempData - 60) / 2 + 56) * bagCount; }
 * else if (tempData > 80 && tempData <= 100) { return roundDouble((tempData -
 * 80) / 5 + 66) * bagCount; } else if (tempData > 100 && tempData <= 120) {
 * return roundDouble((tempData - 100) / 10 + 70) * bagCount; } else if
 * (tempData > 120) { return 72 * bagCount; } }
 * 
 * return bagMoney; }
 */

function computeCanGetMoney(bagCount, bagMoney) {
	// var tempMoney = computeTempMoney(bagCount, bagMoney);
	if (bagCount < 3) {
		return 0;
	}
	else {
		var tempData = bagMoney / bagCount;
		if (tempData > 0 && tempData <= 40) {
			return roundDouble(1.5 * bagMoney);
		}
		else if (tempData > 40 && tempData <= 60) {
			return roundDouble(((tempData - 40) / 1.25 + 40) / tempData) * 40 * bagCount * 0.5 + bagMoney;
		}
		else if (tempData > 60 && tempData <= 80) {
			return roundDouble(((tempData - 60) / 2 + 56) / tempData) * 40 * bagCount * 0.5 + bagMoney;
		}
		else if (tempData > 80 && tempData <= 100) {
			return roundDouble(((tempData - 80) / 5 + 66) / tempData) * 40 * bagCount * 0.5 + bagMoney;
		}
		else if (tempData > 100 && tempData <= 120) {
			return roundDouble(((tempData - 100) / 10 + 70) / tempData) * 40 * bagCount * 0.5 + bagMoney;
		}
		else if (tempData > 120 && tempData <= 140) {
			return bagMoney;
		}
		else if (tempData > 140 && tempData <= 160) {
			return bagMoney - roundDouble(((tempData - 140) / 5) / (220 - tempData)) * 20 * bagCount;
		}
		else if (tempData > 160 && tempData <= 180) {
			return bagMoney - roundDouble(((tempData - 160) / 2 + 4) / (220 - tempData)) * 20 * bagCount;
		}
		else if (tempData > 180 && tempData <= 200) {
			return bagMoney - roundDouble(((tempData - 180) / 1.25 + 14) / (220 - tempData)) * 20 * bagCount;
		}
		else {
			return 170 * bagCount;
		}
	}

}

function selectInputText(element) {
	$(element).select();
}

function bagInfoChange(obj) {
	var bagCount = $("#bagCount").val();
	if ($(obj).attr("id") == "bagCount" && bagCount >= 3) {
		$("#bagMoney").val(40 * bagCount);
	}
	var bagMoney = $("#bagMoney").val();

	if (!isPositiveInteger(bagCount)) {
		$("#bagCount").val("").focus();
		$("#create_bag_step2_tip").html("请填写红包个数");
		return;
	}

	if (!isPositiveInteger(bagMoney)) {
		$("#bagMoney").val("").focus();
		$("#create_bag_step2_tip").html("请填写红包金额");
		return;
	}

	if (parseInt(bagMoney) < parseInt(bagCount)) {
		$("#create_bag_step2_tip").html("红包金额不得小于红包个数");
		return;
	}

	var minGrabCount = computeMinGrabCount(bagCount);
	var canGetMoney = roundDouble(computeCanGetMoney(parseInt(bagCount), parseInt(bagMoney)));

	if (minGrabCount === 0) {
		$("#create_bag_step2_tip").html("您设置的红包个数太少，<strong>无法</strong>返还作业币，换个红包个数或总金额试试！");
	}
	else {
		$("#create_bag_step2_tip").html("如果有<strong>" + minGrabCount + "</strong>个人抢了红包，系统送你<strong>" + canGetMoney + "</strong>作业币，换个红包个数或总金额试试！");
	}
}

function nextStep() {
	var bagCount = $("#bagCount").val();
	if (!isInteger(bagCount)) {
		Alert("请填写红包个数!");
		$("#bagCount").val("");
		$("#bagCount").focus();
		return false;
	}

	var bagMoney = $("#bagMoney").val();
	if (!isNumber(bagMoney)) {
		Alert("请填写红包金额!");
		$("#bagMoney").val("");
		$("#bagMoney").focus();
		return false;
	}

	var sumMoney = parseFloat($("#sumMoney").val());
	if (bagMoney > sumMoney) {
		Alert("余额不足!");
		$("#bagMoney").val("");
		$("#bagMoney").focus();
		return false;
	}

	var bagMsg = $("#bagMsg").val();
	if (!isNonEmptyString(bagMsg)) {
		Alert("请填写留言!");
		$("#bagMsg").val("");
		$("#bagMsg").focus();
		return false;
	}

	postData("step3", {
		bagCount : bagCount,
		bagMoney : bagMoney,
		bagMsg : bagMsg
	});
}

$(document).ready(function() {
	bagInfoChange(null);
});
