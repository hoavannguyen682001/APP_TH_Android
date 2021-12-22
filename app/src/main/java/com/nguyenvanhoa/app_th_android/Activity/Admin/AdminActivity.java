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
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityAdminBinding;
import com.nguyenvanhoa.app_th_android.databinding.ActivityGiangVienBinding;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityAdminBinding binding;
    private FirebaseAuth firebaseAuth;
    private TextView tvName, tvEmail;
    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, binding.drawerLayout,binding.toobar,R.string.mo,R.string.dong);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);

        //header navigation crawler
        header =  binding.navigationView.getHeaderView(0);
        tvName = header.findViewById(R.id.tvName);
        tvEmail = header.findViewById(R.id.tvEmail);

        loadAdminInfo();
    }

    private void loadAdminInfo() {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(firebaseAuth.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String email = "" + snapshot.child("email").getValue();
                            String name = "" + snapshot.child("name").getValue();

                            tvEmail.setText(email);
                            tvName.setText(name);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
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
        binding.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}