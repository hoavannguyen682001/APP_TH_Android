package com.nguyenvanhoa.app_th_android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.nguyenvanhoa.app_th_android.Fragment.Fragment_dangki;
import com.nguyenvanhoa.app_th_android.Fragment.Fragment_giaovien;
import com.nguyenvanhoa.app_th_android.Fragment.Fragment_sinhvien;
import com.nguyenvanhoa.app_th_android.R;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dr_layout;
    private Toolbar tool_bar;
    private NavigationView nv_view;

    private static int frag_sinhvien=0;
    private static int frag_giaovien=1;
    private static int frag_dangki=2;


    private int current_frag = frag_sinhvien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin);

        Anhxa();
        // setSupportActionBar(tool_bar);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, dr_layout,tool_bar,R.string.mo,R.string.dong);
        dr_layout.addDrawerListener(toggle);
        toggle.syncState();

        nv_view.setNavigationItemSelectedListener(this);

        replace_fragment(new Fragment_giaovien());
        nv_view.getMenu().findItem(R.id.nav_qlgv).setChecked(true);
    }

    private void Anhxa() {
        dr_layout=findViewById(R.id.drawer_layout);
        tool_bar=findViewById(R.id.toobar);
        nv_view=findViewById(R.id.navigation_view);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if(id== R.id.nav_qlsv){
            if(current_frag!= frag_sinhvien){
                replace_fragment(new Fragment_sinhvien());
                current_frag=frag_sinhvien;
            }
        }else if( id==R.id.nav_qlgv){
            if(current_frag!= frag_giaovien){
                replace_fragment(new Fragment_giaovien());
                current_frag=frag_giaovien;
            }
        }
        else if( id==R.id.nav_dangki){
            if(current_frag!= frag_dangki){
                replace_fragment(new Fragment_dangki());
                current_frag=frag_dangki;
            }
        }else if( id==R.id.nav_exit){

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

    private void replace_fragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}