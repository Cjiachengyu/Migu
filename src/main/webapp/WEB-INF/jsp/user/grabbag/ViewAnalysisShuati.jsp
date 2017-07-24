<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>刷题-查看解析</title>
<link href="${resRoot}/css/user/grabbag/subview/queNavigate.css?version=${resVersion}" rel="stylesheet"/>

<c:set var="isViewingAnalysis" value="${true}"/>
<%@include file="subview/QueNavigate.jsp"%>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

