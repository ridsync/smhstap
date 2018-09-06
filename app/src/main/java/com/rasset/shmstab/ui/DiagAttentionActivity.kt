package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.fragments.BaseFragment
import com.rasset.shmstab.ui.fragments.DiagSubDefaultInfoFragment
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
    enum class SubFrags(val idx: Int,val tag: String) {
        CUSTOMER_INFO(0,AppConst.FRAG_NAME_DIAG_CUSTOMERINFO)
        , DIAG_INFO_STEP1(1,AppConst.FRAG_NAME_DIAG_INFO_STEP1)
        , DIAG_INFO_STEP2(2,AppConst.FRAG_NAME_DIAG_INFO_STEP2)
        , DIAG_COMPLETE(3,"COMPLETED")
    }

    var mStackFrags = Stack(mutableListOf<SubFrags>())
    private val mapSubFragments: HashMap<SubFrags, BaseFragment> = hashMapOf(SubFrags.CUSTOMER_INFO to DiagSubDefaultInfoFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnose_attention)

        mapSubFragments[SubFrags.CUSTOMER_INFO]?.let {
            replaceFragment(it,SubFrags.CUSTOMER_INFO)
        }

        IB_APPBAR_ACTION.setOnClickListener{
            val fragment:BaseFragment? = when (mStackFrags.peek()){
                SubFrags.CUSTOMER_INFO -> DiagSubStepFirstFragment()
                SubFrags.DIAG_INFO_STEP1 -> DiagSubStepSecondFragment()
                else -> null
            }
            val curFrag:SubFrags = when (mStackFrags.peek()){
                SubFrags.CUSTOMER_INFO -> SubFrags.DIAG_INFO_STEP1
                SubFrags.DIAG_INFO_STEP1 -> SubFrags.DIAG_INFO_STEP2
                else -> SubFrags.CUSTOMER_INFO
            }
            if (fragment != null) {
                replaceFragment(fragment, curFrag, true)
            } else {
                // TODO 예진 정보 전송 후 메인 Refresh
                finish()
            }
        }
        IB_APPBAR_BACK.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        if (mStackFrags.count() == 1){
            finish()
        } else {
            mStackFrags.pop()
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment : BaseFragment, curFrag: SubFrags, isAnim:Boolean = false){
       val transaction =  supportFragmentManager.beginTransaction()
        transaction.addToBackStack(curFrag.tag)
        if (isAnim){
            transaction.setCustomAnimations(R.animator.slide_in_from_right_object_enter, R.animator.slide_out_to_left_object_exit, R.animator.slide_in_left_object_popenter, R.animator.slide_out_to_right_object_popexit)
        }
        transaction.replace(R.id.RL_DIAG_CONTAINER, fragment)
        transaction.commitAllowingStateLoss()
        mStackFrags.push(curFrag)
        mapSubFragments[curFrag] = fragment
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


