<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>米谷管理员</title>
    <link href="${resRoot}/css/common/global.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/css/common/globalDesktop.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/thirdparty/zebra-dialog/zebra_dialog.css" type="text/css" rel="stylesheet"/>
    <link href="${resRoot}/css/admin/adminHomeIndex.css?version=${resVersion}" rel="stylesheet"/>
</head>
<body>

<script src='${resRoot}/thirdparty/jquery-1.11.2.min.js' type='text/javascript'></script>
<script src='${resRoot}/thirdparty/zebra-dialog/jquery.zebra_dialog.js' type='text/javascript'></script>
<script src='${resRoot}/js/common/global.js?version=${resVersion}' type='text/javascript'></script>

<div>
    <div class="index_logo_holder">
        <span>米谷管理员登录</span>
    </div>

    <div class="login_box">
        <div class="login_items_holder">
            <div class="input_holder">
                <span class="input_label">用户名：</span>
                <input id="input_account" class="input_text" type="text" placeholder="用户名">
                <div class="clear"></div>
            </div>

            <div class="input_holder">
                <span class="input_label">密　码：</span>
                <input id="input_password" class="input_text" type="password" placeholder="密码">
                <div class="clear"></div>
            </div>

            <div class="btn_holder">
                <input id="login_btn" class="input_btn" type="submit" value="登  录"/>
            </div>
        </div>
    </div>
</div>

<script src="${resRoot}/js/admin/adminHomeIndex.js?version=${resVersion}" type="text/javascript"></script>

</body>
</html>
