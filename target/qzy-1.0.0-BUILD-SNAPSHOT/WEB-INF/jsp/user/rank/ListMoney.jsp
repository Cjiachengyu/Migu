<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>财富榜</title>
<link href="${resRoot}/css/user/userRankList.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_tab_holder">
    <a class="top_tab tab_not_selected" href="rankrightanswer" ><img class="title_img" src="${resRoot}/image/user/rank/title_right_answer.png">学霸榜</a>
	<a class="top_tab tab_selected"><img class="title_img" src="${resRoot}/image/user/rank/title_money_selected.png">财富榜</a>
    <!--
    <a class="top_tab tab_not_selected" href="ranksentmoney" ><img class="title_img" src="${resRoot}/image/user/rank/title_sent_money.png">豪气榜</a>
    -->
    <%-- <a class="top_tab tab_not_selected" href="rankreturnedmoney" ><img class="title_img" src="${resRoot}/image/user/rank/title_sent_money.png">返币榜</a> --%>
    <a class="top_tab tab_not_selected" href="rankspentmoney"><img class="title_img" src="${resRoot}/image/user/rank/title_sent_money.png">豪气榜</a>
	<div class="clear"></div>
</div>

<div class="user_info_holder">
    <img class="user_portrait" src="${viewModel.user.portrait}" >
    <div class="left_info">
        <div>我的财富</div>
        <div class="color_red" >${viewModel.user.money}币</div>
    </div>
    <div class="right_info">
        <div>排名</div>
        <div class="color_red">${viewModel.myMoneyRank}</div>
    </div>
</div>

<div class="list_holder">
	<c:forEach items="${viewModel.userMoneyRankList}" var="item" varStatus="status">
		<div class="list_item">
			<div class="list_item_index <c:if test="${status.index == 0}">list_item_index_first</c:if>"><span>${status.index + 1}</span></div>
			<div class="list_item_portrait"><img src="${item.portrait}"></div>
			<div class="list_item_name"><span>${item.userName}</span></div>
			<div class="list_item_count"><span>${item.money}币</span></div>
			<div class="clear"></div>
		</div>
	</c:forEach>
</div>


<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

