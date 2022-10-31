package com.peihua.bubble

import android.content.Context
import com.peihua.bubble.BubbleLayout
import android.widget.PopupWindow
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.peihua.bubble.R

object BubblePopupHelper {
    @JvmStatic
    fun create(context: Context, bubbleLayout: BubbleLayout): PopupWindow {
        val popupWindow = PopupWindow(context)
        popupWindow.contentView = bubbleLayout
        popupWindow.isOutsideTouchable = true
        popupWindow.width = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.animationStyle = android.R.style.Animation_Dialog
        // change background color to transparent
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.popup_window_transparent))
        return popupWindow
    }
}