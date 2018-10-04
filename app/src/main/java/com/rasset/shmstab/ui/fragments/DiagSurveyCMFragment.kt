package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagnoseAssetSellInfo
import com.rasset.shmstab.model.DiagnoseCMInfo
import com.rasset.shmstab.model.DiagnoseInfo
import com.rasset.shmstab.utils.isStrNullOrEmpty
import kotlinx.android.synthetic.main.fragment_diag_survey_cm.*

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

    override fun getDiagDatas(): DiagnoseCMInfo? {

        val estateField = RG_CONSULT_FIELD.checkedItem?.tag.toString()
        val etcFieldContent = ET_CONSULT_FIELD_ETC.text.toString()
        val address = TV_LOCATION_ADDRESS.text.toString().trim()
        val addressDetail = ET_LOCATION_ADDRESS_DETAIL.text.toString().trim()
        val construct = RG_DIAG_CONSTRUCT.checkedItem?.tag.toString()
        val purpose = RG_PURPOSE.checkedItem?.tag.toString()
        val purposeEtc = ET_PURPOSE_ETC.text.toString().trim()
        val totalCost = RG_DIAG_TOTAL_COST.checkedItem?.tag.toString()
        val diagConsider = RG_DIAG_CONSIDER.checkedItem?.tag.toString()
        val needConsult = RG_NEED_CONSULT.checkedItem?.tag.toString()

        if (estateField.isStrNullOrEmpty() || address.isStrNullOrEmpty() || construct.isStrNullOrEmpty()
                || purpose.isStrNullOrEmpty() || totalCost.isStrNullOrEmpty() || diagConsider.isStrNullOrEmpty()
                || needConsult.isStrNullOrEmpty()){
            return null
        }

        return DiagnoseCMInfo(itemType=estateField,
                address="$address $addressDetail",
                purposeType=purpose,
                expenseType=totalCost,
                troubleType=diagConsider,
                consultYn=needConsult.toLong(),
                consultEtc01=etcFieldContent,
                consultEtc02=purposeEtc
                )
    }
}