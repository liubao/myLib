package com.liubao.mylib

import android.app.Activity
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast


fun View.setOutLiner(
    radius: Int, elevationDp: Int = 0,
    mode: Int = CommonViewOutlineProvider.MODE_ALL
): View {
    OutLiner(this).setRadius(radius)
        .setElevation(elevationDp)
        .setMode(mode)
    return this
}

fun View.toast(s: String?): View {
    Toast.makeText(this.context, s, Toast.LENGTH_SHORT).show()
    return this
}


inline fun <reified V : View> V.visible(value: Boolean?): V {
    this.visibility = if (value == true) View.VISIBLE else View.GONE
    return this
}

inline fun <reified V : View> V.setHeightByWidth(
    ratio: Float,
    leftSpace: Int = 0,
    rightSpace: Int = 0
): V {
    if (layoutParams != null && ratio != 0f) {
        layoutParams.height = ((layoutParams.width - leftSpace - rightSpace) / ratio).toInt()

    }
    return this
}


inline fun <reified V : View> V.setWidthByRatio(
    designW: Int,
    fallback: Int = designW,
    designScreenW: Float = 375f
): V {
    if (context.screenWidth != 0 && this.layoutParams != null) {
        layoutParams.width = (context.screenWidth / designScreenW * designW).toInt()
    }
    return this
}

inline fun <reified T, R : TextView> R.goneOrSetData(data: T?, block: T.() -> Unit): R {
    if (data == null || (data == "")) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        data.block()
    }
    return this
}

inline fun <reified T, R : View> R.goneOrSetData(data: T?, block: T.() -> Unit): R {
    if (data == null || (data == "")) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        data.block()
    }
    return this
}

inline fun <R : View> R.topMargin(v: Int): R {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin = v
    return this
}

inline fun <R : View> R.setPadding(v: Int): R {
    setPadding(v, v, v, v)
    return this
}

inline fun <R : View> R.bottomMargin(v: Int): R {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = v
    }
    return this
}

fun View.getColor(res: Int): Int {
    return resources.getColor(res)
}

inline fun <reified R : ViewGroup>
        R.newLP(
    w: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    h: Int = ViewGroup.LayoutParams.WRAP_CONTENT
): ViewGroup.LayoutParams {
    val lp = when (this) {
        is RelativeLayout -> RelativeLayout.LayoutParams(w, h)
        is LinearLayout -> LinearLayout.LayoutParams(w, h)
        is ViewGroup.MarginLayoutParams -> ViewGroup.MarginLayoutParams(w, h)
        else -> ViewGroup.LayoutParams(w, h)
    }
    return lp
}

inline fun <reified R : ViewGroup>
        R.forEachChild(block: (child: View, index: Int) -> Unit): R {
    val size = childCount
    for (i in 0 until size) {
        block(getChildAt(i), i)
    }
    return this
}


fun View.getString(res: Int): String? {
    return resources.getString(res)
}

fun TextView.setBold(value: Boolean): TextView {
    setTypeface(if (value) Typeface.DEFAULT_BOLD else Typeface.DEFAULT)
    return this
}

fun View.setPaddingLeft(value: Int): View {
    setPadding(value, paddingTop, paddingRight, paddingBottom)
    return this
}

fun View.setPaddingTop(value: Int): View {
    setPadding(paddingLeft, value, paddingRight, paddingBottom)
    return this

}

fun View.setPaddingRight(value: Int): View {
    setPadding(paddingLeft, paddingTop, value, paddingBottom)
    return this
}

fun View.setPaddingBottom(value: Int): View {
    setPadding(paddingLeft, paddingTop, paddingRight, value)
    return this
}

fun View.setPaddingHorizontal(value: Int): View {
    setPadding(value, paddingTop, value, paddingBottom)
    return this
}

fun View.setPaddingVertical(value: Int): View {
    setPadding(paddingLeft, value, paddingRight, value)
    return this
}


fun TextView.setTextColorRes(id: Int): TextView {
    setTextColor(resources.getColor(id))
    return this
}

fun TextView.setTextSizeDp(size: Int): TextView {
    setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.toFloat())
    return this
}

fun TextView.setTextWithReturn(text: String): TextView {
    setText(text)
    return this
}

fun TextView.setBGRes(id: Int): TextView {
    setBackgroundResource((id))
    return this
}

fun View.measureKt(
    measureController: Controller,
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
): Pair<Int, Int>? {

    val spec =
        measureController?.invoke(
            View.MeasureSpec.getSize(widthMeasureSpec)
            , View.MeasureSpec.getMode(widthMeasureSpec),
            View.MeasureSpec.getSize(heightMeasureSpec),
            View.MeasureSpec.getMode(heightMeasureSpec)
        )

    return spec
}

class RatioController(ratio: Float) {
    val controller: Controller = { wS, wM, hS, hM ->
        Pair(
            View.MeasureSpec.makeMeasureSpec(wS, wM),
            if (ratio <= 0) {
                View.MeasureSpec.makeMeasureSpec(hS, hM)
            } else {
                View.MeasureSpec.makeMeasureSpec(
                    (wS / ratio).toInt(),
                    View.MeasureSpec.EXACTLY
                )
            }
        )
    }
}
typealias   Controller = ((Int, Int, Int, Int) -> Pair<Int, Int>)?

fun View.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun View.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}
