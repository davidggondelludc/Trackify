package com.apm.trackify.base.extensions

inline fun <T> lazyFast(crossinline operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}