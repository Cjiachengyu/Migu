<%@include file="/WEB-INF/jsp/common/EditorCommonTop.jsp" %>
<title>题库中心</title>
<link href="${resRoot}/css/editor/editorQueList.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/css/editor/queItemView.css?version=${resVersion}" rel="stylesheet"/>

<link href="${resRoot}/thirdparty/umeditor/third-party/mathquill/mathquill.min.css" type="text/css" rel="stylesheet"/>
<script src="${resRoot}/thirdparty/umeditor/third-party/mathquill/mathquill.min.js"></script>

<div class="wrap">
    <input type="hidden" id="isChecker" value="${viewModel.editor.isChecker}">
    <div class="que_select_condition">
        <c:if test="${viewModel.editor.isChecker}">
    		<select class="que_editor_selector" onchange="changeFromEditor(this.value);">
	            <option value="0" <c:if test="${viewModel.creatorId == 0}">selected="selected"</c:if>>所有编辑员</option>
	            <c:forEach items="${viewModel.editorList}" var="editor">
	            	<option value="${editor.editorId}" <c:if test="${viewModel.creatorId == editor.editorId}">selected="selected"</c:if>>${editor.editorName}<c:if test="${viewModel.editor.editorId == editor.editorId}">(我)</c:if></option>
	            </c:forEach>
	        </select>
    		<select class="que_status_selector" onchange="changeFromStatus(this.value);">
	            <option value="-1" <c:if test="${viewModel.queStatus == -1}">selected="selected"</c:if>>所有状态</option>
	            <option value="0" <c:if test="${viewModel.queStatus == 0}">selected="selected"</c:if>>通过审核</option>
	            <option value="2" <c:if test="${viewModel.queStatus == 2}">selected="selected"</c:if>>待审核</option>
	            <option value="3" <c:if test="${viewModel.queStatus == 3}">selected="selected"</c:if>>被反馈</option>
	        </select>
    	</c:if>
        <c:if test="${!viewModel.editor.isChecker}">
    		<select class="que_from_type_selector" onchange="changeFromType(this.value);">
	            <option value="1" <c:if test="${viewModel.creatorId == viewModel.editor.editorId}">selected="selected"</c:if>>我录的题</option>
	            <option value="2" <c:if test="${viewModel.creatorId == 0}">selected="selected"</c:if>>系统题库</option>
	        </select>
	        <select class="que_status_selector" onchange="changeFromStatus(this.value);">
	            <option value="-1" <c:if test="${viewModel.queStatus == -1}">selected="selected"</c:if>>所有状态</option>
	            <option value="0" <c:if test="${viewModel.queStatus == 0}">selected="selected"</c:if>>通过审核</option>
	            <option value="2" <c:if test="${viewModel.queStatus == 2}">selected="selected"</c:if>>未过审核</option>
	        </select>
    	</c:if>
    	
    	<select class="que_zsk_selector" onchange="changeFromZsk(this.value);">
            <option value="0" <c:if test="${viewModel.zskId == 0}">selected="selected"</c:if>>所有知识库</option>
            <c:forEach items="${viewModel.zskList}" var="zsk">
	            <option value="${zsk.zskId}" <c:if test="${viewModel.zskId == zsk.zskId}">selected="selected"</c:if>>${zsk.zskName}</option>
            </c:forEach>
        </select>
        
        
        <div class="zsd1_list hidden">
        	<select class="zsd1_item" onchange="changeFromZsd1(this.value);">
	            <c:forEach items="${viewModel.zskList}" var="zsk">
		            <c:forEach items="${zsk.zsd1List}" var="zsd1">
		            	<option zskId="${zsk.zskId}" value="${zsd1.zsd1Id}" class="zsd1 zsd1_${zsk.zskId}" <c:if test="${viewModel.zsd1Id == zsd1.zsd1Id}">selected="selected"</c:if>>${zsd1.zsd1Name}</option>
		            </c:forEach>
	            </c:forEach>
	        </select>
        </div>
        
        <div class="zsd1_list_copy hidden"></div>
        
        <select class="que_zsd1_selector" onchange="changeFromZsd1(this.value);">
            <option value="0" <c:if test="${viewModel.zsd1Id == 0}">selected="selected"</c:if>>所有一级知识点</option>
        </select>
        
        
        <div class="zsd2_list hidden">
        	<select class="zsd2_item" onchange="changeFromZsd2(this.value);">
	            <c:forEach items="${viewModel.zskList}" var="zsk">
		            <c:forEach items="${zsk.zsd1List}" var="zsd1">
		            	<c:forEach items="${zsd1.zsd2List}" var="zsd2">
		            		<c:if test="${zsd2.zsd2Id != 0}">
		            			<option zsd1Id="${zsd1.zsd1Id}" value="${zsd2.zsd2Id}" class="zsd2 zsd2_${zsd1.zsd1Id}" <c:if test="${viewModel.zsd2Id == zsd2.zsd2Id}">selected="selected"</c:if>>${zsd2.zsd2Name}</option>
		            		</c:if>
		            	</c:forEach>
		            </c:forEach>
	            </c:forEach>
	        </select>
        </div>
        
        <div class="zsd2_list_copy hidden"></div>
        
        <select class="que_zsd2_selector" onchange="changeFromZsd2(this.value);">
            <option value="0" <c:if test="${viewModel.zsd2Id == 0}">selected="selected"</c:if>>所有二级知识点</option>
        </select>
    	
    </div>

    <div id="que_list_view">
        <%@include file="subview/QueListView.jsp" %>
    </div>
</div>

<script src='${resRoot}/js/editor/editorQueList.js?version=${resVersion}' type='text/javascript'></script>
<script>
    mainMenuConfig.currentMenuId = "main_menu_que_list";
    pagingBarConfig.gotoPageCallback = refreshQueListView;
</script>

<%@include file="/WEB-INF/jsp/common/EditorCommonBottom.jsp" %>

