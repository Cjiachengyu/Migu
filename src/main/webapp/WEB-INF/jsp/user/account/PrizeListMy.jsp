<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>我的账户</title>
<link href="${resRoot}/css/user/userAccount.css?version=${resVersion}" rel="stylesheet"/>

<%@include file="subview/UserInfo.jsp" %>

<div class="sub_tab_holder clearfix">
    <a class="sub_tab sub_tab_not_selected" href="billrecord">收支</a>
    <a class="sub_tab sub_tab_selected">奖品</a>
    <!-- <a class="sub_tab sub_tab_not_selected" href="giftreceive">收礼</a>
    <a class="sub_tab sub_tab_not_selected" href="giftsend">送礼</a> -->
    <a class="sub_tab sub_tab_not_selected" href="giftlist">礼物</a>
</div>

<input type="hidden" id="myMoney" value="${viewModel.user.money}">

<div>
    <c:forEach items="${viewModel.userPrizeList}" var="item">
    	<c:if test="${item.prizeCount > 0}">
    		 <div class="prize_holder">
	            <div class="prize_image_holder">
	                <img class="prize_image" src="${item.prize.prizeImageUrl}">
	            </div>
	            <div class="prize_name_money_holder">
	                <span class="prize_name">${item.prize.prizeName}</span>
	                <span class="prize_count">${item.prizeCount}个</span>
	            </div>
	            <div class="prize_operation_holder">
	                <span class="operation_button operation_button_left" onclick="showPrize(${item.prize.prizeId});">显摆</span><span class="operation_button operation_button_right" onclick="sendGift(${item.prize.prizeId});">送礼</span>
	            </div>
	            <div class="clear"></div>
	        </div>
    	</c:if>
    </c:forEach>
</div>

<script src='${resRoot}/js/user/userPrizeList.js?version=${resVersion}' type='text/javascript'></script>


<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

