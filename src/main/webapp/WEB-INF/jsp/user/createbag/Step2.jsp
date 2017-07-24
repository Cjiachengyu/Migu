<%@ page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>发作业</title>
<link href="${resRoot}/css/user/userCreateBag.css?version=${resVersion}" rel="stylesheet"/>

<input type="hidden" id="sumMoney" value="${viewModel.user.money}">

<img class="create_bag_step2_top" src="${resRoot}/image/user/createbag/create_bag_step2_top.png">

<div class="set_bag_info_holder">
    <div class="bag_info">
        <div class="setting_row">
            <span class="s2_col_1">红包个数</span>
            <span class="s2_col_2"><input type="tel" id="bagCount" oninput="bagInfoChange(this)" onclick="selectInputText(this);" value="5"></span>
            <span class="s2_col_3">个</span>
            <div class="clear"></div>
        </div>
        <div class="setting_row">
            <span class="s2_col_1">总金额</span>
            <span class="s2_col_2"><input type="tel" id="bagMoney" oninput="bagInfoChange(this)" onclick="selectInputText(this);" value="200"></span>
            <span class="s2_col_3">币</span>
            <div class="clear"></div>
        </div>
        <div class="setting_row_short">
            余额：${viewModel.user.money}作业币
            <div class="clear"></div>
        </div>
        <div class="setting_row hidden">
            <input type="text" id="bagMsg" value="快来答题抢红包！" placeholder="快来答题抢红包！">
            <div class="clear"></div>
        </div>
    </div>
</div>

<div class="set_bag_type_holder hidden">
    <span class="setting_title">红包类型：</span>

    <div class="bag_type_btn_holder">
        <a class="bag_type_btn">拼手气红包</a>
    </div>
</div>

<div id="create_bag_step2_tip"></div>

<div class="next_step_btn_holder">
    <a class="next_step_btn_2" onclick="javascript:nextStep();">创建红包</a>
</div>


<script src='${resRoot}/js/user/userCreateBagStep2.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

	

