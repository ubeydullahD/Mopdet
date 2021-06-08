package com.mopdet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mopdet.Core.TestAdapter;
import com.mopdet.Model.PojoModels.Test.Test;
import com.mopdet.R;

public class TestFragment extends Fragment {

    Test test;
    View mView;
    private RecyclerView rvTest ;
    private TestAdapter testAdapter;
    public TestFragment(Test test) {
        this.test = test;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.test_fragment,container,false);

        rvTest = mView.findViewById(R.id.rv_t);
        rvTest.setHasFixedSize(true);
        rvTest.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        testAdapter = new TestAdapter(getActivity().getBaseContext(),mView,test);
        rvTest.setAdapter(testAdapter);
        return   mView;


    }
}
