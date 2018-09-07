package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_appbarlayout.*

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

        mapSubFragments[currentFragment]?.let { it -> replaceFragment(it) }
        LL_MAIN_CUSTOMER.setOnClickListener {
            if (currentFragment != SubFrags.CUSTOMERS.idx){
                currentFragment = SubFrags.CUSTOMERS.idx
                mapSubFragments[currentFragment]?.let { it -> replaceFragment(it) }
            }
        }

        LL_MAIN_RE_ASSET.setOnClickListener {
            if (currentFragment != SubFrags.REASSET.idx){
                currentFragment = SubFrags.REASSET.idx
                mapSubFragments[currentFragment]?.let { it -> replaceFragment(it) }
            }
        }

        TV_APPBAR_LOGOUT.visibility = View.VISIBLE
        TV_APPBAR_LOGOUT.setOnClickListener{
            if (JUtil.isDoubleClick(it)) return@setOnClickListener

            showDialog()
        }
    }

    fun startDiagAttentionActivity(){
        startActivity(DiagAttentionActivity.newIntent(mContext))
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
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
        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_GET_CUSOMER_LIST, this)
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
        if (mLockDialog != null)
            mLockDialog.show()
    }

    override fun onProgresStop(nReqType: Int) {
        Logger.d("onProgresStop  ")

        Handler().postDelayed({
            if (mLockDialog != null)
                mLockDialog.cancel()
        },2000)

    }
}
