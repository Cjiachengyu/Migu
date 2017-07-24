<%@include file="/WEB-INF/jsp/common/EditorCommonTop.jsp" %>
<title>我要录题</title>
<link href="${resRoot}/css/editor/editorQueEdit.css?version=${resVersion}" rel="stylesheet"/>
<link href="${resRoot}/thirdparty/umeditor/themes/default/css/umeditor.min.css" type="text/css" rel="stylesheet">

<div class="wrap">
    <div class="column_content_holder">
        <div class="sub_title">
            <span>来源：</span>
            <input class="small_input_text" type="text" id="question_source">
        </div>

        <div class="sub_title">
            <span>知识点：</span>

            <div class="right"><a onclick="selectZsd();">添加</a></div>

            <div id="selected_zsd_list" class="selected_zsd_list">
            	<div id="select_zsd_data">
				</div>
            </div>
            <div class="clear"></div>
        </div>

        <div class="sub_title">
            <span>题干：</span>
        </div>

        <div class="content_editor_holder">
            <script id="editor_content" class="em_editor editor_content" type="text/plain"></script>
        </div>

        <div class="clear"></div>
    </div>

    <div class="column_choice_holder">
        <div class="sub_title">
            <span>答案：</span><span id="right_answer_data"></span>
            <input id="right_answer_num" type="hidden" value="">

            <div class="right">
                <span>选项个数：</span>
                <select id="choice_count" onchange="operatingChoiceCount(this);">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4" selected>4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select>
            </div>
            <div class="clear"></div>
        </div>

        <div class="choice_editor_holder">
            <div class="choice_holder_div " id="choice_holder_div_1">
                <div class="choice_operation_holder">
                    <span>A:</span>
                    <a onclick="setRightAnswer(this, 1);" >设为正确答案</a>
                </div>
                <script id="editor_choice_1" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_2">
                <div class="choice_operation_holder">
                    <span>B:</span>
                    <a onclick="setRightAnswer(this, 2);" >设为正确答案</a>
                </div>
                <script id="editor_choice_2" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_3">
                <div class="choice_operation_holder">
                    <span>C:</span>
                    <a onclick="setRightAnswer(this, 3);" >设为正确答案</a>
                </div>
                <script id="editor_choice_3" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_4">
                <div class="choice_operation_holder">
                    <span>D:</span>
                    <a onclick="setRightAnswer(this, 4);" >设为正确答案</a>
                </div>
                <script id="editor_choice_4" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_5">
                <div class="choice_operation_holder">
                    <span>E:</span>
                    <a onclick="setRightAnswer(this, 5);" >设为正确答案</a>
                </div>
                <script id="editor_choice_5" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_6">
                <div class="choice_operation_holder">
                    <span>F:</span>
                    <a onclick="setRightAnswer(this, 6);" >设为正确答案</a>
                </div>
                <script id="editor_choice_6" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_7">
                <div class="choice_operation_holder">
                    <span>G:</span>
                    <a onclick="setRightAnswer(this, 7);" >设为正确答案</a>
                </div>
                <script id="editor_choice_7" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_8">
                <div class="choice_operation_holder">
                    <span>H:</span>
                    <a onclick="setRightAnswer(this, 8);" >设为正确答案</a>
                </div>
                <script id="editor_choice_8" class="em_editor editor_choice" type="text/plain"></script>
            </div>
            <div class="choice_holder_div " id="choice_holder_div_9">
                <div class="choice_operation_holder">
                    <span>I:</span>
                    <a onclick="setRightAnswer(this, 9);" >设为正确答案</a>
                </div>
                <script id="editor_choice_9" class="em_editor editor_choice" type="text/plain"></script>
            </div>
        </div>

        <div class="clear"></div>
    </div>

    <div class="column_analysis_holder">
        <div class="sub_title">
            <span>考点：</span>
            <input class="small_input_text" type="text" id="question_kaodian">
        </div>

        <div class="sub_title">
            <span>分析：</span>
        </div>

        <div class="analysis_editor_holder">
            <script id="editor_analysis" class="em_editor editor_analysis" type="text/plain"></script>
        </div>

        <div class="clear"></div>
    </div>

    <div class="column_answer_holder">
        <div class="sub_title">
            <span>解答：</span>
        </div>

        <div class="answer_editor_holder">
            <script id="editor_answer" class="em_editor editor_answer" type="text/plain"></script>
        </div>

        <div class="submit_btn_holder">
            <input class="submit_btn_1" type="button" value="格式化" onclick="formatAllEditors();">
            <input class="submit_btn_2" type="button" value="高亮图片" onclick="highlightImg();" >
            <input class="submit_btn_all" id="create_que_btn" type="button" value="确定创建" onclick="createQue();">
        </div>
        <div class="clear"></div>
    </div>

    <div class="clear"></div>
</div>

<div id="zskContent" class="hidden">
    <div class="zsk_Content">
        <c:forEach items="${viewModel.zskList}" var="zsk" varStatus="sta">
            <span class="<c:if test='${sta.index == 0}'>selected_zsd_item</c:if>" onclick="clickZsk(this,${zsk.zskId});">${zsk.zskName}</span>
        </c:forEach>
    </div>

    <div class="zsd1_Content">
        <c:forEach items="${viewModel.zskList}" var="zsk">
            <c:forEach items="${zsk.zsd1List}" var="zsd1">
                <div class="zsd1 zsd1_${zsk.zskId}" isZsdAdded="no" zsdstr="zsd_${zsk.zskId}_${zsd1.zsd1Id}_0">
                    <input type="hidden" value="${zsk.zskId}_${zsd1.zsd1Id}_0">
						<span class="con" onclick="clickZsd1(this,${zsd1.zsd1Id});"
                              onmouseenter="displayAddButton(this);"
                              onmouseleave="hideAddButton(this);">${zsd1.zsd1Name}
                            <input type="button" class="add_zsd_float_btn hidden" onclick="addOrRemoveZsd(this);" value="添加">
                        </span>
                </div>
            </c:forEach>
        </c:forEach>
    </div>

    <div class="zsd2_Content">
        <c:forEach items="${viewModel.zskList}" var="zsk">
            <c:forEach items="${zsk.zsd1List}" var="zsd1">
                <c:forEach items="${zsd1.zsd2List}" var="zsd2">
                    <div class="hidden zsd2 zsd2_${zsd1.zsd1Id}" isZsdAdded="no" zsdstr="zsd_${zsk.zskId}_${zsd1.zsd1Id}_${zsd2.zsd2Id}">
                        <input type="hidden" value="${zsk.zskId}_${zsd1.zsd1Id}_${zsd2.zsd2Id}">
						<span class="con" onmouseenter="displayAddButton(this);" onmouseleave="hideAddButton(this);">${zsd2.zsd2Name}
                            <input type="button" class="add_zsd_float_btn hidden" onclick="addOrRemoveZsd(this);" value="添加"></span>
                    </div>
                </c:forEach>
            </c:forEach>
        </c:forEach>
    </div>

    <div class="zsd_data_div">
        <div class="popup_selected_zsd">
            <div class="popup_selected_zsd_title">已经添加的知识点：</div>
            <div id="relatedZsd">
            </div>
        </div>

        <div class="ok_btn_holder">
            <input class="ok_btn_select_zsd" type="button" onclick="confirmZsd();" value="确定">
        </div>
    </div>
</div>

<div id="selected_zsd_item_template" class="hidden">
    <div class="added_zsd_holder"><span>● REPLACE_TEXT</span><input type="button" class="add_zsd_float_btn"  name="REPLACE_ZSD_ID_DATA" onclick="REPLACE_REMOVE_FUNC" value="移除"><div class="clear"></div></div>
</div>

<!-- 编辑或者另存时原来的que相关信息 -->
<input type="hidden" id="operationType" value="${viewModel.operationType}" />

<div id="filter_editor_content_tmp" class="hidden"></div>

<div class="hidden">
    <script id="tmp_um_editor" type="text/plain"></script>
</div>

<c:if test="${viewModel.operationType != 1 }">
	<input type="hidden" id="old_question_source" value="${viewModel.que.source}" />
	<input type="hidden" id="old_question_kaodian" value="${viewModel.que.kaodian}" />
	<div id="old_editor_content" style="display: none;">${viewModel.que.contentHtml}</div>
	<div id="old_editor_analysis" style="display: none;">${viewModel.que.analysisHtml}</div>
	<div id="old_editor_answer" style="display: none;">${viewModel.que.answerHtml}</div>
	
	<input type="hidden" id="old_choice_count" value="${viewModel.que.queChoiceList.size()}" />
	<c:forEach items="${viewModel.que.queChoiceList}" var="queChoice" varStatus="status" >
		<div id="old_editor_choice_${ status.index + 1}" style="display: none;" <c:if test="${queChoice.isRightAnswer}">rightAnswerNum="${ status.index + 1}"</c:if> >${queChoice.queChoiceHtml}</div>
	</c:forEach>
	
	<input type="hidden" id="old_zsd_data_count" value="${viewModel.queZsdList.size()}" />
	<c:forEach items="${viewModel.queZsdList}" var="queZsd" varStatus="status" >
		<input type="hidden" id="old_zsd_data_${ status.index + 1}" value="${queZsd.zskId}_${queZsd.zsd1Id}_${queZsd.zsd2Id}" >
	</c:forEach>
</c:if>

<script src="${resRoot}/thirdparty/umeditor/umeditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="${resRoot}/thirdparty/umeditor/umeditor.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${resRoot}/thirdparty/umeditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
<script src='${resRoot}/js/editor/editorQueEdit.js?version=${resVersion}' type='text/javascript'></script>
<script>
    mainMenuConfig.currentMenuId = "main_menu_que_edit";
</script>

<%@include file="/WEB-INF/jsp/common/EditorCommonBottom.jsp" %>

