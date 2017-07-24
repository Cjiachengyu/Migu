<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>接收礼物</title>
<link href="${resRoot}/css/user/userReceiveGift.css?version=${resVersion}" rel="stylesheet"/>

<div id="animation_img_holder" class="animation_img_holder">
    <div class="receive_gift_text"><span class="red">${viewModel.gift.sender.userName}</span>送给你一个<span class="red">${viewModel.gift.prize.prizeName}！</span></div>
    <img class="receive_gift_bg_back" src="${resRoot}/image/user/prize/receive_gift_back.png?version=${resVersion}">
    <img class="receive_gift_img" src="${viewModel.gift.prize.prizeImageUrl}">
    <img class="receive_gift_bg_front" src="${resRoot}/image/user/prize/receive_gift_front.png?version=${resVersion}">
</div>

<script src='${resRoot}/js/common/userShowAdjustImg.js?version=${resVersion}' type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
