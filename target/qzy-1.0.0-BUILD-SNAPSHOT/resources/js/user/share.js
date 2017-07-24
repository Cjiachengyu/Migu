$(document).ready(function() {
	var shareTitle = $("#share_title").val();
	var shareMsg = $("#share_msg").val();
	var shareLink = $("#share_link").val();
	var shareImage = $("#share_image").val();
	var sucessTip = $("#sucess_tip").val();
	var successCallback = $("#success_callback").val();

	weixinShareConfig.shareLink = shareLink;
	weixinShareConfig.shareMsg = shareMsg;
	weixinShareConfig.shareTitle = shareTitle;
	weixinShareConfig.shareImage = shareImage;

	weixinShareConfig.shareInitCallback = function() {
		$("#share_tip").html(sucessTip);
		$(".share_tip_mask_holder").show();
	};
	weixinShareConfig.shareSuccessCallback = function() {
		window.location.href = successCallback;
	};

	weixinShareFn.initShare();
});
