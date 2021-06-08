package com.mopdet.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.mopdet.Core.ApiUtils;
import com.mopdet.Core.RetrofitProcess;
import com.mopdet.Core.TestResultHistoryAdapter;
import com.mopdet.Model.PojoModels.LoginUser.LoginUser;
import com.mopdet.Model.PojoModels.TestResultHistory.TestResultHistory;
import com.mopdet.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestResultHistoryFragment extends Fragment {

    private View mView;
    private RecyclerView rvTestHistory ;
    private RetrofitProcess retrofitProcess;
    private TestResultHistoryAdapter testHistoryAdapter;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.test_history_resut_rv,container,false);
        getTestResultHistory();
        return  mView;
    }

    public void getTestResultHistory(){

        Gson gson = new Gson();
        mPrefs = getActivity().getSharedPreferences("Mobdet", Context.MODE_PRIVATE);
        String json = mPrefs.getString("User", "");
        LoginUser user = gson.fromJson(json,LoginUser.class);
        retrofitProcess = ApiUtils.getTestResultHistory();
        retrofitProcess.getTestResultHistory(user.getUser().getId()).enqueue(new Callback<TestResultHistory>() {
            @Override
            public void onResponse(Call<TestResultHistory> call, Response<TestResultHistory> response) {
                int a = 0;
                rvTestHistory = mView.findViewById(R.id.rvResultHistory);
                rvTestHistory.setHasFixedSize(true);
                rvTestHistory.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                testHistoryAdapter = new TestResultHistoryAdapter(getActivity().getBaseContext(),response,mView,getActivity().getSupportFragmentManager(),getActivity());
                rvTestHistory.setAdapter(testHistoryAdapter);
            }

            @Override
            public void onFailure(Call<TestResultHistory> call, Throwable t) {
                int  b =0 ;
            }
        });

    }
}
