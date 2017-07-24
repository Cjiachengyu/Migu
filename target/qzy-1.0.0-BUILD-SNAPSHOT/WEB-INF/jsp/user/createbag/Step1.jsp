<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>发作业</title>
<link href="${resRoot}/css/user/userCreateBag.css?version=${resVersion}" rel="stylesheet"/>

<div class="popup_blur_content_holder">
    <img class="create_bag_step1_top" src="${resRoot}/image/user/createbag/create_bag_step1_top.png">

    <%@include file="subview/CreateBagSelector.jsp" %>
    
    <input type="hidden" id="createBagZskId" value="${viewModel.user.createBagZskId}">

    <div class="next_step_btn_holder">
        <a class="next_step_btn_2" onclick="javascript:nextStep();">下一步</a>
    </div>

    <%@include file="subview/CreateBagZskData.jsp" %>
</div>

<script src='${resRoot}/js/user/userCreateBagStep1.js?version=${resVersion}' type='text/javascript'></script>
<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
