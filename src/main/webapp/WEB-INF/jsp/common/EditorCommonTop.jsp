<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>

    <link href="${resRoot}/image/migu_logo.png" type="image/x-icon" rel="shortcut icon" />

    <link href="${resRoot}/css/common/global.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/css/common/globalDesktop.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/thirdparty/zebra-dialog/zebra_dialog.css" type="text/css" rel="stylesheet"/>
    <link href="${resRoot}/css/common/editorCommonTop.css?version=${resVersion}" rel="stylesheet"/>
</head>
<body>

<c:if test="${sessionScope.editor != null}">

<div class="top_root">
    <div class="top_bg">
        <div class="top_holder wrap relative">
            <div class="top_left_holder">
                <div class="top_left_info" style="">
                    <div class="top_left_info_title">
                        <c:if test="${sessionScope.editor.isChecker}"><span>抢作业审查员</span></c:if>
                        <c:if test="${!sessionScope.editor.isChecker}"><span>抢作业编辑员</span></c:if>
                    </div>
                </div>
                <div class="clear"></div>
            </div>

            <div class="top_right_top_holder">
                <div class="top_left_info_small_info">
                    <span>${sessionScope.editor.editorName} - </span>
                    <a href="/migu/editor/setting/changepwd">[修改密码]</a>
                    <a href="/migu/editor/home/logout">[退出]</a>
                </div>
                <div class="clear"></div>
            </div>

            <!-- 主菜单 -->
            <div class="top_right_holder">
            	<!-- 管理员身份 -->
	            <c:if test="${sessionScope.admin != null}">
	            	<div class="top_main_menu_div">
                        <a class="top_main_menu" href="/migu/editor/list/prizelist">奖品查看</a>

                        <div id="main_menu_prize_list" class="main_menu"></div>
                    </div>
                    <div class="top_main_menu_div">
                        <a class="top_main_menu" href="/migu/editor/prizeedit/editprize">奖品录入</a>

                        <div id="main_menu_prize_edit" class="main_menu"></div>
                    </div>
                    <div class="top_main_menu_div">
                        <a class="top_main_menu" href="/migu/editor/quelist/main">题库查看</a>

                        <div id="main_menu_que_list" class="main_menu"></div>
                    </div>
                    <div class="top_main_menu_div">
                        <a class="top_main_menu" href="/migu/editor/queedit/create">题目录入</a>

                        <div id="main_menu_que_edit" class="main_menu"></div>
                    </div>
	            </c:if>
	            <!-- 编辑员身份 -->
	            <c:if test="${sessionScope.admin == null}">
	            	<c:if test="${sessionScope.editor.canOperatePrize}">
	                    <div class="top_main_menu_div">
	                        <a class="top_main_menu" href="/migu/editor/list/prizelist">奖品查看</a>
	
	                        <div id="main_menu_prize_list" class="main_menu"></div>
	                    </div>
	                    <div class="top_main_menu_div">
	                        <a class="top_main_menu" href="/migu/editor/prizeedit/editprize">奖品录入</a>
	
	                        <div id="main_menu_prize_edit" class="main_menu"></div>
	                    </div>
	                </c:if>
	            	<c:if test="${sessionScope.editor.canOperateQue}">
	            		<div class="top_main_menu_div">
	                        <a class="top_main_menu" href="/migu/editor/quelist/main">题库查看</a>
	
	                        <div id="main_menu_que_list" class="main_menu"></div>
	                    </div>
	                    <div class="top_main_menu_div">
	                        <a class="top_main_menu" href="/migu/editor/queedit/create">题目录入</a>
	
	                        <div id="main_menu_que_edit" class="main_menu"></div>
	                    </div>
	            	</c:if>
	            </c:if>
                
            </div>
        </div>
    </div>
</div>
</c:if>

<script src='${resRoot}/thirdparty/jquery-1.11.2.min.js' type='text/javascript'></script>
<script src='${resRoot}/thirdparty/zebra-dialog/jquery.zebra_dialog.js' type='text/javascript'></script>
<script src='${resRoot}/js/common/global.js?version=${resVersion}' type='text/javascript'></script>
<script src='${resRoot}/js/common/editorCommonTop.js?version=${resVersion}' type='text/javascript'></script>
<script src='${resRoot}/js/common/pagingBar.js?version=${resVersion}' type='text/javascript'></script>



