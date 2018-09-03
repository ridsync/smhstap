package com.rasset.shmstab.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rasset.shmstab.R
import com.rasset.shmstab.network.OnNetworkListener
import com.rasset.shmstab.network.protocol.ResultCode
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.showToast

/**
 * Created by devok on 2018-09-03.
 */

open class BaseActivity : AppCompatActivity() , OnNetworkListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {
    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        if (isFinishing) return
        doAlertCommonNetFail(retCode,strErrorMsg,nReqType)
    }

    override fun onProgresStart(nReqType: Int) {
    }

    override fun onProgresStop(nReqType: Int) {
    }

    fun doAlertCommonNetFail(retCode: Int, strErrorMsg: String, reqType: Int) {
        if (isFinishing) return

        if (retCode == ResultCode.API_AUTH_NOT_EXIST_USER) {
            showToast {
                getString(R.string.popup_alert_not_exist_user_ask_login)
            }
        } else if (retCode == ResultCode.API_AUTH_NOT_EXIST_OTHERUSER) {
            showToast {
                getString(R.string.popup_alert_not_exist_user_ask_login)
            }
        }
    }

}
