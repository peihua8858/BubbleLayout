package com.peihua.bubble

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.widget.FrameLayout

/**
 * Bubble View for Android with custom stroke width and color, arrow size, position and direction.
 *
 * @author sudamasayuki
 */
class BubbleLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {
    var arrowDirection: ArrowDirection? = null
    private var mBubble: Bubble? = null
    var arrowWidth = 0f
        private set
    var cornersRadius = 0f
        private set
    var arrowHeight = 0f
        private set
    var arrowPosition = 0f
        private set
    var bubbleColor = 0
        private set
    var strokeWidth = 0f
        private set
    var strokeColor = 0
        private set

    init {
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.BubbleLayout)
        arrowWidth = a.getDimension(R.styleable.BubbleLayout_bl_arrowWidth, convertDpToPixel(8f, context))
        arrowHeight = a.getDimension(R.styleable.BubbleLayout_bl_arrowHeight, convertDpToPixel(8f, context))
        cornersRadius = a.getDimension(R.styleable.BubbleLayout_bl_cornersRadius, 0f)
        arrowPosition = a.getDimension(R.styleable.BubbleLayout_bl_arrowPosition, convertDpToPixel(12f, context))
        bubbleColor = a.getColor(R.styleable.BubbleLayout_bl_bubbleColor, Color.WHITE)
        strokeWidth = a.getDimension(R.styleable.BubbleLayout_bl_strokeWidth, DEFAULT_STROKE_WIDTH)
        strokeColor = a.getColor(R.styleable.BubbleLayout_bl_strokeColor, Color.GRAY)
        val location = a.getInt(R.styleable.BubbleLayout_bl_arrowDirection, ArrowDirection.START.value)
        arrowDirection = ArrowDirection.fromInt(location)
        a.recycle()
        initPadding()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        initDrawable(0, width, 0, height)
    }

    override fun dispatchDraw(canvas: Canvas) {
        if (mBubble != null) {
            mBubble!!.draw(canvas)
        }
        super.dispatchDraw(canvas)
    }

    private fun initDrawable(left: Int, right: Int, top: Int, bottom: Int) {
        if (right < left || bottom < top) {
            return
        }
        val rectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        when (arrowDirection) {
            ArrowDirection.START_CENTER, ArrowDirection.END_CENTER -> arrowPosition =
                (bottom - top) / 2f - arrowHeight / 2
            ArrowDirection.TOP_CENTER, ArrowDirection.BOTTOM_CENTER -> arrowPosition =
                (right - left) / 2f - arrowWidth / 2
            else -> {}
        }
        mBubble = Bubble(
            rectF, layoutDirection, arrowWidth, cornersRadius, arrowHeight, arrowPosition,
            strokeWidth, strokeColor, bubbleColor, arrowDirection!!
        )
    }

    private fun initPadding() {
        var paddingStart = paddingStart
        var paddingEnd = paddingEnd
        var paddingTop = paddingTop
        var paddingBottom = paddingBottom
        when (arrowDirection) {
            ArrowDirection.START, ArrowDirection.START_CENTER -> paddingStart += arrowWidth.toInt()
            ArrowDirection.END, ArrowDirection.END_CENTER -> paddingEnd += arrowWidth.toInt()
            ArrowDirection.TOP, ArrowDirection.TOP_CENTER -> paddingTop += arrowHeight.toInt()
            ArrowDirection.BOTTOM, ArrowDirection.BOTTOM_CENTER -> paddingBottom += arrowHeight.toInt()
            else -> {}
        }
        if (strokeWidth > 0) {
            paddingStart += strokeWidth.toInt()
            paddingEnd += strokeWidth.toInt()
            paddingTop += strokeWidth.toInt()
            paddingBottom += strokeWidth.toInt()
        }
        setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom)
    }

    private fun resetPadding() {
        var paddingStart = paddingStart
        var paddingEnd = paddingEnd
        var paddingTop = paddingTop
        var paddingBottom = paddingBottom
        when (arrowDirection) {
            ArrowDirection.START, ArrowDirection.START_CENTER -> paddingStart -= arrowWidth.toInt()
            ArrowDirection.END, ArrowDirection.END_CENTER -> paddingEnd -= arrowWidth.toInt()
            ArrowDirection.TOP, ArrowDirection.TOP_CENTER -> paddingTop -= arrowHeight.toInt()
            ArrowDirection.BOTTOM, ArrowDirection.BOTTOM_CENTER -> paddingBottom -= arrowHeight.toInt()
            else -> {}
        }
        if (strokeWidth > 0) {
            paddingStart -= strokeWidth.toInt()
            paddingEnd -= strokeWidth.toInt()
            paddingTop -= strokeWidth.toInt()
            paddingBottom -= strokeWidth.toInt()
        }
        setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
    }

    fun setArrowDirection(arrowDirection: ArrowDirection?): BubbleLayout {
        resetPadding()
        this.arrowDirection = arrowDirection
        initPadding()
        return this
    }

    fun setArrowWidth(arrowWidth: Float): BubbleLayout {
        resetPadding()
        this.arrowWidth = arrowWidth
        initPadding()
        return this
    }

    fun setCornersRadius(cornersRadius: Float): BubbleLayout {
        this.cornersRadius = cornersRadius
        requestLayout()
        return this
    }

    fun setArrowHeight(arrowHeight: Float): BubbleLayout {
        resetPadding()
        this.arrowHeight = arrowHeight
        initPadding()
        return this
    }

    fun setArrowPosition(arrowPosition: Float): BubbleLayout {
        resetPadding()
        this.arrowPosition = arrowPosition
        initPadding()
        return this
    }

    fun setBubbleColor(bubbleColor: Int): BubbleLayout {
        this.bubbleColor = bubbleColor
        requestLayout()
        return this
    }

    fun setStrokeWidth(strokeWidth: Float): BubbleLayout {
        resetPadding()
        this.strokeWidth = strokeWidth
        initPadding()
        return this
    }

    fun setStrokeColor(strokeColor: Int): BubbleLayout {
        this.strokeColor = strokeColor
        requestLayout()
        return this
    }

    companion object {
        var DEFAULT_STROKE_WIDTH = -1f
        fun convertDpToPixel(dp: Float, context: Context): Float {
            val metrics = context.resources.displayMetrics
            return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}