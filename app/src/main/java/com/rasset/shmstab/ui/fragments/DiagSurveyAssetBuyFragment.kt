package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.Logger
import kotlinx.android.synthetic.main.fragment_diag_step_second.*
import java.util.HashMap

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyAssetBuyFragment : BaseFragment() {

    private object Holder { val INSTANCE = DiagSurveyAssetBuyFragment() }

    companion object {
        val singleTone: DiagSurveyAssetBuyFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyAssetBuyFragment by lazy { DiagSurveyAssetBuyFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyAssetBuyFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_asset_buy, container, false)
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

}