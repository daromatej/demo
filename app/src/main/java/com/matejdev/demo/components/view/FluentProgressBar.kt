package com.matejdev.demo.components.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

/**
 * A [ProgressBar] that adds a delay when hiding
 */
class FluentProgressBar : ProgressBar {

    companion object {
        private const val HIDE_DELAY_MILLISECONDS: Long = 1000
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private var hideHandler = Handler(Looper.getMainLooper())

    override fun setVisibility(visibility: Int) {
        if (visibility == View.VISIBLE) {
            super.setVisibility(visibility)
            hideHandler.removeCallbacksAndMessages(null)
        } else delayedHide(visibility)
    }

    private fun delayedHide(visibility: Int) {
        hideHandler.postDelayed({ hide(visibility) }, HIDE_DELAY_MILLISECONDS)
    }

    private fun hide(visibility: Int) {
        super.setVisibility(visibility)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        hideHandler.removeCallbacksAndMessages(null)
    }
}
