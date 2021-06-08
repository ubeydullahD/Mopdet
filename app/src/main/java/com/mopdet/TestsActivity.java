package com.mopdet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.mopdet.Core.ApiUtils;
import com.mopdet.Core.RetrofitProcess;
import com.mopdet.Fragments.TestResultHistoryFragment;
import com.mopdet.Fragments.TestsFragment;
import com.mopdet.Model.PojoModels.BaseTest.BaseTest;
import com.mopdet.Model.PojoModels.LoginUser.LoginUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    SharedPreferences mPrefs;
    private RetrofitProcess retrofitProcess;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
        //  toolbar = findViewById(R.id.toolbar);


        mPrefs = getSharedPreferences("Mobdet", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        LoginUser loginUser = gson.fromJson(json, LoginUser.class);

        // toolbar.setTitle("Mobdet");
        // setSupportActionBar(toolbar);



        //Todo Buraya ilk fragment yazılacak


        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                0,0);

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView11);
        navUsername.setText(loginUser.getUser().getName()+" "+loginUser.getUser().getSurname());


       /* mPrefs = getSharedPreferences("Mobdet" ,  Context.MODE_PRIVATE);
        mPrefs.edit().remove("User").commit();*/


        navigationView.setNavigationItemSelectedListener(this);



        String jsonKontrol = mPrefs.getString("Tests", "");
        if(jsonKontrol=="" || jsonKontrol.contains("[]") || jsonKontrol.contains("null")){
            getTest();
        }else {

            fragment = new TestsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
        }




    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            finishAffinity();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.last_test){
            fragment = new TestResultHistoryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }


        return false;
    }

    private void getTest(){

        try{

            retrofitProcess = ApiUtils.getTests();
            retrofitProcess.getTests().enqueue(new Callback<BaseTest>() {
                @Override
                public void onResponse(Call<BaseTest> call, Response<BaseTest> response) {
                    BaseTest beseText = response.body();
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(beseText);
                    prefsEditor.putString("Tests", json);
                    prefsEditor.commit();
                    fragment = new TestsFragment();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                }

                @Override
                public void onFailure(Call<BaseTest> call, Throwable t) {
                    int b = 0;
                }
            });


        }catch (Exception $e){

        }

    }
}
