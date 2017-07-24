<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>${viewModel.showGift.gift.receiver.userName}收到了${viewModel.showGift.gift.sender.userName}送的${viewModel.showGift.gift.prize.prizeName}！</title>
<link href="${resRoot}/css/user/userShowGrabBag.css?version=${resVersion}" rel="stylesheet"/>

<style>
    .show_img { width: 100%; }
    .show_tip { position: absolute; top: 44px; left: 149px; width: 120px; display: inline-block; transform: rotate(-9deg); -webkit-transform: rotate(-9deg);}
    .shower_portrait { position: absolute; top: 61px; left: 90px; width: 45px; border-radius: 100px; display: inline-block; transform: rotate(-9deg); -webkit-transform: rotate(-9deg); }
</style>

<%-- <div class="show_action">
	<span>阅</span><span>${viewModel.showActionList.size()}</span>
	
	<span support="<c:if test="${viewModel.myActionType == 1}">yes</c:if>" class="support_btn <c:if test="${viewModel.myActionType == 1}">support_select</c:if>"  onclick="support(${viewModel.showGrabBag.showId});">赞</span>
	<span class="support_count">${viewModel.support}</span>
	
	<span opposite="<c:if test="${viewModel.myActionType == 2}">yes</c:if>" class="opposite_btn <c:if test="${viewModel.myActionType == 2}">opposite_select</c:if>" onclick="opposite(${viewModel.showGrabBag.showId});">踩</span>
	<span class="opposite_count">${viewModel.opposite}</span>
</div> --%>

<div id="animation_img_holder" class="animation_img_holder">
    <img class="show_img" src="${resRoot}/image/user/show/gift/receive/show_receive_gift.png?version=${resVersion}">
    <div class="show_tip">${viewModel.showGift.shower.userName}：${viewModel.showGift.gift.receiver.userName}收到了${viewModel.showGift.gift.sender.userName}送的${viewModel.showGift.gift.prize.prizeName}！</div>
    <img class="shower_portrait" src="${viewModel.showGift.shower.portrait}">
</div>

<script src='${resRoot}/js/common/userShowAdjustImg.js?version=${resVersion}' type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
