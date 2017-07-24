<%@include file="/WEB-INF/jsp/common/EditorCommonTop.jsp" %>
<title>奖品列表</title>
<link href="${resRoot}/css/editor/editorPrizeList.css?version=${resVersion}" rel="stylesheet"/>

<div id="prize_list" class="wrap">
    <%@include file="subview/PrizeListView.jsp" %>
</div>

<script>
    mainMenuConfig.currentMenuId = "main_menu_prize_list";
</script>
<%@include file="/WEB-INF/jsp/common/EditorCommonBottom.jsp" %>

