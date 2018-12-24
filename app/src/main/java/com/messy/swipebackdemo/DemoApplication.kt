package com.messy.swipebackdemo

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.messy.swipebackhelper.SwipeBackHelper

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (isMainProcess()) {
            SwipeBackHelper.init(this)
        }
    }

    fun getCurrentProcessName(): String {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process in activityManager.runningAppProcesses) {
            if (process.pid == pid)
                return process.processName
        }
        return ""
    }

    fun isMainProcess(): Boolean {
        return packageName == getCurrentProcessName()
    }
}