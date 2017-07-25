<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>
    <!-- Bootstrap -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="${resRoot}/image/migu_logo.png" type="image/x-icon" rel="shortcut icon" />

    <link href="${resRoot}/css/common/global.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/css/common/globalDesktop.css?version=${resVersion}" rel="stylesheet"/>
    <link href="${resRoot}/thirdparty/zebra-dialog/zebra_dialog.css" type="text/css" rel="stylesheet"/>
    <!--
    <link href="${resRoot}/css/common/editorCommonTop.css?version=${resVersion}" rel="stylesheet"/>
    -->

</head>
<body>

<c:if test="${sessionScope.admin != null}">

<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">米谷绘本</a>
        </div>
        <div>
            <!--向左对齐-->
            <ul class="nav navbar-nav navbar-left">
                <li class="active"><a href="#">图书</a></li>
                <li><a href="#">会员</a></li>
                <li><a href="#">借阅</a></li>

            </ul>

            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        操作 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">退出</a></li>
                        <li><a href="#">设置</a></li>
                    </ul>
                </li>
            </ul>
            <!--
            <form class="navbar-form navbar-right" role="search">
                <button type="submit" class="btn btn-default">
                    向右对齐-提交按钮
                </button>
            </form>
            <p class="navbar-text navbar-right">向右对齐-文本</p>
            -->
        </div>
    </div>
</nav>

</c:if>

