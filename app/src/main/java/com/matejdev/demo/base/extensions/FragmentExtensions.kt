package com.matejdev.demo.base.extensions

import androidx.fragment.app.Fragment
import com.matejdev.demo.base.view.BaseActivity

inline fun <reified T : Any> Fragment.arg(key: String, default: T? = null): Lazy<T?> = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Fragment.argNotNull(key: String, default: T? = null): Lazy<T> = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}


fun Fragment.baseActivity() = this.activity as BaseActivity<*>
