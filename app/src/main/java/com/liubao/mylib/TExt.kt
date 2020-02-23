package com.liubao.mylib


inline fun <reified T> T.isAllEqual(vararg ts: T): Boolean {
    ts.forEach {
        if (it?.equals(this) == false) {
            return false
        }
    }
    return true
}

inline fun <R : Boolean?> R.trueDo(block: () -> Unit): R {
    if (this == true) {
        block.invoke()
    }
    return this
}
