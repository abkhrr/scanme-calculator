package com.abkhrr.common.utils.ext.collections

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

operator fun <T> MutableLiveData<List<T>>.plusAssign(item: T){
    val value  = this.value ?: emptyList()
    this.value = value + listOf(item)
}

fun <T> List<T>.safeSingle(): T? {
    return when (size) {
        1 -> this[0]
        else -> null
    }
}