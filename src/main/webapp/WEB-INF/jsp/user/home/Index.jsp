<%@include file="/WEB-INF/jsp/common/UserCommonTop.jsp" %>

<script type="text/javascript" src="${resRoot}/js/common/userHomePage.js?version=${resVersion}"></script>

<%@include file="/WEB-INF/jsp/common/UserHomePage.jsp"%>

<script>
    $(document).ready(function() {
        $(".main_menu_holder_all").show();
    });
</script>

</body>
</html>

