package com.example.liufengwzry;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.liufengwzry.base.BaseActivity;
import com.example.liufengwzry.databinding.ActivityMainBinding;
import com.example.liufengwzry.entitiy.News;
import com.example.liufengwzry.network.Client;
import com.example.liufengwzry.util.Util;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity<ViewModel, ActivityMainBinding> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void Go() {
        setSupportActionBar(toolbar);
        Drawer();
    }
    Callback<ResponseBody> callback=new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Util.e(response);
            try{
                Util.e(new String(response.body().bytes()));
            }catch (Exception e){
                Util.e("cc:"+e);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Util.e(t.toString());
        }
    };
    //region 菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //endregion
    //region 导航配置
    private void Drawer(){
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //endregion
}