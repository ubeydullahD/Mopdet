package com.mopdet.Core;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mopdet.Fragments.StepByStepFragment;
import com.mopdet.Model.PojoModels.Test.Test;
import com.mopdet.R;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;
    private TextView textview14,textview15;
    private Button button;

    public LoadingDialog(Activity myActivity){

        activity=myActivity;

    }

    public void startLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_loader,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void startLoadingDialogNoText(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_loader_no_text,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissDialog(){
        alertDialog.dismiss();

    }

    public void explain(final Test test, final FragmentManager fm){


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View mView = inflater.inflate(R.layout.explain_layout,null);

        button = mView.findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new StepByStepFragment(test);
                fm.beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                alertDialog.dismiss();
            }
        });

        textview14 = mView.findViewById(R.id.textview14);
        textview14.setText(test.getData().get(0).getMobdetTest().getTestName());

        textview15 = mView.findViewById(R.id.textview15);
        textview15.setText(test.getData().get(0).getMobdetTest().getExplanation());

        mView.findViewById(R.id.textview15);
        builder.setView(mView);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

    }
}
