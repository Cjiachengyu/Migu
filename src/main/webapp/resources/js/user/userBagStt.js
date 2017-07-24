$(function() {
	$.ajax({
		'url' : 'pullsttdata',
		'type' : 'post',
		'dataType' : 'json',
		'success' : function(data, statusText) {
			var zsdStr = data.zsdStr;
			var displayData = data.displayData;

			$('#right_count_percent').highcharts({
				colors : [ "#21c027", "#edda35", "#ed9d35", "#ed3535", ],
				chart : {
					plotBackgroundColor : null,
					plotBorderWidth : 0,
					plotShadow : false
				},
				title : {
					text : '',
					align : 'center',
					verticalAlign : 'middle',
					y : 8, // 调整中间文字的上下
					style : {
						fontSize : '16px'
					}
				},
				tooltip : {
					pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						dataLabels : {
							enabled : true,
							distance : -50,
							style : {
								textShadow : '0px 0px 2px white'
							}
						},
						center : [ '50%', '50%' ]
					}
				},
				series : [ {
					type : 'pie',
					name : '比例',
					innerSize : '0%',// 内部圆形的直径
					data : displayData
				} ]
			});
		},
		'error' : function(xhr, e1, e2) {
		}
	});

});

function disPlayCheckdUsers(obj, count) {
	var popContent = $(obj).parent().find("div.checked_users_pop_holder").html();
	if (count < 8) {
		createBorderMaskLayer("got_checked_users_window", "选择本选项的用户:", popContent, 250, 100 + count * 45);
	}
	else {
		createBorderMaskLayer("got_checked_users_window", "选择本选项的用户:", popContent, 250, 350);
	}
}
