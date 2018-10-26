package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagnoseAssetBuyInfo

/**
 * Created by devok on 2018-09-05.
 */

class DiagRichStepFirstFragment : SurveyBaseFragment() {

    private object Holder { val INSTANCE = DiagRichStepFirstFragment() }

    companion object {
        val singleTone: DiagRichStepFirstFragment by lazy { Holder.INSTANCE }

        val instance: DiagRichStepFirstFragment by lazy { DiagRichStepFirstFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagRichStepFirstFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_rich_step_first, container, false)
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
    }

    private fun initFirst(){


    }


    override fun isValidDiagInputs(): Boolean {
        return true
    }

    override fun getDiagDatas(): DiagnoseAssetBuyInfo? {
            return null
    }
}