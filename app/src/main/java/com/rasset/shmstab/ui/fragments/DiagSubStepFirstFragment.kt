package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.dialog.ProgressLockDialog
import kotlinx.android.synthetic.main.fragment_diag_step_first.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagSubStepFirstFragment : BaseFragment() {

    private object Holder { val INSTANCE = DiagSubStepFirstFragment() }

    companion object {
        val singleTone: DiagSubStepFirstFragment by lazy { Holder.INSTANCE }

        val instance: DiagSubStepFirstFragment by lazy { DiagSubStepFirstFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSubStepFirstFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater?.inflate(R.layout.fragment_diag_step_first, container, false)
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
        if (mLockDialog != null)
            mLockDialog.dismiss()
    }

    private fun initFirst(){
        setCheckCardViews(null)

        RL_WEWON_INVEST.setOnClickListener {
            setCheckCardViews(it)
        }
        RL_WEWON_MD.setOnClickListener {
            setCheckCardViews(it)
        }
        RL_WEWON_TAX.setOnClickListener {
            setCheckCardViews(it)
        }
        RL_WEWON_HOME_INTE.setOnClickListener {
            setCheckCardViews(it)
        }
        RL_WEWON_MANAGEMENT.setOnClickListener {
            setCheckCardViews(it)
        }
        RL_WEWON_CM.setOnClickListener {
            setCheckCardViews(it)
        }
    }

    private fun setCheckCardViews(view:View?){
        IV_WEWON_INVEST_CHECK.visibility = View.INVISIBLE
        IV_WEWON_MD_CHECK.visibility = View.INVISIBLE
        IV_WEWON_TAX_CHECK.visibility = View.INVISIBLE
        IV_WEWON_HOME_INTE_CHECK.visibility = View.INVISIBLE
        IV_WEWON_MANAGEMENT_CHECK.visibility = View.INVISIBLE
        IV_WEWON_CM_CHECK.visibility = View.INVISIBLE
        when (view){
            RL_WEWON_INVEST -> IV_WEWON_INVEST_CHECK.visibility = View.VISIBLE
            RL_WEWON_MD -> IV_WEWON_MD_CHECK.visibility = View.VISIBLE
            RL_WEWON_TAX -> IV_WEWON_TAX_CHECK.visibility = View.VISIBLE
            RL_WEWON_HOME_INTE -> IV_WEWON_HOME_INTE_CHECK.visibility = View.VISIBLE
            RL_WEWON_MANAGEMENT -> IV_WEWON_MANAGEMENT_CHECK.visibility = View.VISIBLE
            RL_WEWON_CM -> IV_WEWON_CM_CHECK.visibility = View.VISIBLE
        }
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {

    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode, strErrorMsg, nReqType)
    }

    override fun onProgresStart(nReqType: Int) {

    }

    override fun onProgresStop(nReqType: Int) {

    }

}