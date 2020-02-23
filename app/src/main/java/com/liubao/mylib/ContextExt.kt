package com.liubao.mylib

import android.content.Context
import android.content.Intent


fun restartApp(context: Context) {
    val packageManager = context.packageManager
    val intent = packageManager.getLaunchIntentForPackage(context.packageName)
    val componentName = intent!!.component
    val mainIntent: Intent = Intent.makeRestartActivityTask(componentName)
    context.startActivity(mainIntent);
    Runtime.getRuntime().exit(0)
}

fun getPackageInfo(context: Context, pn: String) =
    try {
        context.packageManager.let {
            it.getPackageInfo(pn, 0)?.apply {
                if (packageName == pn) {
                    return this
                }
            }
        }
    } catch (e: Throwable) {
        null
    }


/**
 * The absolute width of the available display size in pixels
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * The absolute height of the available display size in pixels
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}