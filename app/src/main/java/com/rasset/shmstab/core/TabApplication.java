package com.rasset.shmstab.core;

import android.app.Application;

import com.rasset.shmstab.network.retrofit.RetroRestAPIService;

/**
 * Created by devok on 2018-08-30.
 */
public class TabApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RetroRestAPIService.initRetrofit(AppConst.API_MAIN_URL, null);
    }
}
