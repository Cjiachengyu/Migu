<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>学习报告</title>
<link href="${resRoot}/css/user/userStudyStatistics.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_bar_holder">
    <span class="top_bar_title">学习报告</span>
</div>

<script src='${resRoot}/thirdparty/exporting.js' type='text/javascript'></script>
<script src='${resRoot}/thirdparty/highcharts.js' type='text/javascript'></script>

<div class="user_count">
	<span>总参与人数:${viewModel.userCount}</span>
</div>
<div class="chart_title_holder">
	<span>每日参与人数</span>
</div>
<div id="userCountByDateContainer" class="container"></div>

<div id="userPercentByZskContainer"></div>

<div class="que_count">
	<span>总答题数:${viewModel.queCount}</span>
</div>
<div class="chart_title_holder">
	<span>每日答题数</span>
</div>
<div id="queCountByDateContainer" class="container"></div>

<div id="quesPercentByZskContainer"></div>

<div class="right_percent">
	<span>答题正确率:${viewModel.rightPercent}%</span>
</div>
<div class="chart_title_holder">
	<span>每日答题正确率</span>
</div>
<div id="queRightPercentByDateContainer" class="container"></div>

<div id="rightQuesPercentByZskContainer"></div>

<div class="all_consume_time">
	<span>总答题时间:${viewModel.consumeTimeString}</span>
</div>
<div class="chart_title_holder">
	<span>每日答题时间</span>
</div>
<div id="consumeTimeByDateContainer" class="container"></div>

<div id="consumeTimePercentByZskContainer"></div>

<script src='${resRoot}/js/user/userStudyStatisticsTrend.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

