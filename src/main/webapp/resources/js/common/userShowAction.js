function support(showId) {
	$.ajax({
		type : "post",
		url : "support",
		data : {
			showId : showId,
		},
		success : function(result) {
			var support = $(".support_btn").attr("support");
			if (support == "yes") {
				$(".support_btn").attr("support", "no");
				$(".support_count").text(parseInt($(".support_count").text()) - 1);
				$(".support_btn").removeClass("support_select");
			}
			else {
				$(".support_btn").attr("support", "yes");
				$(".support_count").text(parseInt($(".support_count").text()) + 1);
				$(".support_btn").addClass("support_select");

				var opposite = $(".opposite_btn").attr("opposite");
				if (opposite == "yes") {
					$(".opposite_btn").attr("opposite", "");
					$(".opposite_count").text(parseInt($(".opposite_count").text()) - 1);
					$(".opposite_btn").removeClass("opposite_select");
				}
			}

		}
	});
}

function opposite(showId) {
	$.ajax({
		type : "post",
		url : "opposite",
		data : {
			showId : showId,
		},
		success : function(result) {
			var opposite = $(".opposite_btn").attr("opposite");
			if (opposite == "yes") {
				$(".opposite_btn").attr("opposite", "no");
				$(".opposite_count").text(parseInt($(".opposite_count").text()) - 1);
				$(".opposite_btn").removeClass("opposite_select");
			}
			else {
				$(".opposite_btn").attr("opposite", "yes");
				$(".opposite_count").text(parseInt($(".opposite_count").text()) + 1);
				$(".opposite_btn").addClass("opposite_select");

				var support = $(".support_btn").attr("support");
				if (support == "yes") {
					$(".support_btn").attr("support", "");
					$(".support_count").text(parseInt($(".support_count").text()) - 1);
					$(".support_btn").removeClass("support_select");
				}
			}

		}
	});
}
