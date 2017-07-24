$(document).ready(function() {
	var viewWidth = getViewportSize()[0];
	var scale = viewWidth / 375;
	var shiftWidth = ((viewWidth - 375) / 2);
	$("#animation_img_holder").css("transform", "scale(" + scale + ", " + scale + ")");
	$("#animation_img_holder").css("-webkit-transform", "scale(" + scale + ", " + scale + ")");
	$("#animation_img_holder").css("left", (shiftWidth - 0.5) + "px");
	$("#animation_img_holder").css("top", (shiftWidth * 667 / 375 - 0.5) + "px");
	$("#animation_img_holder").css("opacity", "1");
});
