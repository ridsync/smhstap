package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.core.TabApp
import com.rasset.shmstab.model.DiagnoseBaseInfo
import com.rasset.shmstab.model.DiagnoseInfo
import com.rasset.shmstab.network.NetManager
import com.rasset.shmstab.network.protocol.ParamKey
import com.rasset.shmstab.network.protocol.ReqType
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.network.task.MainListTask
import com.rasset.shmstab.ui.components.CropCircleTransform
import com.rasset.shmstab.ui.dialog.BaseDialogFragment
import com.rasset.shmstab.ui.fragments.*
import kotlinx.android.synthetic.main.custom_appbarlayout.*
import kotlinx.android.synthetic.main.activity_diagnose_attention.*
import com.rasset.shmstab.utils.*
import de.greenrobot.event.EventBus


class DiagAttentionActivity : BaseActivity() {
    companion object {

        fun newIntent(context: Context,diagnoseInfo: DiagnoseInfo): Intent {
            val intent = Intent(context, DiagAttentionActivity::class.java)
            // TODO 이벤트버스?? 다른방법으로 Object전달 리서치할것
            intent.putExtra("diagnoseId",diagnoseInfo.diagnoseId)
            intent.putExtra("dianoseDetailId",diagnoseInfo.diagnoseDetailId)
            intent.putExtra("customerName",diagnoseInfo.customerName)
            intent.putExtra("photoImgPath",diagnoseInfo.photoImgPath)
            intent.putExtra("customerLevel",diagnoseInfo.customerLevel)
            intent.putExtra("customerPhone",diagnoseInfo.customerPhone)
            return intent
        }
    }
    enum class SubFrags(val idx: Int,val title: String, var fragment:BaseFragment?) {
        DIAG_CUSTOER_INFO(0,AppConst.FRAG_NAME_DIAG_CUSTOMER_INFO, null)
        , DIAG_INFO_STEP1(1,AppConst.FRAG_NAME_DIAG_INFO_STEP1, null)
        , DIAG_INFO_STEP2(2,AppConst.FRAG_NAME_DIAG_INFO_STEP2, null)
        , DIAG_COMPLETE(3,AppConst.FRAG_NAME_DIAG_COMPLETED, null)
    }

    lateinit var diagnoseInfo:DiagnoseInfo
    var selectedAdviser:DiagSubStepFirstFragment.ADVISOR = DiagSubStepFirstFragment.ADVISOR.ADVISOR_NAME_INVEST
    var mStackFrags = Stack(mutableListOf<SubFrags>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnose_attention)

        diagnoseInfo = EventBus.getDefault().removeStickyEvent<DiagnoseInfo>(DiagnoseInfo::class.java)
        replaceFragment(getNextFragInfo())
        setMainNavMenuProfile(SubFrags.DIAG_CUSTOER_INFO, diagnoseInfo)
        // 제거해야함
//        val cusromerId = intent.getLongExtra("diagnoseId",0)
//        if (cusromerId > 0){
//            val photoImgPath = intent.getStringExtra("photoImgPath")
//            val customerName = intent.getStringExtra("customerName")
//            val customerLevel = intent.getLongExtra("customerLevel",0)
//            val customerPhone = intent.getStringExtra("customerPhone")
//            val dianoseDetailId = intent.getStringExtra("dianoseDetailId")
////            diagnoseInfo = DiagnoseInfo(diagnoseId=cusromerId,customerName=customerName,photoImgPath=photoImgPath,customerLevel=customerLevel,customerPhone=customerPhone)
//            setMainNavMenuProfile(SubFrags.DIAG_CUSTOER_INFO,diagnoseInfo)
//        } else {
//            setMainNavMenuProfile(SubFrags.DIAG_CUSTOER_INFO, diagnoseInfo)
//        }

        setAppBars()

    }

    private fun setMainNavMenuProfile(step: SubFrags, diagnoseInfo:DiagnoseInfo){

        when (step){
            SubFrags.DIAG_CUSTOER_INFO -> {
                IV_CUSTOMER_PROFILE.isClickable = true
                TV_CUSTOMER_LEVEL.visibility = if (diagnoseInfo.customerLevel > 0) View.VISIBLE else View.GONE
                TV_CUSTOMER_LEVEL.text = getCustomerLevelStr(diagnoseInfo.customerLevel)

                if (diagnoseInfo.photoImgPath != null && !diagnoseInfo.photoImgPath.isNullOrEmpty()){
                    Glide.with(mContext)
                            .load(diagnoseInfo.photoImgPath)
                            .bitmapTransform(CropCircleTransform(mContext))
                            .error(R.drawable.profile_default)
                            .into(IV_CUSTOMER_PROFILE)
                    TV_CUSTOMER_NAME.visibility = View.VISIBLE
                    TV_CUSTOMER_NAME.text = diagnoseInfo.customerName
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
                if (diagnoseInfo.diagnoseId > 0){
                    TV_CUSTOMER_LEVEL.text =  getCustomerLevelStr(diagnoseInfo.customerLevel)
                    TV_CUSTOMER_NAME.text = diagnoseInfo.customerName
                }else {
                    TV_CUSTOMER_LEVEL.text =  getCustomerLevelStr(0)
                    TV_CUSTOMER_NAME.text =  ""
                }
                IV_CUSTOMER_PROFILE_CAMERA.visibility = View.GONE
                TV_CUSTOMER_PHOTO_ADD.visibility = View.GONE
                TV_CUSTOMER_DESC.visibility = View.GONE
            }
        }
        hideIME(mContext,TV_CUSTOMER_LEVEL)
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
                postCustomerDiagInfos()
            } else {
                if (nextFrag == SubFrags.DIAG_INFO_STEP2) {
                    val fragFirst = supportFragmentManager.findFragmentByTag(SubFrags.DIAG_INFO_STEP1.title) as DiagSubStepFirstFragment
                    selectedAdviser = fragFirst.selectedAdvisor
                }
                replaceFragment(nextFrag, true)
                setStatAppBarTitlenEtc()
            }
        }
        IB_APPBAR_BACK.visibility = View.VISIBLE
        IB_APPBAR_BACK.setOnClickListener{
            onBackPressed()
        }
    }

    private fun postCustomerDiagInfos() {

        val fragSecond = supportFragmentManager.findFragmentByTag(SubFrags.DIAG_INFO_STEP2.title) as DiagSubStepSecondFragment
        val fragments = fragSecond.getSubFragments()
        val surveyFragment = fragments[0]
        surveyFragment?.getDiagDatas()?.let { diagInfo ->
                    //API 전송
                    diagInfo.diagnoseId = diagnoseInfo.diagnoseId
                    diagInfo.diagnoseDetailId = diagnoseInfo.diagnoseDetailId
                    diagInfo.applyPart = diagnoseInfo.applyPart
                    diagInfo.diagnoseType = diagnoseInfo.diagnoseType
                    reqNetUpdateDiagInfos(diagInfo)
        }
        if (surveyFragment?.getDiagDatas() == null){
            showToast {
                "질문항목을 모두 선택해주세요."
            }
        }
        Logger.d("postCustomerDiagInfos  ")
    }

    private fun reqNetUpdateDiagInfos(diagInfo: DiagnoseBaseInfo) {
        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_POST_DIAGNOSE_UPDATE, this)
        task.addParam(ParamKey.PARAM_USERID, TabApp.userInfo?.userId)
        task.addParam(ParamKey.PARAM_DIAG_INFO, diagInfo)
        NetManager.startTask(task)
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
            setMainNavMenuProfile(it,diagnoseInfo)
        }

    }

    private fun replaceFragment(curFrag: SubFrags, isAnim:Boolean = false){
       val transaction =  supportFragmentManager.beginTransaction()
        transaction.addToBackStack(curFrag.title)
        if (isAnim){
            transaction.setCustomAnimations(R.animator.slide_in_from_right_object_enter, R.animator.slide_out_to_left_object_exit, R.animator.slide_in_left_object_popenter, R.animator.slide_out_to_right_object_popexit)
        }
        transaction.replace(R.id.FR_DIAG_CONTAINER, curFrag.fragment,curFrag.title)
        transaction.commitAllowingStateLoss()
        mStackFrags.push(curFrag)
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {
        Logger.d("onNetSuccess  ")
        // TODO
        finish()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        showToast {
            "[ 서버 전송 완료 ]"
        }
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


