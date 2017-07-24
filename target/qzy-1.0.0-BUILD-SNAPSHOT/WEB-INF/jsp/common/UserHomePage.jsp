<link href="${resRoot}/css/common/userHomePage.css?version=${resVersion}" rel="stylesheet"/>

<div class="main_menu_holder_all hidden">
    <div class="main_menu_top_half_all">
        <div id="adjust_main_menu_height">
            <c:if test="${sessionScope.user == null}">
                <a class="btn_login" href="/qzy/user/home/login">登录</a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a class="top_user_name" href="/qzy/user/account/billrecord">${sessionScope.user.userName}</a>
                <img class="btn_user_account" src="${sessionScope.user.portrait}" onclick="window.location.href='/qzy/user/account/billrecord';">
            </c:if>
        </div>

        <div class="main_menu_big_icon_holder">
            <a class="main_menu_icon_shuati" href="/qzy/user/shuati/selectzsd"></a>
            <a class="main_menu_icon_create_bag" href="/qzy/user/createbag/step1"></a>
        </div>
    </div>

    <div class="main_menu_row main_menu_row_1">
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_account" href="/qzy/user/account/billrecord"></a>
        </div>
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_rank" href="/qzy/user/friend/rankrightanswer"></a>
        </div>
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_study_report" href="/qzy/user/learningreports/myreports"></a>
        </div>
        <div class="clear"></div>
    </div>
    <div class="main_menu_row main_menu_row_2">
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_baglist" href="/qzy/user/baglist/listmysent"></a>
        </div>
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_prize" href="/qzy/user/prize/prizeexchange"></a>
        </div>
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_about" href="http://mp.weixin.qq.com/s?__biz=MzAxMzMzMTE1MQ==&mid=204960942&idx=1&sn=a82d7609e48d50e99093535bb8800596#rd"></a>
        </div>
        <div class="clear"></div>
    </div>
    
    <div class="main_menu_row main_menu_row_2">
        <div class="main_menu_icon_holder">
            <a class="main_menu_icon main_menu_icon_baglist" href="/qzy/user/wrong/quelist"></a>
        </div>
        <div class="clear"></div>
    </div>
    
</div>
