package com.rasset.shmstab.network.retrofit;

import com.rasset.shmstab.network.res.BaseModel;
import com.rasset.shmstab.network.res.BaseModelOld;
import com.rasset.shmstab.network.res.ResContentList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by andman on 2015-12-24.
 */
public interface MainAPInterface {

    @GET("list")
    Call<ResContentList> reqGetContentList(@QueryMap Map<String, Object> params);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("upload")
    Call<BaseModel>  reqPostComment(@Body Map<String, Object> params);

}
