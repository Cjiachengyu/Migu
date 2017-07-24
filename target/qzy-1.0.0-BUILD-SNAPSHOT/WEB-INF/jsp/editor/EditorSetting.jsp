<%@include file="/WEB-INF/jsp/common/EditorCommonTop.jsp" %>
<title>用户设置</title>

<style>
    .change_pwd_holder {
        margin: 100px auto;
        width: 500px;
    }

    .input_row {
        margin: 20px auto;
    }

    .input_title {
        padding: 5px;
        display: inline-block;
        width: 150px;
        text-align: right;
    }

    .input_password {
        padding: 5px;
        width: 270px;
    }

    .input_btn {
        padding: 5px 0;
        width: 414px;
        margin: 0 35px;
    }
</style>

<div class="change_pwd_holder">
    <div class="input_row">
        <span class="input_title">请输入原密码：</span>
        <input class="input_password" type="password" id="old_password">
    </div>
    <div class="input_row">
        <span class="input_title">请输入新密码：</span>
        <input class="input_password" type="password" id="new_password_1">
    </div>
    <div class="input_row">
        <span class="input_title">再输入新密码：</span>
        <input class="input_password" type="password" id="new_password_2">
    </div>
    <div class="input_row">
        <input class="input_btn" type="button" value="确定修改" onclick="changePwd();">
    </div>
</div>

<script>
    function changePwd() {
        var oldPwd = $("#old_password").val();
        var newPwd1 = $("#new_password_1").val();
        var newPwd2 = $("#new_password_2").val();

        if (!isNonEmptyString(oldPwd)) {
            AlertDialog("请输入原密码");
            $("#old_password").focus();
            return;
        }

        if (!isNonEmptyString(newPwd1)) {
            AlertDialog("请输入新密码");
            $("#new_password_1").focus();
            return;
        }

        if (!isNonEmptyString(newPwd2)) {
            AlertDialog("请再输入新密码");
            $("#new_password_2").focus();
            return;
        }

        if (newPwd1 !== newPwd2) {
            AlertDialog("两次输入新密码不一致");
            return;
        }

        if (newPwd1.length < 6) {
            AlertDialog("输入新密码长度太短，不能少于6位");
            return;
        }

        $.ajax({
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            type : "post",
            url : "dochangepwd",
            data : {
                oldPwd : oldPwd,
                newPwd : newPwd1
            },
            success : function(result) {
                checkEditorTimeout(result);

                if (result === "ok") {
                    AlertDialog("修改密码成功", null, function() {
                        window.location.href = "/qzy/editor/quelist/main";
                    });
                }
                else if (result === "notequal") {
                    AlertDialog("旧密码输入错误");
                    $("#old_password").val("").focus();
                }
                else if (result === "tooshort") {
                    AlertDialog("输入新密码长度太短，不能少于6位");
                }
            }
        });
    }

    $(".input_password").on("keydown", function(e) {
        if (e.which == 13) {
            changePwd();
        }
    });
</script>

<%@include file="/WEB-INF/jsp/common/EditorCommonBottom.jsp" %>

