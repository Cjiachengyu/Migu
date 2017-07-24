<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>我的账户</title>
<link href="${resRoot}/css/user/userAccount.css?version=${resVersion}" rel="stylesheet"/>

<%@include file="subview/UserInfo.jsp" %>

<div class="sub_tab_holder clearfix">
    <a class="sub_tab sub_tab_selected">收支</a>
    <a class="sub_tab sub_tab_not_selected" href="prizelist">奖品</a>
    <!-- <a class="sub_tab sub_tab_not_selected" href="giftreceive">收礼</a>
    <a class="sub_tab sub_tab_not_selected" href="giftsend">送礼</a> -->
    <a class="sub_tab sub_tab_not_selected" href="giftlist">礼物</a>
</div>

<div class="bill_holder">
    <c:forEach items="${viewModel.billList}" var="bill">
        <div class="bill_item" onclick="Alert('${bill.description}');">
            <span class="create_time">${bill.createTimeString}</span>
            <c:if test="${bill.billType == 1 || bill.billType == 2 || bill.billType == 3 || bill.billType == 4}">
                <span class="money">-${bill.money}</span>
            </c:if>
            <c:if test="${bill.billType != 1 && bill.billType != 2 && bill.billType != 3 && bill.billType != 4}">
                <span class="money">+${bill.money}</span>
            </c:if>

            <span class="description">${bill.description}</span>
            <div class="clear"></div>
        </div>
    </c:forEach>
</div>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

