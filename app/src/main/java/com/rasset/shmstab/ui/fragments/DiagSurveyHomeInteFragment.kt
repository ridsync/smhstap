package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagnoseAssetSellInfo
import com.rasset.shmstab.model.DiagnoseHomeInteInfo
import com.rasset.shmstab.model.DiagnoseInfo
import kotlinx.android.synthetic.main.fragment_diag_survey_home_inte.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyHomeInteFragment : SurveyBaseFragment() {

    private object Holder { val INSTANCE = DiagSurveyHomeInteFragment() }

    companion object {
        val singleTone: DiagSurveyHomeInteFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyHomeInteFragment by lazy { DiagSurveyHomeInteFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyHomeInteFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_home_inte, container, false)
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

    override fun getDiagDatas(): DiagnoseHomeInteInfo? {


        val address = TV_LOCATION_ADDRESS.text.toString().trim()
        val addressDetail = ET_LOCATION_ADDRESS_DETAIL.text.toString().trim()

        val diagArea = RG_DIAG_AREA.checkedItem?.tag.toString()
        val totalCost = RG_TOTAL_COST.checkedItem?.tag.toString()
        val style = RG_STYLE.checkedItem?.tag.toString()
        val date = RG_CONSTRUCT_DATE.checkedItem?.tag.toString()
        val needConsult = RG_INVEST_CONSIDER.checkedItem?.tag.toString()

        if (address.isNullOrEmpty() || diagArea.isNullOrEmpty() || totalCost.isNullOrEmpty()
                || style.isNullOrEmpty() || date.isNullOrEmpty() || needConsult.isNullOrEmpty()){
            return null
        }

        return DiagnoseHomeInteInfo(address="$address $addressDetail",
                sizeType=diagArea,
                costType=totalCost,
                styleType=style,
                buildType=date,
                consultYn=needConsult.toLong()
                )
    }
}