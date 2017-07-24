function clickStar(zskId, zsd1Id, zsd2Id, starNum) {

	var starStatusValue = $("#star_" + zskId + "_" + zsd1Id + "_" + zsd2Id).find("div").eq(starNum - 1).attr("starStatusValue");

	var object = {
		zskId : zskId,
		zsd1Id : zsd1Id,
		zsd2Id : zsd2Id,
		starNum : starNum,
		starStatusValue : starStatusValue
	};

	$("#starZsdData").data(object);

	createBorderMaskLayer("star_popup_window", starTitleArr[starNum - 1], starContentArr[starNum - 1][starStatusValue - 1], 300, 195);
}

function clickMoon(zskId, zsd1Id, moonNum) {

	var moonStatusValue = $("#moon_" + zskId + "_" + zsd1Id).find("div").eq(moonNum - 1).attr("moonStatusValue");

	var object = {
		zskId : zskId,
		zsd1Id : zsd1Id,
		moonNum : moonNum,
		moonStatusValue : moonStatusValue
	};

	$("#moonZsdData").data(object);

	createBorderMaskLayer("moon_popup_window", moonTitleArr[moonNum - 1], moonContentArr[moonNum - 1][moonStatusValue - 1], 300, 195);
}

$(document).ready(function() {
	zskListContent = $("#zsk_list").html();

	var shuatiZskId = $("#shuatiZskId").val();// 上次刷题的zskId，第一次为0

	$(".zsd1").hide();// 隐藏所有的知识点
	var firstZsk;

	if (shuatiZskId == 0) {

		firstZsk = $(".zsk_list_holder").find("a:eq(0)");
		$(".zsd1_" + firstZsk.attr("zsdId")).show();

		openSelectZskPopup();
	}
	else {
		firstZsk = $("#zsk_list").find("a[id='zsk_" + shuatiZskId + "']");

		$(".zsd1_" + shuatiZskId).show();
	}

	setZsk(firstZsk);// 设置当前知识库的内容

	$("#zsk_list").html("");

});
