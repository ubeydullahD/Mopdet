package com.mopdet.Core;

import com.mopdet.Model.PojoModels.BaseTest.BaseTest;
import com.mopdet.Model.PojoModels.LoginUser.LoginUser;
import com.mopdet.Model.PojoModels.Test.Test;
import com.mopdet.Model.PojoModels.TestResult.TestResult;
import com.mopdet.Model.PojoModels.TestResultHistory.TestResultHistory;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitProcess {

    @Headers({"Accept: application/json"})
    @POST("register")
    Call<Object> register(@Body RequestBody params);

    @Headers({"Accept: application/json"})
    @POST("loginMD")
    Call<LoginUser> loginMD(@Body RequestBody params);

    @Headers({"Accept: application/json"})
    @GET("getTests")
    Call<BaseTest> getTests();

    @POST("getTestAnswer")
    @FormUrlEncoded
    Call<Test> getTestAnswer(@Field("test_id") int test_id);

    @Headers({"Accept: application/json"})
    @POST("getExplain")
    Call<TestResult> saveResult(@Body RequestBody params);

    @POST("getHistoryTest")
    @FormUrlEncoded
    Call<TestResultHistory> getTestResultHistory(@Field("user_id") int user_id);
}
