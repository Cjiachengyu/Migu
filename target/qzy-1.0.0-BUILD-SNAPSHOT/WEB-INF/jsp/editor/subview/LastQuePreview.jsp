<link href="${resRoot}/css/editor/lastQuePreview.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/editor/queItemView.css?version=${resVersion}" rel="stylesheet"/>

<div class="last_que_preview_left">
    <c:set var="que" value="${viewModel.lastQue}"/>
    <div class="last_que_holder">
        <%@include file="QueItemView.jsp" %>
    </div>
</div>

<div class="last_que_preview_right">
    <div class="preview_tip">
        <p>左侧显示的是您刚刚编辑的题目的最终样子</p>

        <p>请仔细核对</p>
    </div>
    <div class="preview_ok_btn_holder">
        <input class="preview_ok_btn" type="button" onclick="closeLastQuePop();" value="OK，继续录题">
        <c:if test="${viewModel.operationType != 1}">
            <input class="preview_ok_btn" type="button" onclick="backToList();" value="返回题目列表">
        </c:if>
    </div>
</div>

<div class="clear"></div>
