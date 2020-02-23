package com.liubao.mylib

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


fun Drawable.tint(context: Context, colorId: Int): Drawable {
    setTint(ContextCompat.getColor(context, colorId))
    return this
}

