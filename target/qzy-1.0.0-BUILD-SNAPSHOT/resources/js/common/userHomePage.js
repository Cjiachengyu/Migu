$(document).ready(function() {
	var adjustHeight = 50;
	var viewHeight = getViewportSize()[1];
	if (viewHeight < 540) {
		adjustHeight = 50 - 540 + viewHeight;
	}

	if (adjustHeight < 30) {
		adjustHeight = 30;
	}
	else {
		$(".main_menu_row").css("margin-top", 35 + "px");
	}

	$("#adjust_main_menu_height").css("height", adjustHeight + "px");
});

