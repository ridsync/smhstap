package com.rasset.shmstab.network.task;

import android.content.Context;

import com.rasset.shmstab.network.res.BaseModel;
import com.google.gson.JsonElement;
import  com.rasset.shmstab.network.OnNetworkListener;
import  com.rasset.shmstab.network.protocol.ReqType;
import com.rasset.shmstab.network.retrofit.MainAPInterface;
import com.rasset.shmstab.network.retrofit.RetroRestAPIService;

import org.json.JSONException;

import java.io.IOException;

import retrofit2.Call;

/**
 * @author okc
 * @version 1.0
 * @see
 * @since 2015-12-20.
 */
public class MainListTask extends NetworkTask<BaseModel> {

    public MainListTask() {
        super();
    }

    public MainListTask(Context mContext, int requestType, OnNetworkListener onNetworkListener) {
        super(mContext, requestType, onNetworkListener);
    }

    // 상속 구현 ResInterfce API 생성
    protected Call<? extends BaseModel> getRetroCallable() {
        MainAPInterface service = RetroRestAPIService.createService(MainAPInterface.class);
        // reqType별 API WindowsTopViewService 별도 반환
        if (ReqType.REQUEST_TYPE_GET_USER_LIST == mRequestType) {
            return service.reqGetContentList(mParams);
        } else if (ReqType.REQUEST_TYPE_GET_USER_LIST_ALL == mRequestType) {
            return service.reqPostComment(mParams);
        } else {
            return null;
        }
    }

    // Retrofit JsonElement로부터 직접파싱?
    @Override
    protected BaseModel onParse(JsonElement json, int requestType) throws JSONException, IOException {
        return null;
    }
}
