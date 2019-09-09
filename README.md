# SwipeBackHelper

A simple swipe back helper tool on android

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

[![Download](https://api.bintray.com/packages/swordsoul/android/swipebackhelper/images/download.svg)](https://bintray.com/swordsoul/adnroid/swipebackhelper/_latestVersion)

## Quick Setup

### Include library

Edit your build.gradle file and add below dependency.

```kotlin
dependencies {
    ···
    def swipebackhelper_version = 1.0
    implementation 'com.microtears.swipebackhelper:swipebackhelper:$swipebackhelper_version'
    ···
}
```

## How to use

### Step.1 Init

```kotlin
class DemoApplication : Application() {

    ...

    override fun onCreate() {
        super.onCreate()
        if (isMainProgress()) {
            //If yout application is multi-process
            //Must call SwipeBackHelper.init(Application) in your main process
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

    ...

}
```

### Step.2

```kotlin
class YourActivity : AppCompatActivity() {

    ...

    private val swipeBackHelper = SwipeBackHelper()
    //private val swipeBackHelper = SwipeBackHelper().apply { isFullScreen = true }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (!swipeBackHelper.progressTouchEvent(ev))
            super.dispatchTouchEvent(ev)
        else
            false
    }

    ...

}
```

## License

Apache License
 Version 2.0, January 2004
<http://www.apache.org/licenses/>

TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
