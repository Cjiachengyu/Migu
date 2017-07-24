<%--@elvariable id="isViewingAnalysis" type="java.lang.Boolean"--%>

<!-- 导航区 -->
<div class="que_num_nav_holder">
    <div class="que_num_nav_icon que_num_nav_left_icon" onclick="gotoPrev();"></div>
    <c:forEach var="num" begin="1" end="${fn:length(viewModel.bag.queList)}">
        <span class="que_num" id="que_num_nav_${num}" onclick="gotoQue(${num});">${num}</span>
    </c:forEach>
    <div class="que_num_nav_icon que_num_nav_right_icon" onclick="gotoNext();"></div>
</div>

<!-- 题目区 -->
<div class="que_list_holder" id="queList">
    <c:forEach var="num" begin="1" end="${fn:length(viewModel.bag.queList)}">
        <c:set var="que" value="${viewModel.queNumQueMap[num.intValue()]}"/>
        <c:set var="tqcList" value="${viewModel.queNumTqcListMap[num.intValue()]}"/>

        <div class="que_holder" id="que_holder_${num}">
            <div class="que_content_top_bar">
                <c:if test="${que.source != null && que.source != ''}">
                    <div class="que_source_holder">来源：${que.source}</div>
                </c:if>
                <c:if test="${que.source == null || que.source == ''}">
                    <div class="que_source_holder">来源：互联网</div>
                </c:if>
                <div class="que_feedback" onclick="startFeedback(${que.queId});">题目有错?</div>
                <div class="clear"></div>
            </div>
            <div class="que_content_holder">${que.contentHtml}</div>
            <div class="que_choice_holder">
                <c:forEach var="tqc" items="${tqcList}">
                    <div class="que_choice_item" id="que_choice_item_${tqc.queChoice.queChoiceId}" queChoiceId="${tqc.queChoice.queChoiceId}"
                            <c:if test="${isViewingAnalysis && tqc.queChoice.isRightAnswer}"> isRightAnswer="true" </c:if>
                            <c:if test="${!isViewingAnalysis}"> onclick="selectChoice(${tqc.queChoice.queChoiceId}, true);" </c:if> >
                        <div class="que_choice_title_holder">
                            <div id="que_choice_title_${tqc.queChoice.queChoiceId}" class="que_choice_title">${tqc.title}</div>
                        </div>
                        <div class="que_choice_content">${tqc.queChoice.queChoiceHtml}</div>
                        <div class="clear"></div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${isViewingAnalysis}">
            	<c:if test="${que.zsdCatalogList != null && que.zsdCatalogList.size() > 0}">
                    <div class="que_analysis_holder">
                        <div class="que_analysis_title_holder">考点：</div>
                        <c:forEach items="${que.zsdCatalogList}" var="zsdCatalog">
                            <div>${zsdCatalog.zsdString}</div>
                        </c:forEach>
                    </div>
           		</c:if>
            	
                <c:if test="${que.analysisHtml != null && que.analysisHtml != ''}">
                    <div class="que_analysis_holder">
                        <div class="que_analysis_title_holder">分析：</div>
                        <div>${que.analysisHtml}</div>
                    </div>
                </c:if>
                <c:if test="${que.answerHtml != null && que.answerHtml != ''}">
                    <div class="que_answer_holder">
                        <div class="que_answer_title_holder">解答：</div>
                        <div>${que.answerHtml}</div>
                    </div>
                </c:if>
            </c:if>
        </div>
    </c:forEach>
</div>

<!-- 上一题，提交，下一题 -->
<div class="common_dock_bottom">
    <div class="operation_btn_holder">
        <c:if test="${!isViewingAnalysis}">
            <a class="oper_que_nav_btn" id="oper_que_submit_btn" href="javascript:submitBag();">提交</a>
        </c:if>
        <c:if test="${isViewingAnalysis}">
            <a class="oper_que_nav_btn" id="prev_que_btn" href="/qzy/user/grabbag/${viewModel.bag.bagId}/startgrab">返回结果页</a>
            <a class="oper_que_nav_btn" id="next_que_btn" onclick="gotoNext();">下一题</a>
        </c:if>
    </div>
</div>
    

<!-- 加载以前的作答数据 -->
<div id="user_answer_data_holder" class="hidden">
    <input id="current_time" type="hidden" value="${currentTime}">
    <input id="que_list_count" type="hidden" value="${fn:length(viewModel.bag.queList)}">

    <c:forEach var="num" begin="1" end="${fn:length(viewModel.bag.queList)}">
        <c:set var="userAnswer" value="${viewModel.queNumUserAnswerMap[num.intValue()]}"/>
        <c:if test="${userAnswer != null}">
            <div id="user_answer_${num}">
                <input type="hidden" class="user_choice_data" value="${userAnswer.userChoiceId}">
                <input type="hidden" class="beg_answer_time_data" value="${userAnswer.beginAnswerTime}">
                <input type="hidden" class="end_answer_time_data" value="${userAnswer.endAnswerTime}">
                <input type="hidden" class="consume_time_data" value="${userAnswer.consumeTime}">
            </div>
        </c:if>
    </c:forEach>
</div>

<div id="queFeedBack" class="hidden">
    <div class="que_feedback_holder">
        <div class="feedback_item" onclick="selectFeedback(this);">
            <span class="feedback_title"></span><span class="feedback_content">题目内容或答案有错</span>
        </div>
        <div class="feedback_item" onclick="selectFeedback(this);">
            <span class="feedback_title"></span><span class="feedback_content">排版样式有错</span>
        </div>
        <div class="feedback_item" onclick="selectFeedback(this);">
            <span class="feedback_title"></span><span class="feedback_content">数学公式样式有错</span>
        </div>
        <div class="feedback_item" onclick="selectFeedback(this);">
            <span class="feedback_title"></span><span class="feedback_content">其它：</span>
            <input id="feedback_msg" type="text" placeholder="填写反馈">
        </div>

        <div class="feedback_ok_btn" onclick="confirmFeedBack();">提交反馈</div>
    </div>
</div>

<script src='${resRoot}/thirdparty/touch-0.2.14.min.js' type='text/javascript'></script>

<script src='${resRoot}/js/user/grabbag/queFeedback.js?version=${resVersion}' type='text/javascript'></script>
<c:if test="${!isViewingAnalysis}">
    <script src='${resRoot}/js/user/grabbag/answering.js?version=${resVersion}' type='text/javascript'></script>
</c:if>
<c:if test="${isViewingAnalysis}">
    <script src='${resRoot}/js/user/grabbag/viewAnalysis.js?version=${resVersion}' type='text/javascript'></script>
</c:if>

