package com.microtears.swipebackdemo

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.microtears.swipebackhelper.SwipeBackHelper
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val swipeBackHelper = SwipeBackHelper().apply { isFullScreen = true/*开启全面屏适配*/ }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (!swipeBackHelper.progressTouchEvent(ev))
            super.dispatchTouchEvent(ev)
        else
            false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        button.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}
