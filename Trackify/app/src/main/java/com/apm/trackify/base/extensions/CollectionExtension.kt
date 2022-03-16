package com.apm.trackify.base.extensions

import java.util.*

fun <K, V> HashMap<K, V>.getOrDefaultSet(key: K, defaultValue: V): V {
    val value = get(key)
    return if (value == null) {
        set(key, defaultValue)
        defaultValue
    } else value
}

fun <T> List<T>.swap(i: Int, j: Int): List<T> {
    if (isInBounds(i) && isInBounds(j)) {
        Collections.swap(this, i, j)
    }
    return this
}

fun <T> List<T>.isInBounds(index: Int): Boolean {
    return index in 0..lastIndex
}