package com.peihua.bubble

/**
 * 箭头方向
 *
 * @author sudamasayuki
 */
enum class ArrowDirection(val value: Int) {
    /**
     * 箭头方向向左
     */
    START(0),

    /**
     * 箭头方向向右
     */
    END(1),

    /**
     * 箭头方向向上
     */
    TOP(2),

    /**
     * 箭头方向向下
     */
    BOTTOM(3),

    /**
     * 箭头方向向左并垂直居中
     */
    START_CENTER(4),

    /**
     * 箭头方向向右并垂直居中
     */
    END_CENTER(5),

    /**
     * 箭头方向向上并水平居中
     */
    TOP_CENTER(6),

    /**
     * 箭头方向向下并水平居中
     */
    BOTTOM_CENTER(7);

    companion object {
        @JvmStatic
        fun fromInt(value: Int): ArrowDirection {
            for (arrowDirection in values()) {
                if (value == arrowDirection.value) {
                    return arrowDirection
                }
            }
            return START
        }
    }
}