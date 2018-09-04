package com.rasset.shmstab.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.Window
import com.rasset.shmstab.R

/**
 * Created by devok on 2018-09-04.
 */
class ProgressLockDialogFragment : BaseDialogFragment(), View.OnClickListener {

    private var mResDialogLayout = R.layout.dialog_progress
    private var mResTitle: Int = 0

    interface OnPositvelListener {
        fun onClickPositive(dialog: ProgressLockDialogFragment)
    }

    interface OnNegativelListener {
        fun onClickNegative(dialog: ProgressLockDialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {

        val builder = AlertDialog.Builder(context!!.applicationContext, R.style.MaterialCustomDialog)
        mRootView = layoutInflater.inflate(mResDialogLayout, null)
        builder.setView(mRootView)
        val dlg = builder.create()
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setCanceledOnTouchOutside(false)
        dlg.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false
        return dlg
    }

    override fun onStart() {
       super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onClick(v: View) {}

    override fun onDestroyView() {
        super.onDestroyView()
        mTvContents = null
    }

    fun setTitle(mStrTitle: Int) {
        this.mResTitle = mStrTitle
    }

}