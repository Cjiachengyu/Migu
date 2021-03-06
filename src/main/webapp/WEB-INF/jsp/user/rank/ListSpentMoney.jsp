<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>豪气榜</title>
<link href="${resRoot}/css/user/userRankList.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_tab_holder">
    <a class="top_tab tab_not_selected" href="rankrightanswer" ><img class="title_img" src="${resRoot}/image/user/rank/title_right_answer.png">学霸榜</a>
	<a class="top_tab tab_not_selected" href="rankmoney"><img class="title_img" src="${resRoot}/image/user/rank/title_money.png">财富榜</a>
    <!--
    <a class="top_tab tab_selected"><img class="title_img" src="${resRoot}/image/user/rank/title_sent_money_selected.png">豪气榜</a>
    -->
    <%-- <a class="top_tab tab_not_selected" href="rankreturnedmoney" ><img class="title_img" src="${resRoot}/image/user/rank/title_sent_money.png">返币榜</a> --%>
    <a class="top_tab tab_selected"><img class="title_img" src="${resRoot}/image/user/rank/title_sent_money_selected.png">豪气榜</a>
	<div class="clear"></div>
</div>

<div class="user_info_holder">
    <img class="user_portrait" src="${viewModel.user.portrait}" >
    <div class="left_info">
        <div>累计消费</div>
        <div class="color_red" >${viewModel.user.spentMoney}币</div>
    </div>
    <div class="right_info">
        <div>排名</div>
        <div class="color_red">${viewModel.mySpentMoneyRank}</div>
    </div>
</div>

<div class="list_holder">
	<c:forEach items="${viewModel.userSpentMoneyRankList}" var="item" varStatus="status">
		<div class="list_item">
			<div class="list_item_index <c:if test="${status.index == 0}">list_item_index_first</c:if>"><span>${status.index + 1}</span></div>
			<div class="list_item_portrait"><img src="${item.portrait}"></div>
			<div class="list_item_name"><span>${item.userName}</span></div>
			<div class="list_item_count"><span>${item.spentMoney}币</span></div>
			<div class="clear"></div>
		</div>
	</c:forEach>
</div>


<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

