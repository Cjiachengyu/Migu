<div class="answer_and_analysis_holder">
    <!-- 导航区 -->
    <div class="que_num_nav_holder">
        <div class="que_num_nav_icon que_num_nav_left_icon" onclick="gotoPrev();"></div>
        <span class="que_num" id="que_num_nav_1" onclick="gotoQue(1);">1</span>
        <span class="que_num" id="que_num_nav_2" onclick="gotoQue(2);">2</span>
        <span class="que_num" id="que_num_nav_3" onclick="gotoQue(3);">3</span>

        <div class="que_num_nav_icon que_num_nav_right_icon" onclick="gotoNext();"></div>
    </div>

    <!-- 题目区 -->
    <div class="que_list_holder" id="queList">

        <div class="que_holder" id="que_holder_1">
            <div class="que_content_top_bar">
                <div class="que_source_holder">来源：互联网</div>
                <div class="clear"></div>
            </div>
            <div class="que_content_holder">一对情侣一起去买了一块饼，女生吃了3/7块饼，男生吃掉剩下的4/7块饼。男生比女生多出了4.5元，请问这块饼多少元？
            </div>
            <div class="que_choice_holder">
                <div class="que_choice_item" id="que_choice_item_1" isRightAnswer="true" queChoiceId="1"
                     onclick="selectChoice(1, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_1" class="que_choice_title">A</div>
                    </div>
                    <div class="que_choice_content">4.5</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_2" queChoiceId="2"
                     onclick="selectChoice(2, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_2" class="que_choice_title">B</div>
                    </div>
                    <div class="que_choice_content">13.5</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_3" queChoiceId="3"
                     onclick="selectChoice(3, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_3" class="que_choice_title">C</div>
                    </div>
                    <div class="que_choice_content">31.5</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_4" queChoiceId="4"
                     onclick="selectChoice(4, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_4" class="que_choice_title">D</div>
                    </div>
                    <div class="que_choice_content">45</div>
                    <div class="clear"></div>
                </div>
            </div>

            <div class="que_analysis_1 hidden">
                <!-- <div class="que_analysis_holder">
                    <div class="que_analysis_title_holder">考点：</div>
                        <div>题目1考点</div>
                </div>

                <div class="que_analysis_holder">
                    <div class="que_analysis_title_holder">分析：</div>
                    <div>题目1分析</div>
                </div> -->
                <div class="que_answer_holder">
                    <div class="que_answer_title_holder">解答：</div>
                    <div>一对情侣显然都是男生付钱啦，答31.5的都活该撸一辈子……</div>
                </div>
            </div>

        </div>

        <div class="que_holder" id="que_holder_2">
            <div class="que_content_top_bar">
                <div class="que_source_holder">来源：互联网</div>

                <div class="clear"></div>
            </div>
            <div class="que_content_holder">地铁上有三头羊，中途上了一只狼，狼到站下车了，请问还有几只羊？</div>
            <div class="que_choice_holder">

                <div class="que_choice_item" id="que_choice_item_5" queChoiceId="5"
                     onclick="selectChoice(5, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_5" class="que_choice_title">A</div>
                    </div>
                    <div class="que_choice_content">0</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_6" queChoiceId="6"
                     onclick="selectChoice(6, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_6" class="que_choice_title">B</div>
                    </div>
                    <div class="que_choice_content">1</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_7" queChoiceId="7"
                     onclick="selectChoice(7, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_7" class="que_choice_title">C</div>
                    </div>
                    <div class="que_choice_content">2</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_8" queChoiceId="8" isRightAnswer="true"
                     onclick="selectChoice(8, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_8" class="que_choice_title">D</div>
                    </div>
                    <div class="que_choice_content">3</div>
                    <div class="clear"></div>
                </div>
            </div>

            <div class="que_analysis_2 hidden">
                <!-- <div class="que_analysis_holder">
                    <div class="que_analysis_title_holder">考点：</div>
                        <div>题目2考点</div>
                </div>

                <div class="que_analysis_holder">
                    <div class="que_analysis_title_holder">分析：</div>
                    <div>题目2分析</div>
                </div> -->
                <div class="que_answer_holder">
                    <div class="que_answer_title_holder">解答：</div>
                    <div>地铁上禁止吃东西~~~</div>
                </div>
            </div>

        </div>


        <div class="que_holder" id="que_holder_3">
            <div class="que_content_top_bar">
                <div class="que_source_holder">来源：互联网</div>
                <div class="clear"></div>
            </div>
            <div class="que_content_holder">房间里有四个屋角，每个屋角上坐着一只猫，每只猫的前面又有三只猫，每只猫的尾巴上还有一只猫。请问：房间里最少有多少猫？</div>
            <div class="que_choice_holder">

                <div class="que_choice_item" id="que_choice_item_9"
                     queChoiceId="9"
                     onclick="selectChoice(9, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_9" class="que_choice_title">A</div>
                    </div>
                    <div class="que_choice_content">16只</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_10" queChoiceId="10" isRightAnswer="true"
                     onclick="selectChoice(10, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_10" class="que_choice_title">B</div>
                    </div>
                    <div class="que_choice_content">4只</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_11" queChoiceId="11"
                     onclick="selectChoice(11, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_11" class="que_choice_title">C</div>
                    </div>
                    <div class="que_choice_content">6只</div>
                    <div class="clear"></div>
                </div>
                <div class="que_choice_item" id="que_choice_item_12" queChoiceId="12"
                     onclick="selectChoice(12, true);">
                    <div class="que_choice_title_holder">
                        <div id="que_choice_title_12" class="que_choice_title">D</div>
                    </div>
                    <div class="que_choice_content">8只</div>
                    <div class="clear"></div>
                </div>
            </div>

            <div class="que_analysis_3 hidden">
                <!-- <div class="que_analysis_holder">
                    <div class="que_analysis_title_holder">考点：</div>
                    <div>题目3考点</div>
                </div>

                <div class="que_analysis_holder">
                    <div class="que_analysis_title_holder">分析：</div>
                    <div>题目3分析</div>
                </div> -->

                <div class="que_answer_holder">
                    <div class="que_answer_title_holder">解答：</div>
                    <div>4只猫。每个墙角各有1只，都卷起尾巴坐着呢~~~~</div>
                </div>
            </div>
        </div>
    </div>

    <div class="common_dock_bottom">
        <div class="submit_operation_btn_holder">
            <a class="oper_que_nav_btn" id="oper_que_submit_btn" href="javascript:submitBag();">提交</a>
        </div>
    </div>

</div>


<div class="answer_all_right hidden">
    <div class="answer_all_right_top">
        <img class="grab_bag_title_img" src="${resRoot}/image/user/grabbag/title_grab_right_win.png">

        <div class="user_grab_bag_win_animation_holder">
            <div class="user_grab_bag_win_animation">
                <img class="grab_bag_face_img" src="${resRoot}/image/user/grabbag/face_win.png?v=2">

                <div class="money_change_tip">
                    <span class="money_change_tip_text">全部答对，+1000作业币</span>
                </div>
                <div class="money_change_tip_money_all_holder">
                    <span class="money_change_tip_money_all">我的财富变为：${sessionScope.user.money + 1000}作业币</span>
                </div>
            </div>

            <div class="operation_btn_holder">
                <a class="orange_2d_btn operation_btn_wide" onclick="goToAnalysis();">查看解析</a>
            </div>

            <div class="clear"></div>
        </div>
    </div>

    <img class="induce_img_fixed" src="${resRoot}/image/user/grabbag/induce_dark.png?v=1">
</div>


<div class="answer_not_right hidden">
    <div class="answer_all_right_top">
        <img class="grab_bag_title_img" src="${resRoot}/image/user/grabbag/title_grab_right_win.png">

        <div class="user_grab_bag_win_animation_holder">
            <div class="user_grab_bag_win_animation">
                <img class="grab_bag_face_img" src="${resRoot}/image/user/grabbag/face_win.png?v=2">

                <div class="money_change_tip">
                    <span class="money_change_tip_text">答错<span class="wrong_answer_count"></span>题，+1000作业币</span>
                </div>
                <div class="money_change_tip_money_all_holder">
                    <span class="money_change_tip_money_all">我的财富变为：${sessionScope.user.money + 1000}作业币</span>
                </div>
            </div>

            <div class="operation_btn_holder">
                <a class="orange_2d_btn operation_btn_wide" onclick="goToAnalysis();">查看解析</a>
            </div>

            <div class="clear"></div>
        </div>
    </div>

    <img class="induce_img_fixed" src="${resRoot}/image/user/grabbag/induce_dark.png?v=1">
</div>


<script src='${resRoot}/thirdparty/touch-0.2.14.min.js' type='text/javascript'></script>

<script src='${resRoot}/js/user/tuiguang/answering.js?version=${resVersion}' type='text/javascript'></script>

<script>
    $(document).ready(function() {
        if (getViewportSize()[1] < 500) {
            $(".induce_img_fixed").removeClass("induce_img_fixed").addClass("induce_img_normal");
        }
    });
</script>
