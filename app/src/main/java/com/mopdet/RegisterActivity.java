package com.mopdet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.mopdet.Core.ApiUtils;
import com.mopdet.Core.LoadingDialog;
import com.mopdet.Core.RetrofitProcess;
import com.mopdet.Model.PojoModels.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView returnSignIn,name,surname,email,password,repassword,age,nickName;
    private RadioButton male,female,other;
    private Button register;
    private String sex = "";

    private AlertDialog alertDialog;

    private RetrofitProcess retrofitProcess;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nickName = (EditText) findViewById(R.id.nickName);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText)  findViewById(R.id.surname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword =(EditText) findViewById(R.id.repassword);
        age = (EditText) findViewById(R.id.age);
        returnSignIn = findViewById(R.id.returnSignIn);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.radio1);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                int ageInt;
                UserModel userModel = new UserModel();
                userModel.setNickName(nickName.getText().toString());
                userModel.setName(name.getText().toString());
                userModel.setSurname(surname.getText().toString());
                userModel.setMail(email.getText().toString());
                userModel.setPassword(password.getText().toString());
                userModel.setRepassword(repassword.getText().toString());
                try {
                    ageInt =  Integer.parseInt(age.getText().toString());
                } catch (NumberFormatException nfe) {
                    ageInt = 0;
                }
                userModel.setAge(ageInt);
                userModel.setSex(sex);
                userModel.setAdmin(false);
                userModel.setActive(false);

                if(userModel.getNickName().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter username",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getName().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your name",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getSurname().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your surname",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getMail().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your mail",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getPassword().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your password",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getRepassword().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your repassword",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getAge()<=0){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your age",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                if(userModel.getSex().equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your sex",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                    snackbar.show();
                    return;
                }
                final LoadingDialog loadingDialog = new LoadingDialog(RegisterActivity.this);
                loadingDialog.startLoadingDialog();
                JSONObject item = new JSONObject();
                try {
                    item.put("username",userModel.getNickName());
                    item.put("name",userModel.getName());
                    item.put("surname",userModel.getSurname());
                    item.put("email",userModel.getMail());
                    item.put("password",userModel.getPassword());
                    item.put("password_confirmation",userModel.getRepassword());
                    item.put("age",userModel.getAge());
                    item.put("sex",userModel.getSex());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                retrofitProcess = ApiUtils.register();

                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(item).toString());
                retrofitProcess.register(body).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        loadingDialog.dismissDialog();
                        if(response.body()==null){
                            String sonuc ="";
                            if(!response.isSuccessful()) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response.errorBody().string());
                                    JSONObject jsonObjectErr = (JSONObject) jsonObject.get("errors");
                                    Iterator<String> keys = jsonObjectErr.keys();

                                    while(keys.hasNext()) {
                                        String key = keys.next();
                                        JSONArray array = (JSONArray) jsonObjectErr.get(key);
                                        sonuc = sonuc+" "+array.get(0)+"\n";
                                    }


                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),sonuc,Snackbar.LENGTH_SHORT);
                            snackbar.getView().setBackgroundColor(Color.GRAY);
                            snackbar.show();
                        }else{

                            AlertDialog.Builder ab = new AlertDialog.Builder(RegisterActivity.this);
                            LayoutInflater inflater = getLayoutInflater();
                            ab.setView(inflater.inflate(R.layout.user_saved,null));
                            ab.setCancelable(false);
                            alertDialog = ab.create();
                            alertDialog.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                            },3000);

                        }

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        int b = 0;
                    }
                });

            }
        });


        returnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //Cinsiyet belirleniyor.
        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "male";
                }
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "female";
                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "female";
                }
            }
        });



    }
}
