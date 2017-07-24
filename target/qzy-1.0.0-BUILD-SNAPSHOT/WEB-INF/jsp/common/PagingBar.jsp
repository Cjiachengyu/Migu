<style>
    .paging_bar { margin: 18px 0 10px 0; }
    .paging_bar_rightside { float: right; color: #AAA; }
    .paging_bar_rightside a { text-decoration: none; color: #FF703C; }
    .paging_bar_leftside { float: left; }
    .dest_page { width: 25px; position: relative; bottom: 2px; }
</style>

<div class="clearfix font_middle paging_bar">
    <div class="paging_bar_rightside">
        <c:choose>
            <c:when test="${viewModel.pageModule.currentPage == 1}"> 首页 上一页 </c:when>
            <c:otherwise>
                <a href="javascript:pagingBarFn.gotoPage(1);" class="highlight"> 首页 </a>
                <a href="javascript:pagingBarFn.gotoPage(${viewModel.pageModule.currentPage - 1});" class="highlight"> 上一页 </a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${viewModel.pageModule.currentPage == viewModel.pageModule.pageCount}"> 下一页 尾页 </c:when>
            <c:otherwise>
                <a href="javascript:pagingBarFn.gotoPage(${viewModel.pageModule.currentPage + 1});" class="highlight"> 下一页 </a>
                <a href="javascript:pagingBarFn.gotoPage(${viewModel.pageModule.pageCount});" class="highlight"> 尾页 </a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="paging_bar_leftside">
        <span class="hidden">${viewModel.pageModule.pageCount}</span>
        <span>当前页数：${viewModel.pageModule.currentPage} / ${viewModel.pageModule.pageCount}&nbsp;&nbsp;</span>
        <c:if test="${viewModel.pageModule.pageCount > 1}">
            <input class="dest_page" type="text" onkeydown="pagingBarFn.inputKeyDown(event);"/>
            <a class="highlight" onclick="pagingBarFn.jumpToPage(this);"> 跳转 </a>
        </c:if>
    </div>
    <div class="clear"></div>
</div>

