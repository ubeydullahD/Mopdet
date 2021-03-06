package com.mopdet.Core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mopdet.Model.PojoModels.BaseTest.BaseTest;
import com.mopdet.Model.PojoModels.BaseTest.Datum;
import com.mopdet.Model.PojoModels.Test.Test;
import com.mopdet.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;
    private int itemCount ;
    public BaseTest test;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;
    private FragmentManager fm;
    private Activity activity;
    private Fragment fragment;

    public TestsAdapter(Context mContext, int itemCount, BaseTest baseTest, FragmentManager fm, FragmentActivity activity) {
        this.mContext = mContext;
        this.itemCount = itemCount;
        this.test = baseTest;
        mPrefs =  mContext.getSharedPreferences("Mobdet",MODE_PRIVATE);
        this.fm = fm;
        this.activity = activity;

    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.test_card_tasarim,parent,false);
        return new TestsAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, final int position) {


        final Datum data = test.getData().get(position);
        final String json = mPrefs.getString("Test"+data.getTestId(), "");
        holder.textView.setText(data.getTestName().toString());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(activity);
                if(json=="" || json.contains("[]")){

                    getTestViaId(data.getTestId(),loadingDialog);
                }else{
                    Gson gson = new Gson();
                    Test test = gson.fromJson(json, Test.class);
                    loadingDialog.explain(test,fm);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return  itemCount;
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {

        public TextView textView;
        public ConstraintLayout constraintLayout;

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.testName);
            constraintLayout = itemView.findViewById(R.id.testcard);

        }
    }

    private  void getTestViaId(final int id, final LoadingDialog loadingDialog){

        try {

            retrofitProcess = ApiUtils.getTestAnswer();
            retrofitProcess.getTestAnswer(id).enqueue(new Callback<Test>() {
                @Override
                public void onResponse(Call<Test> call, Response<Test> response) {

                    Test test = response.body();
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(test);
                    prefsEditor.putString("Test"+id, json);
                    prefsEditor.commit();
                    loadingDialog.explain(test,fm);

                }

                @Override
                public void onFailure(Call<Test> call, Throwable t) {
                    int  b = 0;
                }
            });

        }catch (Exception e){


        }

    }



}
