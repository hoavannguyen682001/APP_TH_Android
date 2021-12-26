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
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.Activity.LoginActivity;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.SinhVienActivity;
import com.nguyenvanhoa.app_th_android.Adapter.ThongBao_Adapter;
import com.nguyenvanhoa.app_th_android.Model.ThongBao;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityGiangVienBinding;

import java.util.ArrayList;
import java.util.List;

public class GiangVienActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActivityGiangVienBinding binding;
    private FirebaseAuth firebaseAuth;
    private TextView tvName, tvEmail;
    private View header;
    private ImageView ivPerson;
    private ArrayList<ThongBao> arrayList;
    private ThongBao_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityGiangVienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

//        binding.navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, binding.drawerLayout,binding.toobar,R.string.mo,R.string.dong);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);
        //header navigation crawler
        header =  binding.navigationView.getHeaderView(0);
        tvName = header.findViewById(R.id.tvName);
        tvEmail = header.findViewById(R.id.tvEmail);
        ivPerson = header.findViewById(R.id.ivPersonGV);

        adapter = new ThongBao_Adapter(this, R.layout.row_listview_thongbao,setArrayList());
        binding.lvTB.setAdapter(adapter);
        loadUserInfo();
    }

    private ArrayList<ThongBao> setArrayList() {
        arrayList = new ArrayList<>();

        arrayList.add(new ThongBao("12/11 2021","Thông báo bảo vệ đề tài NCKH"));
        arrayList.add(new ThongBao("14/12 2021","Thông báo nghiệm thu đề tài NCKH"));
        arrayList.add(new ThongBao("17/12 2021","Danh sách đề tài được nghiệm thu"));
        arrayList.add(new ThongBao("17/12 2021","Thông báo số 4"));
        arrayList.add(new ThongBao("20/12 2021","Thông báo số 5"));
        arrayList.add(new ThongBao("22/12 2021","Thông báo số 6"));
        arrayList.add(new ThongBao("24/12 2021","Danh sách đề tài năm 2021"));
        return arrayList;
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.dsda_duyet:
                startActivity(new Intent(getApplicationContext(), DSDT_DaDK_Activity.class));
                break;
            case R.id.dschua_duyet:
                startActivity(new Intent(getApplicationContext(), DSDT_ChuaDuyet_Activity.class));
                break;
            case R.id.ttcanhan:
                startActivity(new Intent(getApplicationContext(), Profile_GiangVien_Activity.class));
                break;
            case R.id.nav_exit:
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void loadUserInfo(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String email = "" + snapshot.child("email").getValue();
                        String name = "" + snapshot.child("name").getValue();
                        String profileImage = "" + snapshot.child("profileImage").getValue();
                        tvEmail.setText(email);
                        tvName.setText(name);

                        Glide.with(GiangVienActivity.this)
                                .load(profileImage)
                                .placeholder(R.drawable.ic_person)
                                .into(ivPerson);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}