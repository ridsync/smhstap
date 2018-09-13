package com.rasset.shmstab.utils

import android.content.Context
import android.widget.Toast
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.regex.Pattern


/**
 * Created by devok on 2018-08-29.
 */
inline fun Context.showToast(message: () -> String) {
     val toast = Toast.makeText(this, message(), Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
    }
    toast.show()
}

fun hideIME(context: Context?, view: View): Boolean {
    if (context == null) return false

    val mgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return mgr.hideSoftInputFromWindow(view.windowToken, 0)
}

fun makePhoneNumber(phoneNumber: String): String {
    val regEx = "(\\d{3})(\\d{3,4})(\\d{4})"
    return if (!Pattern.matches(regEx, phoneNumber)) phoneNumber else phoneNumber.replace(regEx.toRegex(), "$1-$2-$3")
}

class Stack<T:Comparable<T>>(list:MutableList<T>) {

    var items: MutableList<T> = list


    fun isEmpty():Boolean = this.items.isEmpty()

    fun count():Int = this.items.count()

    fun push(element:T) {
        val position = this.count()
        this.items.add(position, element)
    }

    override  fun toString() = this.items.toString()

    fun pop():T? {
        if (this.isEmpty()) {
            return null
        } else {
            val item =  this.items.count() - 1
            return this.items.removeAt(item)
        }
    }

    fun peek():T? {
        if (isEmpty()) {
            return null
        } else {
            return this.items[this.items.count() - 1]
        }
    }

}