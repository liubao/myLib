package com.liubao.mylib

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CommonViewOutlineProvider : ViewOutlineProvider() {
    var radius = 0
    var mode = 0
    var alpha = .5f
    override fun getOutline(
        view: View,
        outline: Outline
    ) {
        val w = view.width
        val h = view.height
        outline.alpha = alpha
        when (mode) {
            MODE_NONE -> {
                outline.alpha = 0f
                outline.setRect(0, 0, w, h)
            }
            MODE_ALL -> outline.setRoundRect(
                0,
                0,
                w,
                h,
                radius.toFloat()
            )
            MODE_LEFT -> outline.setRoundRect(
                0,
                0,
                w + radius,
                h,
                radius.toFloat()
            )
            MODE_TOP -> outline.setRoundRect(
                0,
                0,
                w,
                h + radius,
                radius.toFloat()
            )
            MODE_RIGHT -> outline.setRoundRect(
                0 - radius,
                0,
                w,
                h,
                radius.toFloat()
            )
            MODE_BOTTOM -> outline.setRoundRect(
                0,
                -radius,
                w,
                h,
                radius.toFloat()
            )
        }
    }

    companion object {
        const val MODE_NONE = -1
        const val MODE_NO = -1
        const val MODE_ALL = 0
        const val MODE_LEFT = 1
        const val MODE_TOP = 2
        const val MODE_RIGHT = 3
        const val MODE_BOTTOM = 4
    }
}

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class OutLiner(var view: View) {
    var outlineProvider: CommonViewOutlineProvider?
    fun setElevation(elevation: Int): OutLiner {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.elevation = view.dp2px(elevation).toFloat()
        }
        return this
    }

    /*
    CommonViewOutlineProvider.MODE
     */
    fun setMode(mode: Int): OutLiner {
        if (outlineProvider != null) {
            outlineProvider!!.mode = mode
        }
        return this
    }

    fun setRadius(radius: Int): OutLiner {
        if (outlineProvider != null) {
            outlineProvider!!.radius = view.dp2px(radius)
        }
        return this
    }

    fun newCommonViewOutlineProvider(): CommonViewOutlineProvider? {
        var commonViewOutlineProvider: CommonViewOutlineProvider? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            commonViewOutlineProvider = CommonViewOutlineProvider()
            commonViewOutlineProvider.mode = CommonViewOutlineProvider.MODE_NO
        }
        return commonViewOutlineProvider
    }

    init {
        outlineProvider = newCommonViewOutlineProvider()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.outlineProvider = outlineProvider
            view.clipToOutline = true
        }
    }
}
