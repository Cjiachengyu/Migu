<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>学习报告</title>
<link href="${resRoot}/css/user/userStudyReport.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_bar_holder">
    <span class="top_bar_title">学习报告</span>
</div>

<script src='${resRoot}/thirdparty/exporting.js' type='text/javascript'></script>
<script src='${resRoot}/thirdparty/highcharts.js' type='text/javascript'></script>

<div class="chart_title_holder">
	<span>每日答题数</span>
</div>
<div id="container" class="container"></div>

<div class="chart_title_holder">
	<span>每日正确率</span>
</div>
<div id="rateContainer" class="container"></div>



<script src='${resRoot}/js/user/userStudyReportTrend.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

