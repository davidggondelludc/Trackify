package com.apm.trackify.extensions

import java.util.*

fun <T> List<T>.swap(i: Int, j: Int): List<T> {
    if (isInBounds(i) && isInBounds(j)) {
        Collections.swap(this, i, j)
    }
    return this
}

fun <T> List<T>.isInBounds(index: Int): Boolean {
    return index in 0..lastIndex
}