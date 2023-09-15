package com.abkhrr.common.utils.common

import android.os.Handler
import android.os.Looper

inline fun <T> T.applyIf(predicate: Boolean, block: T.() -> Unit): T = apply {
    if (predicate) block(this)
}

fun launchDelayedFunction(timeMillis: Long = 3000, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action() }, timeMillis)
}