package com.rasset.shmstab.utils

import android.content.Context
import android.widget.Toast
import android.view.Gravity



/**
 * Created by devok on 2018-08-29.
 */
inline fun Context.showToast(message: () -> String) {
     val toast = Toast.makeText(this, message(), Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
    }
    toast.show()
}
