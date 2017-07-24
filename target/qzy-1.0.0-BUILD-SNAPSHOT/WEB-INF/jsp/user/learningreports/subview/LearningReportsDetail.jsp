<div class="data_select_holder">
	<c:if test="${viewModel.dateType == 1}"><span class="select_holder">本周</span></c:if><c:if test="${viewModel.dateType != 1}"><span class="not_select_holder" onclick="setReportDate(1);">本周</span></c:if><c:if test="${viewModel.dateType == 2}"><span class="select_holder">最近一月</span></c:if><c:if test="${viewModel.dateType != 2}"><span class="not_select_holder" onclick="setReportDate(2);">最近一月</span></c:if><c:if test="${viewModel.dateType == 3}"><span class="select_holder">最近三月</span></c:if><c:if test="${viewModel.dateType != 3}"><span class="not_select_holder" onclick="setReportDate(3);">最近三月</span></c:if><c:if test="${viewModel.dateType == 4}"><span class="select_holder">全部</span></c:if><c:if test="${viewModel.dateType != 4}"><span class="not_select_holder" onclick="setReportDate(4);">全部</span></c:if>
</div>

<div class="title_holder">
		<div class="score_time">
			<c:if test="${viewModel.dateType == 1}">本周(${viewModel.beginTimeString}~${viewModel.endTimeString})</c:if>
			<c:if test="${viewModel.dateType == 2}">一月(${viewModel.beginTimeString}~${viewModel.endTimeString})</c:if>
			<c:if test="${viewModel.dateType == 3}">三月(${viewModel.beginTimeString}~${viewModel.endTimeString})</c:if>
			<c:if test="${viewModel.dateType == 4}">全部(截止${viewModel.endTimeString})</c:if>
			得分:
		</div>
		<div class="score">${viewModel.score}</div>
		<div class="score_defeat">
			<c:if test="${viewModel.scoreDefeat == 0.0}">击败了全国${viewModel.scoreDefeat}%的小伙伴!</c:if>
			<c:if test="${viewModel.scoreDefeat > 0.0 && viewModel.scoreDefeat < 60.0}">击败了全国${viewModel.scoreDefeat}%的小伙伴!</c:if>
			<c:if test="${viewModel.scoreDefeat > 60.0}">击败了全国${viewModel.scoreDefeat}%的小伙伴!</c:if>
            <a class="view_trend" href="/qzy/user/studyreport/displayreport">查看走势</a>
		</div>
</div>

<div class="basic_message_holder">
	<div class="answer_count">
		<span>
			<c:if test="${viewModel.dateType == 1}">本周</c:if>
			<c:if test="${viewModel.dateType == 2}">最近一月</c:if>
			<c:if test="${viewModel.dateType == 3}">最近三月</c:if>
			<c:if test="${viewModel.dateType == 4}">全部</c:if>答题数:${viewModel.answerCount}
		</span>
	</div>
	<div class="right_percent"><span>正确率:${viewModel.rightPercent}%</span></div>
	<div><span>答题时间:${viewModel.consumeTimeString}</span></div>
</div>

<div class="detail_message_holder">
	<div class="detail_item_holder">
		<c:forEach items="${viewModel.zskList}" var="zsk">
			<c:if test="${zsk.isDisplay == 1}">
				<div class="zsk">
					<div class="zsk_name">${zsk.zskName}</div>
					<c:forEach items="${zsk.zsd1ReportList}" var="zsd1Report">
						<c:if test="${zsd1Report.allUserAnswerList.size() > 0}">
							<div class="zsd1">
                                <span class="zsd1_col1 ellipsis ">${zsd1Report.zsd1.zsd1Name}</span>
                                <div class="right">
                                    <span class="zsd1_col2">答题:${zsd1Report.allUserAnswerList.size()}</span>
                                    <span class="zsd1_col3">正确:${zsd1Report.rightPercent}%</span>
                                </div>
							</div>
							<c:if test="${zsd1Report.zsd1.zsd2List != null &&  zsd1Report.zsd1.zsd2List.size() >= 1  && zsd1Report.zsd1.zsd2List.get(0).zsd2Id != 0}">
								<c:forEach items="${zsd1Report.zsd2ReportList }" var="zsd2Report">
									<c:if test="${zsd2Report.allUserAnswerList.size() > 1}">
										<div class="zsd2">
                                            <span class="zsd2_col1 ellipsis ">${zsd2Report.zsd2.zsd2Name }</span>
                                            <div class="right">
                                                <span class="zsd2_col2">答题:${zsd2Report.allUserAnswerList.size() }</span>
                                                <span class="zsd2_col3">正确:${zsd2Report.rightPercent }%</span>
                                            </div>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>
