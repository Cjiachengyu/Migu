<div class="hidden">
    <div id="zsk_list">
        <div <c:if test="${sessionScope.zhuanFaBagId != null}">class="zsk_list_holder"</c:if> <c:if test="${sessionScope.zhuanFaBagId == null}">class="zsk_list_holder_only"</c:if> >
            <c:forEach var="zsl" items="${viewModel.zslList}" varStatus="status">
            	<div class="zsl">${zsl.zslName}</div>
            	<c:forEach items="${zsl.zskList}" var="zsk">
	                <a id="zsk_${zsk.zskId}" zskId="${zsk.zskId}" class="zsd_btn"
	                   href="javascript:selectZsk('${zsk.zskId}');">${zsk.zskName}</a>
            	</c:forEach>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.zhuanFaBagId != null}">
            <div class="zhuanfa_holder">
                <a href="zhuanfabag">转发上次红包的题目</a>
            </div>
        </c:if>
    </div>

    <div id="zsd1_list">
        <div class="zsd1_list_holder">
        	<c:forEach items="${viewModel.zslList}" var="zsl">
        		<c:forEach var="zsk" items="${zsl.zskList}">
	                <a id="zsk_${zsk.zskId}_zsd1_0" zsd1Id="0"
	                   class="zsd_btn zsk_${zsk.zskId}_btn"
	                   href="javascript:selectZsd1('${zsk.zskId}', '0');">随机</a>
	                <c:forEach var="zsd1" items="${zsk.zsd1List}">
	                    <a id="zsk_${zsk.zskId}_zsd1_${zsd1.zsd1Id}" zsd1Id="${zsd1.zsd1Id}"
	                       class="zsd_btn zsk_${zsk.zskId}_btn"
	                       href="javascript:selectZsd1('${zsk.zskId}', '${zsd1.zsd1Id}');">${zsd1.zsd1Name}</a>
	                </c:forEach>
	            </c:forEach>
        	</c:forEach>
        </div>
    </div>

    <div id="zsd2_list">
        <div class="zsd2_list_holder">
        	<c:forEach items="${viewModel.zslList}" var="zsl">
        		<c:forEach var="zsk" items="${zsl.zskList}">
	                <c:forEach var="zsd1" items="${zsk.zsd1List}">
	                    <a id="zsk_${zsk.zskId}_zsd1_${zsd1.zsd1Id}_zsd2_0" zsd2Id="0"
	                       class="zsd_btn zsk_${zsk.zskId}_zsd1_${zsd1.zsd1Id}_btn"
	                       href="javascript:selectZsd2('${zsk.zskId}', '${zsd1.zsd1Id}', '0');">随机</a>
	                    <c:forEach var="zsd2" items="${zsd1.zsd2List}">
	                        <a id="zsk_${zsk.zskId}_zsd1_${zsd1.zsd1Id}_zsd2_${zsd2.zsd2Id}" zsd2Id="${zsd2.zsd2Id}"
	                           class="zsd_btn zsk_${zsk.zskId}_zsd1_${zsd1.zsd1Id}_btn"
	                           href="javascript:selectZsd2('${zsk.zskId}', '${zsd1.zsd1Id}', '${zsd2.zsd2Id}');">${zsd2.zsd2Name}</a>
	                    </c:forEach>
	                </c:forEach>
	            </c:forEach>
        	</c:forEach>
        </div>
    </div>
</div>
