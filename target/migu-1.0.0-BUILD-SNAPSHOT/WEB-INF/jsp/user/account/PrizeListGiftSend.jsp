<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>我的账户</title>
<link href="${resRoot}/css/user/userAccount.css?version=${resVersion}" rel="stylesheet"/>

<%@include file="subview/UserInfo.jsp" %>

<div class="sub_tab_holder clearfix">
    <a class="sub_tab sub_tab_not_selected" href="billrecord">收支</a>
    <a class="sub_tab sub_tab_not_selected" href="prizelist">奖品</a>
    <a class="sub_tab sub_tab_not_selected" href="giftreceive">收礼</a>
    <a class="sub_tab sub_tab_selected">送礼</a>
</div>

<input type="hidden" id="myMoney" value="${viewModel.user.money}">

<div>
    <c:forEach items="${viewModel.giftSendList }" var="gift">
        <div class="prize_holder">
            <div class="prize_image_holder">
                <img class="prize_image" src="${gift.prize.prizeImageUrl}">
            </div>
            <div class="prize_name_money_holder">
                <span class="prize_name">${gift.prize.prizeName}</span>
                <c:if test="${gift.receiver != null}"><span class="prize_count">送给了：${gift.receiver.userName}</span></c:if>
                <c:if test="${gift.receiver == null}"><span class="prize_count">没人领取</span></c:if>
            </div>
            <div class="prize_operation_holder">
            <c:if test="${gift.receiver != null}">
            	<span class="operation_button operation_button_one" onclick="showGift(${gift.giftId},1);">显摆</span>
            </c:if>
            <c:if test="${gift.receiver == null}">
            	<span class="operation_button operation_button_one" onclick="reSendGift(${gift.giftId});">重送</span>
            </c:if>
            </div>
            <div class="clear"></div>
        </div>
    </c:forEach>
</div>

<script src='${resRoot}/js/user/userPrizeList.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

