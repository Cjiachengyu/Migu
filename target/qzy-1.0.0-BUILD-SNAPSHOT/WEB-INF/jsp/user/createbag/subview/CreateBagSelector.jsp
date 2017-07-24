<div class="select_input_row" onclick="openSelectZskPopup();">
    <span class="select_input_col1">选择知识库</span>
    <span class="select_input_col2" id="selected_zsk_content"></span>
    <span class="select_input_col3">&gt;</span>

    <div class="clear"></div>
</div>

<div class="select_input_row" onclick="openSelectZsd1Popup();">
    <span class="select_input_col1">一级知识点</span>
    <span class="select_input_col2" id="selected_zsd1_content"></span>
    <span class="select_input_col3">&gt;</span>

    <div class="clear"></div>
</div>

<div id="select_zsd2_input_row" class="select_input_row hidden" onclick="openSelectZsd2Popup();">
    <span class="select_input_col1">二级知识点</span>
    <span class="select_input_col2" id="selected_zsd2_content"></span>
    <span class="select_input_col3">&gt;</span>

    <div class="clear"></div>
</div>

<div class="select_input_row">
    <span class="select_input_col1">选择难度</span>
    <!--下面的换行不可以格式化，否则样式会出问题 -->
    <span class="select_input_col_2_3"><a id="btn_difficulty_0" class="btn_difficulty btn_difficulty_selected" href="javascript:selectDifficulty(0);"
            >随机</a><a id="btn_difficulty_1" class="btn_difficulty" href="javascript:selectDifficulty(1);"
            >容易</a><a id="btn_difficulty_2" class="btn_difficulty" href="javascript:selectDifficulty(2);"
            >普通</a><a id="btn_difficulty_3" class="btn_difficulty" href="javascript:selectDifficulty(3);">困难</a></span>
    <div class="clear"></div>
</div>

