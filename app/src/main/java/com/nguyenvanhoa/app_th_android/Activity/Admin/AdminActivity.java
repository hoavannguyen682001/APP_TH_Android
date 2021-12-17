package com.nguyenvanhoa.app_th_android.Activity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;
import com.nguyenvanhoa.app_th_android.R;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dr_layout;
    private Toolbar tool_bar;
    private NavigationView nv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin);

        Anhxa();
        // setSupportActionBar(tool_bar);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_qlgv:
                startActivity(new Intent(getApplicationContext(), QLTKGiaoVien_Activity.class));
                break;
            case R.id.nav_qlsv:
                startActivity(new Intent(getApplicationContext(), QLTKSinhVien_Activity.class));
                break;
            case R.id.nav_dangki:
                startActivity(new Intent(getApplicationContext(), DangKiTaiKhoan_Activity.class));
                break;
            default:
                break;
        }
        dr_layout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(dr_layout.isDrawerOpen(GravityCompat.START)){
            dr_layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}