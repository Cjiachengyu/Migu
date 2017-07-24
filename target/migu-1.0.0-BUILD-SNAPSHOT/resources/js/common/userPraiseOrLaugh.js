function praiseUser(praiseUserId) {

	var myMoney = $("#myMoney").val();
	if (myMoney < 10) {
		Alert("您的作业币不够");
		return;
	}
	Confirm("花费10个作业币赞美一下？", function() {
		$("#myMoney").val(myMoney - 10);
		window.location.href = "/migu/user/show/praiseuser";
	});

}

function laughUser(laughUserId) {

	var myMoney = $("#myMoney").val();
	if (myMoney < 10) {
		Alert("您的作业币不够");
		return;
	}
	Confirm("花费10个作业币嘲笑一下？", function() {
		$("#myMoney").val(myMoney - 10);
		window.location.href = "/migu/user/show/laughuser";
	});

}

function grabBag(bagId) {
	window.location.href = "/migu/user/grabbag/" + bagId + "/startgrab";
}