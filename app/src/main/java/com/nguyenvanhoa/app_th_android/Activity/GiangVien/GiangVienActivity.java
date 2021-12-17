package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.nguyenvanhoa.app_th_android.Adapter.Giaovien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;

public class GiangVienActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout dr_layout;
    private Toolbar tool_bar;
    private NavigationView nv_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_giang_vien);

        Anhxa();

        nv_view.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, dr_layout,tool_bar,R.string.mo,R.string.dong);
        dr_layout.addDrawerListener(toggle);
        toggle.syncState();

        nv_view.setNavigationItemSelectedListener(this);
    }

    private void Anhxa() {
        dr_layout=findViewById(R.id.drawer_layout);
        tool_bar=findViewById(R.id.toobar);
        nv_view=findViewById(R.id.navigation_view);
    }

    @Override
    public void onBackPressed() {
        if(dr_layout.isDrawerOpen(GravityCompat.START)){
            dr_layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.dsda_duyet:

                break;
            case R.id.dschua_duyet:

                break;
            case R.id.ttcanhan:
                startActivity(new Intent(getApplicationContext(), Profile_GiangVien_Activity.class));
                break;
            default:
                break;
        }
        dr_layout.closeDrawer(GravityCompat.START);

        return true;
    }
}