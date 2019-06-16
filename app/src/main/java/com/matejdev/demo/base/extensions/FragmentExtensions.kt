package com.matejdev.demo.base.extensions

import androidx.fragment.app.Fragment
import com.matejdev.demo.base.view.BaseActivity

/**
 * Bind nullable intent extras to activity/fragment fields
 */
inline fun <reified T : Any> Fragment.arg(key: String, default: T? = null): Lazy<T?> = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}


/**
 * Bind intent extras to activity/fragment fields
 */
inline fun <reified T : Any> Fragment.argNotNull(key: String, default: T? = null): Lazy<T> = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

/**
 * Helper to retrieve [BaseActivity] from any fragment
 */
fun Fragment.baseActivity() = this.activity as BaseActivity<*>
