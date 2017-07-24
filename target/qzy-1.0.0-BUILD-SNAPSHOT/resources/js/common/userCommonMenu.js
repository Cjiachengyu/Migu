function clickHomeMainMenu() {
	$(".main_menu_holder_all").toggle();
	var btn = $("#common_home_btn");
	var currentSrc = btn.attr("src");
	if (currentSrc.contains("qzy_home_button")) {
		btn.attr("src", currentSrc.replace("qzy_home_button", "qzy_home_return"));
	}
	else {
		btn.attr("src", currentSrc.replace("qzy_home_return", "qzy_home_button"));
	}
}

