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
import com.rasset.shmstab.model.DiagRichStepFifth
import com.rasset.shmstab.model.DiagnoseAssetBuyInfo
import com.rasset.shmstab.model.DiagnoseInfo
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.dpToPx
import com.rasset.shmstab.utils.isStrNullOrEmpty
import kotlinx.android.synthetic.main.fragment_diag_rich_step_fifrth.*
import kotlinx.android.synthetic.main.fragment_diag_step_second.*
import java.util.HashMap

/**
 * Created by devok on 2018-09-05.
 */

class DiagRichStepFifthFragment : SurveyBaseFragment() {

    private object Holder {
        val INSTANCE = DiagRichStepFifthFragment()
    }

    companion object {
        val singleTone: DiagRichStepFifthFragment by lazy { Holder.INSTANCE }

        val instance: DiagRichStepFifthFragment by lazy { DiagRichStepFifthFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagRichStepFifthFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_rich_step_fifrth, container, false)
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

    override fun getDiagDatas(): DiagRichStepFifth? {
        val tryEstateType = RG_TRY_ESTATE.checkedItem?.tag.toString()
        val lottoEstateType = RG_LOTTO_TYPE.checkedItem?.tag.toString()

        if (tryEstateType.isStrNullOrEmpty()
                || lottoEstateType.isStrNullOrEmpty()) {
            return null
        }

        return DiagRichStepFifth(tryEstateType = tryEstateType, lottoEstateType = lottoEstateType)
    }

}