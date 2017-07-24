<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>
<title>刷题</title>
<link href="${resRoot}/css/user/userShuati.css?version=${resVersion}" rel="stylesheet"/>

<div class="top_bar_holder">
    <span class="top_bar_title">刷题</span>
</div>

<div class="hidden">
	<div id="zsk_list">
		<div class="zsk_list_holder">
		    <c:forEach items="${viewModel.zslList}" var="zsl">
		    	<div class="zsl">${zsl.zslName}</div>
		    	<c:forEach var="zsk" items="${zsl.zskList}">
			       <a id="zsk_${zsk.zskId}" zsdId="${zsk.zskId}" class="zsd_btn"
			          href="javascript:selectZsk('${zsk.zskId}');">${zsk.zskName}</a>
			    </c:forEach>
		    </c:forEach>
		</div>
	</div>
</div>

<input type="hidden" id="shuatiZskId" value="${viewModel.user.shuatiZskId}">

<div class="select_input_row" onclick="openSelectZskPopup();">
    <span class="select_input_col1"><span class="selected_zsk_title">当前知识库</span></span>
    <span class="select_input_col2" id="selected_zsk_content"></span>
    <span class="select_input_col3">&gt;</span>

    <div class="clear"></div>
</div>

<div class="zsd_report_father_holder">

</div>

<div class="hidden">
    <div id="starZsdData" class="hidden"></div>
    <div id="moonZsdData" class="hidden"></div>

    <div id="star1_status1_popup" class="hidden">
        <div class="star_popup_holder">
            <div>您需要回答10道题以上才可以得到星星！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    <div id="star1_status2_popup" class="hidden">
        <div class="star_popup_holder">
            <div>恭喜您，成功回答10题以上！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">领取奖励</a>
        </div>
    </div>
    
    <div id="moon1_status1_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>您需要回答30道题以上才可以得到月亮！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    <div id="moon1_status2_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>恭喜您，成功回答30题以上！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">领取奖励</a>
        </div>
    </div>
    
    <div id="star2_status1_popup" class="hidden">
        <div class="star_popup_holder">
            <div>您需要答对15道题以上才可以得到星星！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    <div id="star2_status2_popup" class="hidden">
        <div class="star_popup_holder">
            <div>恭喜您，成功答对15题以上！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">领取奖励</a>
        </div>
    </div>
    
    <div id="moon2_status1_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>您需要答对30道题以上才可以得到月亮！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    <div id="moon2_status2_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>恭喜您，成功答对30题以上！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">领取奖励</a>
        </div>
    </div>

    <div id="star3_status1_popup" class="hidden">
        <div class="star_popup_holder">
            <div>您需要连续答对20道题以上才可以得到星星！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    <div id="star3_status2_popup" class="hidden">
        <div class="star_popup_holder">
            <div>恭喜您，连续答对20题以上！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">领取奖励</a>
        </div>
    </div>
    
    <div id="moon3_status1_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>您需要连续答对30道题以上才可以得到月亮！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    <div id="moon3_status2_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>恭喜您，连续答对30题以上！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">领取奖励</a>
        </div>
    </div>

    <div id="star_status3_popup" class="hidden">
        <div class="star_popup_holder">
            <div>您已经领取了这颗星星！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:changStarStatus()" class="ok_button">去刷题</a>
        </div>
    </div>
    
    <div id="moon_status3_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>您已经领取了这个月亮！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:changMoonStatus()" class="ok_button">去刷题</a>
        </div>
    </div>

    <div id="got_star_success_popup" class="hidden">
        <div class="star_popup_holder">
            <div>恭喜您，成功领取第<span>REPLACE_STAR_NUM_TEXT</span>个星星,获得<span>REPLACE_STAR_MONEY_TEXT</span>个作业币！</div>
        </div>
        <div class="star_popup_button_div">
            <a onclick="javascript:closeAllLayers();" class="ok_button">确定</a>
        </div>
    </div>
    
    <div id="got_moon_success_popup" class="hidden">
        <div class="moon_popup_holder">
            <div>恭喜您，成功领取第<span>REPLACE_MOON_NUM_TEXT</span>个月亮,获得<span>REPLACE_MOON_MONEY_TEXT</span>个作业币！！</div>
        </div>
        <div class="moon_popup_button_div">
            <a onclick="javascript:closeAllLayers();" class="ok_button">确定</a>
        </div>
    </div>
</div>

<script src='${resRoot}/js/user/userStudyReportZsd.js?version=${resVersion}' type='text/javascript'></script>

<%@include file="/WEB-INF/jsp/common/UserCommonBottom.jsp" %>

