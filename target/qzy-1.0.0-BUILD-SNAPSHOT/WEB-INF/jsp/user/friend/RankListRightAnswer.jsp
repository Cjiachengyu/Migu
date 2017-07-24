<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>学霸榜</title>
<link href="${resRoot}/css/user/userFriendRankList.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_bar_holder">
    <span class="top_bar_title">本周好友榜</span>
    <a class="all_rank_link" href="/qzy/user/rank/rankrightanswer">查看总排行</a>
</div>

<div class="sub_tab_holder">
    <a class="sub_tab sub_tab_selected" >学霸榜</a>
	<a class="sub_tab sub_tab_not_selected" href="rankmoney">财富榜</a>
    <a class="sub_tab sub_tab_not_selected" href="rankspentmoney">豪气榜</a>
	<div class="clear"></div>
</div>

<div class="rank_info_holder">
    <span class="rank_info">${viewModel.user.userName} 本周答对 <span class="orange_red">${viewModel.user.rightAnswerCount}题</span>，排名<span class="orange_red"> 第${viewModel.myRightAnswerRank}</span></span>
</div>

<div class="list_holder">
	<c:forEach items="${viewModel.userRightAnswerRankList}" var="item" varStatus="status">
		<div class="list_item">
			<div class="list_item_index"><span>${status.index + 1}</span></div>
			<div class="list_item_portrait"><img src="${item.portrait}"></div>
			<div class="list_item_name"><span>${item.userName}</span></div>
			<div class="list_item_count_right_answer">
                <span class="list_item_count_right_answer_row1" >${item.weekRightAnswerCount}题</span>
                <span class="list_item_count_right_answer_row2" >正确率${item.weekRightPercent}%</span>
            </div>
			<div class="clear"></div>
		</div>
	</c:forEach>
</div>

<c:if test="${viewModel.userRightAnswerRankList.size() <= 3}">
    <%@include file="subview/FewFriendsTip.jsp" %>
</c:if>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

