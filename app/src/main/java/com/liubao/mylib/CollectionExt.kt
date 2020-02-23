package com.liubao.mylib

import android.view.View
import android.view.ViewGroup


inline fun <reified ITEM, R : List<ITEM?>?> R.inRangeDo(index: Int, block: ITEM?.() -> Unit): R {
    if (this == null) return this
    if (index < this.size) {
        block.invoke(get(index))
    }
    return this
}

fun <R : List<*>?> R.isNullOrEmpty(): Boolean {
    return (this == null || this.isEmpty())
}

inline fun <R : ViewGroup, reified T : List<ITEM>, reified V : View,
        reified ITEM>
        R.outRangeGoneOrSetData(data: T?,
                                block: (childView: V, item: ITEM) -> Unit): R {
    if (data == null) return this
    for (i in 0 until childCount) {
        val childView = getChildAt(i)
        if (childView is V) {
            if (i < data.size) {
                childView.visibility = View.VISIBLE
                block.invoke(childView, data[i])
            } else {
                childView.visibility = View.GONE
            }
        }
    }
    return this
}