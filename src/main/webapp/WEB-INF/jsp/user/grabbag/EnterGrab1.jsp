<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>抢红包</title>
<link href="${resRoot}/css/user/grabbag/enterGrab.css?version=${resVersion}" rel="stylesheet"/>

<div class="grab_bag_info_holder">
    <div class="grab_bag_money_info">${viewModel.bag.bagMoney}作业币</div>
    <div class="grab_bag_zsd_info">${viewModel.bag.zsdCatalog.zsdString}</div>
    <div class="grab_bag_left_info">还剩<span class="grab_bag_left_info_count">${viewModel.bag.bagCount - viewModel.bag.sentCount}</span>个红包</div>
</div>

<a class="btn_start_answer" href="gotoanswering">开始答题</a>

<script src='${resRoot}/js/user/grabbag/userGrabBag.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

