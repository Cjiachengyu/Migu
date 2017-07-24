$(function() {
	$.ajax({
		'url' : 'getdisplaydata',
		'type' : 'post',
		'dataType' : 'json',
		'success' : function(data, statusText) {

			var indexDate = data.indexDate;
			var dateAllQueList = data.dateAllQueList;
			var dateRightQueList = data.dateRightQueList;
			var dateRightPercent = data.dateRightPercent;

			$('#container').highcharts({
				chart : {
					type : 'spline'
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					categories : indexDate
				},
				yAxis : {
					title : {
						text : ''
					},
					min : 0
				},
				tooltip : {
					shared : true,
					crosshairs : [ {
						width : 1,
						color : 'red'
					} ]

				},
				plotOptions : {
					spline : {
						dataLabels : {
							enabled : true
						},
						enableMouseTracking : true
					}
				},
				series : [ {
					name : '答题总数',
					data : dateAllQueList
				}, {
					name : '答对题数',
					data : dateRightQueList,
					color : '#21C027'
				} ]
			});

			$('#rateContainer').highcharts({
				chart : {
					type : 'spline'
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					categories : indexDate
				},
				yAxis : {
					title : {
						text : ''
					},
					min : 0
				},
				tooltip : {
					shared : true,
					valueSuffix : '%',
					crosshairs : [ {
						width : 1,
						color : 'red'
					} ],
					headerFormat : '<span style="font-size:11px">{point.x} {series.name}</span>',
					pointFormat : ': <b>{point.y:.1f}%</b><br/>'
				},
				plotOptions : {
					spline : {
						dataLabels : {
							enabled : true,
							format : '{point.y:.1f}%'
						},
						enableMouseTracking : true
					}
				},
				series : [ {
					name : '正确率',
					data : dateRightPercent
				} ]
			});

		},
		'error' : function(xhr, e1, e2) {
		}
	});

});
