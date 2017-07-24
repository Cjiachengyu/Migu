<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>发出的作业</title>
<link href="${resRoot}/css/user/userBagList.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_tab_holder">
    <a class="top_tab tab_not_selected" href="listmygot"><img class="title_img" src="${resRoot}/image/user/baglist/title_got_bag.png">抢到的红包</a>
    <a class="top_tab tab_selected"><img class="title_img" src="${resRoot}/image/user/baglist/title_sent_bag_selected.png">发出的作业</a>
    <div class="clear"></div>
</div>

<div class="user_info_holder">
    <span>共发出${viewModel.mySentBagList.size()}份作业，总计${viewModel.mySentBagMoney}币</span>
</div>

<div class="bag_list_holder">
    <c:forEach items="${viewModel.mySentBagList}" var="item" varStatus="status">
        <a class="bag_list_item<c:if test="${status.index % 2 == 0}"> bag_list_item_even</c:if>" href="/qzy/user/grabbag/${item.bagId}/startgrab">
            <img class="bag_list_item_icon" src="${resRoot}/image/user/baglist/bag_icon.png">
            <div class="bag_list_item_left_info">
                <div class="bag_list_item_left_info_zsd">${item.zsdCatalog.zsdString}</div>
                <div class="bag_list_item_left_info_time">${item.createTimeString}</div>
            </div>
            <div class="bag_list_item_right_info">
                <div class="bag_list_item_right_info_money">${item.bagMoney}币</div>
                <div class="bag_list_item_right_info_count">${item.sentCount}/${item.bagCount}个</div>
            </div>
            <div class="clear"></div>
        </a>
    </c:forEach>
</div>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

