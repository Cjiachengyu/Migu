<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>奖品兑换</title>
<link href="${resRoot}/css/user/prize/prizeExchange.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_bar_holder">
    <span class="top_bar_title">奖品兑换</span>
</div>

<div>
	<span onclick="lottery(${viewModel.user.money});">参与抽奖</span>
</div>

<div class="prize_exchange_list_holder">
    <c:forEach items="${viewModel.prizeList }" var="item">
        <div class="prize_holder">
            <div class="prize_image_holder">
                <img class="prize_image" src="${item.prizeImageUrl}">
            </div>
            <div class="prize_name_money_holder">
                <span class="prize_name">${item.prizeName}</span>
                <span class="prize_money">${item.prizeMoney}币</span>
            </div>
            <div class="prize_operation_holder">
                <span class="prize_operation_button" onclick="javascript:prizeExchange(${item.prizeId},'${item.prizeName}',${item.prizeMoney},${viewModel.user.money});">兑换</span>
            </div>
            <div class="clear"></div>
        </div>
    </c:forEach>
</div>


<div id="prizeExchange" class="hidden">
    <div class="prize_popup_holder">
        <div></div>
    </div>
</div>
<div id="myMoney" class="hidden">
    <div class="money_popup_holder">
        <div></div>
    </div>
</div>

<div id="prizeExchangeButton" class="hidden">
    <div class="popup_btn_holder">
        <a onclick="javascript:confirmExchangePrize()">确定兑换</a>
    </div>
</div>

<div id="lotteryButton" class="hidden">
    <div class="popup_btn_holder">
        <a onclick="javascript:confirmLottery()">确定抽奖</a>
    </div>
</div>

<div id="prizeData" class="hidden"></div>


<script src='${resRoot}/js/user/userPrizeExchange.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

