package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagnoseAssetSellInfo
import com.rasset.shmstab.model.DiagnoseInfo

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyCMFragment : SurveyBaseFragment() {

    private object Holder { val INSTANCE = DiagSurveyCMFragment() }

    companion object {
        val singleTone: DiagSurveyCMFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyCMFragment by lazy { DiagSurveyCMFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyCMFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_cm, container, false)
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

    fun initFirst(){

    }

    override fun isValidDiagInputs(): Boolean {
        return true
    }

    override fun getDiagDatas(): DiagnoseAssetSellInfo {
        return DiagnoseAssetSellInfo()
    }
}