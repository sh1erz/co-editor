package com.example.coedit.ui.widgets.hscrollmenu

import com.example.coedit.R

enum class EditMenuItem(val titleId: Int, val icon: Int){
    OPEN_TEXT_STYLES(R.string.text_styles, R.drawable.ic_action_text_style),
    CHECKBOX(R.string.checkbox, R.drawable.ic_baseline_check_box_24),
    ADD_IMAGE(R.string.add_image, R.drawable.ic_baseline_image_24),
    OPEN_THEME_MENU(R.string.choose_theme, R.drawable.ic_baseline_theme_24)
}

