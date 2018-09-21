package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.model.CustomerInfo

import com.rasset.shmstab.network.NetManager
import com.rasset.shmstab.network.protocol.ParamKey
import com.rasset.shmstab.network.protocol.ReqType
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.network.res.ResContentList
import com.rasset.shmstab.network.task.MainListTask
import com.rasset.shmstab.ui.dialog.MainCustomDialog
import com.rasset.shmstab.ui.fragments.BaseFragment
import com.rasset.shmstab.ui.fragments.MainSubCustomersFragment
import com.rasset.shmstab.ui.fragments.MainSubREAssetFragment
import com.rasset.shmstab.utils.JUtil
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.Prefer
import com.rasset.shmstab.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_appbarlayout.*
import android.support.v4.app.ActivityOptionsCompat
import com.rasset.shmstab.model.DiagnoseInfo
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.item_main_customer.*


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [testDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MainActivity : BaseActivity() {

    enum class SubFrags(val idx: Int) {
        CUSTOMERS(0) , REASSET(1)
    }
    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
//            intent.putExtra(INTENT_USER_ID, user.id)
            return intent
        }
    }

    private var currentFragment:Int = SubFrags.CUSTOMERS.idx
    private val mapSubFragments: HashMap<Int, BaseFragment> = hashMapOf(SubFrags.CUSTOMERS.idx to MainSubCustomersFragment(), SubFrags.REASSET.idx to MainSubREAssetFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LL_MAIN_CUSTOMER.setBackgroundResource(R.color.main_nav_menu_selected_color)
        LL_MAIN_RE_ASSET.setBackgroundResource(R.color.main_primary_color)
        mapSubFragments[currentFragment]?.let { it -> replaceFragment(it) }
        LL_MAIN_CUSTOMER.setOnClickListener {
            if (currentFragment != SubFrags.CUSTOMERS.idx){
                currentFragment = SubFrags.CUSTOMERS.idx
                mapSubFragments[currentFragment]?.let { it -> replaceFragment(it) }
            }
            LL_MAIN_CUSTOMER.setBackgroundResource(R.color.main_nav_menu_selected_color)
            LL_MAIN_RE_ASSET.setBackgroundResource(R.color.main_primary_color)
        }

        LL_MAIN_RE_ASSET.setOnClickListener {
//            if (currentFragment != SubFrags.REASSET.idx){
//                currentFragment = SubFrags.REASSET.idx
//                mapSubFragments[currentFragment]?.let { it -> replaceFragment(it) }
//            }
//            LL_MAIN_CUSTOMER.setBackgroundResource(R.color.main_primary_color)
//            LL_MAIN_RE_ASSET.setBackgroundResource(R.color.main_nav_menu_selected_color)
            showToast {
                "준비중입니다."
            }
        }
        setAppBars()
    }

    // 선택유저 정보 넘기기
    fun startDiagAttentionActivity(diagnoseInfo: DiagnoseInfo, imgView:View? ){
        if (imgView!=null) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imgView, "profile")
            startActivity(DiagAttentionActivity.newIntent(mContext,diagnoseInfo),options.toBundle())
        } else {
            startActivity(DiagAttentionActivity.newIntent(mContext,diagnoseInfo))
        }
        EventBus.getDefault().postSticky(diagnoseInfo)
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
    }

    private fun setAppBars(){
        // 현재 화면위치 에 따른 Appbar visibility
        IB_APPBAR_MENU.visibility = View.VISIBLE
        IB_APPBAR_MENU.setOnClickListener {
            LL_MAIN_NAV_MENU.visibility = if ( LL_MAIN_NAV_MENU.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        TV_APPBAR_TEXT_LOGO.visibility = View.VISIBLE
        TV_APPBAR_LOGOUT.visibility = View.VISIBLE
        TV_APPBAR_LOGOUT.setOnClickListener{
            if (JUtil.isDoubleClick(it)) return@setOnClickListener
            showDialog()
        }
    }

    private fun replaceFragment(fragment : BaseFragment){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.FR_MAIN_CONTAINER, fragment)
                .commit()
    }

    private fun showDialog(){

        val dialog = MainCustomDialog.newInstance(mContext).apply {
            setTitle(R.string.common_alert)
            setMsgContents("로그아웃하시겠습니까?")
            setPositiveButton(R.string.btn_confirm, MainCustomDialog.OnPositvelListener { dialog ->
                if (JUtil.isDoubleClick(dialog.view)) return@OnPositvelListener
                Prefer.clearPreference(mContext)
                startActivity(LoginActivity.newIntent(mContext))
                finish()
            })
            setNegativeButton(R.string.btn_cancel, MainCustomDialog.OnNegativelListener { dialog ->
                if (JUtil.isDoubleClick(dialog.view)) return@OnNegativelListener
            })
        }
        dialog.show(supportFragmentManager, AppConst.DIALOG_LOGIN_FAIL)
    }

    private fun getUserList(lId: Long, lFirstSeq: Long) {
        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_GET_DIAGNOSE_LIST, this)
        task.addParam(ParamKey.PARAM_LISTTYPE, 1) // to server 1234
        task.addParam(ParamKey.PARAM_FIRSTSEQ, lFirstSeq)
        NetManager.startTask(task)
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {
        Logger.d("onNetSuccess  ")

        if (data is ResContentList){

        }
    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode,strErrorMsg,nReqType)
        Logger.d("onNetFail  ")
    }

    override fun onProgresStart(nReqType: Int) {
        Logger.d("onProgresStart  ")
            mLockDialog.show()
    }

    override fun onProgresStop(nReqType: Int) {
        Logger.d("onProgresStop  ")

        Handler().postDelayed({
            mLockDialog.cancel()
        },2000)

    }
}
