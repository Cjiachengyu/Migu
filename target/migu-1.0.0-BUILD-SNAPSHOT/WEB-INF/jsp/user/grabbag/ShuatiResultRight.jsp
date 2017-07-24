<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>刷题</title>
<link href="${resRoot}/css/user/grabbag/grabPageCommon.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/shuatiResultRight.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/moneyInfo.css?version=${resVersion}" rel="stylesheet"/>

<div class="grab_bag_info_holder">
    <img class="grab_bag_title_img" src="${resRoot}/image/user/grabbag/title_shuati_right.png?v=2">
    <img class="grab_bag_face_img" src="${resRoot}/image/user/grabbag/face_shuati_right.png">

    <div class="money_change_tip">
        <span class="money_change_tip_text">全部答对，奖励<span class="money_change_tip_money">${viewModel.userBag.gotMoney}</span>作业币</span>
    </div>

    <div class="money_change_tip_money_all_holder">
        <span class="money_change_tip_money_all">我的财富变为：${viewModel.user.money}作业币</span>
    </div>

    <%@include file="subview/MoneyInfo.jsp" %>

    <div class="operation_btn_holder">
        <a class="orange_2d_btn operation_btn" href="javascript:viewAnalysis();">查看解析</a>
        <a class="orange_2d_btn operation_btn" href="/migu/user/createbag/gotoshuati?zsk=${viewModel.bag.zskId}&zsd1=${viewModel.bag.zsd1Id}&zsd2=${viewModel.bag.zsd2Id}&difficulty=0">继续刷题</a>
    </div>
</div>

<script src='${resRoot}/js/user/grabbag/userGrabBag.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>


