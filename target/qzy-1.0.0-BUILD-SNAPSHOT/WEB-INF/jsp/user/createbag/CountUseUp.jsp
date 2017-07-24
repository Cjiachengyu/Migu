<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>发作业</title>
<link href="${resRoot}/css/user/countUseUp.css?version=${resVersion}" rel="stylesheet"/>

<div class="out_time_holder">
	<span class="out_time_tip">您今天已经发了3个红包，明天再来吧！</span>
</div>

<script>
    setTimeout(function () {
        window.location.href = '/qzy/user/baglist/listmysent';
    }, 1500)
</script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

