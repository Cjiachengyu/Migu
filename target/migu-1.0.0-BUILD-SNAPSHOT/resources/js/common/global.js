// common simple tools
String.prototype.contains = function(str) {
	return this.indexOf(str) > -1 ? true : false;
};

function callElementEvent(ele, event) {
	ele[event]();
};

function callElementEventById(ele, event) {
	$("#" + ele)[0][event]();
};

function getTargetFromEvent(event) {
	event = event || window.event;
	var target = event.target || event.srcElement;
	return target;
}

function setCookie(name, value, days) {
	if (days == null) {
		days = 365;
	}

	var expires = new Date();
	expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + expires.toGMTString();
}

function getCookie(name) {
	var cookieArray = document.cookie.split("; ");
	var cookie = null;
	for (var i = 0; i < cookieArray.length; i++) {
		var array = cookieArray[i].split("=");
		if (name == array[0]) {
			cookie = array[1];
			break;
		}
	}
	return cookie;
}

function deleteCookie(name) {
	var expires = new Date();
	expires.setTime(expires.getTime() - 1);
	var cookie = getCookie(name);
	if (cookie != null) {
		document.cookie = name + "=" + cookie + ";expires=" + expires.toGMTString();
	}
}

function Alert(msg, callback) {
	openDialog("消息提示", msg, "确定", callback);
}

function Confirm(msg, okCallback, cancelCallback) {
	openDialog("请确认您的操作", msg, "确定", okCallback, "取消", cancelCallback);
}

function openDialog(title, content, btn1, callback1, btn2, callback2) {
	// 打开一个对话框，下面可以有按钮
	var current_time = new Date().getTime();
	var mask_id = current_time + "mask_div";
	var layer_id = current_time + "layer_div";

	if (document.getElementById(layer_id)) {
		document.removeChild(document.getElementById(layer_id));
	}

	if (document.getElementById(mask_id)) {
		document.removeChild(document.getElementById(mask_id));
	}

	var width = 350;
	var height = 200;

	var newDiv = document.createElement("div");
	newDiv.id = layer_id;
	newDiv.style.position = "fixed";
	newDiv.style.zIndex = "500";

	var viewSize = getViewportSize();
	var newDivWidth = Math.min(width, viewSize[0] - 40);
	var newDivHeight = Math.min(height, viewSize[1] - 40);

	var htmlPopupNavTitle = "<span class='popup_nav_title relative'>" + title + "</span>";
	var htmlPopupNav = "<div class='popup_nav clearfix'>" + htmlPopupNavTitle + "</div>";
	var htmlPopupContentWrapper = "<div style='margin: 40px auto 0 auto; text-align: center; width: " + (newDivWidth - 80) + "px;'>" + content + "</div>";
	var htmlPopupContent = "<div class='popup_content_holder' style='width: " + newDivWidth + "px; height: " + (newDivHeight - 90) + "px;'>" + htmlPopupContentWrapper + "</div>";
	var htmlPopup = htmlPopupNav + htmlPopupContent;

	var popupBtnBar = document.createElement("div");
	popupBtnBar.style.width = newDivWidth + "px";
	popupBtnBar.style.height = 50 + "px";

	var btnCount = 0;
	if (btn1) {
		btnCount = btnCount + 1;
	}

	if (btn2) {
		btnCount = btnCount + 1;
	}

	var btnAllWidth = 100 / btnCount;
	var btnWidth = btnAllWidth * 0.6;
	var btnMargin = btnAllWidth * 0.2;

	function appendBtn(btn, callback) {
		var newA = document.createElement("a");
		newA.onclick = function() {
			if (callback) {
				callback();
			}
			closePopup(layer_id, mask_id);
		};
		newA.innerHTML = btn;
		newA.className = "popup_dialog_btn";
		newA.style.width = btnWidth + "%";
		newA.style.marginLeft = btnMargin + "%";
		newA.style.marginRight = btnMargin + "%";

		popupBtnBar.appendChild(newA);
	}

	if (btn1) {
		appendBtn(btn1, callback1);
	}

	if (btn2) {
		appendBtn(btn2, callback2);
	}

	newDiv.style.width = newDivWidth + "px";
	newDiv.style.height = newDivHeight + "px";
	newDiv.style.left = (viewSize[0] / 2 - newDivWidth / 2) + "px";
	newDiv.style.top = (viewSize[1] / 2 - newDivHeight / 2) + "px";
	newDiv.className = "dialog_root_div";
	newDiv.innerHTML = htmlPopup;
	newDiv.appendChild(popupBtnBar);

	showShade(mask_id, 400);
	document.body.appendChild(newDiv);
}

function createBorderMaskLayer(contentDivId, title, content, width, height) {
	var current_time = new Date().getTime();
	var mask_id = current_time + "mask_div";
	var layer_id = current_time + "layer_div";

	if (document.getElementById(layer_id)) {
		document.removeChild(document.getElementById(layer_id));
	}

	if (document.getElementById(mask_id)) {
		document.removeChild(document.getElementById(mask_id));
	}

	if (width == null || width == 0) {
		width = 320;
	}

	if (height == null || height == 0) {
		height = 480;
	}

	var newDiv = document.createElement("div");
	newDiv.id = layer_id;
	newDiv.style.position = "fixed";
	newDiv.style.zIndex = "500";

	var viewSize = getViewportSize();
	var newDivWidth = Math.min(width, viewSize[0] - 40);
	var newDivHeight = Math.min(height, viewSize[1] - 60);

	var htmlPopupNavCloseImg = "<span class='popup_nav_close_img'></span>";
	var htmlPopupNavClose = "<a class='popup_nav_close' href='javascript:closePopup(\"" + layer_id + "\", \"" + mask_id + "\");'>" + htmlPopupNavCloseImg + "</a>";
	var htmlPopupNavTitle = "<span class='popup_nav_title relative'>" + title + "</span>";
	var htmlPopupNavClear = "<div class='clear'></div>";
	var htmlPopupNav = "<div class='popup_nav clearfix'>" + htmlPopupNavClose + htmlPopupNavTitle + htmlPopupNavClear + "</div>";
	var htmlPopupContent = "<div id='" + contentDivId + "' class='popup_content_holder' style='width: " + newDivWidth + "px; height: " + (newDivHeight - 40) + "px;'>" + content + "</div>";
	var htmlPopup = htmlPopupNav + htmlPopupContent;

	newDiv.style.width = newDivWidth + "px";
	newDiv.style.height = newDivHeight + "px";
	newDiv.style.left = (viewSize[0] / 2 - newDivWidth / 2) + "px";
	newDiv.style.top = (viewSize[1] / 2 - newDivHeight / 2) + "px";
	newDiv.className = "layer_root_div";
	newDiv.innerHTML = htmlPopup;

	showShade(mask_id, 400);
	document.body.appendChild(newDiv);

	$("#" + contentDivId).html(content);
}

function showShade(mask_id, zIndex) {
	var newMask = document.createElement("div");
	var width = Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);
	var height = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);

	newMask.id = mask_id;
	newMask.className = "full_mask_root_div";

	newMask.style.zIndex = zIndex;
	newMask.style.width = width + "px";
	newMask.style.height = height + "px";
	document.body.appendChild(newMask);
}

function closePopup(layer_id, mask_id) {
	document.body.removeChild(document.getElementById(layer_id));
	document.body.removeChild(document.getElementById(mask_id));
}

function closeAllLayers() {
	var masks = $(".full_mask_root_div");
	for (var i = 0; i < masks.length; i++) {
		document.body.removeChild(masks[i]);
	}

	var layers = $(".layer_root_div");
	for (var i = 0; i < layers.length; i++) {
		document.body.removeChild(layers[i]);
	}
}

function getViewportSize() {
	var myWidth = 0, myHeight = 0;
	if (typeof (window.innerWidth) == 'number') {
		myWidth = window.innerWidth;
		myHeight = window.innerHeight;
	}
	else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
		myWidth = document.documentElement.clientWidth;
		myHeight = document.documentElement.clientHeight;
	}
	else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
		myWidth = document.body.clientWidth;
		myHeight = document.body.clientHeight;
	}
	return [ myWidth, myHeight ];
}

function postData(path, params) {

	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", path);

	for ( var key in params) {
		if (params.hasOwnProperty(key)) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);

			form.appendChild(hiddenField);
		}
	}

	document.body.appendChild(form);
	form.submit();
}

function getData(path, params, anchor) {
	var append = "";
	for ( var key in params) {
		if (params.hasOwnProperty(key)) {
			if (append === "") {
				append = append + "?" + key + "=" + params[key];
			}
			else {
				append = append + "&" + key + "=" + params[key];
			}
		}
	}

	if (anchor !== null) {
		append = append + "#" + anchor;
	}

	window.location.href = path + append;
}

function getMac() {
	var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
	if (!wmi)
		return false;
	var pr = wmi.ExecQuery("SELECT MACAddress FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True");
	if (!pr)
		return false;
	try {
		var e = new Enumerator(pr);
	}
	catch (e) {
		return false;
	}
	var mac = [], s;
	for (; !e.atEnd(); e.moveNext()) {
		s = e.item();
		mac.push(s.MACAddress);
	}
	return mac;
}

function AlertDialog(msg, type, callBack) {
	if (type == null) {
		type = "information";
	}

	if (callBack) {
		new $.Zebra_Dialog(msg, {
			'type' : type,
			'title' : '消息提示',
			'onClose' : callBack
		});
	}
	else {
		new $.Zebra_Dialog(msg, {
			'type' : type,
			'title' : '消息提示'
		});
	}
}

function ConfirmDialog(msg, yesFunc, cancleFun) {
	new $.Zebra_Dialog(msg, {
		'type' : 'question',
		'title' : '确认',
		'buttons' : [ {
			caption : '确定',
			callback : yesFunc
		}, {
			caption : '取消',
			callback : cancleFun
		} ]
	});
}

function isInWeixinBrowser() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	}
	else {
		return false;
	}
}

function isPositiveInteger(value) {
	return isInteger(value) && value > 0;
}

function isPositiveNumber(value) {
	return isNumber(value) && value > 0;
}

function isInteger(value) {
	return value && !isNaN(value) && !value.toString().contains(".");
}

function isNumber(value) {
	return value && !isNaN(value);
}

function isNonEmptyString(value) {
	return value && value.toString().length > 0;
}

function refreshMathquill() {
	$(".mathquill-embedded-latex").mathquill();
}

function checkEditorTimeout(result) {
	if (result === "timeout") {
		window.location.href = "/migu/editor/home/logout";
	}
}

function checkAdminTimeout(result) {
	if (result === "timeout") {
		window.location.href = "/migu/admin/home/logout";
	}
}
