package com.abkhrr.libs.ext

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

fun View.setOnApplyWindowTopInsetMargin(extra: Int = 0) =
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top + extra
        updateMargin(top = topInset)
        insets
    }

fun View.updateMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
    left?.let { leftMargin = it }
    top?.let { topMargin = it }
    right?.let { rightMargin = it }
    bottom?.let { bottomMargin = it }
}