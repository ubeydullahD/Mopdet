package com.mopdet;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView signupButton,editTextUserName,editTextPassword;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = getSharedPreferences("Mobdet",MODE_PRIVATE);

        String json = mPrefs.getString("User", "");
        if(json=="" || json.contains("[]")){
            getTest();
            setContentView(R.layout.activity_login);
            login = findViewById(R.id.cirLoginButton);
            signupButton = findViewById(R.id.signupButton);
            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent =  new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });

            editTextUserName = (EditText) findViewById(R.id.editTextEmail);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);

            login.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    if(editTextUserName.getText().toString().equals("")){
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter username",Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                        snackbar.show();
                        return;
                    }
                    if(editTextUserName.getText().toString().equals("")){
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Please enter your passwoord",Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(R.color.primaryTextColor);
                        snackbar.show();
                        return;
                    }
                    final LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);
                    loadingDialog.startLoadingDialogNoText();

                    JSONObject item = new JSONObject();
                    try {
                        item.put("username",editTextUserName.getText().toString());
                        item.put("password",editTextPassword.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    retrofitProcess = ApiUtils.loginMD();
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(item).toString());
                    retrofitProcess.loginMD(body).enqueue(new Callback<LoginUser>() {
                        @Override
                        public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                            loadingDialog.dismissDialog();
                            if(response.body()==null){
                                if(!response.isSuccessful()) {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response.errorBody().string());
                                        String str  =  jsonObject.get("message").toString()+" Username or password invalid";
                                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),str,Snackbar.LENGTH_SHORT);
                                        snackbar.getView().setBackgroundColor(Color.GRAY);
                                        snackbar.show();

                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else {

                                LoginUser loginUser = response.body();
                                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(loginUser);
                                prefsEditor.putString("User", json);
                                prefsEditor.commit();
                                Intent intent = new Intent(LoginActivity.this,TestsActivity.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(Call<LoginUser> call, Throwable t) {

                        }
                    });

                }
            });
        }
        else {
            Intent intent = new Intent(LoginActivity.this,TestsActivity.class);
            startActivity(intent);
        }

    }

    private void getTest(){

        try{

            retrofitProcess = ApiUtils.getTests();
            retrofitProcess.getTests().enqueue(new Callback<BaseTest>() {
                @Override
                public void onResponse(Call<BaseTest> call, Response<BaseTest> response) {

                }

                @Override
                public void onFailure(Call<BaseTest> call, Throwable t) {

                }
            });


        }catch (Exception $e){

        }

    }


}
