package com.abkhrr.common.utils.ext.view

import android.app.Activity
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.common.utils.ext.context.inputManager
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun View.slideDown() {
    animate().setDuration(600).translationY(dip(height) * 1f).start()
}

fun View.slideUp() {
    animate().setDuration(600).translationY(16f).start()
}

fun View.showKeyboard() {
    clearFocus()
    requestFocus()
    context.inputManager?.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.closeKeyboard() {
    clearFocus()
    context.inputManager?.hideSoftInputFromWindow(windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

fun View?.backgroundColor(hexCode: String) {
    this?.run { setBackgroundColor(Color.parseColor(hexCode)) }
}

fun View?.backgroundColor(resId: Int) {
    this?.run { setBackgroundColor(ContextCompat.getColor(context, resId)) }
}

fun View?.onItemClicked() {
    this?.let {
        setOnClickListener { _ ->
            isEnabled = false
            postDelayed({ isEnabled = true }, 1000)
        }
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.visibleWithTime(animate: Boolean = true) {
    if (!animate) {
        visibility = View.VISIBLE
        alpha = 1.0f
        return
    }

    alpha = 0f
    visibility = View.VISIBLE
    animate().alpha(1.0f).duration = 100
}

fun View.invisibleWithTime(delayMillis: Long = 100) {
    animate().alpha(0f).duration = delayMillis

    Handler(Looper.getMainLooper()).postDelayed({
        visibility = View.INVISIBLE
    }, delayMillis)
}

fun View.goneWithTime(delayMillis: Long = 100) {
    animate().alpha(0f).duration = delayMillis

    Handler(Looper.getMainLooper()).postDelayed({
        visibility = View.GONE
    }, delayMillis)
}

fun View?.toggleView(isTrue: Boolean, isGone: Boolean = true) {
    this?.let {
        if (isTrue) {
            visibleWithTime()
        } else {
            if (isGone) {
                goneWithTime()
            } else {
                invisibleWithTime()
            }
        }
    }
}

fun ViewGroup.inflate(layoutResId: Int): View = LayoutInflater.from(context).inflate(
    layoutResId,
    this,
    false
)

fun Activity.snack(msg: String) {
    val contentView = findViewById<View>(android.R.id.content)
    Snackbar.make(contentView, msg, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.snack(msg: String) {
    val contentView = view?.findViewById<View>(android.R.id.content)
    if (contentView != null) Snackbar.make(contentView, msg, Snackbar.LENGTH_SHORT).show()

}

fun Long.toBytesReadable() = when {
    this == Long.MIN_VALUE || this < 0 -> "N/A"
    this < 1024L -> "$this B"
    this <= 0xfffccccccccccccL shr 40 -> "%.1f KB".format(this.toDouble() / (0x1 shl 10))
    this <= 0xfffccccccccccccL shr 30 -> "%.1f MB".format(this.toDouble() / (0x1 shl 20))
    this <= 0xfffccccccccccccL shr 20 -> "%.1f GB".format(this.toDouble() / (0x1 shl 30))
    this <= 0xfffccccccccccccL shr 10 -> "%.1f TB".format(this.toDouble() / (0x1 shl 40))
    this <= 0xfffccccccccccccL -> "%.1f PiB".format((this shr 10).toDouble() / (0x1 shl 40))
    else -> "%.1f EiB".format((this shr 20).toDouble() / (0x1 shl 40))
}

fun Long.toSumReadable(): String? {
    if (this < 1000) return "" + this
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.0f%c",
        this / 1000.0.pow(exp),
        "kMGTPE"[exp - 1]
    )
}

fun String.capital() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

fun <T : RecyclerView> T.removeItemDecorations() {
    for (i in 0 until itemDecorationCount) {
        removeItemDecorationAt(i)
    }
}

inline fun View.onSingleClickListener(
    delayedLoaderIsUsed: Boolean = false,
    crossinline listener: (View?) -> Unit
) {
    setOnClickListener(object : View.OnClickListener {
        private val DEFAULT_DELAY_MS = 750L
        private val LOADER_DELAY_MS  = 1500L
        private var notClicked       = true

        override fun onClick(view: View) {
            if (notClicked) {
                notClicked = false
                listener(view)
                val delayMs = if (delayedLoaderIsUsed) LOADER_DELAY_MS else DEFAULT_DELAY_MS
                view.postDelayed({ notClicked = true }, delayMs)
            }
        }
    })
}