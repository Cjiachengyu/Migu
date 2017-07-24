$(function() {
	$.ajax({
		'url' : 'getdisplaydata',
		'type' : 'post',
		'dataType' : 'json',
		'success' : function(data, statusText) {
			var indexDate = data.indexDate;
			var userCountByDate = data.userCountByDate;
			var queCountByDate = data.queCountByDate;
			var queRightPercentByDate = data.queRightPercentByDate;
			var consumeTimeByDate = data.consumeTimeByDate;

			var userPercentByZsk = data.userPercentByZsk;
			var quesPercentByZsk = data.quesPercentByZsk;
			var rightQuesPercentByZsk = data.rightQuesPercentByZsk;
			var consumeTimePercentByZsk = data.consumeTimePercentByZsk;

			$('#userCountByDateContainer').highcharts({
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
					name : '参与人数',
					data : userCountByDate
				} ]
			});

			// zsk
			$('#userPercentByZskContainer').highcharts({
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
							distance : 5,
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
					data : userPercentByZsk
				} ]
			});

			$('#queCountByDateContainer').highcharts({
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
					name : '答题数',
					data : queCountByDate
				} ]
			});

			// zsk
			$('#quesPercentByZskContainer').highcharts({
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
							distance : 5,
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
					data : quesPercentByZsk
				} ]
			});

			$('#queRightPercentByDateContainer').highcharts({
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
					name : '答题正确率',
					data : queRightPercentByDate
				} ]
			});

			// zsk
			$('#rightQuesPercentByZskContainer').highcharts({
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
							distance : 5,
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
					data : rightQuesPercentByZsk
				} ]
			});

			$('#consumeTimeByDateContainer').highcharts({
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
						text : '小时'
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
					name : '答题时间',
					data : consumeTimeByDate
				} ]
			});

			// zsk
			$('#consumeTimePercentByZskContainer').highcharts({
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
							distance : 5,
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
					data : consumeTimePercentByZsk
				} ]
			});

		},
		'error' : function(xhr, e1, e2) {
		}
	});

});
