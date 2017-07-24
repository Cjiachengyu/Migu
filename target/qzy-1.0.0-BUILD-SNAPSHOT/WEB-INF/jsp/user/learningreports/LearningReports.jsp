<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>学习报告</title>
<link href="${resRoot}/css/user/userLearningReports.css?version=${resVersion}" rel="stylesheet"/>

<div id="learning_reports_detail_view">
        <%@include file="subview/LearningReportsDetail.jsp" %>
</div>

<script src='${resRoot}/js/user/userLearningReports.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
