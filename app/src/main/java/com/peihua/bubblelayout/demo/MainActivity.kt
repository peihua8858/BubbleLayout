package com.peihua.bubblelayout.demo

import com.peihua.bubble.BubblePopupHelper.create
import androidx.appcompat.app.AppCompatActivity
import android.widget.PopupWindow
import android.os.Bundle
import com.peihua.bubblelayout.demo.R
import com.peihua.bubble.BubbleLayout
import android.view.LayoutInflater
import com.peihua.bubble.BubblePopupHelper
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.peihua.bubble.ArrowDirection
import java.util.*

class MainActivity : AppCompatActivity() {
    private var popupWindow: PopupWindow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<View>(R.id.btn_popup) as Button
        val bubbleLayout = LayoutInflater.from(this).inflate(R.layout.layout_sample_popup, null) as BubbleLayout
        popupWindow = create(this, bubbleLayout)
        val random = Random()
        button.setOnClickListener { v ->
            val location = IntArray(2)
            v.getLocationInWindow(location)
            if (random.nextBoolean()) {
                bubbleLayout.arrowDirection = ArrowDirection.TOP
            } else {
                bubbleLayout.arrowDirection = ArrowDirection.BOTTOM
            }
            popupWindow!!.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.height + location[1])
        }
    }
}