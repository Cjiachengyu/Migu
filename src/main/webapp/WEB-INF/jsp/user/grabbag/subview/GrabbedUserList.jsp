<div class="already_grabbed_bag_info">
    <div class="bag_info">
        <c:if test="${viewModel.bag.bagCount > viewModel.bag.sentCount}">
            <span class="bag_info_text">共${viewModel.bag.bagCount}个红包，还剩${viewModel.bag.bagCount - viewModel.bag.sentCount}个</span>
        </c:if>
        <c:if test="${viewModel.bag.bagCount == viewModel.bag.sentCount}">
            <span class="bag_info_text">共${viewModel.bag.bagCount}个红包，${viewModel.bagBlankTimeString}被抢光</span>
        </c:if>

        <c:if test="${viewModel.bag.creatorId == viewModel.user.userId}">
            <a class="bag_stt_btn" href="/migu/user/bagstt/${viewModel.bag.bagId}/showbag"></a>
        </c:if>
    </div>

    <div class="already_grabbed_bag_list">
        <c:if test="${fn:length(viewModel.alreadyGrabbedBagList) == 0}">
            <div class="nobody_grab">还没有人抢这个红包！</div>
        </c:if>
        <c:forEach items="${viewModel.alreadyGrabbedBagList}" var="item">
            <div class="already_grabbed_bag_item">
                <div class="user_portrait_div">
                    <img class="user_portrait" src="${item.user.portrait}">
                </div>

                <div class="middle_div">
                    <div class="user_name">${item.user.userName}</div>
                    <div class="bag_end_time">${item.endBagTimeString}</div>
                </div>

                <c:if test="${item.userBagStatus == 1}">
                    <div class="bag_money">+${item.gotMoney}币</div>
                </c:if>
                <c:if test="${item.userBagStatus != 1}">
                    <div class="bag_right_answer_count">对${item.rightAnswerCount}题</div>
                </c:if>

                <div class="operation_holder">
                    <c:if test="${item.rightAnswerCount == 3}">
                        <c:if test="${item.userId != viewModel.user.userId}">
                            <img src="${resRoot}/image/user/grabbag/show_icon_parise.png" class="laugh_or_praise_btn" onclick="praiseUser(${item.user.userId},'${item.user.userName}');">
                            <c:if test="${item.praiseOrLaughCount > 0}">
                                <div class="praise_or_laugh_count">${item.praiseOrLaughCount}</div>
                            </c:if>
                        </c:if>
                        <c:if test="${item.userId == viewModel.user.userId}">
                            <img src="${resRoot}/image/user/grabbag/show_icon_show_my_bag.gif" class="laugh_or_praise_btn" onclick="showMy();">
                            <c:if test="${item.praiseOrLaughCount > 0}">
                                <div class="praise_or_laugh_count">${item.praiseOrLaughCount}</div>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${item.rightAnswerCount != 3}">
                        <c:if test="${item.userId != viewModel.user.userId}">
                            <img src="${resRoot}/image/user/grabbag/show_icon_laugh.png" class="laugh_or_praise_btn" onclick="laughUser(${item.user.userId},'${item.user.userName}');">
                            <c:if test="${item.praiseOrLaughCount > 0}">
                                <div class="praise_or_laugh_count">${item.praiseOrLaughCount}</div>
                            </c:if>
                        </c:if>
                        <c:if test="${item.userId == viewModel.user.userId}">
                            <img src="${resRoot}/image/user/grabbag/show_icon_cheat.png" class="laugh_or_praise_btn" onclick="cheatGrab();">
                            <c:if test="${item.praiseOrLaughCount > 0}">
                                <div class="praise_or_laugh_count">${item.praiseOrLaughCount}</div>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>

                <div class="clear"></div>
            </div>
        </c:forEach>
    </div>
</div>
