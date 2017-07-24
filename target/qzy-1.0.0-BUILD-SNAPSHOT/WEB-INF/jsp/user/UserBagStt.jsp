<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>学习报告</title>
<link href="${resRoot}/css/user/userBagStt.css?version=${resVersion}" rel="stylesheet"/>

<script src='${resRoot}/thirdparty/exporting.js' type='text/javascript'></script>
<script src='${resRoot}/thirdparty/highcharts.js' type='text/javascript'></script>

<div class="top_title">
    <div class="title_info_main">大家快来抢红包</div>
    <div class="title_info_right_holder">
        <div>${viewModel.bag.createTimeString}</div>
        <div>${viewModel.bag.bagTypeString}</div>
        <div>${viewModel.bag.zsdCatalog.zsdString}</div>
    </div>
</div>

<c:if test="${fn:length(viewModel.userBagList) == 0}" >
    <div class="no_one_grab_tip_holder">很遗憾，还没有人来抢您的红包！</div>
</c:if>

<c:if test="${fn:length(viewModel.userBagList) > 0}" >
    <div id="right_count_percent"></div>

    <div class="user_stt_holder">
        <div class="user_stt_title_holder">
            <span class="user_stt_title">用户统计</span>
        </div>

        <div class="user_list">
            <c:forEach items="${viewModel.userBagList}" var="userbag" varStatus="status">
                <div class="user_item">
                    <span class="user_item_col user_portrait"><img class="user_portrait_img" src="${userbag.user.portrait}"></span>
                    <span class="user_item_col user_name">${userbag.user.userName}</span>
                    <span class="user_item_col user_consumeTime">用时:${userbag.consumeTimeString}</span>
                    <span class="user_item_col user_rightCount">答对${userbag.rightAnswerCount}题</span>

                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </c:forEach>
        </div>
    </div>

    <div class="que_stt_holder">
        <div class="que_stt_title_holder">
            <span class="que_stt_title">每题统计</span>
        </div>

        <c:forEach items="${viewModel.queNumQueMap}" var="item" varStatus="status">
            <div class="que_item_all_holder">
                <span class="que_content_title">第${status.index + 1}题</span>

                <div class="que_content_holder">
                    <div class="que_content_html">${item.value.contentHtml}</div>
                    <div class="que_choice_holder">
                        <c:forEach items="${viewModel.queNumTqcsListMap[(status.index + 1).intValue()]}" var="qc">
                            <div class="que_choice_item">
                                <span class="que_choice_item_title">${qc.title}:</span>
                                <span class="que_choice_item_html">${qc.queChoice.queChoiceHtml}</span>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="que_analysis_html">${item.value.analysisHtml}</div>
                </div>

                <span class="answer_stt_title">选项统计：</span>

                <div class="answer_stt_holder">
                    <c:forEach items="${viewModel.queNumTqcsListMap[(status.index + 1).intValue()]}" var="qc">
                        <div class="answer_row_all">
                        	<div class="checked_users_pop_holder hidden">
	                        	<c:forEach items="${qc.checkedUsers}" var="userBag" varStatus="status">
		                        	<div class="pop_user_info">
		                        		<span class="pop_index">${status.index + 1}</span>
			                        	<span><img class="pop_user_portrait" src="${userBag.user.portrait}"></span>
		                    			<span class="pop_user_name">${userBag.user.userName}</span>
		                        	</div>
	                        	</c:forEach>
	                        	<div class="pop_ok_btn" onclick="javascript:closeAllLayers();">确定</div>
                        	</div>
                            <span class="answer_row_item answer_title">${qc.title}:</span>
                            <span class="answer_row_item answer_percent_bar" <c:if test="${qc.checkedUsers != null && qc.checkedUsers.size() > 0}">onclick="disPlayCheckdUsers(this,${qc.checkedUsers.size()});"</c:if> >
                                <span style="width: ${qc.rightPercent}%;" <c:if test="${qc.queChoice.isRightAnswer}"> class="answer_row_item answer_percent_bar_inner" </c:if> <c:if test="${!qc.queChoice.isRightAnswer}"> class="answer_row_item answer_percent_bar_inner_wrong" </c:if> >&nbsp;</span>
                            </span>
                            <span class="answer_row_item answer_user_count">${qc.checkedUsers.size()}/${viewModel.userBagList.size()}</span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

    <script src='${resRoot}/js/user/userBagStt.js?version=${resVersion}' type='text/javascript'></script>
</c:if>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

