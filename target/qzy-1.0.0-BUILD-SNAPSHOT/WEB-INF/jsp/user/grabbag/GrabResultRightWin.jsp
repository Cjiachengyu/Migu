<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>抢红包</title>
<link href="${resRoot}/css/user/grabbag/grabPageCommon.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/grabResultRightWin.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/moneyInfo.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/grabbedUserList.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/user/grabbag/subview/induceInfo.css?version=${resVersion}" rel="stylesheet"/>

<div class="grab_bag_info_holder">
    <img class="grab_bag_title_img" src="${resRoot}/image/user/grabbag/title_grab_right_win.png?v=2">
    <div class="user_grab_bag_win_animation_holder">
        <div class="user_grab_bag_win_animation">
            <img class="grab_bag_face_img" src="${resRoot}/image/user/grabbag/face_win.png?v=2">
            <div class="money_change_tip">
                <span class="money_change_tip_text">得到<span class="money_change_tip_money">${viewModel.userBag.gotMoney}</span>作业币！</span>
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <div class="money_change_tip_money_all_holder">
        <span class="money_change_tip_money_all">我的财富变为：${viewModel.user.money}作业币</span>
    </div>

    <input type="hidden" id="myMoney" value="${viewModel.user.money}">

    <div class="zhuanfa_tip_holder">
        <span class="zhuanfa_tip">转发原题，看看小伙伴们怎么做~</span>
    </div>

    <div class="operation_btn_holder">
        <a class="orange_2d_btn operation_btn" href="javascript:viewAnalysis();">查看解析</a>
        <a class="orange_2d_btn operation_btn" href="/qzy/user/createbag/zhuanfabag?bagId=${viewModel.bag.bagId}">转发原题</a>
    </div>
</div>

<%@include file="subview/GrabbedUserList.jsp" %>

<img class="induce_img" src="${resRoot}/image/user/grabbag/induce_dark.png?v=1">

<script src='${resRoot}/js/user/grabbag/userGrabBag.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
