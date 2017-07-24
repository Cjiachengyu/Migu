<div class="que_list_holder">
    <%@include file="/WEB-INF/jsp/common/PagingBar.jsp" %>

    <c:forEach items="${viewModel.queList}" var="que">
        <div class="que_item_all_holder <c:if test="${(viewModel.editor.isChecker && (que.queStatus == 2 || que.queStatus == 3)) || (!viewModel.editor.isChecker && que.queStatus == 2)}">que_status_red</c:if>"
            <c:if test='${viewModel.editor.isChecker && viewModel.queStatus == 3 && que.joinedFeedback != ""}'>title="${que.joinedFeedback}"</c:if>
        >
            <div class="que_item_info_all" >
                <div class="left">
                    <span>创建者：${que.creator.editorName}</span>
                </div>
                <div class="right operatingQue">
                	<c:if test="${viewModel.editor.isChecker &&  que.queStatus == 0}">
                		<a onclick="javascript:checkQue(${que.queId});">打回</a>
                	</c:if>
                	<c:if test="${viewModel.editor.isChecker &&  que.queStatus == 2}">
                		<a onclick="javascript:checkQue(${que.queId});">通过</a>
                	</c:if>
                    <a onclick="javascript:deleteQue(${que.queId});">删除</a>
                    <a href="/qzy/editor/queedit/${que.queId}/modify">修改</a>
                    <a href="/qzy/editor/queedit/${que.queId}/saveas">另存</a>
                </div>
                <div class="clear"></div>
            </div>
            <div class="que_item_content_all">
                <%@include file="QueItemView.jsp" %>
            </div>
        </div>
    </c:forEach>

    <%@include file="/WEB-INF/jsp/common/PagingBar.jsp" %>
</div>

<script src='${resRoot}/js/editor/queListView.js?version=${resVersion}' type='text/javascript'></script>

