package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import com.rasset.shmstab.R

import com.rasset.shmstab.network.NetManager
import com.rasset.shmstab.network.protocol.ParamKey
import com.rasset.shmstab.network.protocol.ReqType
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.network.res.ResContentList
import com.rasset.shmstab.network.task.MainListTask
import com.rasset.shmstab.utils.Logger
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
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    companion object {

        private val INTENT_USER_ID = "user_id"

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

        ET_LOGIN_USERNAME.addTextChangedListener(mIdWatcher)
        ET_LOGIN_PASSWORD.addTextChangedListener(mPassWatcher)
    }

    private fun reqNetLogin(userId: String, password: String) {
        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_GET_USER_LIST, this)
        task.addParam(ParamKey.PARAM_LOGIN_ID, userId)
        task.addParam(ParamKey.PARAM_LOGIN_PASSWORD, password)
        NetManager.startTask(task)
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {
        Logger.d("onNetSuccess  ")

        if (data is BaseModel){
            if (data.resVal == 100){ // 로그인 성공
                showToast { "로그인 성공 : OK" }
                startActivity(MainActivity.newIntent(mContext))
            } else {  // 로그인 실패 사유
                showToast { "로그인 실패 : ${data.resMsg}" }
            }
        }
    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode,strErrorMsg,nReqType)
        Logger.d("onNetFail  ")
        // 로그인 실패
        showToast { "로그인 실패 서버통신실패 : ${strErrorMsg}" }
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
