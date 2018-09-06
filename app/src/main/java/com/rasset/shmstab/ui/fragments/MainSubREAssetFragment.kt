package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.dialog.ProgressLockDialog

/**
 * Created by devok on 2018-09-05.
 */

class MainSubREAssetFragment : BaseFragment() {

    private object Holder { val INSTANCE = MainSubREAssetFragment() }

    companion object {
        val singleTone: MainSubREAssetFragment by lazy { Holder.INSTANCE }

        val instance: MainSubREAssetFragment by lazy { MainSubREAssetFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainSubREAssetFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater?.inflate(R.layout.fragment_main_re_asset, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isFirstInit){
            isFirstInit = true
            initFirst()
        }

    }

    override fun onStop() {
        super.onStop()
        if (mLockDialog != null)
            mLockDialog.dismiss()
    }

    fun initFirst(){

    }



    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {

    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode, strErrorMsg, nReqType)
    }

    override fun onProgresStart(nReqType: Int) {

    }

    override fun onProgresStop(nReqType: Int) {

    }

}