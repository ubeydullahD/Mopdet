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
import com.mopdet.Core.TestsAdapter;
import com.mopdet.Model.PojoModels.BaseTest.BaseTest;
import com.mopdet.R;

public class TestsFragment extends Fragment {

    View mView;

    private RecyclerView rvTests ;
    private TestsAdapter testsAdapter;
    SharedPreferences mPrefs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tests_fragment,container,false);

        mPrefs = this.getActivity().getSharedPreferences("Mobdet", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Tests", "");
        BaseTest baseTest = gson.fromJson(json, BaseTest.class);

        rvTests = mView.findViewById(R.id.tests_rv);
        rvTests.setHasFixedSize(true);
        rvTests.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        testsAdapter = new TestsAdapter(getActivity().getBaseContext(),baseTest.getData().size(),baseTest,getActivity().getSupportFragmentManager(),getActivity());
        rvTests.setAdapter(testsAdapter);
        return   mView;
    }
}
