package com.liubao.mylib

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * 给color添加透明度
 * @param alpha 透明度 0～100
 * @param baseColor 基本颜色
 * @return
 */
fun Int.alphaColor(alpha: Int): Int {
    val a = 255.coerceAtMost(0.coerceAtLeast((((alpha / 100f * 255).toInt())))) shl 24
    val rgb = 0x00ffffff and this
    return a + rgb
}

fun Int.tintDrawable(context: Context, colorId: Int): Drawable? {
    val d = ContextCompat.getDrawable(context, this) ?: return null
    val drawableUp = DrawableCompat.wrap(d)
    drawableUp.setTint(ContextCompat.getColor(context, colorId))
    return drawableUp
}