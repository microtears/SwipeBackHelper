package com.messy.swipebackdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.messy.swipebackhelper.SwipeBackHelper
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val swipeBackHelper = SwipeBackHelper()
    //private val swipeBackHelper = SwipeBackHelper().apply { isFullScreen = true/*开启全面屏适配*/ }

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
