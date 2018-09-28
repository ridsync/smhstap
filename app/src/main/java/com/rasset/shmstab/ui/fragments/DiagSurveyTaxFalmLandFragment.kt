package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagnoseTaxFalmInfo
import kotlinx.android.synthetic.main.fragment_diag_survey_tax_falmland.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyTaxFalmLandFragment : SurveyBaseFragment() {

    private object Holder { val INSTANCE = DiagSurveyTaxFalmLandFragment() }

    companion object {
        val singleTone: DiagSurveyTaxFalmLandFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyTaxFalmLandFragment by lazy { DiagSurveyTaxFalmLandFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyTaxFalmLandFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_tax_falmland, container, false)
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

    override fun getDiagDatas(): DiagnoseTaxFalmInfo? {

        val usage = RG_USAGE.checkedItem?.tag.toString()
        val isFarmLand = RG_FARMLAND.checkedItem?.tag.toString()
        val isPurpose = RG_USE_PURPOSE.checkedItem?.tag.toString()
        val exceptLand = RG_EXECPT_FARMLAND.checkedItem?.tag.toString()
        val residence = RG_RESIDENCE.checkedItem?.tag.toString()
        val urban = RG_URBAN.checkedItem?.tag.toString()
        val needConsult = RG_NEED_CONSULT.checkedItem?.tag.toString()

        if (usage.isNullOrEmpty() || isFarmLand.isNullOrEmpty() || isPurpose.isNullOrEmpty()
                || exceptLand.isNullOrEmpty() || residence.isNullOrEmpty() || urban.isNullOrEmpty() || needConsult.isNullOrEmpty()){
            return null
        }


        return DiagnoseTaxFalmInfo()
    }

}