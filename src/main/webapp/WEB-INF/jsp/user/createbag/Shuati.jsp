<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>刷题</title>
<link href="${resRoot}/css/user/userCreateBagShuati.css?version=${resVersion}" rel="stylesheet"/>

<img class="create_bag_shuati_top" src="${resRoot}/image/user/createbag/create_bag_shuati_top.png">

<%@include file="subview/CreateBagSelector.jsp" %>

<div class="next_step_btn_holder">
    <a class="orange_2d_btn next_step_btn_2" onclick="javascript:gotoShuati();">开始刷题</a>
</div>

<%@include file="subview/CreateBagZskData.jsp" %>

<script src='${resRoot}/js/user/userCreateBagStep1.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>
