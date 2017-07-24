$(function() {
	var isOpera = !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
	var isChrome = !!window.chrome && !isOpera;

	var inputAccount = $("#input_account");
	var inputPassword = $("#input_password");
	inputAccount.focus();

	var submitForm = function() {
		if (!isChrome) {
			AlertDialog("很抱歉，暂不支持您的浏览器，请使用Chrome或切换至极速模式再来尝试，谢谢！");
			return;
		}

		var account = inputAccount.val();
		if (account === "") {
			AlertDialog("请输入用户名");
			inputAccount.focus();
			return;
		}

		var password = inputPassword.val();
		if (password === "") {
			AlertDialog("请输入密码");
			inputPassword.focus();
			return;
		}

		$.ajax({
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "post",
			url : "login",
			data : {
				account : account,
				password : password,
			},
			success : function(result) {
				if (result === "gotoque") {
					window.location.href = "/migu/editor/quelist/main";
				}
				else if (result === "gotoprize") {
					window.location.href = "/migu/editor/list/prizelist";
				}
				else if (result === "noprivilege") {
					AlertDialog("您没有任何权限！");
				}
				else if (result === "simplepwd") {
					AlertDialog("您的密码过于简单，请修改密码", null, function() {
						window.location.href = "/migu/editor/setting/changepwd";
					})
				}
				else {
					AlertDialog("用户名或密码错误");
				}
			}
		});
	}

	inputAccount.on("keydown", function(e) {
		if (e.which == 13) {
			submitForm();
		}
	});

	inputPassword.on("keydown", function(e) {
		if (e.which == 13) {
			submitForm();
		}
	});

	$("#login_btn").on("click", function(event) {
		submitForm();
	});
});

