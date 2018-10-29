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
import com.rasset.shmstab.model.DiagRichStepFourth
import com.rasset.shmstab.model.DiagnoseAssetBuyInfo
import com.rasset.shmstab.model.DiagnoseInfo
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.dpToPx
import com.rasset.shmstab.utils.isStrNullOrEmpty
import kotlinx.android.synthetic.main.fragment_diag_rich_step_fourth.*
import kotlinx.android.synthetic.main.fragment_diag_step_second.*
import java.util.HashMap

/**
 * Created by devok on 2018-09-05.
 */

class DiagRichStepFourthFragment : SurveyBaseFragment() {

    private object Holder {
        val INSTANCE = DiagRichStepFourthFragment()
    }

    companion object {
        val singleTone: DiagRichStepFourthFragment by lazy { Holder.INSTANCE }

        val instance: DiagRichStepFourthFragment by lazy { DiagRichStepFourthFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagRichStepFourthFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_rich_step_fourth, container, false)
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

    override fun getDiagDatas(): DiagRichStepFourth? {

        val lonRate = RG_LOAN_RATE.checkedItem?.tag.toString()
        val cocernType = RG_CONCEN_TYPE.checkedItem?.tag.toString()

        if (lonRate.isStrNullOrEmpty()
                || cocernType.isStrNullOrEmpty()) {
            return null
        }

        return DiagRichStepFourth(lonRate = lonRate, cocernType = cocernType)
    }

}