package com.abkhrr.common.utils

import android.app.Activity
import android.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.abkhrr.common.R

object AppUtil {
    fun setStatusBarColor(
        activityName: Activity,
        useThemeStatusBarColor: Boolean,
        iconColorWhite: Boolean
    ) {
        activityName.window.apply {
            if (iconColorWhite) {
                WindowCompat.setDecorFitsSystemWindows(this, false)
                val windowInsetColor = WindowInsetsControllerCompat(this, decorView)
                windowInsetColor.isAppearanceLightStatusBars = false
            } else {
                WindowCompat.setDecorFitsSystemWindows(this, true)
                val windowInsetColor = WindowInsetsControllerCompat(this, decorView)
                windowInsetColor.isAppearanceLightStatusBars = true
            }
            statusBarColor = if (useThemeStatusBarColor) activityName.resources.getColor(
                R.color.white,
                activityName.theme
            ) else Color.TRANSPARENT
        }
    }
}