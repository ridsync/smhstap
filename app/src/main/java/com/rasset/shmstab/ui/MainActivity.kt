package com.rasset.shmstab.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rasset.shmstab.R

import com.rasset.shmstab.network.NetManager
import com.rasset.shmstab.network.protocol.ParamKey
import com.rasset.shmstab.network.protocol.ReqType
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.network.res.ResContentList
import com.rasset.shmstab.network.task.MainListTask
import com.rasset.shmstab.utils.Logger

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [testDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MainActivity : BaseActivity() {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
//            intent.putExtra(INTENT_USER_ID, user.id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUserList(1,0)
    }

    private fun getUserList(lId: Long, lFirstSeq: Long) {
        val task = MainListTask(applicationContext, ReqType.REQUEST_TYPE_GET_USER_LIST, this)
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
