$(function() {
	var operationType = $("#operationType").val();
	if (operationType == 2) {
		$("#prize_name").val($("#old_prize_name").val());
		$("#prize_money").val($("#old_prize_money").val());
		$("#prize_image").attr("src", $("#old_prize_image").val());
	}
});

function checkPrizeImage() {
	var prizeImage = $("#prizeImage").val();

	if (prizeImage !== "") {
		var array = prizeImage.split(".");
		var type = array[array.length - 1];
		type = type.toLowerCase();
		if (type != "jpg" && type != "png" && type != "gif") {
			AlertDialog("文件只能是 .jpg/.png/.gif");
			$("#file_text_field").val("");
		}
		else {
			var form = $("#submitPrizeImage");

			var options = {
				url : "/migu/editor/prizeedit/uploadprizeimage",
				type : 'post',
				success : function(result) {

					$("#prize_image").attr("src", result.imageSrc);
				}
			};
			form.ajaxSubmit(options);
		}
	}
}

function createPrize() {
	var prizeName = $("#prize_name").val();
	var prizeMoney = $("#prize_money").val();
	var prizeImage = $("#prize_image").attr("src");

	if (prizeName == "" || prizeName == null) {
		AlertDialog("请填写奖品名称");
		return false;
	}

	if (prizeMoney == "" || prizeMoney == null) {
		AlertDialog("请填写奖品价格");
		return false;
	}

	if (!isInteger(prizeMoney)) {
		AlertDialog("奖品价格必须是整数");
		return false;
	}

	if (prizeImage == "") {
		AlertDialog("请上传奖品图像");
		return false;
	}

	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		url : "/migu/editor/prizeedit/docreateprize",
		data : {
			prizeName : prizeName,
			prizeMoney : prizeMoney,
			prizeImage : prizeImage,
		},
		success : function(result) {
			$("#old_prize_name").val("");
			$("#old_prize_money").val("");
			$("#old_prize_image").val("");
			$("#prize_name").val("");
			$("#prize_money").val("");
			$("#prize_image").attr("src", "");
			AlertDialog("录入成功！");
		},
		error : function() {
			AlertDialog("出现错误！");
		}
	});
}
