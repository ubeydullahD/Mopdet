package com.mopdet.Core;

public class ApiUtils {
    public static final String BASE_URL="https://www.burak.social/mobdet/api/";

    public static RetrofitProcess register(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess loginMD(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess getTestAnswer(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }
    public static RetrofitProcess getTests(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess saveResult(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess getTestResultHistory(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

}
