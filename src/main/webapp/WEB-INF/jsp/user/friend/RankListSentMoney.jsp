<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>豪气榜</title>
<link href="${resRoot}/css/user/userFriendRankList.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_tab_holder">
    <a class="top_tab tab_not_selected" href="rankrightanswer" >学霸榜</a>
	<a class="top_tab tab_not_selected" href="rankmoney">财富榜</a>
    <a class="top_tab tab_selected">豪气榜</a>
	<div class="clear"></div>
</div>

<div class="user_info_holder">
    <img class="user_portrait" src="${viewModel.user.portrait}" >
    <div class="left_info">
        <div>本周发出</div>
        <div class="color_red" >${viewModel.user.sentMoney}币</div>
    </div>
    <div class="right_info">
        <div>排名</div>
        <div class="color_red">${viewModel.mySentMoneyRank}</div>
    </div>
</div>

<div class="list_holder">
	<c:forEach items="${viewModel.userSentMoneyRankList}" var="item" varStatus="status">
		<div class="list_item">
			<div class="list_item_index"><span>${status.index + 1}</span></div>
			<div class="list_item_portrait"><img src="${item.portrait}"></div>
			<div class="list_item_name"><span>${item.userName}</span></div>
			<div class="list_item_count"><span>${item.sentMoney}币</span></div>
			<div class="clear"></div>
		</div>
	</c:forEach>
</div>


<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

