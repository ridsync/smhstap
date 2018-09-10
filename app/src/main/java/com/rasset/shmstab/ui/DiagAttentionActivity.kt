package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.fragments.BaseFragment
import com.rasset.shmstab.ui.fragments.DiagSubCustomerInfoFragment
import com.rasset.shmstab.ui.fragments.DiagSubStepFirstFragment
import com.rasset.shmstab.ui.fragments.DiagSubStepSecondFragment
import com.rasset.shmstab.utils.JUtil
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.Stack
import kotlinx.android.synthetic.main.custom_appbarlayout.*
import kotlinx.android.synthetic.main.dialog_maincustom.*

class DiagAttentionActivity : BaseActivity() {
    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DiagAttentionActivity::class.java)
            return intent
        }
    }
    enum class SubFrags(val idx: Int,val title: String, var fragment:BaseFragment?) {
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

        setAppBars()
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
            setAppBarTitle()
        }
    }
    private fun setAppBars(){
        setAppBarTitle()
        IB_APPBAR_ACTION.visibility = View.VISIBLE
        IB_APPBAR_ACTION.setOnClickListener{
            val nextFrag = getNextFragInfo()
            if (nextFrag == SubFrags.DIAG_COMPLETE) {
                // TODO 예진 정보 전송 후 메인 Refresh
                // 전송 Dialog ??
                finish()
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
            } else {
                replaceFragment(nextFrag, true)
                setAppBarTitle()
            }
        }
        IB_APPBAR_BACK.visibility = View.VISIBLE
        IB_APPBAR_BACK.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setAppBarTitle(){
        // 현재 화면위치 에 따른 Appbar Title
        mStackFrags.peek()?.let {
            TV_APPBAR_TEXT.text =  it.title
            when (it){
                SubFrags.DIAG_CUSTOER_INFO -> {
                    IV_APPBAR_STEP1.visibility = View.GONE
                    IV_APPBAR_STEP2.visibility = View.GONE
                    IB_APPBAR_ACTION.text = resources.getText(R.string.btn_next)
                }
                SubFrags.DIAG_INFO_STEP1 -> {
                    IV_APPBAR_STEP1.visibility = View.VISIBLE
                    IV_APPBAR_STEP2.visibility = View.VISIBLE
                    IV_APPBAR_STEP1.setImageResource(R.drawable.ic_step1_on)
                    IV_APPBAR_STEP2.setImageResource(R.drawable.ic_step2)
                    IB_APPBAR_ACTION.text = resources.getText(R.string.btn_next)
                }
                SubFrags.DIAG_INFO_STEP2 -> {
                    IV_APPBAR_STEP1.visibility = View.VISIBLE
                    IV_APPBAR_STEP2.visibility = View.VISIBLE
                    IV_APPBAR_STEP1.setImageResource(R.drawable.ic_step1)
                    IV_APPBAR_STEP2.setImageResource(R.drawable.ic_step2_on)
                    IB_APPBAR_ACTION.text = resources.getText(R.string.btn_completed)
                }
                else -> Unit
            }
        }

    }

    private fun replaceFragment(curFrag: SubFrags, isAnim:Boolean = false){
       val transaction =  supportFragmentManager.beginTransaction()
        transaction.addToBackStack(curFrag.title)
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


