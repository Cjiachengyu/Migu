<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="qc:admins" content="3673501355612571563757121" />
    <title>抢作业 - 登录</title>
    <link href="${resRoot}/css/common/global.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/css/common/globalMobile.css?version=${resVersion}" rel="stylesheet"/>
    <style>
        body {background: #ff4444; color: white; }
        .top_img {width: 100%; max-height: 230px; }
        .index_logo_holder { position: fixed; top: 0; text-align: center; height: 70px; font-size: 30px; left: 0; right: 0; padding: 20px 0;}
        .login_box { width: 80%; padding: 5%; margin: 0 auto 10px auto;}
        .input_holder { border: solid 1px red; margin-bottom: 20px; background: white;}
        .input_label { float: left; width: 80px; font-size: 20px; text-align: right; display: inline-block; padding: 10px; color: black;}
        .input_text { float: left; width: 120px; font-size: 20px; border: none; outline: none; padding: 10px;}
        .input_btn { padding: 10px; width: 100%; font-size: 17px; background: red; border: solid 1px white; color: white; }
        .qq_login_holder { padding: 10px 5%;}
        .qq_login_title { margin-bottom: 20px;}
        .qq_login {display: block; text-align: center;}
    </style>
</head>
<body>

<script src='${resRoot}/thirdparty/jquery-1.11.2.min.js' type='text/javascript'></script>
<script src='${resRoot}/js/common/global.js?version=${resVersion}' type='text/javascript'></script>

<div>
    <img class="top_img" src="${resRoot}/image/login_top.png">
    <div class="index_logo_holder">
        <span>抢作业-登录</span>
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

    <div class="qq_login_holder">
        <div class="qq_login_title">您也可以：</div>
        <div>
            <a class="qq_login" href="/qzy/qqconnect/auth"><img src="${resRoot}/image/qq_login_btn.png"></a>
        </div>
    </div>
</div>

<script src="${resRoot}/js/editor/editorHomeIndex.js?version=${resVersion}" type="text/javascript"></script>

</body>
</html>
