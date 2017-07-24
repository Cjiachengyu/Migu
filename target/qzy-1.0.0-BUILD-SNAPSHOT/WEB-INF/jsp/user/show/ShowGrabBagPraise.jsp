<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>赞美页面</title>

<style>

    .show_img { width: 100%; }
        
	.show_grab_bag_win_portrait { width: 60px; z-index: 3; border-radius: 60px; position: absolute; left: 45px; top: 68px;
	    transform: rotate(-15deg);
	    -webkit-transform: rotate(-15deg);
	}
	
	.show_grab_bag_win_text_holder { width: 150px; z-index: 3; position: absolute; left: 125px; top: 65px;
	transform: rotate(-5deg);
    -webkit-transform: rotate(-5deg);
	}
	
</style>

<div id="animation_img_holder" class="animation_img_holder">
    <img class="show_img" src="${resRoot}/image/user/show/grabbag/praise/show_praise.png?version=${resVersion}">
    <img class="show_grab_bag_win_portrait" src="${viewModel.showGrabBag.userBag.user.portrait}">
	<div class="show_grab_bag_win_text_holder">${viewModel.showGrabBag.userBag.user.userName}全部答对了！真是学霸！</div>
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
