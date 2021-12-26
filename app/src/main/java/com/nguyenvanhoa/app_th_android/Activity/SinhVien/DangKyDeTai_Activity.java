package com.nguyenvanhoa.app_th_android.Activity.SinhVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityDangKyDeTaiBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DangKyDeTai_Activity extends AppCompatActivity {
    private ActivityDangKyDeTaiBinding binding;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDangKyDeTaiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

//        loadTTDeTai();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();

        binding.btnDangKyDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check){
                    Toast.makeText(DangKyDeTai_Activity.this, "Bạn đã đăng ký đề tài NCKH, không thể đăng ký thêm...", Toast.LENGTH_SHORT).show();
                }else{
                    validateData();
                }
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), SinhVienActivity.class));
    }

    String tenDT="", linhvuc="", tenCNDT="", tenTV="", tenGV="", tgThucHien="", tgDK="";
    String chiPhi="";
    private void validateData() {
        tenDT = binding.edtTenDT.getText().toString();
        linhvuc = binding.edtLVDT.getText().toString();
        tenCNDT = binding.edtTenCNDT.getText().toString();
        tenTV = binding.edtSVTG.getText().toString();
        tenGV = binding.edtGV.getText().toString();
        tgThucHien = binding.edtThoiGian.getText().toString();
        tgDK = dateFormat.format(calendar.getTimeInMillis());
        chiPhi = binding.edtChiPhi.getText().toString();

        if(TextUtils.isEmpty(tenDT) || TextUtils.isEmpty(linhvuc) ||TextUtils.isEmpty(tenCNDT) ||TextUtils.isEmpty(tenTV) ||
                TextUtils.isEmpty(tenGV) ||TextUtils.isEmpty(tgThucHien) ||TextUtils.isEmpty(binding.edtChiPhi.getText().toString())){
            Toast.makeText(DangKyDeTai_Activity.this, "Hãy nhập đầy đủ thông tin...", Toast.LENGTH_SHORT).show();
        }else{
            uploadDeTai();
        }
    }

    private void uploadDeTai() {
        progressDialog.show();

        Long timestamp = System.currentTimeMillis();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", timestamp);
        hashMap.put("uid", firebaseAuth.getUid());
        hashMap.put("tenDT", tenDT);
        hashMap.put("linhvuc", linhvuc);
        hashMap.put("tenCNDT",tenCNDT);
        hashMap.put("tenTV", tenTV);
        hashMap.put("tenGV", tenGV);
        hashMap.put("tgThucHien", tgThucHien);
        hashMap.put("tgDK", tgDK);
        hashMap.put("chiPhi", chiPhi);
        hashMap.put("trangThaiDT", "Chờ duyệt");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DanhSachDeTai");
        ref.child(""+firebaseAuth.getUid())
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(DangKyDeTai_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        check = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(DangKyDeTai_Activity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                });

    }
//    private void loadTTDeTai() {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DanhSachDeTai");
//        ref.child(firebaseAuth.getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String id = "" + snapshot.child("id").getValue();
//                        if(id != null){
//                            check = true;
//                        }
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }

}