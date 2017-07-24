<div class="que_item_info_all_holder">
    <div>
        <span class="que_item_sub_title">来源：</span><span><c:if test="${que.source != '' && que.source != null}">${que.source}</c:if></span>
    </div>

    <div>
        <span class="que_item_sub_title">考点：</span><span><c:if test="${que.kaodian != '' && que.kaodian != null}">${que.kaodian}</c:if></span>
    </div>

    <div>
        <span class="que_item_sub_title">知识点：</span><c:if test="${que.zsdCatalogList != null && que.zsdCatalogList.size() > 0}"><c:forEach items="${que.zsdCatalogList}" var="zsdCatalog" varStatus="status"><span> ● ${zsdCatalog.zsdString }</span></c:forEach></c:if>
    </div>

    <div class="que_item_sep"></div>

    <div>
        <div class="que_item_sub_title">题干+选项：</div>
        <div><c:if test="${que.contentHtml != '' && que.contentHtml != null}">${que.contentHtml}</c:if></div>
    </div>

    <div>
        <c:if test="${que.queChoiceList != '' && que.queChoiceList.size() > 0}">
            <c:forEach var="queChoice" varStatus="status" items="${que.queChoiceList}">
                <div class="que_choice_item">
                    <c:if test="${queChoice.isRightAnswer}"><span class="que_choice_marker">(√)●</span><span class="que_choice_holder">${queChoice.queChoiceHtml}</span></c:if>
                    <c:if test="${!queChoice.isRightAnswer}"><span class="que_choice_marker">●</span><span class="que_choice_holder">${queChoice.queChoiceHtml}</span></c:if>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <div class="que_item_sep"></div>

    <div>
        <div class="que_item_sub_title">分析：</div>
        <div><c:if test="${que.analysisHtml != '' && que.analysisHtml != null}">${que.analysisHtml}</c:if></div>
    </div>

    <div class="que_item_sep"></div>

    <div>
        <div class="que_item_sub_title">解答：</div>
        <div><c:if test="${que.answerHtml != '' && que.answerHtml != null}">${que.answerHtml}</c:if></div>
    </div>
</div>
