<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>${viewModel.shareInfo.shareMsg}</title>
<link href="${resRoot}/css/user/userGrabBag.css?version=${resVersion}" rel="stylesheet"/>

<input id="share_title" type="hidden" value="${viewModel.shareInfo.shareTitle}">
<input id="share_msg" type="hidden" value="${viewModel.shareInfo.shareMsg}">
<input id="share_link" type="hidden" value="${viewModel.shareInfo.shareLink}">
<input id="share_image" type="hidden" value="${viewModel.shareInfo.shareImage}">

<input id="sucess_tip" type="hidden" value="${viewModel.shareInfo.sucessTip}">
<input id="success_callback" type="hidden" value="${viewModel.shareInfo.successCallback}">

<div class='share_tip_holder'>
    <span id="share_tip" class="share_tip">请稍候...</span>
</div>

<div class="share_tip_mask_holder">
    <img id="share_tip_mask" class="share_tip_mask" src="${viewModel.shareInfo.sucessImage}">
</div>

<script src='${resRoot}/js/common/userWeixinShare.js?version=${resVersion}' type="text/javascript"></script>
<script src='${resRoot}/js/user/share.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

