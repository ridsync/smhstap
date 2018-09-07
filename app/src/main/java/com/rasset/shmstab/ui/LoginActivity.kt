package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst

import com.rasset.shmstab.network.NetManager
import com.rasset.shmstab.network.protocol.ParamKey
import com.rasset.shmstab.network.protocol.ReqType
import com.rasset.shmstab.network.protocol.ResultCode
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.network.res.ResUserLogin
import com.rasset.shmstab.network.task.MainListTask
import com.rasset.shmstab.ui.dialog.MainCustomDialog
import com.rasset.shmstab.utils.JUtil.isDoubleClick
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.Prefer
import com.rasset.shmstab.utils.showToast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [testDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class LoginActivity : BaseActivity() {
    companion object {

        private val INTENT_USER_ID = "user_id"
        private val instance: LoginActivity? = null

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
//            intent.putExtra(INTENT_USER_ID, user.id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        BTN_LOGIN.setOnClickListener {

            val userId = ET_LOGIN_USERNAME.text.toString().trim()
            val password = ET_LOGIN_PASSWORD.text.toString().trim()

            if (userId.isEmpty()){
                showToast { "[ 아이디를 입력해주세요 ]" }
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                showToast { "[ 패스워드를 입력해주세요 ]" }
                return@setOnClickListener
            }
            reqNetLogin(userId,password)
        }

//        ET_LOGIN_USERNAME.addTextChangedListener(mIdWatcher)
//        ET_LOGIN_PASSWORD.addTextChangedListener(mPassWatcher)
    }

    private fun showDialog(){

        val dialog = MainCustomDialog.newInstance(mContext).apply {
            setTitle(R.string.common_alert)
            setMsgContents("아이디 정보가 맞지 않습니다.\n본사 담당자에게 문의 해주세요.")
            setPositiveButton(R.string.common_confirm, MainCustomDialog.OnPositvelListener { dialog ->
                if (isDoubleClick(dialog.view)) return@OnPositvelListener
//                    showToast { "감사합니다" }
                }
            )
        }
        dialog.show(supportFragmentManager,AppConst.DIALOG_LOGIN_FAIL)
    }


    private fun reqNetLogin(userId: String, password: String) {
        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_POST_USER_LOGIN, this)
        task.addParam(ParamKey.PARAM_LOGIN_ID, userId)
        task.addParam(ParamKey.PARAM_LOGIN_PASSWORD, password)
        NetManager.startTask(task)
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {
        Logger.d("onNetSuccess  ")

        if (data is ResUserLogin){
            showToast { "로그인 성공 : OK" }
            data.userId?.let {
                Prefer.setSharedPreference(AppConst.PREFERENCE_USERINFO_ID, it,mContext)
            }
            startActivity(MainActivity.newIntent(mContext))
            finish()
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        }
    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        Logger.d("onNetFail  ")
        if (nReqType == ReqType.REQUEST_TYPE_POST_USER_LOGIN) {
            // 로그인 실패
            if (retCode != ResultCode.API_AUTH_NOT_EXIST_USER) {
                showDialog()
                return
            }
        }
        super.onNetFail(retCode,strErrorMsg,nReqType)
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

    private val mIdWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
//            BTN_LOGIN.isEnabled = s.length >= 0
        }
    }
    private val mPassWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
        }
    }

}
