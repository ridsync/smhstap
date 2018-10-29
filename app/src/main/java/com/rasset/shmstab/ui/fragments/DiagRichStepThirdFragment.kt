package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagRichStepSecond
import com.rasset.shmstab.model.DiagRichStepThird
import com.rasset.shmstab.model.DiagnoseAssetBuyInfo
import com.rasset.shmstab.model.DiagnoseInfo
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.dpToPx
import com.rasset.shmstab.utils.isStrNullOrEmpty
import kotlinx.android.synthetic.main.fragment_diag_rich_step_third.*
import kotlinx.android.synthetic.main.fragment_diag_step_second.*
import java.util.HashMap

/**
 * Created by devok on 2018-09-05.
 */

class DiagRichStepThirdFragment : SurveyBaseFragment() {

    private object Holder {
        val INSTANCE = DiagRichStepThirdFragment()
    }

    companion object {
        val singleTone: DiagRichStepThirdFragment by lazy { Holder.INSTANCE }

        val instance: DiagRichStepThirdFragment by lazy { DiagRichStepThirdFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagRichStepThirdFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_rich_step_third, container, false)
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

    private fun initFirst() {

    }

    override fun isValidDiagInputs(): Boolean {
        return true
    }

    override fun getDiagDatas(): DiagRichStepThird? {

        val buyRassetType = RG_BUY_RASSET.checkedItem?.tag.toString()
        val homeType = RG_HOME_TYPE.checkedItem?.tag.toString()
        val estateType = RG_DIAG_RESTATE_TYPE.checkedItem?.tag.toString()
        val expEdu = RG_EXP_EDUCATION.checkedItem?.tag.toString()
        val taxEdu = RG_TAX_EDUCATION.checkedItem?.tag.toString()
        val taxField = RG_TAX_FILED.checkedItem?.tag.toString()
        val taxFintechEdu = RG_FINTECH_EDU.checkedItem?.tag.toString()


        if (buyRassetType.isStrNullOrEmpty()
                || homeType.isStrNullOrEmpty()
                || estateType.isStrNullOrEmpty()
                || expEdu.isStrNullOrEmpty()
                || taxEdu.isStrNullOrEmpty()
                || taxField.isStrNullOrEmpty()
                || taxFintechEdu.isStrNullOrEmpty()) {
            return null
        }

        return DiagRichStepThird(buyRassetType = buyRassetType, homeType = homeType,
                estateType = estateType, expEdu = expEdu, taxEdu = taxEdu
                , taxField = taxField, taxFintechEdu = taxFintechEdu)
    }

}