package com.abkhrr.common.utils.ext.intent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.abkhrr.common.utils.common.launchDelayedFunction

fun Context.intentTo(c: Class<*>, extIntent: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, c)
    extIntent?.invoke(intent)
    startActivity(intent)
}

fun Context.intentTo(c: String, extIntent: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, Class.forName(c))
    extIntent?.invoke(intent)
    startActivity(intent)
}

fun <T: View> Activity.findLazy(@IdRes id: Int): Lazy<T> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        findViewById(id)
    }
}

fun Activity.intExtras(key: String): Lazy<Int> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getIntExtra(key, 0)
    }
}

inline fun <reified T : Any> Activity.launchActivity(requestCode: Int = -1, options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> Activity.launchActivityAndFinish(requestCode: Int = -1, options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivityForResult(intent, requestCode, options)
    finish()
}

inline fun <reified T : Any> Activity.launchDelayedActivityAndFinish(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    launchDelayedFunction(3000) {
        val intent = newIntent<T>(this)
        intent.init()
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        if(options != null) intent.putExtras(options)
        startActivity(intent)
        finish()
    }
}

inline fun <reified T : Any> Context.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : Any> Context.launchActivityAndFinish(options: Bundle? = null, activity: Activity? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent, options)
    activity?.finish()
}


inline fun <reified T : Any> Fragment.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this.requireContext())
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : Any> Fragment.launchActivityAndFinish(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this.requireContext())
    intent.init()
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent, options)
    activity?.finish()
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

fun Activity.longExtras(key: String): Lazy<Long> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getLongExtra(key, 0L)
    }
}

fun Activity.booleanExtras(key: String): Lazy<Boolean> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getBooleanExtra(key, false)
    }
}

fun Activity.parcelExtras(key: String): Lazy<Parcelable?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            intent.getParcelableExtra(key) as Parcelable?
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("Parcel extras not found")
        }
    }
}

fun Activity.doubleExtras(key: String): Lazy<Double> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getDoubleExtra(key, 0.0)
    }
}

fun Activity.intentData(): Lazy<Uri?>{
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.data
    }
}

fun Fragment.stringExtras(key: String): Lazy<String?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            arguments?.getString(key)
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("String extras not found")
        }
    }
}

fun Fragment.intExtras(key: String): Lazy<Int?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getInt(key, 0)
    }
}

fun Fragment.longExtras(key: String): Lazy<Long?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getLong(key, 0L)
    }
}

fun Fragment.booleanExtras(key: String): Lazy<Boolean?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getBoolean(key, false)
    }
}

fun <T>Fragment.parcelExtras(key: String): Lazy<T?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            arguments?.getParcelable(key) as T?
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("Parcel extras not found")
        }
    }
}

fun Fragment.doubleExtras(key: String): Lazy<Double?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getDouble(key, 0.0)
    }
}

fun <T: Parcelable>Fragment.parcelArrayListExtras(key: String): Lazy<ArrayList<T>?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            arguments?.getParcelableArrayList(key)
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("Parcel extras not found")
        }
    }
}