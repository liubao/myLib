package com.liubao.mylib

import android.graphics.Color


fun String?.fallback(fallBack: String?): String? {
    return if (this == null || this == "") fallBack else this
}


fun String?.notEmpty(): Boolean {
    if (this != null && this != "") {
        return true
    }
    return false
}


inline fun String?.whenNotEmpty(block: (s: String) -> Unit): Boolean {
    if (this != null && this != "") {
        block(this)
        return true
    }
    return false
}

fun String.parseColor(): Int {
    return if (this.startsWith("#"))
        Color.parseColor(this)
    else
        Color.parseColor("#$this")
}