<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>抢红包</title>
<link href="${resRoot}/css/user/tuiguang/alreadyGrabTuiGuangBag.css?version=${resVersion}" rel="stylesheet"/>

<div class="grab_bag_info_holder">
    
    <div class="grab_tip">
		<span class="result_tip">您已领取新手红包</span>
    </div>
</div>

<script>
    setTimeout(function () {
        window.location.href = '/qzy/user/home/index';
    }, 1500)
</script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>


