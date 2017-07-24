<%@include file="/WEB-INF/jsp/common/EditorCommonTop.jsp" %>
<title>奖品编辑</title>
<link href="${resRoot}/css/editor/editorPrizeEdit.css?version=${resVersion}" rel="stylesheet"/>
<script src="${resRoot}/thirdparty/jquery.form.js?version=${resVersion}" type="text/javascript"></script>

<div class="wrap">
    <div class="prize_holder">
        <div class="prize_image_div_box">
            <div class="prize_image_div_top">
                <img id="prize_image" src="">
            </div>
        </div>
        <div class="prize_info_holder">
            <div class="prize_name_div">
                <span>奖品名称：</span>
                <input type="text" id="prize_name">
            </div>
            <div class="prize_money_div">
                <span>奖品价格：</span>
                <input type="text" id="prize_money">
            </div>
            <div class="prize_image_div">
            <form id="submitPrizeImage" enctype='multipart/form-data' method='post'>
                <input class="pick_image_btn" type="file" id="prizeImage" name="prizeImage" onchange="checkPrizeImage();"/>
            </form>
            </div>
        </div>

        <div class="clear"></div>

        <div class="ok_btn_holder">
            <input class="ok_btn" type="button" onclick="createPrize();" value="确定录入">
        </div>
    </div>
</div>

<input type="hidden" id="operationType" value="${viewModel.operationType}">

<c:if test="${viewModel.operationType == 2}">
    <input type="hidden" id="old_prize_name" value="${viewModel.prize.prizeName}">
    <input type="hidden" id="old_prize_image" value="${viewModel.prize.prizeImageUrl}">
    <input type="hidden" id="old_prize_money" value="${viewModel.prize.prizeMoney}">
</c:if>

<script src="${resRoot}/js/editor/editorPrizeEdit.js?version=${resVersion}" type="text/javascript"></script>
<script>
    mainMenuConfig.currentMenuId = "main_menu_prize_edit";
</script>
<%@include file="/WEB-INF/jsp/common/EditorCommonBottom.jsp" %>

