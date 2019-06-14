package com.matejdev.demo.base.extensions

import android.view.View

fun Boolean.toVisibleOrGone() = if (this) View.VISIBLE else View.GONE

fun Boolean.toVisibleOrInvisible() = if (this) View.VISIBLE else View.INVISIBLE

