# SwipeBackHelper
A simple swipe back helper tool on android
## Quick Setup
### Include library
```kotlin
Edit your build.gradle file in your root project and add below repositories
repositories {
        google()
        jcenter()
        ···
        maven {
            url "https://messy.bintray.com/SwipeBackHelper"
        }
        ···
    }
```
Edit your build.gradle file and add below dependency.
```kotlin
dependencies {
    ···
    def swipebackhelper_version = 1.0
    implementation "com.messy.swipebackhelper:swipebackhelper:$swipebackhelper_version"
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
            //If yout application is multi-progress
            //Must call SwipeBackHelper.init(Application) in your main progress
            SwipeBackHelper.init(this)
        }
    }

    fun getCurrentProgressName(): String {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process in activityManager.runningAppProcesses) {
            if (process.pid == pid)
                return process.processName
        }
        return ""
    }

    fun isMainProgress(): Boolean {
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
                        http://www.apache.org/licenses/

TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION