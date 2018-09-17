package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.model.CustomerInfo
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.components.CropCircleTransform
import com.rasset.shmstab.ui.fragments.*
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.Stack
import com.rasset.shmstab.utils.getCustomerLevelStr
import kotlinx.android.synthetic.main.custom_appbarlayout.*
import kotlinx.android.synthetic.main.activity_diagnose_attention.*

class DiagAttentionActivity : BaseActivity() {
    companion object {

        fun newIntent(context: Context,customerInfo: CustomerInfo): Intent {
            val intent = Intent(context, DiagAttentionActivity::class.java)
            // TODO 이벤트버스?? 다른방법으로 Object전달 리서치할것
            intent.putExtra("customerId",customerInfo.customerId)
            intent.putExtra("customerName",customerInfo.customerName)
            intent.putExtra("photoImgPath",customerInfo.photoImgPath)
            intent.putExtra("customerLevel",customerInfo.customerLevel)
            return intent
        }
    }
    enum class SubFrags(val idx: Int,val title: String, var fragment:BaseFragment?) {
        DIAG_CUSTOER_INFO(0,AppConst.FRAG_NAME_DIAG_CUSTOMER_INFO, null)
        , DIAG_INFO_STEP1(1,AppConst.FRAG_NAME_DIAG_INFO_STEP1, null)
        , DIAG_INFO_STEP2(2,AppConst.FRAG_NAME_DIAG_INFO_STEP2, null)
        , DIAG_COMPLETE(3,AppConst.FRAG_NAME_DIAG_COMPLETED, null)
    }

    var customerInfo:CustomerInfo = CustomerInfo()
    var mStackFrags = Stack(mutableListOf<SubFrags>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnose_attention)
        replaceFragment(getNextFragInfo())

        setAppBars()

        // TODO 고객정보 처리 수정해야함
        val cusromerId = intent.getLongExtra("customerId",0)
        if (cusromerId != null){
            val photoImgPath = intent.getStringExtra("photoImgPath")
            val customerName = intent.getStringExtra("customerName")
            val customerLevel = intent.getLongExtra("customerLevel",0)
            customerInfo = CustomerInfo(customerId=cusromerId,customerName=customerName,photoImgPath=photoImgPath,customerLevel=customerLevel)
            setMainNavMenuProfile(SubFrags.DIAG_CUSTOER_INFO,customerInfo)
        } else {
            setMainNavMenuProfile(SubFrags.DIAG_CUSTOER_INFO, customerInfo)
        }

    }

    private fun setMainNavMenuProfile(step: SubFrags, customerInfo:CustomerInfo){

        when (step){
            SubFrags.DIAG_CUSTOER_INFO -> {
                IV_CUSTOMER_PROFILE.isClickable = true
                TV_CUSTOMER_LEVEL.visibility = if (customerInfo.customerLevel > 0) View.VISIBLE else View.GONE
                TV_CUSTOMER_LEVEL.text = getCustomerLevelStr(customerInfo.customerLevel)

                if (customerInfo.photoImgPath != null && !customerInfo.photoImgPath.isNullOrEmpty()){
                    Glide.with(mContext)
                            .load(customerInfo.photoImgPath)
                            .bitmapTransform(CropCircleTransform(mContext))
                            .error(R.drawable.profile_default)
                            .into(IV_CUSTOMER_PROFILE)
                    TV_CUSTOMER_NAME.visibility = View.VISIBLE
                    TV_CUSTOMER_NAME.text = customerInfo.customerName
                    IV_CUSTOMER_PROFILE_CAMERA.visibility = View.GONE
                    TV_CUSTOMER_PHOTO_ADD.visibility = View.GONE
                    TV_CUSTOMER_DESC.visibility = View.GONE
                } else {
                    TV_CUSTOMER_NAME.visibility = View.GONE
                    IV_CUSTOMER_PROFILE_CAMERA.visibility = View.VISIBLE
                    TV_CUSTOMER_PHOTO_ADD.visibility = View.VISIBLE
                    TV_CUSTOMER_DESC.visibility = View.VISIBLE
                }
            }
            SubFrags.DIAG_INFO_STEP1, SubFrags.DIAG_INFO_STEP2 -> {
                IV_CUSTOMER_PROFILE.isClickable = false
                TV_CUSTOMER_LEVEL.visibility = View.VISIBLE
                TV_CUSTOMER_NAME.visibility = View.VISIBLE
                if (customerInfo.customerId > 0){
                    TV_CUSTOMER_LEVEL.text =  getCustomerLevelStr(customerInfo.customerLevel)
                    TV_CUSTOMER_NAME.text = customerInfo.customerName
                }else {
                    TV_CUSTOMER_LEVEL.text =  getCustomerLevelStr(0)
                    TV_CUSTOMER_NAME.text =  ""
                }
                IV_CUSTOMER_PROFILE_CAMERA.visibility = View.GONE
                TV_CUSTOMER_PHOTO_ADD.visibility = View.GONE
                TV_CUSTOMER_DESC.visibility = View.GONE
            }
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
            supportFinishAfterTransition()
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        } else {
            mStackFrags.pop()
            super.onBackPressed()
            setStatAppBarTitlenEtc()
        }
    }
    private fun setAppBars(){
        setStatAppBarTitlenEtc()
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
                setStatAppBarTitlenEtc()
            }
        }
        IB_APPBAR_BACK.visibility = View.VISIBLE
        IB_APPBAR_BACK.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setStatAppBarTitlenEtc(){
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
            // 현위치에 따른 프로필정보
            setMainNavMenuProfile(it,customerInfo)
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


