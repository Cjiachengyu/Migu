<%@include file="/WEB-INF/jsp/common/AdminCommonTop.jsp" %>
<title>编辑操作界面</title>
<link href="${resRoot}/css/admin/adminEditorList.css?version=${resVersion}" rel="stylesheet"/>

<div class="editor_list_holder">
	<div class="editor_title">
		<span class="title_index">序号</span>
		<span class="title_editor_name">编辑名</span>
		<span class="title_prize">操作奖品</span>
		<span class="title_que">操作题目</span>
	</div>
	
	<c:forEach items="${viewModel.editorList}" var="editor" varStatus="status">
	<div class="editor_item">
		<span class="item_index">${status.index + 1}</span>
		<a class="item_editor_name" href="/qzy/editor/quelist/${editor.editorId}/adminlogin">${editor.editorName}</a>
		<input type="checkbox" class="item_prize" <c:if test="${editor.canOperatePrize}">checked</c:if> >
		<input type="checkbox" class="item_que" <c:if test="${editor.canOperateQue}">checked</c:if> >
	</div>
	</c:forEach>

    <div class="ok_btn_div">
	    <span class="ok_btn" onclick="changePrivilege();">确定</span>
    </div>
</div>

<script src='${resRoot}/js/admin/adminEditorList.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/AdminCommonBottom.jsp" %>


