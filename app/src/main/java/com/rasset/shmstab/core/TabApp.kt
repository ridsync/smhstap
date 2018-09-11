package com.rasset.shmstab.core

import android.app.Application
import com.rasset.shmstab.model.UserInfo
import com.rasset.shmstab.network.retrofit.RetroRestAPIService

/**
 * Created by devok on 2018-08-30.
 */
class TabApp : Application() {

    companion object {
        var userInfo: UserInfo? =null
    }

    override fun onCreate() {
        super.onCreate()

        RetroRestAPIService.initRetrofit(AppConst.API_MAIN_URL, null)
    }
}
