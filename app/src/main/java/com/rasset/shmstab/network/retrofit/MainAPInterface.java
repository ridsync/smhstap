package com.rasset.shmstab.network.retrofit;

import com.rasset.shmstab.network.res.BaseModel;
import com.rasset.shmstab.network.res.ResContentList;
import com.rasset.shmstab.network.res.ResCustomerList;
import com.rasset.shmstab.network.res.ResDiagnoseInfo;
import com.rasset.shmstab.network.res.ResUserLogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by andman on 2015-12-24.
 */
public interface MainAPInterface {

    @POST("userLogin")
    Call<ResUserLogin> reqPostUserLogin(@Body Map<String, Object> params);

    @GET("internListCustomers/{userId}")
    Call<ResCustomerList> reqGetDiagnoseList(@Path("userId") String userId);

    @Headers("Content-Type: application/json")
    @POST("updateDiagInfos")
    Call<BaseModel>  reqPostDiagnoseUpdate(@Body Map<String, Object> params);

    @Headers("Content-Type: application/json")
    @POST("uploadRichSurvey")
    Call<BaseModel>  reqPostUploadRichSurvey(@Body Map<String, Object> params);

    @Headers("Content-Type: application/json")
    @POST("sendSmsRichSurvey")
    Call<BaseModel>  reqPostSendSmsRichSurvey(@Body Map<String, Object> params);


}
