package com.mopdet.Core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mopdet.Model.PojoModels.Test.Test;
import com.mopdet.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;
    private int itemCount ;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;
    private View mView;
    private Test test;
    private TextView textView3;
    private Button button;
    public int  TotalPuan = 0;
    public ArrayList arrayList = new ArrayList();

    public TestAdapter(Context mContext, View mView, Test test) {
        this.mContext = mContext;
        this.mView = mView;
        this.test = test;

    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.question_card,parent,false);
        textView3 = mView.findViewById(R.id.textView3);
        button = mView.findViewById(R.id.button);
        textView3.setText(test.getData().get(0).getMobdetTest().getExplanation());
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(test.getData().get(0).getMobdetTest().getQuestions().size()>arrayList.size()){
                    Snackbar snackbar = Snackbar.make(itemView.findViewById(android.R.id.content),"Please answer all questions",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.colorPrimaryDark);
                    snackbar.show();
                }
            }
        });

        return new TestAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, final int position) {

        holder.textView.setText((position+1)+"."+test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getQuestion());
        holder.radiobir.setText(test.getData().get(0).getMobdetTest().getQuestions().get(position).getAnswer().get(0).getAnswer());
        holder.radioiki.setText(test.getData().get(0).getMobdetTest().getQuestions().get(position).getAnswer().get(1).getAnswer());
        holder.radiouc.setText(test.getData().get(0).getMobdetTest().getQuestions().get(position).getAnswer().get(2).getAnswer());
        holder.radiodort.setText(test.getData().get(0).getMobdetTest().getQuestions().get(position).getAnswer().get(3).getAnswer());

        holder.radiobir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!arrayList.contains(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId())){
                    arrayList.add(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId());
                }
                if(b){
                    TotalPuan = TotalPuan+4;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-4;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        holder.radioiki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!arrayList.contains(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId())){
                    arrayList.add(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId());
                }
                if(b){
                    TotalPuan = TotalPuan+3;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-3;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        holder.radiouc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!arrayList.contains(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId())){
                    arrayList.add(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId());
                }
                if(b){
                    TotalPuan = TotalPuan+2;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-2;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        holder.radiodort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!arrayList.contains(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId())){
                    arrayList.add(test.getData().get(0).getMobdetTest().getQuestions().get(position).getmDQuestion().getId());
                }
                if(b){
                    TotalPuan = TotalPuan+1;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-1;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return test.getData().get(0).getMobdetTest().getQuestions().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {

        TextView textView ;
        RadioButton radiobir,radioiki,radiouc,radiodort;

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.editText);
            radiobir = itemView.findViewById(R.id.radiobir);
            radioiki = itemView.findViewById(R.id.radioiki);
            radiouc = itemView.findViewById(R.id.radiouc);
            radiodort  = itemView.findViewById(R.id.radiodort);

        }
    }

}
