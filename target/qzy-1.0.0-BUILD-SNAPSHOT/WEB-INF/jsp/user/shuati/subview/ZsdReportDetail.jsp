<div class="zsd_report_holder">

    <c:forEach items="${viewModel.zsd1ReportList}" var="zsd1Report">
        <div class="zsd1 zsd1_${zsd1Report.zsd1.zskId}">
            <div class="zsd1_info">
                <span class="zsd1_name">
                	<span class="zsd1_name_span">${zsd1Report.zsd1.zsd1Name}</span>
                	<img class="practice_img" onclick="shuaTi(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},0);" src="${resRoot}/image/user/studyreport/practice.png">
                </span>
                <div class="clear"></div>
            </div>

            <div class="zsd1_moon_holder <c:if test="${zsd1Report.zsd1.zsd2List != null &&  zsd1Report.zsd1.zsd2List.size() >= 1  && zsd1Report.zsd1.zsd2List.get(0).zsd2Id != 0}">zsd1_moon_no_zsd2</c:if>">
                <div class="zsd1_left">
                    <div class="zsd1_percent_detail">答题数：${zsd1Report.rightUserAnswerList.size()}/${zsd1Report.allUserAnswerList.size()}</div>
                    <div class="zsd1_percent">正确率：${zsd1Report.rightPercent}%</div>
                    <div class="clear"></div>
                </div>

                <div class="zsd1_right" id="moon_${zsd1Report.zsd1.zskId}_${zsd1Report.zsd1.zsd1Id}">
                    <div class="moon" moonStatusValue="${zsd1Report.userMoon.moon1Status}" onclick="javascript:clickMoon(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},1);">
                        <img src="${resRoot}/image/user/studyreport/moonStatus${zsd1Report.userMoon.moon1Status}?v=1">
                    </div>
                    <div class="moon" moonStatusValue="${zsd1Report.userMoon.moon2Status}" onclick="javascript:clickMoon(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},2);">
                        <img src="${resRoot}/image/user/studyreport/moonStatus${zsd1Report.userMoon.moon2Status}?v=1">
                    </div>
                    <div class="moon" moonStatusValue="${zsd1Report.userMoon.moon3Status}" onclick="javascript:clickMoon(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},3);">
                        <img src="${resRoot}/image/user/studyreport/moonStatus${zsd1Report.userMoon.moon3Status}?v=1">
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>

            <c:if test="${zsd1Report.zsd1.zsd2List != null &&  zsd1Report.zsd1.zsd2List.size() >= 1  && zsd1Report.zsd1.zsd2List.get(0).zsd2Id != 0}">
            	<c:forEach items="${zsd1Report.zsd2ReportList}" var="zsd2Report">
	                <div class="zsd2">
	                    <div class="zsd2_left">
	                    	<div class="practice_holder"><img onclick="shuaTi(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},${zsd2Report.zsd2.zsd2Id});" src="${resRoot}/image/user/studyreport/practice.png"></div>
	                        <div class="zsd2_name" onclick="Confirm('去刷“${zsd2Report.zsd2.zsd2Name}”的题？', function() { shuaTi(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},${zsd2Report.zsd2.zsd2Id}); } );">${zsd2Report.zsd2.zsd2Name}</div>
	                        <div class="zsd2_percent_detail">${zsd2Report.rightUserAnswerList.size()}/${zsd2Report.allUserAnswerList.size()}</div>
	                        <div class="zsd2_percent">${zsd2Report.rightPercent}%</div>
	                        <div class="clear"></div>
	                    </div>
	                    <div class="zsd2_right" id="star_${zsd1Report.zsd1.zskId}_${zsd1Report.zsd1.zsd1Id}_${zsd2Report.zsd2.zsd2Id}">
	                        <div class="star" starStatusValue="${zsd2Report.userStar.star1Status}" onclick="javascript:clickStar(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},${zsd2Report.zsd2.zsd2Id},1);">
	                            <img src="${resRoot}/image/user/studyreport/starStatus${zsd2Report.userStar.star1Status}">
	                        </div>
	                        <div class="star" starStatusValue="${zsd2Report.userStar.star2Status}" onclick="javascript:clickStar(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},${zsd2Report.zsd2.zsd2Id},2);">
	                            <img src="${resRoot}/image/user/studyreport/starStatus${zsd2Report.userStar.star2Status}">
	                        </div>
	                        <div class="star" starStatusValue="${zsd2Report.userStar.star3Status}" onclick="javascript:clickStar(${zsd1Report.zsd1.zskId},${zsd1Report.zsd1.zsd1Id},${zsd2Report.zsd2.zsd2Id},3);">
	                            <img src="${resRoot}/image/user/studyreport/starStatus${zsd2Report.userStar.star3Status}">
	                        </div>
	                        <div class="clear"></div>
	                    </div>
	                    <div class="clear"></div>
	                </div>
	            </c:forEach>
            </c:if>
        </div>
    </c:forEach>
    <div class="clear"></div>
</div>


<script src='${resRoot}/js/user/stutyreport/initZsdReportDetail.js?version=${resVersion}' type='text/javascript'></script>