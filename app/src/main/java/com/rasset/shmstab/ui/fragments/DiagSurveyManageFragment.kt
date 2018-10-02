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
import com.rasset.shmstab.model.DiagnoseManagementInfo
import kotlinx.android.synthetic.main.fragment_diag_survey_management.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyManageFragment : SurveyBaseFragment() {

    private object Holder {
        val INSTANCE = DiagSurveyManageFragment()
    }

    companion object {
        val singleTone: DiagSurveyManageFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyManageFragment by lazy { DiagSurveyManageFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyManageFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_management, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isFirstInit) {
            isFirstInit = true
            initFirst()
        }

    }

    override fun onStop() {
        super.onStop()
    }

    fun initFirst() {

    }

    override fun isValidDiagInputs(): Boolean {
        return true
    }

    override fun getDiagDatas(): DiagnoseManagementInfo? {

        val address = TV_LOCATION_ADDRESS.text.toString().trim()
        val addressDetail = ET_LOCATION_ADDRESS_DETAIL.text.toString().trim()

        val consultType = RG_CONSULT_TYPE.checkedItem?.tag.toString()
        val consultTypeEtc = ET_CONSULT_TYPE_ETC.text.toString().trim()

        val consultField = RG_CONSULT_FIELD.checkedItem?.tag.toString()
        val consultFieldEtc = ET_CONSULT_FIELD_ETC.text.toString().trim()

        val isLesseCount = RG_LESSEE.checkedItem?.tag.toString()
        val isEstimate = RG_ESTIMATE.checkedItem?.tag.toString()
        val leaseConsider = RG_LEASE.checkedItem?.tag.toString()
        val needConsult = RG_NEED_CONSULT.checkedItem?.tag.toString()

        if (address.isNullOrEmpty() || consultType.isNullOrEmpty() || consultField.isNullOrEmpty()
                || consultFieldEtc.isNullOrEmpty() || isLesseCount.isNullOrEmpty() || isEstimate.isNullOrEmpty()
                || leaseConsider.isNullOrEmpty()|| needConsult.isNullOrEmpty()){
            return null
        }

        return DiagnoseManagementInfo(address="$address $addressDetail",
                itemType=consultField,
                propertyType=consultType,
                hlCountType=isLesseCount,
                estimateType=isEstimate,
                leaseType=consultField,
                consultYn=leaseConsider.toLong(),
                consultEtc01=consultFieldEtc,
                consultEtc02=consultTypeEtc
                )
    }
}