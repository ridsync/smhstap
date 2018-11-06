package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.model.DiagRichStepFirst
import com.rasset.shmstab.model.DiagnoseAssetBuyInfo
import com.rasset.shmstab.utils.isStrNullOrEmpty
import kotlinx.android.synthetic.main.fragment_diag_rich_step_first.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagRichStepFirstFragment : SurveyBaseFragment() {

    private object Holder { val INSTANCE = DiagRichStepFirstFragment() }

    companion object {
        val singleTone: DiagRichStepFirstFragment by lazy { Holder.INSTANCE }

        val instance: DiagRichStepFirstFragment by lazy { DiagRichStepFirstFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagRichStepFirstFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_rich_step_first, container, false)
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

    private fun initFirst(){


    }


    override fun isValidDiagInputs(): Boolean {
        val ages = RG_DIAG_AGES.checkedItem?.tag.toString()
        val totalAsset = RG_TOTAL_ASSET.checkedItem?.tag.toString()
        val rateAsset = RG_RATE_ASSET.checkedItem?.tag.toString()

        return (ages.isStrNullOrEmpty()
                || totalAsset.isStrNullOrEmpty()
                || rateAsset.isStrNullOrEmpty())
    }

    override fun getDiagDatas(): DiagRichStepFirst? {

        val ages = RG_DIAG_AGES.checkedItem?.tag.toString()
        val totalAsset = RG_TOTAL_ASSET.checkedItem?.tag.toString()
        val rateAsset = RG_RATE_ASSET.checkedItem?.tag.toString()

        if (ages.isStrNullOrEmpty()
                || totalAsset.isStrNullOrEmpty()
                || rateAsset.isStrNullOrEmpty()) {
            return null
        }

        return DiagRichStepFirst(ages=ages,totalAsset=totalAsset,
                rateAsset=rateAsset)
    }
}