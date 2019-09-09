package com.microtears.swipebackdemo

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.microtears.swipebackhelper.SwipeBackHelper

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (isMainProcess()) {
            SwipeBackHelper.init(this)
        }
    }

    private fun getCurrentProcessName(): String {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process in activityManager.runningAppProcesses) {
            if (process.pid == pid)
                return process.processName
        }
        return ""
    }

    private fun isMainProcess(): Boolean {
        return packageName == getCurrentProcessName()
    }
}