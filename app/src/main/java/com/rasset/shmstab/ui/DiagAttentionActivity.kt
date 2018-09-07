package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.fragments.BaseFragment
import com.rasset.shmstab.ui.fragments.DiagSubCustomerInfoFragment
import com.rasset.shmstab.ui.fragments.DiagSubStepFirstFragment
import com.rasset.shmstab.ui.fragments.DiagSubStepSecondFragment
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.Stack
import kotlinx.android.synthetic.main.custom_appbarlayout.*

class DiagAttentionActivity : BaseActivity() {
    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DiagAttentionActivity::class.java)
            return intent
        }
    }
    enum class SubFrags(val idx: Int,val tag: String, var fragment:BaseFragment?) {
        DIAG_CUSTOER_INFO(0,AppConst.FRAG_NAME_DIAG_CUSTOMER_INFO, null)
        , DIAG_INFO_STEP1(1,AppConst.FRAG_NAME_DIAG_INFO_STEP1, null)
        , DIAG_INFO_STEP2(2,AppConst.FRAG_NAME_DIAG_INFO_STEP2, null)
        , DIAG_COMPLETE(3,AppConst.FRAG_NAME_DIAG_COMPLETED, null)
    }

    var mStackFrags = Stack(mutableListOf<SubFrags>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnose_attention)
        replaceFragment(getNextFragInfo())

        IB_APPBAR_ACTION.setOnClickListener{
            val nextFrag = getNextFragInfo()
            if (nextFrag == SubFrags.DIAG_COMPLETE) {
                // TODO 예진 정보 전송 후 메인 Refresh
                // 전송 Dialog ??
                finish()
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
            } else {
                replaceFragment(nextFrag, true)
            }
        }
        IB_APPBAR_BACK.setOnClickListener{
            onBackPressed()
        }
    }

    private fun getNextFragInfo(): DiagAttentionActivity.SubFrags {
        var nextFrag:SubFrags = SubFrags.values()[mStackFrags.count()]
        val fragment:BaseFragment? = when (nextFrag){
            SubFrags.DIAG_CUSTOER_INFO -> DiagSubCustomerInfoFragment()
            SubFrags.DIAG_INFO_STEP1 -> DiagSubStepFirstFragment()
            SubFrags.DIAG_INFO_STEP2 -> DiagSubStepSecondFragment()
            else -> null
        }
        nextFrag.fragment = fragment
        return nextFrag
    }

    override fun onBackPressed() {
        if (mStackFrags.count() == 1){
            finish()
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        } else {
            mStackFrags.pop()
            super.onBackPressed()
        }
    }

    private fun replaceFragment(curFrag: SubFrags, isAnim:Boolean = false){
       val transaction =  supportFragmentManager.beginTransaction()
        transaction.addToBackStack(curFrag.tag)
        if (isAnim){
            transaction.setCustomAnimations(R.animator.slide_in_from_right_object_enter, R.animator.slide_out_to_left_object_exit, R.animator.slide_in_left_object_popenter, R.animator.slide_out_to_right_object_popexit)
        }
        transaction.replace(R.id.FR_DIAG_CONTAINER, curFrag.fragment)
        transaction.commitAllowingStateLoss()
        mStackFrags.push(curFrag)
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {
        Logger.d("onNetSuccess  ")
    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode, strErrorMsg, nReqType)
        Logger.d("onNetFail  ")
    }

    override fun onProgresStart(nReqType: Int) {
        Logger.d("onProgresStart  ")
    }

    override fun onProgresStop(nReqType: Int) {
        Logger.d("onProgresStop  ")
    }

}


