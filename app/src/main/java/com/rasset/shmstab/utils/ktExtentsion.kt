package com.rasset.shmstab.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by devok on 2018-08-29.
 */
inline fun Context.showToast(message: () -> String) {
    Toast.makeText(this, message(), Toast.LENGTH_SHORT).show()
}
