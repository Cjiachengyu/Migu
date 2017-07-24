<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>抢红包</title>
<link href="${resRoot}/css/user/grabbag/grabPageCommon.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/enterGrab.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/moneyInfo.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/grabbedUserList.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/induceInfo.css?version=${resVersion}" rel="stylesheet"/>

<div class="grab_bag_info_holder">
    <img class="grab_bag_face_img" src="${resRoot}/image/user/grabbag/bag_bg.png">
    <div class="grab_bag_left_info">还剩<span class="grab_bag_left_info_count">${viewModel.bag.bagCount - viewModel.bag.sentCount}</span>个红包</div>

    <div class="grab_bag_money_info">${viewModel.bag.bagMoney}作业币</div>
    <div class="grab_bag_zsd_info">${viewModel.bag.zsdCatalog.zsdString}</div>

    <%@include file="subview/MoneyInfo.jsp" %>

    <div class="operation_btn_holder">
        <a class="orange_2d_btn operation_btn_wide" href="gotoanswering">开始答题</a>
    </div>

</div>

<%@include file="subview/GrabbedUserList.jsp" %>

<img class="induce_img" src="${resRoot}/image/user/grabbag/induce_dark.png?v=1">

<script src='${resRoot}/js/user/grabbag/userGrabBag.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>


