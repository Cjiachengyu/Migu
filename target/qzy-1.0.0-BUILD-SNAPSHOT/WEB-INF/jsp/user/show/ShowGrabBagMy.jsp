<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>${viewModel.showGrabBag.shower.userName}抢了个大红包</title>
<link href="${resRoot}/css/user/userShowGrabBag.css?version=${resVersion}" rel="stylesheet"/>

<div id="animation_img_holder" class="animation_img_holder">
    <img class="show_grab_bag_win_bg_glow" src="${resRoot}/image/user/show/grabbag/win/bg_glow.png?version=${resVersion}">
    <img class="show_grab_bag_win_bg_big" src="${resRoot}/image/user/show/grabbag/win/bg_big.png?version=${resVersion}">
    <img class="show_grab_bag_win_body" src="${resRoot}/image/user/show/grabbag/win/body.png?version=${resVersion}">
    <img class="show_grab_bag_win_arm" src="${resRoot}/image/user/show/grabbag/win/arm.png?version=${resVersion}">
    <img class="show_grab_bag_win_eyes" src="${resRoot}/image/user/show/grabbag/win/eyes.png?version=${resVersion}">
    <img class="show_grab_bag_win_bubble" src="${resRoot}/image/user/show/grabbag/win/bubble.png?version=${resVersion}">
    <img class="show_grab_bag_win_portrait" src="${viewModel.showGrabBag.shower.portrait}">
    <div class="show_grab_bag_win_text_holder">${viewModel.showGrabBag.shower.userName}：我抢到一个大红包，里面有<span class="show_grab_bag_win_text_money">${viewModel.showGrabBag.userBag.gotMoney}</span>个作业币！</div>
    <img class="show_grab_bag_win_flag" src="${resRoot}/image/user/show/grabbag/win/flag.png?version=${resVersion}">
</div>

<div class="show_action hidden">
	<span onclick="grabBag(${viewModel.showGrabBag.bagId});">抢此红包</span>
	<span>阅</span><span>${viewModel.showActionList.size()}</span>
	
	<span support="<c:if test="${viewModel.myActionType == 1}">yes</c:if>" class="support_btn <c:if test="${viewModel.myActionType == 1}">support_select</c:if>"  onclick="support(${viewModel.showGrabBag.showId});">赞</span>
	<span class="support_count">${viewModel.support}</span>
	
	<span opposite="<c:if test="${viewModel.myActionType == 2}">yes</c:if>" class="opposite_btn <c:if test="${viewModel.myActionType == 2}">opposite_select</c:if>" onclick="opposite(${viewModel.showGrabBag.showId});">踩</span>
	<span class="opposite_count">${viewModel.opposite}</span>
</div>

<input type="hidden" id="myMoney" value="${viewModel.user.money}">
<div class="praise_laugh_holder">
    <span class="goto_action">收到${viewModel.praiseOrlaughCount}个赞，我也<img class="goto_action_img" onclick="praiseUser(${viewModel.showGrabBag.userId});" src="${resRoot}/image/user/show/praise_in_show.png">一下</span>
    <span class="goto_bag_btn" onclick="grabBag(${viewModel.showGrabBag.bagId});">我也抢一下</span>
</div>

<script src='${resRoot}/js/common/userShowAdjustImg.js?version=${resVersion}' type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

