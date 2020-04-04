package com.rasset.shmstab.ui

import android.os.Bundle
import android.os.Handler
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.Prefer
import com.rasset.shmstab.utils.showToast
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [testDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class SplashActivity : BaseActivity() {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        Handler().postDelayed({
//
//            val userId = Prefer.getPreferenceLong(AppConst.PREFERENCE_USERINFO_ID,mContext)
//            if ( userId > 0) {
//                startActivity(MainActivity.newIntent(mContext))
//            } else {
//                startActivity(LoginActivity.newIntent(mContext))
//            }
//            finish()
//        },1700)

    }

    private fun postUserLogin(lId: Long, lFirstSeq: Long) {
//        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_GET_USER_LIST, this)
//        task.addParam(ParamKey.PARAM_LISTTYPE, 1) // to server 1234
//        task.addParam(ParamKey.PARAM_FIRSTSEQ, lFirstSeq)
//        NetManager.startTask(task)
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


