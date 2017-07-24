<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>${viewModel.showPrize.shower.userName}收藏了一个${viewModel.showPrize.prize.prizeName}！</title>
<link href="${resRoot}/css/user/userShowGrabBag.css?version=${resVersion}" rel="stylesheet"/>

<style>
    .show_img { width: 100%; }
    .show_tip { position: absolute; top: 26px; left: 0; right: 0; text-align: center; display: inline-block;}
    .prize_name { position: absolute; color: white; top: 127px; left: 0; right: 0; text-align: center; display: inline-block; font-size: 28px;}
    .prize_img { position: absolute; width: 200px; height: 200px; top: 208px; left: 84px; display: inline-block;}
</style>

<%-- <div class="show_action">
	<span>阅</span><span>${viewModel.showActionList.size()}</span>
	
	<span support="<c:if test="${viewModel.myActionType == 1}">yes</c:if>" class="support_btn <c:if test="${viewModel.myActionType == 1}">support_select</c:if>"  onclick="support(${viewModel.showGrabBag.showId});">赞</span>
	<span class="support_count">${viewModel.support}</span>
	
	<span opposite="<c:if test="${viewModel.myActionType == 2}">yes</c:if>" class="opposite_btn <c:if test="${viewModel.myActionType == 2}">opposite_select</c:if>" onclick="opposite(${viewModel.showGrabBag.showId});">踩</span>
	<span class="opposite_count">${viewModel.opposite}</span>
</div> --%>

<div id="animation_img_holder" class="animation_img_holder">
    <img class="show_img" src="${resRoot}/image/user/show/prize/show_prize.png?version=${resVersion}">
    <div class="show_tip">${viewModel.showPrize.shower.userName}收藏了一个${viewModel.showPrize.prize.prizeName}！</div>
    <div class="prize_name">${viewModel.showPrize.prize.prizeName}</div>
    <img class="prize_img" src="${viewModel.showPrize.prize.prizeImageUrl}">
</div>


<script src='${resRoot}/js/common/userShowAdjustImg.js?version=${resVersion}' type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
