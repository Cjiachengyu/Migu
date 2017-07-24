// page config
var mainMenuConfig = {
	currentMenuId : ""
};

$(document).ready(function () {
	if (mainMenuConfig.currentMenuId) {
		$(".main_menu ").removeClass("current_main_menu");
		$("#" + mainMenuConfig.currentMenuId).addClass("current_main_menu");
	}
});

