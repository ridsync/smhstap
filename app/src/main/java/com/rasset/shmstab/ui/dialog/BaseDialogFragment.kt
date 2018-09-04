package com.rasset.shmstab.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Created by devok on 2018-09-04.
 */
open class BaseDialogFragment : DialogFragment() {

    protected var mOnViewClickListener: BaseDialogFragment.OnViewClickListener? = null
    protected var mOnClickListener: BaseDialogFragment.OnClickListener? = null
    protected var mOnDismissListener: BaseDialogFragment.OnDismissListener? = null
    protected var mContext: Context? = null
    protected var mRootView: View? = null
    var data: Any? = null
    protected var mTvTitle: TextView? = null
    protected var mTvContents: TextView? = null
    protected var mBtnPositive: Button? = null
    protected var mBtnNegative: Button? = null

    interface OnViewClickListener {
        fun onClick(view: View, dialog: BaseDialogFragment)
    }

    interface OnClickListener {
        fun onClickPositive(dialog: BaseDialogFragment)

        fun onClickNegative(dialog: BaseDialogFragment)

        fun onClickNeutral(dialog: BaseDialogFragment)
    }

    interface OnDismissListener {
        fun onDismiss(dialog: BaseDialogFragment)
    }

    interface OnItemClickListener {
        fun onItemClick(dialog: BaseDialogFragment, which: Int)
    }

    interface OnMultiItemClickListener {
        fun onItemClick(dialog: BaseDialogFragment, which: Int, isChecked: Boolean)
    }

    fun setOnViewClickListener(listener: BaseDialogFragment.OnViewClickListener) {
        mOnViewClickListener = listener
    }

    fun setOnClickListener(listener: BaseDialogFragment.OnClickListener) {
        mOnClickListener = listener
    }

    fun setOnDismissListener(listener: BaseDialogFragment.OnDismissListener) {
        mOnDismissListener = listener
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        if (mOnDismissListener != null)
            mOnDismissListener!!.onDismiss(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        data = null
    }

}