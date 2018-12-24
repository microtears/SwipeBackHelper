package com.messy.swipebackhelper

import android.app.Application
import android.view.MotionEvent

class SwipeBackHelper {
    companion object {
        private val lock = Any()
        private var lifecycleHelper: ActivityLifecycleHelper? = null
        private fun getLifecycleHelper(): ActivityLifecycleHelper {
            if (lifecycleHelper == null)
                synchronized(lock) {
                    if (lifecycleHelper == null)
                        lifecycleHelper = ActivityLifecycleHelper()
                }
            return lifecycleHelper!!
        }

        fun init(application: Application) {
            application.registerActivityLifecycleCallbacks(getLifecycleHelper())
        }
    }

    private val touchHelper = TouchHelper(getLifecycleHelper())

    var isFullScreen: Boolean
        get() = TouchHelper.isFullScreen
        set(value) {
            TouchHelper.isFullScreen = value
        }

    fun progressTouchEvent(ev: MotionEvent): Boolean {
        return touchHelper.processTouchEventInternal(ev)
    }
}