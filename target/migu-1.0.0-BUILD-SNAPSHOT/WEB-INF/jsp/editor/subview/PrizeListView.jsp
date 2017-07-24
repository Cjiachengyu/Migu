<div class="prize_list_holder">
    <c:forEach items="${viewModel.prizeList}" var="prize" varStatus="status">
        <div class="prize_holder">
            <div class="prize_index_holder">
                <span>${status.index + 1}</span>
            </div>
            <div class="prize_image_holder">
                <img src="${prize.prizeImageUrl	}">
            </div>
            <div class="prize_name_holder">
                <span class="prize_name">${prize.prizeName}</span>
            </div>
            <div class="prize_money_holder">
                <span class="prize_money">价格：${prize.prizeMoney}</span>
            </div>
            <div class="prize_operation_holder">
                <input type="button" class="modify_btn" onclick="window.location.href='/migu/editor/prizeedit/${prize.prizeId}/modify';" value="修改">
                <input type="button" class="delete_btn" onclick="deletePrize(${prize.prizeId});" value="删除">
            </div>
            <div class="clear"></div>
        </div>
    </c:forEach>
</div>

<script src='${resRoot}/js/editor/prizeListView.js?version=${resVersion}' type='text/javascript'></script>
