function changStarStatus() {
	var starStatusValue = $("#starZsdData").data("starStatusValue");

	if (starStatusValue == 2) {

		var zskId = $("#starZsdData").data("zskId");
		var zsd1Id = $("#starZsdData").data("zsd1Id");
		var zsd2Id = $("#starZsdData").data("zsd2Id");
		var starNum = $("#starZsdData").data("starNum");

		$.ajax({
			type : "post",
			url : "changestarstatus",
			data : {
				zskId : zskId,
				zsd1Id : zsd1Id,
				zsd2Id : zsd2Id,
				starNum : starNum,
			},
			success : function(result) {
				var divSelector = $("#star_" + zskId + "_" + zsd1Id + "_" + zsd2Id).find("div").eq(starNum - 1);
				divSelector.find("img").attr("src", divSelector.find("img").attr("src").replace("starStatus2", "starStatus3"));

				divSelector.attr("starStatusValue", "3");

				var item = gotStarPopupContent;
				if (starNum == 1) {
					item = item.replace("REPLACE_STAR_MONEY_TEXT", "50");
				}
				else if (starNum == 2) {
					item = item.replace("REPLACE_STAR_MONEY_TEXT", "200");
				}
				else {
					item = item.replace("REPLACE_STAR_MONEY_TEXT", "750");
				}

				item = item.replace("REPLACE_STAR_NUM_TEXT", starNum);

				createBorderMaskLayer("got_star_popup_window", "领取奖励", item, 300, 210);
			}
		});
	}
	else if (starStatusValue == 1 || starStatusValue == 3) {
		var zskId = $("#starZsdData").data("zskId");
		var zsd1Id = $("#starZsdData").data("zsd1Id");
		var zsd2Id = $("#starZsdData").data("zsd2Id");

		window.location.href = "/qzy/user/createbag/gotoshuati" + "?zsk=" + zskId + "&zsd1=" + zsd1Id + "&zsd2=" + zsd2Id + "&difficulty=0";
	}

	closeAllLayers();
}

function changMoonStatus() {

	var moonStatusValue = $("#moonZsdData").data("moonStatusValue");

	if (moonStatusValue == 2) {

		var zskId = $("#moonZsdData").data("zskId");
		var zsd1Id = $("#moonZsdData").data("zsd1Id");
		var moonNum = $("#moonZsdData").data("moonNum");

		$.ajax({
			type : "post",
			url : "changemoonstatus",
			data : {
				zskId : zskId,
				zsd1Id : zsd1Id,
				moonNum : moonNum,
			},
			success : function(result) {
				var divSelector = $("#moon_" + zskId + "_" + zsd1Id).find("div").eq(moonNum - 1);
				divSelector.find("img").attr("src", divSelector.find("img").attr("src").replace("moonStatus2", "moonStatus3"));

				divSelector.attr("moonStatusValue", "3");

				var item = gotMoonPopupContent;
				if (moonNum == 1) {
					item = item.replace("REPLACE_MOON_MONEY_TEXT", "150");
				}
				else if (moonNum == 2) {
					item = item.replace("REPLACE_MOON_MONEY_TEXT", "600");
				}
				else {
					item = item.replace("REPLACE_MOON_MONEY_TEXT", "2250");
				}

				item = item.replace("REPLACE_MOON_NUM_TEXT", moonNum);

				createBorderMaskLayer("got_moon_popup_window", "领取奖励", item, 300, 210);
			}
		});
	}
	else if (moonStatusValue == 1 || moonStatusValue == 3) {
		var zskId = $("#moonZsdData").data("zskId");
		var zsd1Id = $("#moonZsdData").data("zsd1Id");

		window.location.href = "/qzy/user/createbag/gotoshuati" + "?zsk=" + zskId + "&zsd1=" + zsd1Id + "&zsd2=0&difficulty=0";
	}

	closeAllLayers();

}

function openSelectZskPopup() {
	createBorderMaskLayer("select_zsk_popup", "选择知识库", zskListContent, 300, 300);
}

// 设置输入框里的内容
function setZsk(zskElement) {
	$("#selected_zsk_content").html(zskElement.text());
}

// 在弹窗里点击知识点
function selectZsk(zskId) {
	var zskElement = $("#zsk_" + zskId);
	setZsk(zskElement);

	$(".zsd1").hide();
	$(".zsd1_" + zskId).show();

	// 将选择的zskId存入user表
	$.ajax({
		type : "post",
		url : "recordshuatizskid",
		data : {
			zskId : zskId,
		},
		success : function(result) {
		}
	});

	closeAllLayers();

}

function shuaTi(zskId, zsd1Id, zsd2Id) {
	event.stopPropagation();// 阻止冒泡事件
	window.location.href = "/qzy/user/createbag/gotoshuati" + "?zsk=" + zskId + "&zsd1=" + zsd1Id + "&zsd2=" + zsd2Id + "&difficulty=0";
}

var starTitleArr = new Array();
var starContentArr = new Array();
var gotStarPopupContent = '';

var moonTitleArr = new Array();
var moonContentArr = new Array();
var gotMoonPopupContent = '';

var zskListContent;

$(document).ready(function() {
	$.ajax({
		type : "post",
		url : "initzsdreportdetail",
		success : function(result) {
			$(".zsd_report_father_holder").html(result);
		}
	});

	// 星星初始化
	starTitleArr.push("第一颗星");
	starTitleArr.push("第二颗星");
	starTitleArr.push("第三颗星");

	gotStarPopupContent = $("#got_star_success_popup").html();
	$("#got_star_success_popup").html('');

	var starStatus3 = $("#star_status3_popup").html();
	$("#star_status3_popup").html("");

	var popupStar1Status1Content = $("#star1_status1_popup").html();
	$("#star1_status1_popup").html("");
	var popupStar1Status2Content = $("#star1_status2_popup").html();
	$("#star1_status2_popup").html("");
	var popupStar1Content = new Array();
	popupStar1Content.push(popupStar1Status1Content);
	popupStar1Content.push(popupStar1Status2Content);
	popupStar1Content.push(starStatus3);

	var popupStar2Status1Content = $("#star2_status1_popup").html();
	$("#star2_status1_popup").html("");
	var popupStar2Status2Content = $("#star2_status2_popup").html();
	$("#star2_status2_popup").html("");
	var popupStar2Content = new Array();
	popupStar2Content.push(popupStar2Status1Content);
	popupStar2Content.push(popupStar2Status2Content);
	popupStar2Content.push(starStatus3);

	var popupStar3Status1Content = $("#star3_status1_popup").html();
	$("#star3_status1_popup").html("");
	var popupStar3Status2Content = $("#star3_status2_popup").html();
	$("#star3_status2_popup").html("");
	var popupStar3Content = new Array();
	popupStar3Content.push(popupStar3Status1Content);
	popupStar3Content.push(popupStar3Status2Content);
	popupStar3Content.push(starStatus3);

	starContentArr.push(popupStar1Content);
	starContentArr.push(popupStar2Content);
	starContentArr.push(popupStar3Content);

	// 月亮初始化
	moonTitleArr.push("第一个月亮");
	moonTitleArr.push("第二个月亮");
	moonTitleArr.push("第三个月亮");

	gotMoonPopupContent = $("#got_moon_success_popup").html();
	$("#got_moon_success_popup").html('');

	var moonStatus3 = $("#moon_status3_popup").html();
	$("#moon_status3_popup").html("");

	var popupMoon1Status1Content = $("#moon1_status1_popup").html();
	$("#moon1_status1_popup").html("");
	var popupMoon1Status2Content = $("#moon1_status2_popup").html();
	$("#moon1_status2_popup").html("");
	var popupMoon1Content = new Array();
	popupMoon1Content.push(popupMoon1Status1Content);
	popupMoon1Content.push(popupMoon1Status2Content);
	popupMoon1Content.push(moonStatus3);

	var popupMoon2Status1Content = $("#moon2_status1_popup").html();
	$("#moon2_status1_popup").html("");
	var popupMoon2Status2Content = $("#moon2_status2_popup").html();
	$("#moon2_status2_popup").html("");
	var popupMoon2Content = new Array();
	popupMoon2Content.push(popupMoon2Status1Content);
	popupMoon2Content.push(popupMoon2Status2Content);
	popupMoon2Content.push(moonStatus3);

	var popupMoon3Status1Content = $("#moon3_status1_popup").html();
	$("#moon3_status1_popup").html("");
	var popupMoon3Status2Content = $("#moon3_status2_popup").html();
	$("#moon3_status2_popup").html("");
	var popupMoon3Content = new Array();
	popupMoon3Content.push(popupMoon3Status1Content);
	popupMoon3Content.push(popupMoon3Status2Content);
	popupMoon3Content.push(moonStatus3);

	moonContentArr.push(popupMoon1Content);
	moonContentArr.push(popupMoon2Content);
	moonContentArr.push(popupMoon3Content);

});
