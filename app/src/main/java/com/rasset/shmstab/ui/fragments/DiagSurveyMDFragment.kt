package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagnoseAssetSellInfo
import kotlinx.android.synthetic.main.fragment_diag_survey_md.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyMDFragment : SurveyBaseFragment() {

    private object Holder { val INSTANCE = DiagSurveyMDFragment() }

    companion object {
        val singleTone: DiagSurveyMDFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyMDFragment by lazy { DiagSurveyMDFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyMDFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_md, container, false)
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

    override fun getDiagDatas(): DiagnoseAssetSellInfo? {

        val address = TV_LOCATION_ADDRESS.text.toString().trim()
        val addressDetail = ET_LOCATION_ADDRESS_DETAIL.text.toString().trim()

        val investConsider = RG_DIAG_MD_PURPOSE.checkedItem?.tag.toString()
        val sellWonder = RG_DIAG_SELL_WONDER.checkedItem?.tag.toString()
        val sellTiming = RG_SELL_TIMING.checkedItem?.tag.toString()
        val buyMotive = RG_DIAG_MD_MOTIVE.checkedItem?.tag.toString()
        val needConsult = RG_SELL_NEED_CONSULTANT.checkedItem?.tag.toString()

        if (address.isNullOrEmpty() || investConsider.isNullOrEmpty() || sellWonder.isNullOrEmpty()
                || sellTiming.isNullOrEmpty() || buyMotive.isNullOrEmpty() || needConsult.isNullOrEmpty()){
            return null
        }

        return DiagnoseAssetSellInfo()
    }

}