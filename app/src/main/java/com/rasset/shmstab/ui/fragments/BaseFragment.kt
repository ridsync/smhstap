package com.rasset.shmstab.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.rasset.shmstab.R
import com.rasset.shmstab.ui.dialog.ProgressLockDialog

/**
 * Created by devok on 2018-09-03.
 */

class BaseFragment : Fragment(){

    lateinit var mLockDialog: ProgressLockDialog
    lateinit var mActivity: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity!!
        mLockDialog = ProgressLockDialog(mActivity, R.style.TransparentDialog)
    }

    override fun onStop() {
        super.onStop()
        if (mLockDialog != null)
            mLockDialog.dismiss()
    }
}
