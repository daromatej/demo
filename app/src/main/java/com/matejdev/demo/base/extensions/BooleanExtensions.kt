package com.matejdev.demo.base.extensions

import android.view.View

/**
 * Converts boolean to [View.VISIBLE] or [View.GONE]
 */
fun Boolean.toVisibleOrGone() = if (this) View.VISIBLE else View.GONE

/**
 * Converts boolean to [View.VISIBLE] or [View.INVISIBLE]
 */
fun Boolean.toVisibleOrInvisible() = if (this) View.VISIBLE else View.INVISIBLE

