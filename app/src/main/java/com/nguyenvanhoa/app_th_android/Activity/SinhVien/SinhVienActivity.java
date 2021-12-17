package com.nguyenvanhoa.app_th_android.Activity.SinhVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.nguyenvanhoa.app_th_android.Adapter.ThongBao_Adapter;
import com.nguyenvanhoa.app_th_android.Model.ThongBao;
import com.nguyenvanhoa.app_th_android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SinhVienActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dr_layout;
    private Toolbar tool_bar;
    private NavigationView nv_view;
    private TextView tvDate,tvTitle;
    private ListView lvTB;
    private ArrayList<ThongBao> arrayList;
    private ThongBao_Adapter adapter;

    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sinh_vien);

        Anhxa();

        nv_view.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, dr_layout,tool_bar,R.string.mo,R.string.dong);
        dr_layout.addDrawerListener(toggle);
        toggle.syncState();
        nv_view.setNavigationItemSelectedListener(this);

        //simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        adapter = new ThongBao_Adapter(this, R.layout.row_listview_thongbao,setArrayList());
        lvTB.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if(dr_layout.isDrawerOpen(GravityCompat.START)){
            dr_layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void Anhxa() {
        dr_layout= findViewById(R.id.drawer_layout);
        tool_bar= findViewById(R.id.toobar);
        nv_view= findViewById(R.id.navigation_view);
        tvDate = findViewById(R.id.tvDate);
        tvTitle = findViewById(R.id.tvTitle);
        lvTB = findViewById(R.id.lvTB);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_ttdt:
                startActivity(new Intent(getApplicationContext(), ThongTinDeTai_Activity.class));
                break;
            case R.id.nav_dkdt:
                startActivity(new Intent(getApplicationContext(), DangKyDeTai_Activity.class));
                break;
            case R.id.nav_ttcn:
                startActivity(new Intent(getApplicationContext(), Profile_SinhVien_Activity.class));
                break;
            default:
                break;
        }
        dr_layout.closeDrawer(GravityCompat.START);

        return true;
    }

    private ArrayList setArrayList(){
        arrayList = new ArrayList<>();

        arrayList.add(new ThongBao("12/11 2021","Thông báo bảo vệ đề tài NCKH"));
        arrayList.add(new ThongBao("14/12 2021","Thông báo nghiệm thu đề tài NCKH"));
        arrayList.add(new ThongBao("17/12 2021","Danh sách đề tài được nghiệm thu"));
        arrayList.add(new ThongBao("17/12 2021","Thông báo số"));
        arrayList.add(new ThongBao("20/12 2021","Thông báo 5"));
        arrayList.add(new ThongBao("22/12 2021","Thông báo 6"));
        arrayList.add(new ThongBao("24/12 2021","Danh sách đề tài năm 2021"));
        return arrayList;
    }
}